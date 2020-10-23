package com.dell.springboot.entities;

public class TeacherToCourse {
    private String tName;
    private String courseName;

    @Override
    public String toString() {
        return "TeacherToCourse{" +
                "tName='" + tName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public TeacherToCourse(String tName, String courseName) {
        this.tName = tName;
        this.courseName = courseName;
    }
}
