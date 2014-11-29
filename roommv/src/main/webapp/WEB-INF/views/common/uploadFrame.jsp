<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>浪漫屋|男人来自火星，女人来自金星</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value='/css/bootstrap.min.css'/>" type="text/css"
	rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-theme.min.css'/>"
	type="text/css" rel="stylesheet" />
</head>
<body>
	<form id="imageForm" action="<c:url value='/upload/htmlImage' />"
		method="post" enctype="multipart/form-data" style="width:100%">
		<div style="margin:0px; display:inline">
			标题图片：<input type="file" name="file" />

			<input type="button" id="imageSubmit" value="上传图片"  style="margin-top:20px"/>

			<img src="<c:out value='${result.value}'/>" alt="" id="image" style="float:right;margin-right:700px;overflow:hidden"/>
		</div>
	</form>
</body>
</html>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
	window.jQuery
			|| document
					.write("<script src=\"<c:url value='/js/jquery-1.11.1.min.js'/>\"><\/script>");
</script>
<script src="http://libs.baidu.com/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
	window.jQuery
			|| document
					.write("<script src=\"<c:url value='/js/bootstrap.min.js'/>\"><\/script>");
</script>
<script type="text/javascript">
$("#imageSubmit").click(function(){
	$("#imageForm").submit();
});
</script>

