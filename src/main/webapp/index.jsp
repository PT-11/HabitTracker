<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Your Daily Tasks</title>
</head>

<body class="background-color">

    <div id="wrapper">
        <div id="header">
            <h2 class="header-center header-font  display-1">Daily Habits</h2>
            <h6 class="header-center header-font" >Take control of your day</h6>
        </div>
    </div>

<div class="form-center">
    <form method="get" action="HabitServlet">
        <input type="text" id="habitName" name="habitName" placeholder="Add a new task for the day">
        <input type="hidden" id="command" name="command" value="ADD">
        <input class="submit-button" type="submit" value="Add Task">

    </form>
</div>

    <div id="container">
        <div id="content">
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th class="standard-text">Habit Name</th>
                        <th class="standard-text">Actions</th>
                        <th class="standard-text">Edit</th>
                    </tr>
                </thead>

                <c:forEach var="habit" items="${habits}">

                    <c:url var="tempLink" value="HabitServlet">
                        <c:param name="command" value="COMPLETE" />
                        <c:param name="id" value="${habit.id}" />
                    </c:url>

                    <c:url var="tempLink2" value="HabitServlet">
                        <c:param name="command" value="LOAD" />
                        <c:param name="habitId" value="${habit.id}" />
                    </c:url>

                    <tr>
                        <td class="standard-text">${habit.name}</td>
                        <td class="standard-text"><a href="${tempLink}">Completed</a></td>
                        <td class="standard-text"><a href="${tempLink2}">Update</a></td>
                    </tr>

                </c:forEach>

            </table>

        </div>
    </div>



</body>
</html>