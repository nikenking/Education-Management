package com.dell.springboot.Service;

import com.dell.springboot.entities.*;
import com.dell.springboot.mapper.ClassMapper;
import com.dell.springboot.mapper.StudentMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {
    @Autowired
    ClassMapper cm;
    public List<String> getAllClass(){
        return  cm.getAllClass();
    }
    public List<Course> getAllCourses(Integer id){
        return cm.getAllCourses(id);
    }
    public List<Integer> getAllCourseId(Integer id){
        List<Course> allCourses = getAllCourses(id);
        List<Integer> res = new ArrayList<>();
        for (Course allCours : allCourses) {
            res.add(allCours.cId);
        }
        return res;
    }
    public List<Classes> getClassesById(String id){
        return cm.getClassesById(id);
    }
    public void addClasses(Classes classes){
        cm.addClasses(classes);
    }
    public void addRelation(ClassToCourse c){
        cm.addRelation(c);
    }
    public List<Integer> getMaxRelationId(){
        return cm.getMaxRelationId();
    }
    public void deleteClass(String id){
        cm.deleteClass(Integer.parseInt(id));
    }
    public void deleteClassToCourse(String id){
        cm.deleteClassToCourse(Integer.parseInt(id));
    }
}
