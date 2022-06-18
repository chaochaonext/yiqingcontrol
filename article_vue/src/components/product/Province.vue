<template>
  <el-table :data="tableData" height="700px" style="width: 100%">
    <el-table-column prop="name" label="省份" />
    <el-table-column prop="local_confirm_add" label="本土确诊" />
    <el-table-column prop="wzz_add" label="本土无症状" />
    <el-table-column prop="abroad_confirm_add" label="境外输入" />
    <el-table-column prop="dead_add" label="死亡病例" />
    <el-table-column prop="nowConfirm" label="现有确诊" />
    <el-table-column prop="confirm" label="累计确诊" />
    <el-table-column prop="heal" label="累计治愈" />
    <el-table-column prop="dead" label="累计死亡" />
    <el-table-column prop="highRiskAreaNum" label="高风险地区" />
    <el-table-column prop="mediumRiskAreaNum" label="中风险地区" />
    <el-table-column label="各省信息" 
      ><a @click="toCitys()">详细信息</a></el-table-column
    >
  </el-table>
</template>

<script>
import { postRequest, getRequest } from "@/utils/api";
import store from '../../store/index'

export default {
  data() {
    return {
      tableData: [],
    };
  },
  store,
  methods: {
    initData() {
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
      setTimeout(() => {
        var data = this.$store.state.provinces;
        for (var i = 0; i < data.length; i++) {
          var province = {
            name:data[i].name,
            wzz_add:data[i].today.wzz_add,
            local_confirm_add:data[i].today.local_confirm_add,
            abroad_confirm_add:data[i].today.abroad_confirm_add,
            dead_add:data[i].today.dead_add,
            confirm:data[i].total.confirm,
            mediumRiskAreaNum:data[i].total.mediumRiskAreaNum,
            highRiskAreaNum:data[i].total.highRiskAreaNum,
            nowConfirm:data[i].total.nowConfirm,
            heal:data[i].total.heal,
            dead:data[i].total.dead,
          };
          this.tableData.push(province);
        }
      },500);
    },
    toCitys() {
      this.$router.push("/city");
    },
  },
  mounted() {
    setTimeout(() => {
      this.initData();
    }, 1000);
  },
};
</script>
