package com.dell.springboot.mapper;

import com.dell.springboot.entities.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {
    @Select("select * from class")
    public List<String> getAllClass();
    @Select("select * from course where courseId in (select courseId from classtocourse where classId=#{id})")
    public List<Course> getAllCourses(Integer id);
    @Select("select * from class where classId=#{tId}")
    public List<Classes> getClassesById(String tId);
    @Insert("insert into class(classId) values(#{c.cId})")
    public void addClasses(@Param("c") Classes classes);
    @Insert("insert into classtocourse(id,classId,courseId) values(#{c.id},#{c.cId},#{c.courseId})")
    public void addRelation(@Param("c")ClassToCourse classToCourse);
    @Select("select Max(id) id from classtocourse")
    public List<Integer> getMaxRelationId();
    @Delete("delete from class where classId=#{id}")
    public void deleteClass(Integer id);
    @Delete("delete from classtocourse where classId=#{id}")
    public void deleteClassToCourse(Integer id);

}
