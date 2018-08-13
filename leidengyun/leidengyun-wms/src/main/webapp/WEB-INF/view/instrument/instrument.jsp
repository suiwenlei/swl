<%@ page contentType="text/html;charset=GBK"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="仪器字典维护"/>
</jsp:include>

<div class="page-header">
	<h1>
		仪器字典维护
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
									<label class="control-label" for="form-field-1"> 仪器名称： </label>
									<input name="insName" type="text" class="form-data input-medium search-data">
								</label>
								
<!-- 								<button id="_search" type="button" class="btn btn-info btn-sm"> -->
<!-- 									<i class="ace-icon fa fa-search bigger-110"></i>搜索 -->
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
			// 列表
			var $table = $("#_table").table({
    			url : "${_path}/admin/inst/list",
    			formId : "_form",
				tools : [
					{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/inst/edit', handler : function(){
						$.aceRedirect("${_path}/admin/inst/edit");
					}},
					{text : '停用', clazz : 'btn-warning', icon : 'fa fa-lock orange', permission : '/admin/inst/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, false);
					}},
					{text : '启用', clazz : 'btn-success', icon : 'fa fa-unlock green', permission : '/admin/inst/enable', handler : function(){
						$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, true);
					}},
					{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/inst/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "确认要删除?", 
							url : "${_path}/admin/inst/delete"
						});
					}}
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'insId', title:'仪器编号' ,mobileHide : true},
			        {field:'insType', title:'设备类型',mobileHide : true},
			        {field:'insName', title:'仪器名称',mobileHide : true},
			        {field:'insDm', title:'设备代码',mobileHide : true},
			        {field:'insDESC', title:'仪器描述',mobileHide : true},
			        {field:'unit', title:'单位',mobileHide : true},
			        {field:'magNitude', title:'数量级',mobileHide : true},
			        {field:'pramNum', title:'仪器所挂参数个数',mobileHide : true},
			      
			        {field:'act', title:'是否停用', replace : function (d){
				        if(d.isEnable)
				        	return "<span class='label label-sm label-success'>" + d.isEnableStr + 	"</span>";
			        	else
			        		return "<span class='label label-sm label-warning'>" + d.isEnableStr + "</span>";
			        }},
			        {field:'createTime', title:'创建时间', mobileHide : true}
				],
				operate : [
					{text : '修改', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/inst/edit', handler : function(d, i){
						$.aceRedirect("${_path}/admin/inst/edit?id=" + d.id);
					}},
					{text : '停用', clazz : 'orange', icon : 'fa fa-lock', permission : '/admin/inst/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, false);
						},
						show : function(d){
							return d.isEnable;
						}
					},
					{text : '启用', clazz : 'green', icon : 'fa fa-unlock', permission : '/admin/inst/enable', 
						handler : function(){
							$table.ajaxEnable({url : "${_path}/admin/inst/enable"}, true);
						},
						show : function(d){
							return !d.isEnable;
						}
					},
					{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/inst/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "确认要删除?", 
							url : "${_path}/admin/inst/delete"
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
