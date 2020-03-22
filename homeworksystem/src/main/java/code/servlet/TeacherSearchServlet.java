package code.servlet;

import code.jdbc.HomeworkJdbc;
import code.model.StudentHomework;
import code.model.TeacherHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 18:28 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
@WebServlet("/searchallhomwork")
public class TeacherSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> studentHomeworkList = null;
        List<TeacherHomework> teacherHomeworkList = null;
        try {
            studentHomeworkList = HomeworkJdbc.selectAllStudentHomework();
            teacherHomeworkList = HomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studenthomeworklist",studentHomeworkList);
//        req.setAttribute("teacherhomeworklist",teacherHomeworkList);
        req.getRequestDispatcher("search.jsp").forward(req,resp);
    }

}
