package com.ncu.edu.dao;

import com.ncu.edu.pojo.ReturnApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReturnApplicationDao {
    List<ReturnApplication> selectAll();
    ReturnApplication selectById(@Param("id") String id);
    int save(ReturnApplication returnApplication);
    int update(@Param("returnApplicationId") String id);
    void selectLimit(@Param("page") Integer page,@Param("offset") Integer offset);
}
