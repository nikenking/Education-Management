package com.dell.springboot.mapper;

import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher")
    public List<Teacher> getAllTeacher();
    @Select("select * from teacher where tId like CONCAT('%',#{tId},'%')")
    public List<Teacher> getTeacherById(String tId);
    @Select("select * from teacher where tName like CONCAT('%',#{tName},'%')")
    public List<Teacher> getTeacherByName(String tName);
    @Insert("insert into teacher(tId,tName,tGender,tPass,courseId) values(#{t.tId},#{t.tName},#{t.tGender},#{t.tPass},#{t.courseId})")
    public void addTeacher(@Param("t") Teacher teacher);
    @Update("update teacher set tId=#{t.tId},tName=#{t.tName},tGender=#{t.tGender},tPass=#{t.tPass},courseId=#{t.courseId} where tId=#{t.tId}")
    public void changeTeacher(@Param("t") Teacher teacher);
    @Delete("delete from teacher where tId=#{id}")
    public void deleteTeacher(String id);
    /*一：找老师对应班级id，返回List<String>
    * 二：找班级对应学生，返回List<Student>
    SELECT classId from teachertoclass where tId=1001
    select * from student WHERE sClassId=17203*/
    @Select("select classId from teachertoclass where tId=#{tId}")
    public List<String> getAllClass(String tId);
    @Select("select * from student WHERE sClassId=#{id}")
    public List<Student> getClassStudent(String id);
}
