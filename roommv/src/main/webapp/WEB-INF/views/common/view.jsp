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
<script type="text/javascript" charset="utf-8"
	src="<c:url value='/ueditor/ueditor.config.js'/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<c:url value='/ueditor/ueditor.all.min.js'/>">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="<c:url value='/ueditor/lang/zh-cn/zh-cn.js'/>"></script>
</head>
<body>
<form action="<c:url value='/htmlInfo/audit'/>">
	<div>
		<div >
		<input type="hidden" id="id" name="id" value="${htmlInfo.id}"/>
		<input type="hidden" id="htmlId" name="htmlId" value="${htmlInfo.htmlId}"/>
			<iframe
				src="<c:url value='/tempstaticpages/${htmlInfo.htmlId}.html'/>"
				style="width: 100%; height: 1000px;border:0"  ></iframe>
		</div>
		<div class="text-center"
			style="z-index: 999px; position: fixed; bottom: 0; height: 150px; width: 100%;box-shadow:-10px -5px 3px #888888">
			<select name="auditStatus" id="auditStatus" style="width: 100px; height: 33px; margin-top: 20px">
				<option value="ing" <c:if test="${htmlInfo.auditStatus eq 'ing'}">selected</c:if> >待审核</option>
				<option value="pass" <c:if test="${htmlInfo.auditStatus eq 'pass'}">selected</c:if> >审核通过</option>
				<option value="fail" <c:if test="${htmlInfo.auditStatus eq 'fail'}">selected</c:if> >审核不通过</option>
			</select><c:if test="${htmlInfo.auditStatus eq 'ing' or htmlInfo.auditStatus eq null}"><input id="audit" type="submit" class="btn btn-primary" style="margin-left: 30px" value="审核文章"></input></c:if> <br />
			备注:
			<textarea name="remark" id="remark" style="width: 800px; height: 80px; margin-top: 10px"
				rows="2">${htmlInfo.remark}</textarea>

		</div>
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
