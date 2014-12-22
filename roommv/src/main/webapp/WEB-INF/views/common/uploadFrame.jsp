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
<body  onload="load()">
	<form id="imageForm" action="<c:url value='/upload/htmlImage' />"
		method="post" enctype="multipart/form-data" style="width: 100%" onsubmit="return validate();">
		<div style="margin: 0px; display: inline">
			标题图片：<input type="file" name="file" class="btn btn-primary" accept="image/*" id="file" style="width: 250px;" />
			 <input id="id" type="hidden" name="id" value="${id }" /></input>
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
 	function load() {//当修改页面初始化时，result.value为空，所以需要重新去后台取一边
		var id = $(window.parent.document).find("#id").val();
		$("#id").val(id);
		//var url = "<c:out value='${result.value}'/>";
		$.ajax({
			url : "<c:url value='/htmlInfo/imageUrl'/>",
			type : "POST",
			data : {
				"id" : id
			},
			success : function(result) {
				if (result.resultCode == 0) {
					$(window.parent.document).find("#showIframe").attr("src",
							result.value);
				}
			}
		});
	} 
	function validate() {
		var fileName = $("#file").val();
		if (fileName == "") {
			alert("请先选择要上传的文件");
			return false;
		} else {
			var types = [ "BMP", "PCX", "TIFF", "GIF", "JPEG", "TGA", "EXIF",
					"JPG", "FPX", "SVG", "PSD", "CDR", "PCD", "DXF", "UFO",
					"EPS", "AI", "PNG", "HDRI", "RAW", "bmp", "pcx", "tiff",
					"gif", "jpeg", "tga", "exif", "fpx", "svg", "psd", "cdr",
					"pcd", "dxf", "ufo", "eps", "ai", "png", "hdri", "raw",
					"jpg" ];
			var type = fileName.substring(fileName.lastIndexOf(".") + 1);
			var rightTypes = types.some(function(item, index, array) {
				return (type == item);
			});
			if (!rightTypes) {
				alert("所选文件不属于图片类型");
				return false;
			}
		}
		return true;
	}
</script>

