<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="仪器"/>
</jsp:include>

<div class="page-header">
	<h1>
		${empty instru.id ? '添加' : '修改'}仪器
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<form id="_editForm" class="form-horizontal" role="form">
			<input type="hidden" name="id" value="${instru.id}"> 
			
			<!-- 仪器编号 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>仪器编号</label>
				
				<div class="col-sm-9">
				
					<select id="_devId" name="insId" class="form-data col-xs-10 col-sm-5">
						<c:forEach var="item" items="${devIdTypeList}">
							<option value="${item.dm}" ${(item.dm eq instru.insId)?'selected="selected"':''}>${item.dm}</option>
						</c:forEach>
					</select>
				</div>
				
				
<!-- 				<div class="col-sm-9"> -->
<!-- 					<div class="clearfix help-validate"> -->
<%-- 						<input id="_name" name="insId" type="text" value="${instru.insId}" class="form-data col-xs-10 col-sm-5" placeholder="仪器编号" --%>
<!-- 							required="true" maxlength="64"/> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
			</div>
			
			<!-- 设备类型 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>设备类型</label>


				<div class="col-sm-9">
					<select id="_name" name="insType" class="form-data col-xs-10 col-sm-5"
						required="true">
						<c:forEach var="item" items="${devIdTypeList}" >
							<option value="${item.dm}" ${(item.dm eq instru.insType)?'selected="selected"':''}>${item.dm}</option>
						</c:forEach>
					</select>
				</div>
				
<!-- 				<div class="col-sm-9"> -->
<!-- 					<div class="clearfix help-validate"> -->
<%-- 						<input id="_name" name="insType" type="text" value="${instru.insType}" class="form-data col-xs-10 col-sm-5" placeholder="设备类型" --%>
<!-- 							required="true"  maxlength="64"/> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
			</div>
			
			<!-- 仪器名称 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>仪器名称</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="insName" type="text" value="${instru.insName}" class="form-data col-xs-10 col-sm-5" placeholder="仪器名称"
							required="true"  maxlength="128"/>
					</div>
				</div>
				
			</div>
			
			<!-- 设备代码 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>设备代码</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="insDm" type="text" value="${instru.insDm}" class="form-data col-xs-10 col-sm-5" placeholder="设备代码"
							required="true" maxlength="128"/>
					</div>
				</div>
				
			</div>
			<!-- 仪器描述 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>仪器描述</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="insDESC" type="text" value="${instru.insDESC}" class="form-data col-xs-10 col-sm-5" placeholder="仪器描述"
							required="true"  maxlength="128"/>
					</div>
				</div>
				
			</div>
			
			<!-- 单位-->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>单位</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="unit" type="text" value="${instru.unit}" class="form-data col-xs-10 col-sm-5" placeholder="单位"
							required="true"  maxlength="128"/>
					</div>
				</div>
				
			</div>
			
			<!-- 数量级-->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>数量级</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="magNitude" type="text" value="${instru.magNitude}" class="form-data col-xs-10 col-sm-5" placeholder="数量级"
							required="true"  maxlength="128"/>
					</div>
				</div>
				
			</div>
			
			
			
			<!-- 仪器所挂参数个数 -->
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>仪器所挂参数个数</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="pramNum" type="text" value="${instru.pramNum}" class="form-data col-xs-10 col-sm-5" placeholder="仪器所挂参数个数"
							required="true" maxlength="128"/>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="_sort" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>排序</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_sort" name="sort" type="text" value="${instru.sort}" class="form-data col-xs-10 col-sm-5" placeholder="排序"
							required="true" type="integer" min="1" max="9999"/>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span class="form-star">*</span>是否有效</label>

				<div class="col-xs-12 col-sm-9">
					<div class="clearfix help-validate">
						<div>
							<label class="line-height-1 blue">
								<input name="isEnable" value="true" type="radio" class="ace" ${instru.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 是</span>
							</label>
						</div>
	
						<div>
							<label class="line-height-1 blue">
								<input name="isEnable" value="false" type="radio" class="ace" ${!instru.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 否</span>
							</label>
						</div>
					</div>
				</div>
			</div>
			
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button id="_submit" type="button" class="btn btn-info" data-loading-text="正在提交..." permission="/inst/save">
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

<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			//焦点
			$("#_name").focus();
			
			// 提交
			$("#_submit").click(function(){
				if($('#_editForm').validate()){
					var btn = $(this);
					btn.button('loading');
					$.post("${_path}/admin/inst/save", $.formJson('_editForm'),function(d) {
						if(d){
							btn.button('reset');
							if(d.code == 1){
								$.aceRedirect("${_path}/admin/inst");
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
				$.aceRedirect("${_path}/admin/inst");
			});
			
			// 回车绑定
			$(".form-data").bind('keypress',function(event){
                if(event.keyCode == "13"){
                	event.preventDefault();
                	$("#_submit").click();
                }
            });
            
            // 权限处理
			$.permission();
		});
	});
</script>
