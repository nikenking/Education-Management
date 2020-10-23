package com.dell.springboot.entities;

import java.util.List;

public class Classes {
    public Integer cId;
    public List<Course> courses;

    public Classes(Integer cId) {
        this.cId = cId;
    }

    public Classes(Integer cId, List<Course> courses) {
        this.cId = cId;
        this.courses = courses;
    }

    public String getcId_s() {
        return String.valueOf(cId);
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public List<Course> getCourses() {
        System.out.println(courses);
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "cId=" + cId +
                ", courses=" + courses +
                '}';
    }
}
