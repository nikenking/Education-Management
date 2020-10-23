package com.dell.springboot.entities;

public class Student {
    private String sId;
    private String sName;
    private Integer sGender;
    private Integer sClassId;
    private String sPass;
    private String oneScore;
    public Student(String sId, String sName, Integer sGender, Integer sClassId, String sPass) {
        this.sId = sId;
        this.sName = sName;
        this.sGender = sGender;
        this.sClassId = sClassId;
        this.sPass = sPass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sGender=" + sGender +
                ", sClassId=" + sClassId +
                ", sPass='" + sPass + '\'' +
                ", oneScore='" + oneScore + '\'' +
                '}';
    }

    public String getOneScore() {
        return oneScore;
    }

    public void setOneScore(String oneScore) {
        this.oneScore = oneScore;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsGender() {
        return sGender;
    }

    public void setsGender(Integer sGender) {
        this.sGender = sGender;
    }

    public Integer getsClassId() {
        return sClassId;
    }

    public void setsClassId(Integer sClassId) {
        this.sClassId = sClassId;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }
}
