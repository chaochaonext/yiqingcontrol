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
 * @Date: 2022/6/13 17:39
 */
@Configuration
public class CollectQQData {
    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400";

    public static R getAllData() throws IOException {
        String resultBody = Jsoup.connect("https://view.inews.qq.com/g2/getOnsInfo?name=disease_other")
                .ignoreContentType(true)
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(resultBody);
        //获取data部分
        JSONObject data = jsonObject.getJSONObject("data");
        //获取其中往日疫情数据chinaDayAddList
        JSONArray chinaDayAddList = data.getJSONArray("chinaDayAddList");
        //将往日的数据存在Map中 chinaLastAdd
        Map<String, Object> chinaLastAdd = new HashMap<>();
        for (int i = 0;i < chinaDayAddList.size();i++) {
            JSONObject currentDateData = chinaDayAddList.getJSONObject(i);
            //日期
            String date = currentDateData.getString("date");
            //本土确诊
            int localConfirmadd = currentDateData.getIntValue("localConfirmadd");
            //康复人数
            double heal = currentDateData.getDoubleValue("heal");
            //输入病例
            int importedCase = currentDateData.getIntValue("importedCase");
            //死亡人数
            int dead = currentDateData.getIntValue("dead");
            // 确诊病例
            int confirm = currentDateData.getIntValue("confirm");
            //猜测
            int suspect = currentDateData.getIntValue("suspect");
            //
            int infect = currentDateData.getIntValue("infect");
            // 本土无症状
            int localinfectionadd = currentDateData.getIntValue("localinfectionadd");
            //
            double deadRate = currentDateData.getDoubleValue("deadRate");
            //
            double healRate = currentDateData.getDoubleValue("healRate");
            Map<String,Object> dateData = new HashMap<>();
            dateData.put("date",date);
            dateData.put("localConfirmadd",localConfirmadd);
            dateData.put("heal",heal);
            dateData.put("importedCase",importedCase);
            dateData.put("dead",dead);
            dateData.put("confirm",confirm);
            dateData.put("suspect",suspect);
            dateData.put("infect",infect);
            dateData.put("localinfectionadd",localinfectionadd);
            dateData.put("deadRate",deadRate);
            dateData.put("healRate",healRate);
            chinaLastAdd.put(date,dateData);
        }
        //将各省份的数据存入  provincesCompareMap
        JSONObject provincesCompare = data.getJSONObject("provinceCompare");
        Map<String,Object> provincesCompareMap = new HashMap<>();
        for (int i = 1;i <= 34;i++){
            JSONObject provinceCompare = provincesCompare.getJSONObject(ProvinceEnum.getName(i));
            int zero = provinceCompare.getIntValue("zero");
            int confirmAdd = provinceCompare.getIntValue("confirmAdd");
            int heal = provinceCompare.getIntValue("heal");
            int nowConfirm = provinceCompare.getIntValue("nowConfirm");
            int dead = provinceCompare.getIntValue("dead");
            Map<String,Integer> provinceCompareMap = new HashMap<>();
            provinceCompareMap.put("zero",zero);
            provinceCompareMap.put("confirmAdd",confirmAdd);
            provinceCompareMap.put("heal",heal);
            provinceCompareMap.put("nowConfirm",nowConfirm);
            provinceCompareMap.put("dead",dead);
            provincesCompareMap.put(ProvinceEnum.getName(i),provinceCompareMap);
        }
        //各个城市之间的数据存入  citysDetailMap
        JSONArray statisGradeCityDetail = data.getJSONArray("statisGradeCityDetail");
        Map<String,Object> citysDetailMap = new HashMap<>();
        for (int i = 0;i < statisGradeCityDetail.size();i++){
            JSONObject cityDetailJSONObject = statisGradeCityDetail.getJSONObject(i);
            Map<String,Object> cityDetailMap = new HashMap<>();
            //省份
            String province = cityDetailJSONObject.getString("province");
            //城市
            String city = cityDetailJSONObject.getString("city");
            //截止日期
            Date mtime = cityDetailJSONObject.getDate("mtime");
            //确诊病例
            int confirm = cityDetailJSONObject.getIntValue("confirm");
            //死亡人数
            int dead = cityDetailJSONObject.getIntValue("dead");
            // 本土确诊
            int confirmAdd = cityDetailJSONObject.getIntValue("confirmAdd");
            //治愈人数
            int heal = cityDetailJSONObject.getIntValue("heal");
            //当前病例
            int nowConfirm = cityDetailJSONObject.getIntValue("nowConfirm");
            //无症状
            int wzz = cityDetailJSONObject.getIntValue("wzz");
            cityDetailMap.put("province",province);
            cityDetailMap.put("city",city);
            cityDetailMap.put("mtime",mtime);
            cityDetailMap.put("confirm",confirm);
            cityDetailMap.put("dead",dead);
            cityDetailMap.put("confirmAdd",confirmAdd);
            cityDetailMap.put("heal",heal);
            cityDetailMap.put("nowConfirm",nowConfirm);
            cityDetailMap.put("wzz",wzz);
            citysDetailMap.put(city,cityDetailMap);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("chinaLastAdd",chinaLastAdd);
        resultMap.put("provincesCompareMap",provincesCompareMap);
        resultMap.put("citysDetailMap",citysDetailMap);

        return R.ok().data(chinaLastAdd).data(provincesCompareMap).data(citysDetailMap);
    }
}
