package com.leidengyun.wms.controller.admin;

import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.wms.common.ConfigServlet;
import com.leidengyun.wms.model.Dev;
import com.leidengyun.wms.model.Permission;
import com.leidengyun.wms.model.UserDev;
import com.leidengyun.wms.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe
 */
@Api(tags = "管理员设备分配管理")
@Controller
@RequestMapping("/admin/userDev")
public class UserDevController extends BaseController {

	
	@Resource
	private UserDevService userDevService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private DevService devService;
	@Resource
	private UserService userService;
	@Resource
	private AppService appService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserAppService userAppService;
	
	@Resource
	private RolePermissionService rolePermissionService;
	
	@ApiOperation("初始页")
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String edit(
//			@ApiParam(value = "用户id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId,
			Model model) {
		model.addAttribute("userId", 00000);
		model.addAttribute("devList", getDevList(00000));
		return "/dev/userDev";
	}
	
	

	@ApiOperation("管理员应用关联提交")
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Result allocateSave(
//			@ApiParam(value = "用户id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId,
			@ApiParam(value = "设备ids") String devIds) {
		
		Integer userId=00000;
		List<Integer> idList = getAjaxIds(devIds);
		List<UserDev> dlist = new ArrayList<UserDev>();
		UserDev bean = null;
		for (Integer devId : idList) {
			bean = new UserDev();
			bean.setDevId(devId);
			bean.setUserId(userId);
			dlist.add(bean);
		}
		userDevService.allocate(userId, idList, dlist);
		
		//此处需要自动配置菜单
		//
		List<Integer> appList = new ArrayList<>(); 
		appList.add(81);
		
		//2.将新的菜单配置项插入权限表中
		Permission permission;
		Permission permission_ls;
		if(null!=appList && devIds !=""){
			for (Integer appId : appList) {
				permission = new Permission();
				permission.setId(null);
				permission.setParentId(null);
				permission.setAppId(appId);
				permission.setIcon(ConfigServlet.MEUN_ICON);
				permission.setName(ConfigServlet.MEUN_NAME);
				permission.setUrl(ConfigServlet.MEUN_URL);
				permission.setSort(1);
				permission.setIsMenu(true);
				permission.setIsEnable(true);


				permission_ls = new Permission();
				permission_ls.setId(null);
				permission_ls.setParentId(null);
				permission_ls.setAppId(appId);
				permission_ls.setIcon(ConfigServlet.MEUN_ICON_ls);
				permission_ls.setName(ConfigServlet.MEUN_NAME_ls);
				permission_ls.setUrl(ConfigServlet.MEUN_URL_ls);
				permission_ls.setSort(2);
				permission_ls.setIsMenu(true);
				permission_ls.setIsEnable(true);

				
				List<Permission> list1 = permissionService.findByName(ConfigServlet.MEUN_NAME, appId, true);
				if(list1 !=null && list1.size()>0){
					for (Permission p : list1) {
						permissionService.update(p);
					}
				}else{
					permissionService.save(permission);
				}

				/*历史数据*/
				List<Permission> list1_ls = permissionService.findByName(ConfigServlet.MEUN_NAME_ls, appId, true);
				if(list1_ls !=null && list1_ls.size()>0){
					for (Permission p : list1_ls) {
						permissionService.update(p);
					}
				}else {
					permissionService.save(permission_ls);
				}

				
				List<Permission> plist = permissionService.
				findByName(permission.getName(), permission.getAppId(), true);
				if(idList !=null && idList.get(0) !=null){
					int index=1;
					for (Integer devId : idList) {
						permission = new Permission();
						permission.setId(null);
						permission.setParentId(plist.get(0).getId());
						permission.setAppId(appId);
						permission.setIcon(ConfigServlet.MEUN_ICON);
						permission.setName(devService.get(devId).getDevId());
						permission.setUrl(ConfigServlet.MEUN_SUB_URL+devService.get(devId).getDevId());
						permission.setIsMenu(true);
						permission.setIsEnable(true);
						permission.setSort(index);
						
						if(null !=devId){
							List<Permission> list = permissionService.findByParentId(plist.get(0).getId(),appId);
							int dex=1;
							if(list != null && list.size()>0){
								for (Permission p : list) {
									p.setSort(dex);
									permissionService.update(p);
									dex++;
								}
							}else{
								permissionService.save(permission);
							}
						}
						index++;
					}
				}


				List<Permission> plist_ls = permissionService.
						findByName(permission_ls.getName(), permission_ls.getAppId(), true);
				
				
				if(idList !=null && idList.get(0) !=null){
					int index=1;
					for (Integer devId : idList) {
						permission_ls = new Permission();
						permission_ls.setId(null);
						permission_ls.setParentId(plist_ls.get(0).getId());
						permission_ls.setAppId(appId);
						permission_ls.setIcon(ConfigServlet.MEUN_ICON_ls);
						permission_ls.setName(devService.get(devId).getDevId());
						permission_ls.setUrl(ConfigServlet.MEUN_SUB_URL_ls+devService.get(devId).getDevId());
						permission_ls.setIsMenu(true);
						permission_ls.setIsEnable(true);
						permission_ls.setSort(index);
						if(null !=devId){
							List<Permission> list = permissionService.findByParentId(plist_ls.get(0).getId(),appId);
							int dex=1;
							if(list != null && list.size()>0){
								for (Permission p : list) {
									p.setSort(dex);
									permissionService.update(p);
									dex++;
								}
							}else{
								permissionService.save(permission_ls);
							}
						}
						index++;
					}
				}
			}
			return Result.createSuccessResult().setMessage("授权成功");
		}
		return Result.createSuccessResult().setMessage("请勾选要分配的设备编号!");
	}

	private List<Dev> getDevList(Integer userId) {
		List<Dev> list = devService.findByAll(null);
		
		for (Dev dev : list) {
			UserDev userDev = userDevService.findByUserDevId(userId, dev.getId()+"");
			if(userDev !=null){
				dev.setIsChecked(true);
			}else{
				dev.setIsChecked(false);
			}
		}
		return list;
	}
}