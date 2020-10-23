package com.dell.springboot.Service;

import com.dell.springboot.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginMapper lm;
    public boolean LoginCheck(String uname,String upass){
        String pass = lm.getPassByName(uname);
        System.out.println("should be :"+pass);
        return pass != null && pass.equals(upass);
    }
    public String StudentLoginCheck(String id,String upass){
        String pass = lm.getStudentById(id);
        System.out.println("should be :"+pass);
        if (pass != null && pass.equals(upass)){
            return id;
        }else return null;
    }
    public String TeacherLoginCheck(String id,String upass){
        String pass = lm.getTeacherById(id);
        System.out.println("should be :"+pass);
        if (pass != null && pass.equals(upass)){
            return id;
        }else return null;
    }

}
