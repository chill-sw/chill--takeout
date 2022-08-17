package com.chill.controller;


import com.chill.common.Result;
import com.chill.dto.LoginFormDTO;
import com.chill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("sendMsg")
    public Result sendMsg(@RequestBody String phone){
        return userService.sendMsg(phone);
    }

    @PostMapping("login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO, HttpServletRequest request){
        return userService.login(loginFormDTO,request);
    }
}
