package com.example.habittracker.controller;

import com.example.habittracker.DAO.HabitDAO;
import com.example.habittracker.Model.Habit;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HabitServlet", value = "/HabitServlet")
public class HabitServlet extends HttpServlet {

    private HabitDAO habitDAO;


    @Override
    public void init() throws ServletException {
        super.init();

        try {
            habitDAO = new HabitDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            String theCommand = request.getParameter("command");

            if(theCommand == null) {
                theCommand = "LIST";
            }

            switch(theCommand) {

                case "LIST":
                    listHabit(request, response);
                    break;

                case "ADD":
                    addHabit(request, response);
                    break;

                case "COMPLETE":
                    completeHabit(request, response);
                    break;

                case "LOAD":
                    loadHabit(request, response);
                    break;

                case "UPDATE":
                    updateHabit(request, response);
                    break;

                default:
                    listHabit(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    private void updateHabit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String newName = request.getParameter("habitName");
        String habitId = request.getParameter("habitId");
        habitDAO.updateHabit(newName, Integer.parseInt(habitId));
        listHabit(request, response);

    }

    private void loadHabit(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("habitId");
        Habit habit = habitDAO.findHabit(Integer.parseInt(id));
        request.setAttribute("habit", habit);

        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPage.jsp");
        dispatcher.forward(request, response);

    }

    private void completeHabit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");

        habitDAO.completeHabit(Integer.parseInt(id));
        listHabit(request, response);
    }

    private void listHabit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Habit> habits = new ArrayList<>();
        try {
            habits = habitDAO.viewHabits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("habits", habits);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void addHabit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String habitName = request.getParameter("habitName");
        Habit habit = new Habit(habitName);

        try {
            habitDAO.addHabit(habit);

        } catch (Exception e) {
            e.printStackTrace();
        }
          listHabit(request, response);
    }



}
