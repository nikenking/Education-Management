package com.dell.springboot.entities;

import com.dell.springboot.Service.ClassService;

public class Course {
    public Integer cId;
    public String cName;

    public Course(Integer cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    public String getcId_s(){
        return String.valueOf(cId);
    }
    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
