package com.chen.big_text_2022_05.config.yiqingdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chen.big_text_2022_05.result.R;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChenHC
 * @Date: 2022/6/17 7:23
 */
@Configuration
public class QQData {
    public static R getAllData() throws IOException {
        //腾讯疫情数据   https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=localCityNCOVDataList,diseaseh5Shelf
        String resultBody = Jsoup.connect("https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=localCityNCOVDataList,diseaseh5Shelf")
                .ignoreContentType(true)
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(resultBody);
        return R.ok().data(jsonObject);
    }
}
