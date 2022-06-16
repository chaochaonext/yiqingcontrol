package com.chen.big_text_2022_05.config.yiqingdata;

/**
 * @Author: ChenHC
 * @Date: 2022/6/14 8:32
 */
public enum ProvinceEnum {
     P1("河北"),P2("山西"),P3("辽宁"),P4("吉林"),P5("黑龙江"),P6("江苏"),P7("浙江"),P8("安徽"),P9("福建"),P10("江西"),P11("山东")
    ,P12("河南"),P13("湖北"),P14("湖南"),P15("广东"),P16("海南"),P17("四川"),P18("贵州"),P19("云南"),P20("陕西"),P21("甘肃")
    ,P22("青海"),P23("台湾")
    ,P24("内蒙古"),P25("广西"),P26("西藏"),P27("新疆"),P28("宁夏")
    ,P29("北京"),P30("天津"),P31("上海"),P32("重庆")
    ,P33("香港"),P34("澳门");
    private String province;
    ProvinceEnum(String province){
        this.province = province;
    }
    public static String getName(int n){
        return ProvinceEnum.valueOf("P" + String.valueOf(n)).province;
    }
}
