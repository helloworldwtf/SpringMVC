<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/3/11
  Time: 7:01 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.StudentHomework" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Homework" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交作业</title>
</head>
<body>
<h2 align="left" style="margin: 50px">提交作业</h2>
<div align="left">
    <form method="post" action="${pageContext.request.contextPath}/submit">
        <table border="1" width="700">
            <%
                Homework homework = (Homework) request.getAttribute("homework");
            %>
            <tr>
                <th width="30%" bgcolor="#7fffd4">作业标题:</th>
                <td width="70%"><%=homework.getTitle()%></td>
            </tr>
            <tr>
                <th width="30%" bgcolor="#7fffd4">作业内容:</th>
                <td width="70%"><%=homework.getContent()%></td>
            </tr>
            <tr>
                <th width="30%" bgcolor="#7fffd4">学号:</th>
                <td width="70%"><input type="number" name="studentId"
                                       maxlength="20"  required>
                </td>
            </tr>
            <input type="hidden" name="title" value="<%=homework.getTitle()%>">
            <input type="hidden" name="homeworkId" value="<%=homework.getId()%>">
            <tr>
                <th width="30%" bgcolor="#7fffd4">作业内容</th>
                <td width="70%"><textarea name="content"   rows="10" required></textarea></td>
            </tr>
            <tr style="border: none">
                <td align="center" bgcolor="#7fffd4"><input type="reset" value="清空" bgcolor="#7fffd4"></td>
                <td align="center" ><input type="submit" value="提交"></td>
            </tr>
        </table>


    </form>
</div>
<li><a href="${pageContext.request.contextPath}/studentTable">返回上一页</a></li>
<li><a href="${pageContext.request.contextPath}../../index.jsp">返回首页</a></li>
</body>
</html>