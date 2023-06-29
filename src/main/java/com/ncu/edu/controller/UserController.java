package com.ncu.edu.controller;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Param;
import com.ncu.edu.pojo.User;
import com.ncu.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;

import javax.servlet.http.HttpSession;

@RestController
@SessionAttributes(value = {"role","userId","userName"})
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public ModelAndView login(String username,String password){
        CommonResult<User> result = userService.login(username, password);
        int code = result.getCode();

        ModelAndView mav = new ModelAndView();
        if(code==200){
            User user=result.getData();
            if (!user.getRoleId().equals("3")){
                mav.setViewName("main");
            }else {
                mav.setViewName("checkstand2");
            }
            mav.addObject("role",user.getRoleId());
            mav.addObject("userId",user.getUserId());
            mav.addObject("userName",user.getUserName());
            return mav;
        }else {
            mav.setViewName("index");
            mav.addObject("message","用户名密码错误");
            return mav;
        }
    }

    @RequestMapping("/test")
    public String test(HttpSession session){
        String role = (String) session.getAttribute("role");
        String userId=(String) session.getAttribute("userId");
        if(userId==null){
            return "index";
        }
        System.out.println(role);
        return role;
    }
    @PostMapping("/user/changePassword")
    @ResponseBody
    CommonResult changePassword(@SessionAttribute("userId")String userId,@RequestBody Param param){
        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        final CommonResult result = userService.change(userId, newPassword, oldPassword);
        return result;
    }
}
