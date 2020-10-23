package com.dell.springboot.mapper;

import com.dell.springboot.entities.Classes;
import com.dell.springboot.entities.Course;
import com.dell.springboot.entities.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select courseName from course where courseId=#{id}")
    public String getCourseNameById(Integer id);
    @Select("select * from course where courseId=#{id}")
    public List<Course> getCourseById(Integer id);
    @Select("select * from course where courseName=#{name}")
    public List<Course> getCourseByName(String name);
    @Select("SELECT tName from teacher where courseId=#{courseId}")
    public List<String> getTeacherName(String courseId);
    @Select("select * from course")
    public List<Course> getAllCourse();
    @Insert("insert into course(courseId,courseName) values(#{c.cId},#{c.cName})")
    public void addCourse(@Param("c") Course course);
    @Update("update course set courseName=#{c.cName} where courseId=#{c.cId}")
    public void changeCourse(@Param("c")Course course);
    @Select("select Max(courseId) courseId from course")
    public List<Integer> getMaxRelationId();
    @Delete("delete from course where courseId=#{c.cId}")
    public void delCourse(@Param("c") Course course);

//    @Delete("delete from class where classId=#{id}")
//    public void deleteClass(Integer id);
//    @Delete("delete from classtocourse where classId=#{id}")
//    public void deleteClassToCourse(Integer id);
}
