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
<link href="<c:url value='/css/bootstrap-theme.min.css'/>" type="text/css"
	rel="stylesheet" />
<style type="text/css">
</style>
</head>
<body >
	<div class="jumbotron">
		<div>
			<button type="button"  id="deleteAllHtmlIndex" data-loading-text="删除中..." autocomplete="off" class="btn btn-primary btn-lg"
				onclick="deleteAllHtmlIndex();">删除索引</button>

			<button type="button" style="margin-left: 30px"
				class="btn btn-primary btn-lg" onclick="createHtmlIndex();">创建索引</button>
				
				<button type="button" style="margin-left: 30px"
				class="btn btn-primary btn-lg" onclick="optimizeHtmlIndex();">优化索引</button>
		</div>
	</div>


	<div class="modal fade in bs-example-modal-sm" id="error" tabindex="-1"
		data-backdrop="static" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body">
					<strong>错误!</strong> 操作失败
					<div class="modal-footer">
						<p>
							<button type="button" class="btn btn-danger" id="close">确定</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade in bs-example-modal-sm" id="doing"
		data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body">
					<strong>操作执行中...</strong>
				</div>
				<div class="modal-footer"></div>
			</div>
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
		function deleteAllHtmlIndex() {
			$('#doing').modal('show');
			$.get("<c:url value='/indexControl/deleteAllHtmlIndex'/>",
					function(data, status) {
						if (status == "success" && data.resultCode == 0) {
							 setTimeout("$('#doing').modal('hide')", 1000); 
						} else {
							$('#doing').modal('hide');
							$('#error').modal('show');
						}
					});
		};
		function createHtmlIndex() {
			$('#doing').modal('show');
			$.get("<c:url value='/indexControl/createHtmlIndex'/>", function(
					data, status) {
				if (status == "success" && data.resultCode == 0) {
					setTimeout("$('#doing').modal('hide')", 1000); 
				} else {
					$('#doing').modal('hide');
					$('#error').modal('show');
				}
			});
		};

		function optimizeHtmlIndex() {
			$('#doing').modal('show');
			$.get("<c:url value='/indexControl/optimizeHtmlIndex'/>", function(
					data, status) {
				if (status == "success" && data.resultCode == 0) {
					setTimeout("$('#doing').modal('hide')", 1000); 
				} else {
					$('#doing').modal('hide')
					$('#error').modal('show');
				}
			});
		};
		$(function(){
			$("#close").click(function(){
				$('#error').modal('hide');
			});
		});
	</script>
</body>
</html>