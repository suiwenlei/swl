<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="设备数据"/>
</jsp:include>

<title>设备列表</title>
<style type="text/css">
<!--
	.text-center {
	text-align: center;
}
-->
</style>

<link type="text/css" rel="styleSheet"  href="${_path}/map/css/loading.css" />
<script type="text/javascript" src="${_path}/map/js/loading.js"></script>
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="tabbable">
			<!-- #section:pages/faq -->
			<ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
				<li class="active">
					<a data-toggle="tab" href="#faq-tab-1">
						<i class="blue ace-icon fa fa-film bigger-120"></i>
						设备数据
					</a>
				</li>

				<li>
					<a data-toggle="tab" href="#faq-tab-2">
						<i class="green ace-icon fa fa-bar-chart-o bigger-120"></i>
						折线图
					</a>
				</li>				
			</ul>

			<!-- /section:pages/faq -->
			<div class="tab-content no-border padding-24">
				<div id="faq-tab-1" class="tab-pane fade in active">
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
														<input name="qsrq" type="text" class="form-data input-medium search-data" value=""  onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yy-MM-dd HH:mm:ss'})">
													</label>
													<label>
														<label class="control-label" for="form-field-1"> 至 </label>
														<input name="zzrq" type="text" class="form-data input-medium search-data" value="" onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yy-MM-dd  HH:mm:ss'})">
													</label>
													<label style="margin-left: 25px;">
													<button id="_search" type="button" class="btn btn-info btn-sm">
														<i class="ace-icon fa fa-search bigger-110"></i>查询
													</button>
													</label>
												</form>
											</div>
										</div>
										
									</div>
									
									
									<div>
										<div class="dataTables_wrapper form-inline no-footer">
											<div id="_table" style="overflow: auto;" class="table table-striped table-bordered table-hover dataTable no-footer">
												<table></table>
											</div>
										</div>
										<div id="over" class="overLoading"></div>
										<div id="layout" class="layoutLoading" style="display: none;"><img src="${_path}/map/img/5-121204193Q9-50.gif" /></div>
					
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
										{text : '实时更新', clazz : 'btn-default', icon : 'fa fa-cog blue', permission : '', handler : function(){
											
											timeTicket = window.setInterval(query,6000); //一分钟刷新一次
											
										}},
										{text : '关闭更新', clazz : 'btn-default', icon : 'fa fa-ban blue', permission : '', handler : function(){
											// 关闭更新操作
											//去掉定时器的方法 
											window.clearInterval(timeTicket); 
											window.location.reload();
										}}
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
								
					            
								// 查询
								$("#_search").click(function(){
									query();
								});
								
								function query(){
									showLoading(true);
									$table.search();
									setTimeout(function(){return showLoading(false);},"1000");	
								}
							});
						});
					</script>
				
				</div>

				<div id="faq-tab-2" class="tab-pane fade">
					<div class="row">
						<div class="col-xs-12">
							<div class="row" style="margin-left: -29px;margin-right: -22px;margin-top: -7px;">
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
														<input name="qsrq" id="qsrq1" type="text" class="form-data input-medium search-data1" value="${qsrq}"  onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yy-MM-dd HH:mm:ss '})">
													</label>
													<label>
														<label class="control-label" for="form-field-1"> 至 </label>
														<input name="zzrq" id="zzrq1" type="text" class="form-data input-medium search-data1" value="${zzrq}" onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yy-MM-dd HH:mm:ss '})">
													</label>
													
<!-- 													<label style="margin-left: 25px;"> -->
<!-- 														<label class="control-label" for="form-field-1"> 设备编号： </label> -->
<!-- 														<select id="_devId" name="devId"> -->
<%-- 															<c:forEach var="item" items="${devList}"> --%>
<%-- 																<option value="${item.name}">${item.name}</option> --%>
<%-- 															</c:forEach> --%>
<!-- 														</select> -->
<!-- 													</label> -->
													<label style="margin-left: 25px;">
													<button id="_search1" type="button" class="btn btn-info btn-sm">
														<i class="ace-icon fa fa-search bigger-110"></i>查询
													</button>
													</label>
												</form>
											</div>
										</div>
									</div>
									<!-- 折线图 -->
									<div class="widget-box" style="display: none;" id="lineDiv">
										
										<div id="over" class="overLoading"></div>
										<div id="layout" class="layoutLoading"><img src="${_path}/map/img/5-121204193Q9-50.gif" /></div>
										
										<div class="widget-body">
											<div class="widget-main" style="padding: 0px;">
												<iframe name="Map" src="" id="_map"
												height="700px" width="100%" scrolling="no" frameborder="0" ></iframe>
											</div>
										</div>
									</div>
					
<!-- 									<div> -->
<!-- 										<div class="dataTables_wrapper form-inline no-footer"> -->
<!-- 											<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer"></table> -->
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>
						</div>
					</div>
					<!-- page specific plugin scripts -->
					<script type="text/javascript">
						$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
							
// 							jQuery(function($) {
// 								main();
// 							});
							$("#_search1").click(function(){
								$("#lineDiv").show();
								main();
							});
							
							
							function main(){
								
								var qsrq=$("#qsrq1").val();
								var zzrq=$("#zzrq1").val();
								var devId="${devId}";
								document.getElementById("_map").src="${_path}/sjpt/line/showLine?qsrq="+qsrq+"&zzrq="+zzrq+
										"&devId="+devId;
							}
						});
					</script>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div>
		</div>
	</div><!-- /.col -->
</div><!-- /.row -->
