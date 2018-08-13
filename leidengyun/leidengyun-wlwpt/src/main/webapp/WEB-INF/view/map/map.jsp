<%@ page language="java" pageEncoding="utf-8"%>
<%  
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);   
%>
<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="应用"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${_systemName}</title>
</head>

<body>
<div class="row" >
	<div class="col-xs-12">
		<div class="row" style="margin-left: -29px;margin-right: -22px;margin-top: -7px;">
			<div class="col-xs-12" >
				
				<div class="widget-main" style="padding: 0px;" >
				<iframe name="Map" src="${_path}/sjpt/map/showMap" 
				height="1000px" width="100%" scrolling="no" frameborder="1" ></iframe></div>
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
<!-- page specific plugin scripts -->
<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			$(".search-data").keyup(function () { 
				$table.search();
			});
		});
	});
</script>
</body>
</html>
