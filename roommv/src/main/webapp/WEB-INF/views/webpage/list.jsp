<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div></div>
<div class="content" style="margin-top: 70px">
    <div class="list">
        <input type="hidden" name="start" value="${start}"/>
        <ul class="thumbnails" style="list-style-type: none">
            <c:if test="${not empty htmls}">
                <c:forEach items="${htmls}" var="html">
                    <li class="span4">
                        <div class="thumbnail">
                            <div class="row">
                                <div class="col-sm-9">
                                    <div class="row" style="width: 900px; height: 150p">
                                        <div class="col-xs-8 col-sm-6" style="width: 250px;">
                                            <a target="_blank" style="text-decoration: none"
                                               href="<c:url value='/staticpages/${html.id}.html' />">
                                                <img alt=""
                                                     src="http://localhost:8081/roommv/upload/htmlimage/1421891098125.jpg"
                                                     style="width: 220px; height: 150p"/>
                                            </a>
                                        </div>
                                        <div class="col-xs-4 col-sm-6" style="width: 650px;">
                                            <div>
                                                <a target="_blank" style="text-decoration: none"
                                                   href="<c:url value='/staticpages/${html.id}.html' />">
                                                    <h4 style="color: #3071A9;">
                                                        <c:out value="${html.title}" escapeXml="false"/>
                                                    </h4>
                                                </a>
                                            </div>
                                            <div style="margin-top: 70px">
                                                <c:out value="${html.content}" escapeXml="false"/>
                                                ...
                                            </div>
                                            <div></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </li>
                </c:forEach>
            </c:if>
            <c:if test="${empty htmls}">
                抱歉，没有找到与"<font color='red'>${searchInfo}</font>"相关的网页
            </c:if>
            <li id="Loading"></li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    var stop = true;
    $(window).scroll(
            function () {
                totalheight = parseFloat($(window).height())
                + parseFloat($(window).scrollTop());
                if ($(document).height() <= totalheight) {
                    if (stop == true) {
                        stop = false;
                        var start = $("input[name='start']").val() * 1;
                        var searchInfo = encodeURI(encodeURI($(
                                "input[name='searchInfo']").val()));
                        var url = "<c:url value='/webpage/list/more'/>?searchInfo="
                                + searchInfo;
                        $.get(url, {"start": start, "count": 10},
                                function (data) {
                                    var contentArr = [];
                                    var htmls = data.htmls;
                                    var count = data.count * 1;
                                    for (var i = 0; i < count; i++) {
                                        contentArr.push("<li class='span4'><div class='thumbnail'>");
                                        contentArr.push("<!--<img alt='300x200' src=\"<c:url value='/images/dc.jpg'/>\" />-->");
                                        contentArr.push("<div class='caption'><h4>" + htmls[i].title + "</h4>");
                                        contentArr.push("<p>" + htmls[i].content + "...</p>");
                                        contentArr.push("<p><a class='btn btn-primary' target='_blank' href=\"<c:url value='/staticpages/"+htmls[i].id+".html'/>\" >浏览</a>");
                                        contentArr.push("<a class='btn' href='#'>分享</a></p></div></div></li>");
                                    }
                                    var contentStr = contentArr
                                            .join("");
                                    $("#Loading").before(
                                            contentStr);
                                    $("input[name='start']").val(count + start);
                                    stop = true;
                                }, "json");
                    }
                }
            });
</script>
</body>
</html>