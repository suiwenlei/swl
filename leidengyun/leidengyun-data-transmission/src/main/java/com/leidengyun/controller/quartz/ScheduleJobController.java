package com.leidengyun.controller.quartz;

import com.leidengyun.common.ConfigServlet;
import com.leidengyun.model.ScheduleJob;
import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author Joe
 */
@Api(tags = "定时任务")
@Controller
@RequestMapping("/admin/job")
public class ScheduleJobController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);
	
	@Resource
	private ScheduleJobService scheduleJobService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/quarzt/job";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		ScheduleJob job;
		if (id == null) {
			job = new ScheduleJob();
		}
		else {
			job = scheduleJobService.get(id);
		}
		model.addAttribute("job", job);
		return "/quarzt/jobEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "名称 ") String jobName,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(scheduleJobService.findPaginationByName(jobName, new Pagination<ScheduleJob>(pageNo, pageSize)));
	}

	

	@ApiOperation("启动/停止")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否停止", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		
		List<ScheduleJob> list = scheduleJobService.findListById(getAjaxIds(ids));
		//修改数据库状态的同时修改定时任务状态
		try {
			for (ScheduleJob scheduleJob : list) {
				if(scheduleJob!=null && scheduleJob.getId()!=null && scheduleJob.getIsEnable()!=null){
					scheduleJob = scheduleJobService.get(scheduleJob.getId());
					if(isEnable.equals(ConfigServlet.STATUS_RUNNING)){
						logger.info("--------开始启动定时任务------");
						scheduleJob.setIsEnable(ConfigServlet.STATUS_RUNNING);
						scheduleJobService.addJob(scheduleJob);
					}else{
						logger.info("--------开始停止定时任务------");
						scheduleJob.setIsEnable(ConfigServlet.STATUS_NOT_RUNNING);
						scheduleJobService.deleteJob(scheduleJob);
					}
					scheduleJobService.save(scheduleJob);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("定时任务状态修改失败："+e.getMessage());
		}
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "任务名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String jobName,
			@ApiParam(value = "任务分组", required = true) @ValidateParam({ Validator.NOT_BLANK }) String jobGroup,
			@ApiParam(value = "cron表达式", required = true) @ValidateParam({ Validator.NOT_BLANK }) String cronExpression,
			@ApiParam(value = "任务执行时调用的方法", required = true) @ValidateParam({ Validator.NOT_BLANK }) String beanClass,
			@ApiParam(value = "任务调用的方法名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String methodName,
			@ApiParam(value = "是否启动", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK, Validator.INT }) Integer sort) throws SchedulerException {
		ScheduleJob job;
		if (id == null) {
			job = new ScheduleJob();
			job.setCreateTime(new Date());
		}
		else {
			job = scheduleJobService.get(id);
			  //新增默认是暂停状态，修改的时候要判断cron表达式是否改变，如改变则修改对应的定时任务
			if(!job.getCronExpression().equals(job.getCronExpression())){
				scheduleJobService.updateCron(job.getId(), job.getCronExpression());
			}
			
		}
		job.setJobName(jobName);
		job.setJobGroup(jobGroup);
		job.setCronExpression(cronExpression);
		job.setBeanClass(beanClass);
		job.setMethodName(methodName);
		job.setIsEnable(isEnable);
		job.setSort(sort);
		scheduleJobService.save(job);
		
		return Result.createSuccessResult();
	}

	/**
	 * 
	 * @todo 启动/暂停定时任务 前台页面传过来的status是当前状态，要修改的状态为相反的
	 * @author huiyunfei
	 * @date 2016-9-19 下午5:39:08
	 * @return
	 */
	@RequestMapping(value = "/startOrShutDown")
	public @ResponseBody Result save(@ApiParam(value = "id") Integer id,ScheduleJob scheduleJob, RedirectAttributes redirectAttributes) {
		//修改数据库状态的同时修改定时任务状态
		try {
			if(scheduleJob!=null && id!=null && scheduleJob.getIsEnable()!=null){
				scheduleJob = scheduleJobService.get(id);
				if(scheduleJob.getIsEnable().equals(ConfigServlet.STATUS_NOT_RUNNING)){
					logger.info("--------开始启动定时任务------");
					scheduleJob.setIsEnable(ConfigServlet.STATUS_RUNNING);
					scheduleJobService.addJob(scheduleJob);
				}else{
					logger.info("--------开始停止定时任务------");
					scheduleJob.setIsEnable(ConfigServlet.STATUS_NOT_RUNNING);
					scheduleJobService.deleteJob(scheduleJob);
				}
				scheduleJobService.save(scheduleJob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.createSuccessResult();
	}
}