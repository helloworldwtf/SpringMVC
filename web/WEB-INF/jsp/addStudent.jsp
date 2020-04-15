<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/3/10
  Time: 7:52 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
</head>
<body>
<div align="left">
    <h3>添加新学生</h3>
    <form action="${pageContext.request.contextPath}/addStudent" method="post">
        <table style="line-height: 40px">
            <tr>
                <th>学号:</th>
                <td><input type="number" name="id" maxlength="20" style="" required></td>
            </tr>
            <tr>
                <th>姓名:</th>
                <td><input type="text" name="name" maxlength="20" style="" required></td>
            </tr>

            <tr align="center">
                <th><input type="reset" value="清空"></th>
                <td><input type="submit" value="提交"> </td>
            </tr>
        </table>
    </form>
</div>
<li><a href="${pageContext.request.contextPath}/teacherTable">返回上一页</a></li>

</body>
</html>