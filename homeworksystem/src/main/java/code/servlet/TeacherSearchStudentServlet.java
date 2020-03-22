package code.servlet;

import code.jdbc.StudentJdbc;
import code.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 22:52 2020/3/7
 * @Description:
 * @Modifyed_By:
 */

@WebServlet("/searchstudent")
public class TeacherSearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = null;
        try {
            list = StudentJdbc.selectAllStudent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studentlist",list);
        req.getRequestDispatcher("student.jsp").forward(req,resp);
    }
}
