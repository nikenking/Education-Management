package com.dell.springboot.entities;


public class Teacher {
    private Integer tId;
    private String tName;
    private Integer tGender;
    private String tPass;
    private Integer courseId;
    private String courseName;

    public Teacher() {

    }

    public Teacher(Integer tId, String tName, Integer tGender, String tPass , Integer courseId) {
        System.out.println("开始封装：");
        this.tId = tId;
        this.tName = tName;
        this.tGender = tGender;
        this.courseId = courseId;
        this.tPass = tPass;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId='" + tId + '\'' +
                ", tName='" + tName + '\'' +
                ", tGender=" + tGender +
                ", courseId=" + courseId +
                ", tPass='" + tPass + '\'' +
                '}';
    }
    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Integer gettGender() {
        return tGender;
    }

    public void settGender(Integer tGender) {
        this.tGender = tGender;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String gettPass() {
        return tPass;
    }

    public void settPass(String tPass) {
        this.tPass = tPass;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}
