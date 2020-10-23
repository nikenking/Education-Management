package com.dell.springboot.mapper;

import com.dell.springboot.entities.Score;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.StudentToCourse;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student")
    public List<Student> getAllStudent();
    @Select("select * from student where sId like CONCAT('%',#{sId},'%')")
    public List<Student> getStudentById(String sId);
    @Select("select * from student where sId = #{sId}")
    public List<Student> getStudentByIdExac(String sId);
    @Select("select * from student where sName like CONCAT('%',#{sName},'%')")
    public List<Student> getStudentByName(String sName);
    @Select("SELECT * from studenttocourse where sId like CONCAT('%',#{sId},'%')")
    public List<Score> getScore(String sId);
    @Insert("insert into student(sId,sName,sGender,sClassId,sPass) values(#{s.sId},#{s.sName},#{s.sGender},#{s.sClassId},#{s.sPass})")
    public void addStudent(@Param("s") Student student);
    @Update("update student set sId=#{s.sId},sName=#{s.sName},sGender=#{s.sGender},sClassId=#{s.sClassId} where sId=#{s.sId}")
    public void changeStudent(@Param("s") Student student);
    @Delete("delete from student where sId=#{id}")
    public void deleteStudent(String id);
    @Select("SELECT score from studenttocourse where sId=#{s.sId} and courseId=#{s.courseId}")
    public String getOneScore(@Param("s")StudentToCourse studentToCourse);
}