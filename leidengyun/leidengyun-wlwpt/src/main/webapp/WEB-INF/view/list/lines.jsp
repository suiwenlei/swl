<%@ page language="java" pageEncoding="utf-8"%>
<%  
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);   
%>
<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   
  
   
   <link type="text/css" rel="styleSheet"  href="${_path}/map/css/loading.css" />
   
   <body style="height: 100%; margin: 0;">
   <div id="container" style="height: 100%;"></div>
   <div id="over" class="overLoading"></div>
   <div id="layout" class="layoutLoading"><img src="${_path}/map/img/5-121204193Q9-50.gif" /></div>
   <script type="text/javascript" src="${_path}/map/js/loading.js"></script>
   <script type="text/javascript" src="${_path}/map/js/echarts.min.js"></script>
   <script type="text/javascript">
  
       
       
       
      
       
           
var LineJson='${LineJson}';
var LineJsons=eval("(" +LineJson+ ")");
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '多 Y 轴示例';

var colors = ['#5793f3', '#d14a61', '#675bba'];

option = LineJsons;
if (option && typeof option === "object") {
	
	showLoading(true);
	
	
    myChart.setOption(option, true);
    
    setTimeout(function(){return showLoading(false);},"1000");
}
       </script>
   </body>
</html>

