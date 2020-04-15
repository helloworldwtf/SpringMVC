<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/3/10
  Time: 8:22 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加新作业</title>
</head>
<body>
<div align="left">
    <h3>添加新作业</h3>
    <form action="${pageContext.request.contextPath}/addHomework" method="post">
        <table style="line-height: 40px">
            <tr>
                <th>作业标题:</th>
                <td><input type="text" name="title" maxlength="20" size="40" style="" required></td>
            </tr>
            <tr>
                <th>作业内容:</th>
                <td><textarea name="content" rows="10" cols="42"required></textarea></td>
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