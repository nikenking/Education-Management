package com.dell.springboot.entities;

public class Score {
    private Integer id;
    private String sId;
    private Integer courseId;
    private Integer score;
    private String sName;
    public Score(Integer id, String sId, Integer courseId, Integer score) {
        this.id = id;
        this.sId = sId;
        this.courseId = courseId;
        this.score = score;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", sId='" + sId + '\'' +
                ", courseId=" + courseId +
                ", score=" + score +
                ", sName='" + sName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
