package com.chill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.common.Result;
import com.chill.dto.LoginFormDTO;
import com.chill.dto.UserDTO;
import com.chill.entity.User;
import com.chill.mapper.UserMapper;
import com.chill.service.UserService;
import com.chill.util.RedisConstants;
import com.chill.util.RegexUtils;
import com.chill.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendMsg(String phone) {
        //截取手机号
        String phone1 = phone.substring(10,phone.length()-2);
        //1.校验手机号，写好了正则表达式工具
        if(RegexUtils.isPhoneInvalid(phone1)){
            //2.如果不符合，返回错误信息
            return Result.error("手机号格式错误");
        }
        //3.符合生成验证码,模拟验证码,阿里那个不搞了
        String code = RandomUtil.randomNumbers(6);

        //4.保存验证码到redis
        //2分钟有效
        stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + phone1,code,RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);

        //5.发送验证码
        log.info("发送短信验证码成功，验证码：{}",code);
        return Result.success("success");
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO, HttpServletRequest request) {
        String phone = loginFormDTO.getPhone();
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.error("手机号格式错误");
        }

        String cacheCode = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY+phone);
        String code = loginFormDTO.getCode();
        if (cacheCode == null || !cacheCode.equals(code)){
            //3.不一致报错
            return Result.error("验证码错误");
        }
        //4.一致，根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //5.判断用户是否存在
        if (user==null){
            //6.不存在，创建新用户
            user = createUserWithPhone(phone);
        }
        request.getSession().setAttribute("user",user.getId());

        return Result.success("success");
    }

    private User createUserWithPhone(String phone){
        User user = new User();
        user.setPhone(phone);
        user.setName("user_" + RandomUtil.randomString(10));
        save(user);
        return user;
    }
}
