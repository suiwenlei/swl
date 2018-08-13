<%@ page contentType="text/html;charset=GBK"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="�����ֵ�ά��"/>
</jsp:include>

<div class="page-header">
	<h1>
		�����ֵ�ά��
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">������</h5>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<form id="_form" class="form-inline">
								<label>
									<label class="control-label" for="form-field-1"> �������ƣ� </label>
									<input name="insName" type="text" class="form-data input-medium search-data">
								</label>
								
<!-- 								<button id="_search" type="button" class="btn btn-info btn-sm"> -->
<!-- 									<i class="ace-icon fa fa-search bigger-110"></i>���� -->
<!-- 								</button>  -->
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
			// �б�
			var $table = $("#_table").table({
    			url : "${_path}/admin/inst/list",
    			formId : "_form",
				tools : [
					{text : '����', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/inst/edit', handler : function(){
						$.aceRedirect("${_path}/admin/inst/edit");
					}},
					{text : 'ͣ��', clazz : 'btn-warning', icon : 'fa fa-lock orange', permission : '/admin/inst/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, false);
					}},
					{text : '����', clazz : 'btn-success', icon : 'fa fa-unlock green', permission : '/admin/inst/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, true);
					}},
					{text : 'ɾ��', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/inst/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "ȷ��Ҫɾ��?", 
							url : "${_path}/admin/inst/delete"
						});
					}}
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'insId', title:'�������' ,mobileHide : true},
			        {field:'insType', title:'�豸����',mobileHide : true},
			        {field:'insName', title:'��������',mobileHide : true},
			        {field:'insDm', title:'�豸����',mobileHide : true},
			        {field:'insDESC', title:'��������',mobileHide : true},
			        {field:'unit', title:'��λ',mobileHide : true},
			        {field:'magNitude', title:'������',mobileHide : true},
			        {field:'pramNum', title:'�������Ҳ�������',mobileHide : true},
			      
			        {field:'act', title:'�Ƿ�ͣ��', replace : function (d){
				        if(d.isEnable)
				        	return "<span class='label label-sm label-success'>" + d.isEnableStr + 	"</span>";
			        	else
			        		return "<span class='label label-sm label-warning'>" + d.isEnableStr + "</span>";
			        }},
			        {field:'createTime', title:'����ʱ��', mobileHide : true}
				],
				operate : [
					{text : '�޸�', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/inst/edit', handler : function(d, i){
						$.aceRedirect("${_path}/admin/inst/edit?id=" + d.id);
					}},
					{text : 'ͣ��', clazz : 'orange', icon : 'fa fa-lock', permission : '/admin/inst/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, false);
						},
						show : function(d){
							return d.isEnable;
						}
					},
					{text : '����', clazz : 'green', icon : 'fa fa-unlock', permission : '/admin/inst/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, true);
						},
						show : function(d){
							return !d.isEnable;
						}
					},
					{text : 'ɾ��', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/inst/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "ȷ��Ҫɾ��?", 
							url : "${_path}/admin/inst/delete"
						});
					}}
				],
				after : function(){
					// Ȩ�޴���
					$.permission();
				}
			});
			
			/**
			// ����
			$("#_search").click(function () {
				$table.search();
			});
			
			// �س���
			$(".form-data").bind('keypress',function(event){
			    if(event.keyCode == "13"){
			    	event.preventDefault();
			    	$table.search();
			    }
			});
			 */
			
			// ����
			$(".search-data").keyup(function () { 
				$table.search();
			});
		});
	});
</script>
