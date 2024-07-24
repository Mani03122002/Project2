<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>Dashboard</h2>
    <a href="taskForm.jsp">Add Task</a>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Time Duration</th>
            <th>Task Category</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <%
            // Fetching the tasks list from the request attribute
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks != null) {
                for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getDate() %></td>
            <td><%= task.getTimeDuration() %></td>
            <td><%= task.getTaskCategory() %></td>
            <td><%= task.getDescription() %></td>
            <td>
                <a href="taskForm.jsp?action=edit&taskId=<%= task.getTaskId() %>">Edit</a>
                <form action="DashboardServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No tasks available.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
