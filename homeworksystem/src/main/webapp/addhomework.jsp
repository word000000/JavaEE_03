<%@ page import="code.model.TeacherHomework" %>
<%@ page import="code.jdbc.TeacherHomeworkJdbc" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: guoqi
  Date: 2020/3/8
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">
    作业列表
</h1>
<table align="center" width="970" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">作业编号</td>
        <td align="center">作业标题</td>
    </tr>
    <%
        List<TeacherHomework> tlist = (List<TeacherHomework>) request.getAttribute("teacherhomeworklist");
        if(null == tlist||tlist.size()<=0){
            out.println("None data.");
        }else{
            for(TeacherHomework th:tlist){
    %>
    <tr align="center" bgcolor="white" height="30">
        <td><%=th.getHomeworkId()%></td>
        <td><%=th.getHomeworkTitle()%></td>
    </tr>
    <%}}%>
</table>
<br>
<br>
<br>
<br>
<h1 align="center">
    发布作业
</h1>
<form align="center" action="/addhomework" method="post">
    作业编号:<input type="number" name="homeworkid">
    <br><br><br>
    作业名称:<input type="text" name="homeworktitle">
    <br><br><br>
    <input type="submit" value="发布">
</form>
</body>
</html>
