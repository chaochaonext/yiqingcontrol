package com.chen.big_text_2022_05.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ChenHC
 * @Date: 2022/5/9 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("login_log")
public class UserLoginLog {
    private long id;
    private String username;
    private Date loginDate;
    private String ip;

}
