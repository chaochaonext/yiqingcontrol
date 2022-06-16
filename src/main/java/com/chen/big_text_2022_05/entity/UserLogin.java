package com.chen.big_text_2022_05.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ChenHC
 * @Date: 2022/5/9 12:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {


    private Long id;

    private String username;

    private String password;

    private String code;
}
