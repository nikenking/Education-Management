package com.dell.springboot.Service;

import com.dell.springboot.entities.Course;
import com.dell.springboot.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper cm;
    public String getCourseNameById(Integer id){
        String courseNameById = cm.getCourseNameById(id);
        System.out.println(courseNameById);
        return  courseNameById;
    }
    public List<Course> getAllCourse(){
        return cm.getAllCourse();
    }
    public void addCourse(Course course){
        cm.addCourse(course);
    }

    public List<Course> getCourseById(Integer id){
        return cm.getCourseById(id);
    }

    public void changeCourse(Course course){
        cm.changeCourse(course);
    }
    public List<Integer> getMaxRelationId(){
        return cm.getMaxRelationId();
    }
    public void delCourse(Course course){
        cm.delCourse(course);
    }
    public List<Course> getCourseByName(String name){
        return  cm.getCourseByName(name);
    }
    public List<String> getTeacherName(String courseId){
       return cm.getTeacherName(courseId);
    }

}
