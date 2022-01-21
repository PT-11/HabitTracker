<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.example.habittracker.Model.Habit" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="Styles/styles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik:wght@500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Rubik:wght@500&display=swap" rel="stylesheet">
<link rel="icon" href="images/favicon-32x32.png">
<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
</head>
<body class="background-color" >
<div id="wrapper">
    <div id="header">
        <h2 class="header-center header-font  display-1">Daily Habits</h2>
    </div>
</div>
<h4 class="header-center">Update: ${habit.name}</h4>
<br/>

<div class="form-center">
    <form  method="get" action="HabitServlet">
        <input type="text" id="habitName" placeholder="Enter new task" name="habitName"><br>
        <input type="hidden" id="command" name="command" value="UPDATE">
        <input type="hidden" id="habitId" name="habitId" value="${habit.id}">
        <input class="submit-button" type="submit" value="Submit">
    </form>
</div>



</body>
</html>