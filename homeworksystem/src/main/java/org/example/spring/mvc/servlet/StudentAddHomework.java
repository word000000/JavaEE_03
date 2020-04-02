package org.example.spring.mvc.servlet;

import org.example.spring.mvc.jdbc.StudentHomeworkJdbc;
import org.example.spring.mvc.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


/**
 * @Author:GQM
 * @Date:created in 14:25 2020/3/8
 * @Description:
 * @Modifyed_By:
 */
@WebServlet("/submit")
public class StudentAddHomework extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentHomework nsh = new StudentHomework();
        nsh.setStudentId(Long.parseLong(req.getParameter("studentid")));
        nsh.setHomeworkId(Long.parseLong(req.getParameter("homeworkid")));
        nsh.setHomeworkContent(req.getParameter("homeworkcontent"));
        Timestamp dateNow=new Timestamp(System.currentTimeMillis());
//        Date reDate=new Date(System.currentTimeMillis());
        nsh.setCreatTime(dateNow);


        resp.setContentType("text/html;charset=UTF-8");
        try {
            resp.getWriter().println(StudentHomeworkJdbc.handHomework(nsh)+",3s后跳转");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.setHeader("refresh","3;URL=index.jsp");
    }
}
