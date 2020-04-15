<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/3/11
  Time: 8:43 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作结果</title>
</head>
<body>
<%
    String type = (String)request.getAttribute("type");
    boolean isOK = (boolean)request.getAttribute("isOK");
    if (type.equals("addHomework")){
        if (isOK){
%>

<p style=""><%="添加作业成功"%></p>

<%
}else{
%>

<p style=""><%="添加作业失败"%></p>

<%
    }
}else if (type.equals("addStudent")){
    if (isOK){
%>

<p style=""><%="添加学生成功"%></p>

<%
}else{
%>

<p style=""><%="已有学生信息"%></p>

<%
    }
}else if (type.equals("addStudentHomework")){
    if (isOK){
%>

<p style=""><%="作业提交成功"%></p>

<%
}else {
%>

<p style=""><%="作业提交失败"%></p>

<%
        }
    }
%>
<a href="jsp/studentTable.jsp" style="margin: 50px">返回首页</a>
</body>
</html>