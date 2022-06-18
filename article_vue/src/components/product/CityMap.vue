<template>
  <div class="animated fadeIn">
    <div>{{}}</div>
    <!-- <Row> -->
    <div id="main"></div>
    <!-- </Row> -->
  </div>
</template>
<script>
import echarts from "echarts";
import store from "../../store/index";
import { pinyin } from "pinyin-pro";
import {postRequest} from '@/utils/api'
// import "echarts/map/js/province/liaoning.js";
//引入地图JSON文件
import liaoning from "echarts/map/json/province/liaoning.json";
import taiwan from "echarts/map/json/province/taiwan.json";
import xianggang from "echarts/map/json/province/xianggang.json";
import neimenggu from "echarts/map/json/province/neimenggu.json";
import beijing from "echarts/map/json/province/beijing.json";
import tianjin from "echarts/map/json/province/tianjin.json";
import shanghai from "echarts/map/json/province/shanghai.json";
import jilin from "echarts/map/json/province/jilin.json";
import sichuan from "echarts/map/json/province/sichuan.json";
import fujian from "echarts/map/json/province/fujian.json";
import guangdong from "echarts/map/json/province/guangdong.json";
import shanxi from "echarts/map/json/province/shanxi.json";
import zhejiang from "echarts/map/json/province/zhejiang.json";
import yunnan from "echarts/map/json/province/yunnan.json";
import chongqing from "echarts/map/json/province/chongqing.json";
import hunan from "echarts/map/json/province/hunan.json";
import guangxi from "echarts/map/json/province/guangxi.json";
import shandong from "echarts/map/json/province/shandong.json";
import heilongjiang from "echarts/map/json/province/heilongjiang.json";
import jiangsu from "echarts/map/json/province/jiangsu.json";
import hebei from "echarts/map/json/province/hebei.json";
import hubei from "echarts/map/json/province/hubei.json";
import shanxi1 from "echarts/map/json/province/shanxi1.json";
import henan from "echarts/map/json/province/henan.json";
import aomen from "echarts/map/json/province/aomen.json";
import ningxia from "echarts/map/json/province/ningxia.json";
import anhui from "echarts/map/json/province/anhui.json";
import xizang from "echarts/map/json/province/xizang.json";
import xinjiang from "echarts/map/json/province/xinjiang.json";
import qinghai from "echarts/map/json/province/qinghai.json";
import gansu from "echarts/map/json/province/gansu.json";
import hainan from "echarts/map/json/province/hainan.json";
import guizhou from "echarts/map/json/province/guizhou.json";
import jiangxi from "echarts/map/json/province/jiangxi.json";
//注册地图
echarts.registerMap("liaoning",liaoning)
echarts.registerMap("taiwan",taiwan)
echarts.registerMap("xianggang",xianggang)
echarts.registerMap("neimenggu",neimenggu)
echarts.registerMap("beijing",beijing)
echarts.registerMap("tianjin",tianjin)
echarts.registerMap("shanghai",shanghai)
echarts.registerMap("jilin",jilin)
echarts.registerMap("sichuan",sichuan)
echarts.registerMap("fujian",fujian)
echarts.registerMap("guangdong",guangdong)
echarts.registerMap("shanxi",shanxi)
echarts.registerMap("shanxi1",shanxi1)
echarts.registerMap("zhejiang",zhejiang)
echarts.registerMap("yunnan",yunnan)
echarts.registerMap("chongqing",chongqing)
echarts.registerMap("hunan",hunan)
echarts.registerMap("guangxi",guangxi)
echarts.registerMap("shandong",shandong)
echarts.registerMap("heilongjiang",heilongjiang)
echarts.registerMap("jiangsu",jiangsu)
echarts.registerMap("hebei",hebei)
echarts.registerMap("hubei",hubei)
echarts.registerMap("henan",henan)
echarts.registerMap("aomen",aomen)
echarts.registerMap("ningxia",ningxia)
echarts.registerMap("anhui",anhui)
echarts.registerMap("xizang",xizang)
echarts.registerMap("xinjiang",xinjiang)
echarts.registerMap("qinghai",qinghai)
echarts.registerMap("gansu",gansu)
echarts.registerMap("hainan",hainan)
echarts.registerMap("guizhou",guizhou)
echarts.registerMap("jiangxi",jiangxi)


// 加载echarts下的所有包
const context = require.context("echarts/map/js/province/", true, /\.js$/);
context.keys().forEach((path) => {
  if (path !== "echarts/map/js/province/index.js") {
    // 加载此模块
    context(path);
  }
});

export default {
  data() {
    return {
      nowConfirm: [],
    };
  },
  store,
  methods: {
    //初始化地图
    buildMap() {
      let myChart = echarts.init(document.getElementById("main"));
      let option = {
        tooltip: {
          formatter: function (params, ticket, callback) {
            return (
              params.seriesName + "<br />" + params.name + "：" + params.value
            );
          }, //数据格式化
        },
        visualMap: {
          min: 0,
          max: 1500,
          left: "left",
          top: "bottom",
          text: ["高", "低"], //取值范围的文字
          inRange: {
            color: ["#fff4e6", "#dd2002"], //取值范围的颜色
          },
          show: true, //图注
        },
        geo: {
          map:pinyin(this.$route.query.name, { toneType: 'none' }).replace(/\s*/g,""),
          //map:pro,
          roam: false, //不开启缩放和平移
          zoom: 1.23, //视角缩放比例
          label: {
            normal: {
              show: true,
              fontSize: "10", //注意：地图省份名字字体如果过大会导致字体重叠
              color: "rgba(0,0,0,0.7)",
            },
          },
          itemStyle: {
            normal: {
              borderColor: "rgba(0, 0, 0, 0.2)",
            },
            emphasis: {
              areaColor: "#F3B329", //鼠标选择区域颜色
              shadowOffsetX: 0,
              shadowOffsetY: 0,
              shadowBlur: 20,
              borderWidth: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
        series: [
          {
            name: "现有病例",
            type: "map",
            geoIndex: 0,
            // 此处为展示数据的位置
            data: this.nowConfirm,
          },
        ],
      };
      myChart.setOption(option);
    },
    //初始化数据
    initData() {
      postRequest("/sms/getQQData", this.user).then((resp) => {
        if (resp) {
          if (resp.obj == false) {
            Message.warning({ message: resp.msg });
          } else {
            var children = resp.data.data.diseaseh5Shelf.areaTree[0].children[this.$route.query.index].children;
            for (var i = 0; i < children.length; i++) {
              var name = children[i].name;
              if(name.substr(name.length-1,1) != "市" && name.substr(name.length-1,1) != "区" ){
                name = name + "市"
              }
              var value = children[i].total.nowConfirm;
              this.nowConfirm.push({ name, value });
            }
            console.log(this.nowConfirm)
            this.buildMap();
          }
        } else {
          return false;
        }
      });
    },
  },
  mounted() {
    setTimeout(() => {
      this.initData();
      this.buildMap();
    }, 2000);
  },
};
</script>
 
<style scoped>
* {
  margin: 0;
  padding: 0;
}
html,
body {
  width: 100%;
  height: 100%;
}
#main {
  width: 1080px;
  height: 900px;
  margin: 0px auto;
  border: none;
}
</style>