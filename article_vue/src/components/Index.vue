<template>
  <div class="common-layout" style="background-color: #66b2ff">
    <el-container>
      <el-header height="auto"><china-data></china-data></el-header>
      <el-main><Map></Map></el-main>
    </el-container>
  </div>
</template>

<script>
import Map from "./Map.vue";
import { postRequest, getRequest } from "@/utils/api";
import chinaData from "./ChinaData.vue";
import store from "../store/index";

export default {
  components: { Map, chinaData },
  data() {
    return {
      QQData: {},
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
    },
  },
  mounted() {
    this.initData();
  },
};
</script>

<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
</style>