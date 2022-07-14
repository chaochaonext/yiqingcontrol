package com.chen.big_text_2022_05.controller;

import com.chen.big_text_2022_05.entity.UserLogin;
import com.chen.big_text_2022_05.entity.result.UserLoginResult;
import com.chen.big_text_2022_05.exception.Assert;
import com.chen.big_text_2022_05.result.R;
import com.chen.big_text_2022_05.result.ResponseEnum;
import com.chen.big_text_2022_05.service.LoginService;
import com.chen.big_text_2022_05.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 *      用户登录选项
 * </p>
 *
 * @Author: ChenHC
 * @Date: 2022/5/8 11:07
 */
@RestController
@RequestMapping("/sms")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R login(HttpServletRequest request){
        return R.ok();
    }

    @GetMapping("/login")
    public R login(@RequestBody UserLogin userLogin, HttpServletRequest request){
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        String code = userLogin.getCode();
        //判断用户名、密码、验证码是否为空
        Assert.notEmpty(username, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        Assert.notEmpty(code,ResponseEnum.CODE_NULL_ERROR);
        //把密码进行MD5加密
        password = MD5.encrypt(password);
        //从缓存中获取验证码的值
        String codeTwo = (String)redisTemplate.opsForValue().get("com:chen:big_text:"+username);
        Assert.equals(code,codeTwo,ResponseEnum.CODE_ERROR);
        //获取登录ip
        String ip = request.getRemoteAddr();
        //判断登录信息是否正确
        UserLoginResult userLoginResult = loginService.login(userLogin,ip);

        return R.ok().data("user",userLoginResult);
    }
}
