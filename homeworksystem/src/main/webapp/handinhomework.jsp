<%--
  Created by IntelliJ IDEA.
  User: guoqi
  Date: 2020/3/8
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交作业</title>
</head>
<body>
<h1 align="center">
    学生作业提交
</h1>
<form align="center" action="/submit" method="post">
    学生学号:<input type="number" name="studentid">
    <br><br><br>
    作业编号:<input type="number" name="homeworkid">
    <br><br><br>
    作业内容:<input type="text" name="homeworkcontent">
    <br><br><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
