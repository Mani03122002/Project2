package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        int userId = Integer.parseInt((String) session.getAttribute("userId"));

        try (Connection conn = DBUtil.getConnection()) {
            List<Task> tasks = new ArrayList<>();
            PreparedStatement stmt;

            if ("Admin".equals(role)) {
                stmt = conn.prepareStatement("SELECT * FROM Tasks");
            } else {
                stmt = conn.prepareStatement("SELECT * FROM Tasks WHERE EmployeeID = ?");
                stmt.setInt(1, userId);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                );
                tasks.add(task);
            }

            request.setAttribute("tasks", tasks);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
