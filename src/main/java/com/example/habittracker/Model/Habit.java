package com.example.habittracker.Model;

public class Habit {

    private String name;
    private int currentStreak;
    private boolean isCompleted;
    private int longestStreak;
    private int id;


    public Habit(String name) {
        this.name = name;
        isCompleted = true;
    }

    public Habit(String name, boolean isCompleted, int longestStreak, int currentStreak, int id) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.longestStreak = longestStreak;
        this.currentStreak = currentStreak;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int streaks) {
        this.currentStreak = streaks;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
