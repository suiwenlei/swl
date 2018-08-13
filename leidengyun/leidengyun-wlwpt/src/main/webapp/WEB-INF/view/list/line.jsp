<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="设备数据"/>
</jsp:include>

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
									<input name="qsrq" id="qsrq" type="text" class="form-data input-medium search-data" value="${qsrq}"  onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd '})">
								</label>
								<label>
									<label class="control-label" for="form-field-1"> 至 </label>
									<input name="zzrq" id="zzrq" type="text" class="form-data input-medium search-data" value="${zzrq}" onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd '})">
								</label>
								
								<label style="margin-left: 25px;">
									<label class="control-label" for="form-field-1"> 设备编号： </label>
									<select id="_devId" name="devId">
										<c:forEach var="item" items="${devList}">
											<option value="${item.name}">${item.name}</option>
										</c:forEach>
									</select>
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
				<!-- 折线图 -->
				<div class="widget-box" style="display: none;" id="linDev">
					

					<div class="widget-body">
						<div class="widget-main" style="padding: 0px;overflow: scroll;">
							<iframe name="Map" src="" id="_map"
							height="700px" width="100%" scrolling="no" frameborder="0" ></iframe>
						</div>
					</div>
				</div>

				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
<!-- page specific plugin scripts -->
<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		
// 		jQuery(function($) {
// 			main();
// 		});
		$("#_search").click(function(){
			$("#linDev").show();
			main();
		});
		
		function main(){
			var qsrq=$("#qsrq").val();
			var zzrq=$("#zzrq").val();
			var devId=$("#_devId").val();
			document.getElementById("_map").src="${_path}/sjpt/line/showLine?qsrq="+qsrq+"&zzrq="+zzrq+
					"&devId="+devId;
		}
	});
</script>
