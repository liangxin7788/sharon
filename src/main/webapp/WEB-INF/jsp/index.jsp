<%--
  Created by IntelliJ IDEA.
  User: liangxin
  Date: 2019/7/11
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    pageContext.setAttribute("basePath",basePath);
%> 
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>测试页</h2>
    <a href="${basePath}/web/exportExcel">测试下载</a>
</body>
</html>
