package com.dell.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface LoginMapper {
    @Select("select managerPass from managers where managerName=#{uname}")
    public String getPassByName(String uname);
    @Select("select sPass from student where sId=#{id}")
    public String getStudentById(String id);
    @Select("select tPass from teacher where tId=#{tId}")
    public String getTeacherById(String tId);
}
