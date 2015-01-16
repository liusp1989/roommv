<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>浪漫屋|男人来自火星，女人来自金星</title>
<link href="<c:url value='/css/bootstrap.min.css'/>" type="text/css"
	rel="stylesheet" />
<style type="text/css">
body{
background-color: #E6E6E6;
}
.navgul {
	z-index: 3;
	top: 0;
	box-shadow: 2px 2px 2px #cdcdcd;
	width: 100%
}

ul.nav li a{
font-size:20px
}
</style>
</head>
<body >
	<div>
		<div class="navgdiv">
			<ul class="nav nav-pills nav-justified navgul" >
				<li></li>
				<li><a href="javascript:void(0)"
					onclick="loadUrl(&quot;<c:url value='/webpage/list'/>&quot;);">浪漫创意馆</a></li>
				<li><a href="javascript:void(0)" onclick="loadUrl('');">星座</a></li>
				<li><a href="javascript:void(0)" onclick="loadUrl('');">关于浪漫我想说</a></li>
				<li><a href="javascript:void(0)" onclick="loadUrl('a');">许愿池</a></li>
				<li><a href="javascript:void(0)" onclick="loadUrl('a');">浪漫商城</a></li>
				<li> </li>
			</ul>

		</div>
		<div class="container" style="margin-left: -100px;">
		<div class="sider" style="position: fixed; z-index: 2; margin-top:90px; margin-left: 200px;over-flow:hidden">
		    <ul class="thumbnails" style="list-style-type: none">
							<li class="span5">
								<div class="thumbnail">
									<div class="caption">
													<input class="search" type="search" name="searchInfo" value="${searchInfo }" onkeydown="listenEnterKey(event);" /> <button type="button" class="btn" onclick="searchInfo();">查找</button>				
									</div>
								</div>
							</li>
				</ul>
			</div>
			<div class="" id="content" style="margin-left: 510px;float:left;width:1000px;height:100%"></div>
		</div>
	</div>
	<script src="http://libs.baidu.com/jquery/1.11.1/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		//为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
		window.jQuery||document.write("<script src=\"<c:url value='/js/jquery-1.11.1.min.js'/>\"><\/script>");
	</script>
	<script src="http://libs.baidu.com/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		//为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
		window.jQuery||document.write("<script src=\"<c:url value='/js/bootstrap.min.js'/>\"><\/script>");
	</script>
	<script type="text/javascript">
		$(function() {
			var searchInfo = encodeURI(encodeURI($("input[name='searchInfo']").val()));
			$("#content").load("<c:url value='/webpage/list'/>?searchInfo="+searchInfo);
		});
		function loadUrl(url){
			var searchInfo = encodeURI(encodeURI($("input[name='searchInfo']").val()));
			$("#content").load(url+"?searchInfo="+searchInfo);
		};
		function listenEnterKey(event){
			var theEvent = event||window.event;
			var keyCode = theEvent.keyCode;
			if(keyCode==13){
				searchInfo();
			};
		};
		function searchInfo(){
			var searchInfo = encodeURI(encodeURI($("input[name='searchInfo']").val()));
			$("#content").load("<c:url value='/webpage/list'/>?searchInfo="+searchInfo);
		};
	</script>
</body>
</html>