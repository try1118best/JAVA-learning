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
            <td>删除id：</td>
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
    <a href="/user">查看用户信息</a>
</body>
</html>
