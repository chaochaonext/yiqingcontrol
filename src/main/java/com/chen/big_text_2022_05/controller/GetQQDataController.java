package com.chen.big_text_2022_05.controller;

import com.chen.big_text_2022_05.config.yiqingdata.CollectQQData;
import com.chen.big_text_2022_05.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: ChenHC
 * @Date: 2022/6/16 13:21
 */
@RestController
@RequestMapping("/sms")
public class GetQQDataController {

    @PostMapping("/getQQData")
    public R getQQData(HttpServletRequest request) throws IOException {
        return CollectQQData.getAllData();
    }
}
