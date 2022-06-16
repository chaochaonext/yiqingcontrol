<template>
    <div class="login">
        <div class="box">
            <el-input v-model="user" label="用户名：" placeholder="请输入用户名"></el-input>
            <el-input v-model="password" label="密码：" show-password placeholder="请输入密码"></el-input>
            <div>
              <el-button type="primary" @click="login()">登录</el-button>
              <el-button type="primary" @click="register()">注册</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import {postRequest} from "../utils/api"
export default {
  data() {
    return {
      user: '',
      password: '',
      code:''
    }
  },
  methods:{
    login(){
      postRequest("/sms/login", 
        this.user
      ).then((resp) => {
        if (resp) {
          if (resp.obj == false) {
            Message.warning({ message: resp.msg });
          } else {
            console.log(resp)
            this.$router.push("Main")
          }
        } else {
          return false;
        }
      });
    },
    register(){
      postRequest("/sms/register", 
        this.user
      ).then((resp) => {
        if (resp) {
          if (resp.obj == false) {
            Message.warning({ message: resp.msg });
          } else {
            console.log(resp)
          }
        } else {
          return false;
        }
      });
    },
  }
}
</script>
<style scoped>
  @import "../../static/css/login.css";
</style>