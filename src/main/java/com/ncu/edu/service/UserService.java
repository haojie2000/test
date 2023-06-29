package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CommonResult<User> login(String username,String password);
    CommonResult getAll(Integer offset,Integer size);
    CommonResult getCount();
    CommonResult add(User user);
    CommonResult update(User user);
    CommonResult delete(String userId);
    CommonResult get(String con);
    CommonResult change(String userId,String newPass,String oldPass);
}
