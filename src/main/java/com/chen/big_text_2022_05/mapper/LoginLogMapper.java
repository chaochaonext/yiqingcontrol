package com.chen.big_text_2022_05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.big_text_2022_05.entity.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ChenHC
 * @Date: 2022/5/9 16:42
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<UserLoginLog> {
}
