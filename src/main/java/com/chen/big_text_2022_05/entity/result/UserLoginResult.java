package com.chen.big_text_2022_05.entity.result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ChenHC
 * @Date: 2022/5/9 17:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResult {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String token;
}
