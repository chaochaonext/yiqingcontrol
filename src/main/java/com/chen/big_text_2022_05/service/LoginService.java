package com.chen.big_text_2022_05.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.big_text_2022_05.entity.User;
import com.chen.big_text_2022_05.entity.UserLogin;
import com.chen.big_text_2022_05.entity.result.UserLoginResult;

/**
 * @Author: ChenHC
 * @Date: 2022/5/8 12:03
 */
public interface LoginService extends IService<User> {
    UserLoginResult login(UserLogin userLogin, String ip);
}
