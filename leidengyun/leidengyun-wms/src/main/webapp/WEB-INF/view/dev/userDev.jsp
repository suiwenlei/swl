<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="管理员"/>
</jsp:include>

<div class="page-header">
	<h1>
		分配设备
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="_editForm" class="form-horizontal" role="form" 
			validate="true">
			<input type="hidden" name="userId" value="${userId}">
			<input id="_devIds" type="hidden" name="devIds" value="">
			
<!-- 			<div class="form-group"> -->
<!-- 				<label for="_appId" class="col-sm-3 control-label no-padding-right">用户</label> -->

<!-- 				<div class="col-sm-3"> -->
<!-- 					<select id="_appId" name="userId" class="form-control help-validate"> -->
<%-- 						<option value="${userId}">${user.account}</option> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
			<div class="form-group">
				<div class="col-xs-12 col-sm-9">
					<div id="_devDiv" class="clearfix help-validate">
						<c:forEach var="item" items="${devList}">
							<div class='col-sm-2'>
								<label>
									<input name="devId" value="${item.id}" type="checkbox" class="ace" ${item.isChecked ? 'checked="checked"' : ''}/>
									<span class="lbl">&nbsp;&nbsp;${item.devId}</span>
								</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button id="_submit" type="button" class="btn btn-info" data-loading-text="正在提交...">
						<i class="ace-icon fa fa-check bigger-110"></i>
						提交
					</button>

					&nbsp; &nbsp; &nbsp;
					<button id="_cancle" class="btn" type="reset">
						<i class="ace-icon fa  fa-times bigger-110"></i>
						取消
					</button>
				</div>
			</div>
		</form>

	</div>
</div>


<!--[if lte IE 8]>
  <script src="${_staticPath}/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			
			// 提交
			$("#_submit").click(function(){
				if($('#_editForm').validate()){
					var devIds = "";
					$("input[name='devId']:checked").each(function(i, d){
						if(i > 0){
							devIds += ",";
						}
						devIds += $(this).val();
						
					});
					$("#_devIds").val(devIds);
					
					var btn = $(this);
					btn.button('loading');
					$.post("${_path}/admin/userDev/allocateSave", $.formJson('_editForm'),function(d) {
						if(d){
							btn.button('reset');
							if(d.code == 1){
								$.aceRedirect("${_path}/admin/user");
							}
							else {
								$.gritter.add({text: d.message});
							}
						}
			        },'json');
				}
			});
			
			// 取消
			$("#_cancle").click(function(){
				$.aceRedirect("${_path}/admin/user");
			});
		});
	});
</script>
