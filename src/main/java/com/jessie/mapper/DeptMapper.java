package com.jessie.mapper;

import com.jessie.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, createTime, updateTime from dept")
    List<Dept> getDepts();
}
