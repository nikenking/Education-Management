package com.dell.springboot.Util;

import java.util.regex.Pattern;

public class CheckInput {
    public static boolean checkStudent(String id,String name){
        return Pattern.matches("17310320[1-3]\\d\\d", id) && Pattern.matches("[\\u4e00-\\u9fa5]{2,4}", name);
    }
    public static boolean checkTeacher(String name){
        return Pattern.matches("[\\u4e00-\\u9fa5]{2,4}", name);
    }

}
