package com.ncu.edu.service.impl;

import com.ncu.edu.dao.UserDao;
import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.User;
import com.ncu.edu.service.UserService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.color.CMMException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private SnowflakeUtil snowflakeUtil=new SnowflakeUtil();
    @Override
    public CommonResult<User> login(String username, String password) {
        String userId = userDao.login(username, password);
        if(userId!=null && !userId.equals("")){
            System.out.println("test=====>1");
            User user = userDao.selectById(userId);
            return new CommonResult<User>(200,"用户存在",user);
        }else {
            System.out.println("test=====>2");
            return new CommonResult(400,"用户不存在");
        }
    }

    @Override
    public CommonResult getAll(Integer offset, Integer size) {
        List<User> users = userDao.getAll(offset, size);
        if(users!=null){
            return new CommonResult(200,"查询成功",users);
        }else {
            return new CommonResult(444,"没有数据");
        }
    }

    @Override
    public CommonResult getCount() {
        final int count = userDao.getCount();
        return new CommonResult(200,count);
    }

    @Override
    public CommonResult add(User user) {
        final String userId = snowflakeUtil.snowflakeId();
        user.setUserId(userId);
        final int i = userDao.add(user);
        if(i>0){
            return new CommonResult(200,"添加成功");
        }else {
            return new CommonResult(444,"网络故障，请稍后再试");
        }
    }

    @Override
    public CommonResult update(User user) {
        int i = userDao.selectByUserName(user.getUserName());
        if (i>0){
            int update = userDao.update(user);
            if(update>0){
                return new CommonResult(200,"修改成功");
            }else {
                return new CommonResult(444,"修改失败");
            }
        }else {
            return new CommonResult(555,"用户名重复");
        }
    }

    @Override
    public CommonResult delete(String userId) {
        final int i = userDao.delete(userId);
        if(i>0){
            return new CommonResult(200,"删除成功");
        }else {
            return new CommonResult(444,"删除失败");
        }
    }

    @Override
    public CommonResult get(String con) {
        final List<User> users = userDao.getByCondition(con);
        if(!users.isEmpty()){
            return new CommonResult(200,"查询成功",users);
        }else {
            return new CommonResult(444,"查询失败");
        }
    }

    @Override
    public CommonResult change(String userId, String newPass,String oldPass) {
        int i = userDao.changePassword(userId, newPass,oldPass);
        if(i>0){
            return new CommonResult(200,"修改成功");
        }else {
            return new CommonResult(444,"修改失败");
        }

    }
}
