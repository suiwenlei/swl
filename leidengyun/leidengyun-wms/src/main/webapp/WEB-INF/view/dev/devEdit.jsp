<%@ page language="java" pageEncoding="utf-8"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="设备"/>
</jsp:include>

<div class="page-header">
	<h1>
		${empty dev.id ? '添加' : '修改'}设备
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<form id="_editForm" class="form-horizontal" role="form">
			<input type="hidden" name="id" value="${dev.id}"> 
			
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>设备编号</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="devId" type="text" value="${dev.devId}" class="form-data col-xs-10 col-sm-5" placeholder="设备编号"
							required="true" ajax="{url : '${_path}/admin/dev/validateCode', type : 'post', dataId : '_editForm'}" maxlength="64"/>
					</div>
				</div>
				
			</div>
			
			
			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>设备备注</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_name" name="bz" type="text" value="${dev.bz}" class="form-data col-xs-10 col-sm-5" placeholder="设备备注"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="_sort" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>排序</label>

				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<input id="_sort" name="sort" type="text" value="${dev.sort}" class="form-data col-xs-10 col-sm-5" placeholder="排序"
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
								<input name="isEnable" value="true" type="radio" class="ace" ${dev.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 是</span>
							</label>
						</div>
	
						<div>
							<label class="line-height-1 blue">
								<input name="isEnable" value="false" type="radio" class="ace" ${!dev.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 否</span>
							</label>
						</div>
					</div>
				</div>
			</div>
			
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button id="_submit" type="button" class="btn btn-info" data-loading-text="正在提交..." permission="/app/save">
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
					$.post("${_path}/admin/dev/save", $.formJson('_editForm'),function(d) {
						if(d){
							btn.button('reset');
							if(d.code == 1){
								$.aceRedirect("${_path}/admin/dev");
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
				$.aceRedirect("${_path}/admin/dev");
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
