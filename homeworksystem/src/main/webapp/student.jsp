<%@ page import="org.example.spring.mvc.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: guoqi
  Date: 2020/3/7
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业系统</title>
</head>
<body>
<h1 align="center">
    学生列表
</h1>
<table align="center" width="970" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">学号</td>
        <td align="center">姓名</td>
    </tr>
    <%
        List<Student> list = (List<Student>) request.getAttribute("studentlist");
        if(null == list||list.size()<=0){
            out.println("None data.");
        }else{
            for(Student s:list){
    %>
    <tr align="center" bgcolor="white" height="30">
        <td><%=s.getStudentId()%></td>
        <td><%=s.getStudentName()%></td>
    </tr>
    <%}}%>
</table>
<br/>
<br/>
<br/>
<br/>
<h1 align="center">
    添加学生
</h1>
<form align="center" action="/addstudent" method="post">
    学号:<input type="number" name="studentid">
    <br><br><br>
    姓名:<input type="text" name="studentname">
    <br><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
