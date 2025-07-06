<%@ page import="com.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
    <form action="/user">
        <!-- 使用隐藏字段传递方法参数 -->
        <input type="hidden" name="method" value="delete">
        <table>
            <tr>
                <td>功能：</td>
                <td>
                    <input type="text" name="method"/>
                </td>
            </tr>
        <tr>
            <td>id：</td>
            <td>
                <input type="text" name="id"/>
            </td>
        </tr>
            <tr>
                <td>
                    <input type="submit" value="确认"/>
                </td>
            </tr>
        </table>

    </form>
    <a href="index.jsp">回到首页</a>
    <a href="query.jsp">查看用户信息</a>
</body>
</html>
