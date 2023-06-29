package com.ncu.edu.dao;

import com.ncu.edu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    String login(@Param("username") String username, @Param("password") String password);
    User selectById(@Param("userId") String id);
    List<User> getAll(@Param("offset") Integer offset,@Param("size") Integer size);
    List<User> getByCondition(@Param("con") String con);
    int getCount();
    int add(User user);
    int update(User user);
    int selectByUserName(@Param("userName") String userName);
    int delete(@Param("userId") String userId);
    int changePassword(@Param("userId") String userId,@Param("newPass") String newPass,@Param("oldPass")String oldPass);
}
