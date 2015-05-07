<%--
  Created by IntelliJ IDEA.
  User: jackyliu
  Date: 2015/3/20
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>浪漫屋|男人来自火星，女人来自金星</title>
    <link href="<c:url value='/css/bootstrap.min.css'/>" type="text/css"
          rel="stylesheet"/>
    <html>
    <head>
        <title></title>
    </head>
    <body>
    <div class="row">
        <div class="col-xs-6">.col-xs-6 .col-sm-3</div>
        <div class="col-xs-6">.col-xs-6 .col-sm-3</div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="content">内容</label>
                <input type="text" class="form-control" id="content" placeholder="输入的评价">
                <button type="button" class="btn btn-primary" onclick="sendMes();">发表</button>
            </div>
        </div>
        <div class="col-xs-6">.col-xs-6 .col-sm-3</div>
    </div>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
        //为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
        window.jQuery || document.write("<script src=\"<c:url value='/js/jquery-1.11.1.min.js'/>\"><\/script>");
    </script>
    <script src="http://libs.baidu.com/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        //为了保险起见，当CDN服务器（挂了）上获取不到jQuery时，则使用自己的
        window.jQuery || document.write("<script src=\"<c:url value='/js/bootstrap.min.js'/>\"><\/script>");
    </script>
    <script type="text/javascript">
        Window.WebSocket = Window.WebSocket||Window.MozWebSocket;
        if(!Window.WebSocket){
            alert("当前浏览器不支持websocket");
            return;
        }
        var webSocket = new WebSocket("ws://localhost:8080/chatRoom");
        webSocket.onmessage=function(data){
            alert(data.data);
        }
        function sendMes(){
            var content = $("#content").val();
            webSocket.send(content);
        }
    </script>
    </body>
    </html>
