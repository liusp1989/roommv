<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="roommv" uri="http://www.roommv.com/jsp/jstl/roommv"%>
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
<body style="TEXT-ALIGN: center;">
<div style="margin-top:-100px;">
</div>
<div style="margin-top:200px">
	<button type="button"  style="float:right;margin-right: 50px;" id="addBtn"
		class="btn btn-primary btn-sm" onclick="addHtmlInfo();">新增文章</button>
</div>
<div style="padding-top:50px;">
	<table class="table table-condensed" >
		<tr>
			<th class="info" style="TEXT-ALIGN: center;width:16%">文章编号</th>
			<th class="info" style="TEXT-ALIGN: center;width:16%">文章标题</th>
			<th class="info" style="TEXT-ALIGN: center;width:16%">创建时间</th>
			<th class="info" style="TEXT-ALIGN: center;width:16%">更新时间</th>
			<th class="info" style="TEXT-ALIGN: center;width:16%">审核状态</th>
			<th class="info" style="TEXT-ALIGN: center;width:16%">操作</th>
		</tr>
					<c:if test="${not empty htmlInfos}">
						<c:forEach items="${htmlInfos}" var="htmlInfo">
					<tr>
						<td>${htmlInfo.id }</td>
						<td>${htmlInfo.title }</td>
						<td>${htmlInfo.createDate }</td>
						<td>${htmlInfo.updateDate }</td>
						<td>
						<c:if test="${htmlInfo.auditStatus eq 'ing'}">
							${roommv:auditStatusName("ing")}
						</c:if>
						<c:if test="${htmlInfo.auditStatus eq 'pass'}">
							${roommv:auditStatusName("pass")}
						</c:if>
						<c:if test="${htmlInfo.auditStatus eq 'fail'}">
							${roommv:auditStatusName("fail")}
						</c:if>
						</td>
						<td>
							<button type="button" id="editBtn" class="btn btn-primary btn-sm"
								onclick="editHtmlInfo('${htmlInfo.id }');">修改</button>
							<button type="button" id="viewBtn" class="btn btn-primary btn-sm"
								onclick="viewHtmlInfo('${htmlInfo.id }');">查看</button>
						</td>
					</tr>
				</c:forEach>
					</c:if>
					<c:if test="${empty htmlInfos}">
						<tr>
						<td colspan="4">您暂时还没有编制任何文章</td></tr>
					</c:if>
	</table>
</div>
</body>
</html>
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
	function addHtmlInfo() {
		window.open("<c:url value='/htmlInfo/add'/>");
		
	};
	
	function editHtmlInfo(id){
		 window.open("<c:url value='/htmlInfo/edit'/>/"+id);
	};
	
	function viewHtmlInfo(id){
		window.open("<c:url value='/htmlInfo/view'/>/"+id);
	};
</script>