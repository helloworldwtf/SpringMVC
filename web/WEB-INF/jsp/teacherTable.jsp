<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/3/10
  Time: 8:07 下午
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>作业管理系统</title>
</head>
<body>
<h2>请选择你要操作的类型：</h2>
<div style="margin-left: 50px">
  <ul style="line-height: 40px">
    <li><a href="${pageContext.request.contextPath}/to_addHomework">添加作业</a></li>
    <li><a href="${pageContext.request.contextPath}/to_addStudent">添加学生</a></li>
    <li><a href="${pageContext.request.contextPath}/to_showHomework">显示所有作业</a></li>
    <li><a href="../../index.jsp">返回首页</a></li>
  </ul>
</div>

</body>
</html>
