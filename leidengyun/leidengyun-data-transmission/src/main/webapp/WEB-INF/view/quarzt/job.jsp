<%@ page language="java" pageEncoding="utf-8"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="定时任务"/>
</jsp:include>

<div class="page-header">
	<h1>
		定时任务
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">搜索栏</h5>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<form id="_form" class="form-inline">
								<label>
									<label class="control-label" for="form-field-1"> 任务名称： </label>
									<input name="jobName" type="text" class="form-data input-medium search-data">
								</label>
								<!-- 
								<button id="_search" type="button" class="btn btn-info btn-sm">
									<i class="ace-icon fa fa-search bigger-110"></i>搜索
								</button>
								 -->
							</form>
						</div>
					</div>
				</div>

				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			// 列表
			var $table = $("#_table").table({
    			url : "${_path}/admin/job/list",
    			formId : "_form",
				tools : [
					{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/job/edit', handler : function(){
						$.aceRedirect("${_path}/admin/job/edit");
					}},
					{text : '停止', clazz : 'btn-warning', icon : 'fa fa-lock orange', permission : '/admin/job/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/job/enable"}, false);
					}},
					{text : '重新启动', clazz : 'btn-success', icon : 'fa fa-unlock green', permission : '/admin/job/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/job/enable"}, true);
					}},
					{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/job/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "确认要删除?", 
							url : "${_path}/admin/job/delete"
						});
					}}
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'isEnable', hide : true},
			        {field:'jobName', title:'任务名称'},
			        {field:'jobGroup', title:'任务分组',mobileHide : true},
			        {field:'cronExpression', title:'cron表达式',mobileHide : true},
			        {field:'beanClass', title:'任务执行方法类', mobileHide : true},
			        {field:'methodName', title:'任务调用的方法名',mobileHide : true},
			        {field:'sort', title:'排序', mobileHide : true},
			        {field:'isEnableStr', title:'运行状态', replace : function (d){
				        if(d.isEnable)
				        	return "<span class='label label-sm label-success'>" + d.isEnableStr + 	"</span>";
			        	else
			        		return "<span class='label label-sm label-warning'>" + d.isEnableStr + "</span>";
			        }},
			        {field:'createTime', title:'创建时间', mobileHide : true}
				],
				operate : [
					{text : '修改', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/job/edit', handler : function(d, i){
						$.aceRedirect("${_path}/admin/job/edit?id=" + d.id);
					}},
					{text : '停止', clazz : 'orange', icon : 'fa fa-lock', permission : '/admin/job/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/job/enable"}, false);
						},
						show : function(d){
							return d.isEnable;
						}
					},
					{text : '重新启动', clazz : 'green', icon : 'fa fa-unlock', permission : '/admin/job/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/job/enable"}, true);
						},
						show : function(d){
							return !d.isEnable;
						}
					},
					{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/job/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "确认要删除?", 
							url : "${_path}/admin/job/delete"
						});
					}}
				],
				after : function(){
					// 权限处理
					$.permission();
				}
			});
			
			/**
			// 搜索
			$("#_search").click(function () {
				$table.search();
			});
			
			// 回车绑定
			$(".form-data").bind('keypress',function(event){
			    if(event.keyCode == "13"){
			    	event.preventDefault();
			    	$table.search();
			    }
			});
			 */
			
			// 搜索
			$(".search-data").keyup(function () { 
				$table.search();
			});
		});
	});
</script>
