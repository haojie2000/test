package com.ncu.edu.dao;

import com.ncu.edu.pojo.Return;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReturnDao {
        int save(Return date);
        List<Return> selectAll();
        Return selectById(@Param("id") Integer id);
        List<Return> Limit(@Param("page") Integer page,@Param("offset") Integer offset);
        int getCount();
        List<Return> getByDate(@Param("date")String date);
}
