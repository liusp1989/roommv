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
	<fieldset>
		<div style="position:absolute;left: 50px">
			<form action="<c:url value='/htmlInfo/save'/>" id="htmlInfoForm"
				name="htmlInfoForm" method="post">
				
				<div style="margin-top: 10px">
					当前文档编号：${htmlInfo.id } <input id="id" type="hidden" name="id"
						value="${htmlInfo.id }" />
				</div>
				
				<div style="margin-top: 10px">
					<input type="hidden" name="creator" value="${htmlInfo.creator }" />
					<input type="hidden" name="updator" value="${htmlInfo.updator }" />
					<input type="hidden" name="htmlId" id="htmlId"
						value="${htmlInfo.htmlId }" /> <input type="hidden"
						name="content" id="content" value="${htmlInfo.content }" />
				</div>
				
				<div style="margin-top: 10px">
					标题： <input type="text" name="title" id="title"
						value="${htmlInfo.title }" /><span style="margin-left: 5px;"><font
						color="red">*</font></span>
				</div>
			</form>				
				<div id="uploadDiv"
					style="margin-top: 10px; width: 90%; height: 150px">
					<div style="left: 0px;float:left">
						<div>
							<form id="imageForm" action="<c:url value='/upload/htmlImage' />"
								method="post" enctype="multipart/form-data" style="width: 100%"
								onsubmit="return validate();">
								<input   type="hidden" name="id" value="${htmlInfo.id }" />
									标题图片：<input type="file" name="file" class="btn btn-primary"
										accept="image/*" id="file" style="width: 250px;" />
							</form>
						</div>
						<div>
							<input id="imageSubmit" type="button" onclick="submitImage();" style="margin-top: 20px"
								class="btn btn-primary" value="上传图片"><span id="tips" style="margin-left:20"></span>
						</div>
					</div>
					<div>
						<iframe id="showIframe" name="showIframe"
							style="border: 0px; width: 200px; height: 100px;float:left;margin-left:50px" scrolling="no"
							src="${htmlInfo.imageUrl }"></iframe>
					</div>
				</div>
				
				<div style="margin-top: 10px;">
					<script id="editor" type="text/plain"
						style="width:90%;height:450px;"></script>
				</div>
				
			
			<div style="margin-top: 10px">
				<input id="cancel" type="button" class="btn btn-primary"
					onclick="cancel()"
					style="margin-top: 20px; margin-right: 400px; float: right"
					value="放弃文章"></input> <input id="submit" type="button"
					class="btn btn-primary" onclick="save()"
					style="margin-top: 20px; margin-right: 50px; float: right"
					value="提交文章"></input>
			</div>
			
		</div>
	</fieldset>
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
		var ue = UE.getEditor('editor');
		$(function() {
			var content = "<c:out value='${htmlInfo.content }' escapeXml='false'/>";
			ue.ready(function() {
				UE.getEditor('editor').execCommand('insertHtml', content); //赋值给UEditor
			});
		});

		function save() {
			var hasContent = UE.getEditor('editor').hasContents();
			if (!hasContent) {
				alert("还没有编辑文章");
			} else {
				var bodyContent = UE.getEditor('editor').getContent();
				var formatContent = bodyContent.replace(/\"/g, "'");
				$("#content").val(formatContent);
				$("#htmlInfoForm").submit();
			}
		}
		function cancel() {
			var id = $("#id").val();
			var url = "<c:url value='/htmlInfo/cancel'/>";
			var data = {
				"id" : id
			};
			$.ajax({
				type : 'get',
				url : url,
				data : data,
				success : function(result) {
					if (result.resultCode == 0) {
						alert("成功");
						window.opener.location.reload();
						window.close();
					} else {
						alert("操作失败");
					}
				}
			});
			//UE.getEditor('editor').setContent('欢迎使用ueditor');
		}

		function getLocalData() {
			alert(UE.getEditor('editor').execCommand("getlocaldata"));
		}

		function clearLocalData() {
			UE.getEditor('editor').execCommand("clearlocaldata");
			alert("已清空草稿箱");
		}
		
		function submitImage(){
			var $form =$("#imageForm");
			var $frame = $("<iframe id='imageIframe' name='imageIframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
			$form.attr("target","imageIframe");
			$form.submit();
			var result="";
			$frame.bind("load",function(){
				$frame.unbind("load");
				var frame = $frame[0];
				var doc = frame.contentDocument||frame.document;
				if(doc.readyState&&doc.readyState!="complete")return;
				if(doc.body&&doc.body.innerHTML == "false")return;
				if(doc.XMLDocument){
					result=doc.XMLDocument;
				}else if(doc.body){
					try{
						result=$frame.contents().find("body").text();
						result = jQuery.parseJSON(result);
					}catch(e){
						result = doc.body.innerHTML;
					}
				}else{
					result = doc;
				}
				if(result.resultCode==1){
					$("#tips").html("<font color='red'>"+result.msg+"</font>");
				}else{
					$("#showIframe").attr("src",result.value.imageUrl);
					$("#tips").html("<font color='blue'>上传成功</font>");
				}
				$frame.remove();
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

</body>
</html>