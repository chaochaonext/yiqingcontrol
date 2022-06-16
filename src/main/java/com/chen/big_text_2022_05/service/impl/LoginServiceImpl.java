package com.chen.big_text_2022_05.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.big_text_2022_05.entity.UserLoginLog;
import com.chen.big_text_2022_05.entity.User;
import com.chen.big_text_2022_05.entity.UserLogin;
import com.chen.big_text_2022_05.entity.result.UserLoginResult;
import com.chen.big_text_2022_05.exception.Assert;
import com.chen.big_text_2022_05.mapper.LoginLogMapper;
import com.chen.big_text_2022_05.mapper.LoginMapper;
import com.chen.big_text_2022_05.result.ResponseEnum;
import com.chen.big_text_2022_05.service.LoginService;
import com.chen.big_text_2022_05.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Author: ChenHC
 * @Date: 2022/5/8 12:04
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper,User> implements LoginService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public UserLoginResult login(UserLogin userLogin, String ip) {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        //用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.
                eq("username",username).
                eq("password",password);
        User user = loginMapper.selectOne(userQueryWrapper);
        Assert.notNull(user, ResponseEnum.LOGIN_MOBILE_ERROR);
        String name = user.getName();

        //记录登录日志
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUsername(username);
        loginLog.setLoginDate(new Date());
        loginLog.setIp(ip);
        loginLogMapper.insert(loginLog);
        //生成Token
        String token = JwtUtils.createToken(user.getId(), user.getName());
        //返回结果
        UserLoginResult userLoginResult = new UserLoginResult();
        userLoginResult.setUsername(username);
        userLoginResult.setPassword(password);
        userLoginResult.setName(name);
        userLoginResult.setToken(token);
        return userLoginResult;
    }
}
