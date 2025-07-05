<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
    <form action="/user" method="post">
        <table>
            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td>
                    <input type="text" name="age"/>
                </td>
            </tr>
            <tr>
                <td>分数：</td>
                <td>
                    <input type="text" name="score"/>
                </td>
            </tr>
            <tr>
                <td>奖金：</td>
                <td>
                    <input type="text" name="money"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交"/>
                </td>
            </tr>



        </table>
    </form>

    <a href="index.jsp">回到首页</a>
</body>
</html>
