<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.User" %>
<html>
<head>
    <title>Query</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>分数</td>
        <td>奖金</td>
    </tr>



    <%
        List<User> list =(List<User>) request.getAttribute("list");
        //分别生成10个tr
        for(User user : list){
    %>
    <tr>
        <td>
            <%=user.getId()%>
        </td>
        <td>
            <%=user.getName()%>
        </td>
        <td>
            <%=user.getAge()%>
        </td>
        <td>
            <%=user.getScore()%>
        </td>
        <td>
            <%=user.getMoney()%>
        </td>
    </tr>
    <%
        }
    %>


</table>


<a href="index.jsp">回到首页</a>
</body>
</html>
