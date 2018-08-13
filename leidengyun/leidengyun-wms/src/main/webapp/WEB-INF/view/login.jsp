<%@ page language="java" pageEncoding="utf-8"%>
<%  
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);   
%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>登录-${_systemName}</title>
		
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link type="images/x-icon" rel="shortcut icon" href="${_staticPath}/custom/assets/favicon.ico">
		
		<script type="text/javascript">
			var url = window.location.href;
			if(url.indexOf('${loginUrl}') == -1){
				window.location.href = '${_path}${loginUrl}';
			}
		</script>
		
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${_staticPath}/login/css/login.css" />
		<link rel="stylesheet" href="${_staticPath}/login/css/signin.css" />
		<link rel="stylesheet" href="${_staticPath}/login/css/bootstrap.min.css" />
</head>

<body>

 	<div class="login-warp">
        <div class="loginTop">物联网数据服务平台</div>
        <div class='loginCont'>
        	<div class="loginInput">
        		<form id="_loginForm" action="${_path}/login" method="post" class="form-signin" validate="true" vmessage="false" >
        			<input type="hidden" name="backUrl" value="${backUrl}" />
					<input type="hidden" name="appCode" value="${appCode}" />
		        	<p>用户平台登录</p>
		        	<input type="text" name="account"  autocomplete="off" class="form-control" placeholder="用户名" required autofocus />
	              	<input type="text" name="password"  onfocus="this.type='password'" autocomplete="off"  class="form-control" placeholder="密码" required />
<!--	              	<span id="tips" style="color: red">${msg}</span>-->
<!-- 	                <a href="./forgetPass.jsp">忘记密码？</a> -->
	                <button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
                </form>
	        </div>
        </div>
        <div class="loginBottom"></div>
    </div>	
    
   	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${_staticPath}/assets/js/jquery.js'>"+"<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
	<script type="text/javascript">
	 window.jQuery || document.write("<script src='${_staticPath}/assets/js/jquery1x.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
	
	<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='${_staticPath}/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
	</script>
	
	<script type="text/javascript" src="${_staticPath}/custom/jquery.cookie.js"></script>
	<script type="text/javascript" src="${_staticPath}/custom/jquery.form.min.js"></script>
	<script type="text/javascript" src="${_staticPath}/custom/jquery.validate-2.0.min.js"></script>
	<script type="text/javascript" src="${_staticPath}/custom/jquery.validate-2.0.custom.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
		    
			//验证是否存在错误消息
			var fail = '${errorMessage}';
			if(fail != null && fail != ''){
				alert(fail);
			}
           	
			//判断之前是否有设置cookie
			if($.cookie('_account') != undefined){
				$("#_account").val($.cookie('_account'));
				
				$("#_password").focus();
				$("#_rememberMe").attr("checked", true);
			}
			else{
				$("#_account").focus();
				$("#_rememberMe").attr("checked", false);
			}
		 	
			$('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
		 	});
		 	$('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
		 	});
		 	$('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
		 	});
		 	
			$("#_loginButton").click(function() {
				if($('#_loginForm').validate()){

					if($('#_rememberMe:checked').length > 0){
						$.cookie('_account', $("#_account").val());
					} 
					else{
						$.removeCookie('_account');
					}

					$('#_loginForm').submit();
				}
			});

			// 回车绑定
			$(".form-data").bind('keypress', function(event) {
				if(event.keyCode == "13"){
					event.preventDefault();
					$("#_loginButton").click();
				}
			});
		});
	</script>
</body>
</html>
