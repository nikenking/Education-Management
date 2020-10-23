package com.dell.springboot.Service;

import com.dell.springboot.entities.Score;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.StudentToCourse;
import com.dell.springboot.mapper.CourseMapper;
import com.dell.springboot.mapper.LoginMapper;
import com.dell.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper sm;

    public List<Student> getAllStudent(){
        return sm.getAllStudent();
    }
    public List<Student> getStudentById(String sId){
        return sm.getStudentById(sId);
    }
    public List<Student> getStudentByIdExac(String sId){
        return sm.getStudentByIdExac(sId);
    }
    public List<Student> getStudentByName(String sName){
        return sm.getStudentByName(sName);
    }
    public void addStudent(Student student){
        sm.addStudent(student);
    };

    public void changeStudent(Student student){
        sm.changeStudent(student);
    };

    public void deleteStudent(String id){
        sm.deleteStudent(id);
    }
    public List<Score> getScore(String id){
        return sm.getScore(id);
    }
    public String getOneScore(StudentToCourse studentToCourse){
        return sm.getOneScore(studentToCourse);
    }
}
