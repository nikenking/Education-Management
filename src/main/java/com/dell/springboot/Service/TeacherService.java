package com.dell.springboot.Service;

import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.Teacher;
import com.dell.springboot.mapper.CourseMapper;
import com.dell.springboot.mapper.StudentMapper;
import com.dell.springboot.mapper.TeacherMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper tm;
    @Autowired
    CourseMapper cm;
    public List<Teacher> getAllTeacher(){
        List<Teacher> allTeacher = tm.getAllTeacher();
        for (Teacher teacher : allTeacher) {
            teacher.setCourseName(cm.getCourseNameById(teacher.getCourseId()));
        }
        return allTeacher;
    }
    public List<Teacher> getTeacherById(String tId){
        List<Teacher> teacherById = tm.getTeacherById(tId);
        for (Teacher teacher : teacherById) {
            teacher.setCourseName(cm.getCourseNameById(teacher.getCourseId()));
        }
        return teacherById;
    }
    public List<Teacher> getTeacherByName(String tName){
        List<Teacher> teacherByName = tm.getTeacherByName(tName);
        for (Teacher teacher : teacherByName) {
            teacher.setCourseName(cm.getCourseNameById(teacher.getCourseId()));
        }
        return teacherByName;
    }
    public void addTeacher(Teacher teacher){
        tm.addTeacher(teacher);
    }

    public void changeTeacher(Teacher teacher){
        tm.changeTeacher(teacher);
    };

    public void deleteTeacher(String id){
        tm.deleteTeacher(id);
    }

    public List<String> getAllClass(String tId){
        return tm.getAllClass(tId);
    }
    public List<Student> getClassStudent(String id){
        return tm.getClassStudent(id);
    }
//    @Select("select classId from teachertoclass where tId=#{tId}")
//    public List<String> getAllClass(String tId);
//    @Select("select * from student WHERE sClassId=#{id}")
//    public List<Student> getClassStudent(String id);
}
