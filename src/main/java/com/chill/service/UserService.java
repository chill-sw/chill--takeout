package com.chill.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.dto.LoginFormDTO;
import com.chill.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface UserService extends IService<User> {
    Result sendMsg(String phone);

    Result login(LoginFormDTO loginFormDTO, HttpServletRequest request);
}
