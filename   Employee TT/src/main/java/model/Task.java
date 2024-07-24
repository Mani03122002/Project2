package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DBUtil;

public class Task extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addTask(request, response);
        } else if ("edit".equals(action)) {
            updateTask(request, response);
        } else if ("delete".equals(action)) {
            deleteTask(request, response);
        }
    }

    private void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO Tasks (EmployeeID, ProjectID, Date, TimeDuration, TaskCategory, Description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(request.getParameter("employeeId")));
            stmt.setInt(2, Integer.parseInt(request.getParameter("projectId")));
            stmt.setString(3, request.getParameter("date"));
            stmt.setString(4, request.getParameter("timeDuration"));
            stmt.setString(5, request.getParameter("taskCategory"));
            stmt.setString(6, request.getParameter("description"));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE Tasks SET EmployeeID = ?, ProjectID = ?, Date = ?, TimeDuration = ?, TaskCategory = ?, Description = ? WHERE TaskID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(request.getParameter("employeeId")));
            stmt.setInt(2, Integer.parseInt(request.getParameter("projectId")));
            stmt.setString(3, request.getParameter("date"));
            stmt.setString(4, request.getParameter("timeDuration"));
            stmt.setString(5, request.getParameter("taskCategory"));
            stmt.setString(6, request.getParameter("description"));
            stmt.setInt(7, Integer.parseInt(request.getParameter("taskId")));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "DELETE FROM Tasks WHERE TaskID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(request.getParameter("taskId")));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("dashboard.jsp");
    }
}
