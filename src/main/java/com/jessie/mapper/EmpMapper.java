package com.jessie.mapper;

import com.jessie.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp")
    Long getTotal();

    @Select("select * from emp limit #{start}, #{pageSize}")
    List<Emp> getEmps(Integer start, Integer pageSize);
}
