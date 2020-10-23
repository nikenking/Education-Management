package com.dell.springboot.entities;

public class ClassToCourse {
    private Integer id;
    private String cId;
    private String courseId;

    public ClassToCourse(Integer id, String cId, String courseId) {
        this.id = id;
        this.cId = cId;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ClassToCourse{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
