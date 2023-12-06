package com.jessie.mapper;

import com.jessie.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept")
    List<Dept> getDepts();

    @Delete("delete from dept where id = #{id}")
    void deleteDeptByID(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insertDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept getDeptById(Integer id);
}
