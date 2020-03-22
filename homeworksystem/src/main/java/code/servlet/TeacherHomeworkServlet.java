package code.servlet;

/**
 * @Author:GQM
 * @Date:created in 13:02 2020/3/8
 * @Description:
 * @Modifyed_By:
 */

import code.jdbc.HomeworkJdbc;
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
@WebServlet("/searchteacherhomework")
public class TeacherHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeacherHomework> teacherHomeworkList = null;
        try {
            teacherHomeworkList = HomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("teacherhomeworklist",teacherHomeworkList);
        req.getRequestDispatcher("addhomework.jsp").forward(req,resp);
    }

}
