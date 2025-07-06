<%@ page import="java.util.List" %>
<%@ page import="com.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h1>This is Index Page!</h1>
    <%
        List<User> list =(List<User>) request.getAttribute("list");
    %>
    <%=list%>
</body>
</html>
