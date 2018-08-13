<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="设备数据"/>
</jsp:include>

<link type="text/css" rel="styleSheet"  href="${_path}/map/css/loading.css" />
<script type="text/javascript" src="${_path}/map/js/loading.js"></script>
<div id="over" class="overLoading"></div>
<div id="layout" class="layoutLoading"><img src="${_path}/map/img/5-121204193Q9-50.gif" /></div>

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
									<label class="control-label" for="form-field-1"> 阶段采集日期： </label>
									<input name="qsrq" type="text" class="form-data input-medium search-data" value="${qsrq}"  onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd '})">
								</label>
								<label>
									<label class="control-label" for="form-field-1"> 至 </label>
									<input name="zzrq" type="text" class="form-data input-medium search-data" value="${zzrq}" onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd '})">
								</label>
								<label style="margin-left: 25px;">
								<button id="_search" type="button" class="btn btn-info btn-sm">
									<i class="ace-icon fa fa-search bigger-110"></i>查询1111
								</button>
								</label>
							</form>
						</div>
					</div>
					
				</div>

				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<div style="overflow: auto;">
							<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- page specific plugin scripts -->

<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		
		jQuery(function($) {
			// 列表
			//开启实时更新
			var timeTicket;
			var gson='${gsonTitle}';
			var gsonTitle=eval("(" +gson+ ")");
    		var $table = $("#_table").table({
    			url : "${_path}/sjpt/data/list?devId=${devId}",
    			formId : "_form",
				tools : [
										   
					{text : '批量删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/sjpt/data/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "确认要删除?",
							url : "${_path}/sjpt/data/delete"
						});
					}},
// 					{text : '实时更新', clazz : 'btn-default', icon : 'fa fa-cog grey', permission : '', handler : function(){
						
// 					        clearInterval(timeTicket);
// 					        timeTicket = setInterval(function () {
// 					            // 获取实时更新数据
// 					            alert(1);
// 					        	$table.search();
		
// 					      	}, 6000);
						
// 					}},
// 					{text : '关闭更新', clazz : 'btn-default', icon : 'fa fa-cog grey', permission : '', handler : function(){
// 						// 关闭更新操作
// 				      	clearInterval(timeTicket);			
// 					}},
// 					{text : 'EXCEL导出', clazz : 'btn-default', icon : 'fa fa-cog grey', permission : '/admin/userDev/allocate', handler : function(){
// 						if(!$table.validateSelected(true)){
// 							return;						
// 						}
// 						$.aceRedirect("${_path}/admin/userDev/allocate?userId=" + $table.getSelectedItemKeys("id"));
// 					}}
				],
				columns : gsonTitle,
				after : function(){
					// 权限处理
					$.permission();
				}
			});
			
			//搜索
			$(".search-data").keyup(function () { 
				$table.search();
			});
			$("#_search").change(function () { 
				$table.search();
           	});
            
			// 查询
			$("#_search").click(function(){
				$table.search();
			});
		});
	});
</script>
