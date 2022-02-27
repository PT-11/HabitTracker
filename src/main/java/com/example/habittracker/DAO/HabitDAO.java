package com.example.habittracker.DAO;
import com.example.habittracker.DataSource;
import com.example.habittracker.Model.Habit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {

    private DataSource dataSource;
    private Connection connection;


    public void addHabit(Habit habit) throws Exception {
        String INSERT_HABITS_SQL = "INSERT INTO habits "
                + "(name, longest_streak, current_streak, is_completed) "
                + "VALUES (?, ?,  ?, ?);";
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HABITS_SQL);
        try {
            //Set up connection
            preparedStatement = connection.prepareStatement(INSERT_HABITS_SQL);

            //set the param values for the sql statement
            preparedStatement.setString(1, habit.getName());
            preparedStatement.setInt(2, habit.getLongestStreak());
            preparedStatement.setInt(3, habit.getCurrentStreak());
            preparedStatement.setBoolean(4, habit.isCompleted());
            System.out.println(preparedStatement);

            //execute sql insert
            preparedStatement.executeUpdate();
            System.out.println("Successfully added new habit");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
               connection.close();
               preparedStatement.close();
        }
    }

    public List<Habit> viewHabits() throws SQLException {
        List<Habit> habits = new ArrayList<>();
        DataSource dataSource = new DataSource();
        Connection connection = dataSource.getConnection();
        String sql = "select * from habits";
        ResultSet resultSet = null;

        Statement statement = connection.prepareStatement(sql);
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //retrieve data from result set row
                String name = resultSet.getString("name");
                boolean isCompleted = resultSet.getBoolean("is_completed");
                int longestStreak = resultSet.getInt("longest_streak");
                int currentStreak = resultSet.getInt("current_streak");
                int id = resultSet.getInt("id");

                Habit habit = new Habit(name, isCompleted, longestStreak, currentStreak, id);
                habits.add(habit);

            }

            return habits;

        } finally {
            //close JDBC objects
            connection.close();
            statement.close();
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public Habit findHabit(int id) throws Exception {

        Habit habit = null;
        String sql = "select * from habits where id=?";
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            String name = rs.getString("name");
            boolean is_completed = rs.getBoolean("is_completed");
            int longest_streak = rs.getInt("longest_streak");
            int current_streak = rs.getInt("current_streak");
            int habitId = rs.getInt("id");
            habit = new Habit(name, is_completed, longest_streak, current_streak, habitId);
        } else {
            throw new Exception("Could not find habit with id: " + id);
        }
        connection.close();
        preparedStatement.close();
        rs.close();
        return habit;
    }

    public void completeHabit(int id) throws SQLException {

        //make a separate method to get connection? **TO DO
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
        String sql = "delete from habits where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        connection.close();
        preparedStatement.close();
    }

    public void updateHabit(String newName, int id) throws SQLException {
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
        String sql = "update habits "
                + "set name=? "
                + "where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newName);
        ps.setInt(2, id);
        ps.execute();
        connection.close();
        ps.close();
    }
}
