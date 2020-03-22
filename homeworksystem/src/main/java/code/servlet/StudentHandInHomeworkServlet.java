package code.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:GQM
 * @Date:created in 14:12 2020/3/8
 * @Description:
 * @Modifyed_By:
 */
@WebServlet("/handinhomework")
public class StudentHandInHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("handinhomework.jsp").forward(req,resp);
    }
}
