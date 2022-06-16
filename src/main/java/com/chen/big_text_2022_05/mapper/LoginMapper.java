package com.chen.big_text_2022_05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.big_text_2022_05.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ChenHC
 * @Date: 2022/5/7 21:56
 */
@Mapper
public interface LoginMapper extends BaseMapper<User> {
}
