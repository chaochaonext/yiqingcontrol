<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="name" label="省份" />
    <el-table-column prop="local_confirm_add" label="本土确诊" />
    <el-table-column prop="wzz_add" label="本土无症状" />
    <el-table-column prop="nowConfirm" label="现有确诊" />
    <el-table-column prop="confirm" label="累计确诊" />
    <el-table-column prop="heal" label="累计治愈" />
    <el-table-column prop="dead" label="累计死亡" />
  </el-table>
</template>

<script>
import { postRequest, getRequest } from "@/utils/api";
import store from "../../store/index";

export default {
  data() {
    return {
      tableData: [],
    };
  },
  store,
  methods: {
    initData() {
      //像后端发送请求获取省份数据
      postRequest("/sms/getQQData", this.user).then((resp) => {
        if (resp) {
          if (resp.obj == false) {
            Message.warning({ message: resp.msg });
          } else {
            console.log(resp);
            this.QQData = resp.data;
            this.$store.dispatch("initQQData", this.QQData);
          }
        } else {
          return false;
        }
      });
      //使得赋值延后0.5秒
      setTimeout(() => {
        var data = this.$store.state.provinces[this.$route.query.index].children;
        for (var i = 0; i < data.length; i++) {
          var city = {
            index: i,
            name: data[i].name,
            wzz_add: data[i].today.wzz_add,
            local_confirm_add: data[i].today.local_confirm_add,
            confirm: data[i].total.confirm,
            nowConfirm: data[i].total.nowConfirm,
            heal: data[i].total.heal,
            dead: data[i].total.dead,
          };
          this.tableData.push(city);
        }
      }, 500);
    },
    toCitys(index) {
      this.$router.push({ path: "/city", query: { index: index } });
    },
  },
  mounted() {
    // 另初始化数据延后0.5秒
    setTimeout(() => {
      this.initData();
    }, 1000);
  },
};
</script>