package org.example.spring.mvc.servlet;

import org.example.spring.mvc.jdbc.TeacherHomeworkJdbc;
import org.example.spring.mvc.model.TeacherHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 12:27 2020/3/8
 * @Description:
 * @Modifyed_By:
 */

@WebServlet("/addhomework")
public class TeacherAddHomeworkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherHomework nth = new TeacherHomework();
        nth.setHomeworkId(Long.parseLong(req.getParameter("homeworkid")));
        nth.setHomeworkTitle(req.getParameter("homeworktitle"));
        List<TeacherHomework> thList = null;
        try {
            thList = TeacherHomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean isExist = false;
        for(TeacherHomework th:thList){
            //使用equals方法
            if(nth.getHomeworkId()==th.getHomeworkId()){
                isExist = true;
                break;
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        if(isExist){
            //中文编码
            resp.getWriter().println("该id已被使用,3s后跳转");
            //延时跳转
        }else {
            try {
                if(nth.getHomeworkTitle().equals("")){
                    resp.getWriter().println("id不为空，请检查后再添加,3s后跳转");
                }else{
                    if(TeacherHomeworkJdbc.addHomework(nth)){
                        resp.getWriter().println("添加成功,3s后跳转");
                    }else {
                        resp.getWriter().println("添加失败，请检查后再添加,3s后跳转");
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //延时跳转
        }
        resp.setHeader("refresh","3;URL=index.jsp");
        }
}
