package com.dell.springboot.entities;

public class StudentToCourse {
    private String sId;
    private Integer courseId;

    public StudentToCourse(String sId, Integer courseId) {
        this.sId = sId;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "StudentToCourse{" +
                "sId='" + sId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
