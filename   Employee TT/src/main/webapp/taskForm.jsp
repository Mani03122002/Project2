<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="task-container">
        <h2>Task Management</h2>
        <form action="task" method="post">
            <label for="employeeName">Employee Name:</label>
            <input type="text" id="employeeName" name="employeeName" required>
            <label for="role">Role:</label>
            <input type="text" id="role" name="role" required>
            <label for="project">Project:</label>
            <input type="text" id="project" name="project" required>
            <label for="taskDate">Date:</label>
            <input type="date" id="taskDate" name="taskDate" required>
            <label for="startTime">Start Time:</label>
            <input type="time" id="startTime" name="startTime" required>
            <label for="endTime">End Time:</label>
            <input type="time" id="endTime" name="endTime" required>
            <label for="taskCategory">Task Category:</label>
            <input type="text" id="taskCategory" name="taskCategory" required>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
            <button type="submit">Add Task</button>
        </form>
        <form action="TaskServlet" method="post">
        </form>
    </div>
</body>
</html>
