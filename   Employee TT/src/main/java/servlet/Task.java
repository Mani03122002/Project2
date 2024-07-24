package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Task extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeName = request.getParameter("employeeName");
        String role = request.getParameter("role");
        String project = request.getParameter("project");
        String taskDate = request.getParameter("taskDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String taskCategory = request.getParameter("taskCategory");
        String description = request.getParameter("description");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO Tasks (EmployeeName, Role, Project, TaskDate, StartTime, EndTime, TaskCategory, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, employeeName);
                stmt.setString(2, role);
                stmt.setString(3, project);
                stmt.setDate(4, java.sql.Date.valueOf(taskDate));
                stmt.setTime(5, java.sql.Time.valueOf(startTime));
                stmt.setTime(6, java.sql.Time.valueOf(endTime));
                stmt.setString(7, taskCategory);
                stmt.setString(8, description);
                stmt.executeUpdate();
            }
            response.sendRedirect("dashboard.jsp"); // Redirect to a success page or dashboard
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error", e);
        }
    }
}
