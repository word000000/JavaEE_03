package org.example.spring.mvc.servlet;

import org.example.spring.mvc.jdbc.StudentJdbc;
import org.example.spring.mvc.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 21:31 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
@WebServlet("/addstudent")
public class TeacherAddStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student newStudent = new Student();
        newStudent.setStudentId(Long.parseLong(req.getParameter("studentid")));
        newStudent.setStudentName(req.getParameter("studentname"));
        List<Student> studentList = null;
        try {
            studentList = StudentJdbc.selectAllStudent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean isExist = false;
        for(Student student:studentList){
            //使用equals方法
            if( newStudent.getStudentId().equals(student.getStudentId())){
                isExist = true;
                break;
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        if(isExist){
            //中文编码
            resp.getWriter().println("该学号已被注册,3s后跳转");
            //延时跳转
        }else {

            try {
                if(newStudent.getStudentName().equals("")){

                    resp.getWriter().println("姓名不为空，请检查后再添加,3s后跳转");
                }else{
                    if(StudentJdbc.addStudent(newStudent)){
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
