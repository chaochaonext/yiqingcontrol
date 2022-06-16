<template>
    <div class="login">
        <div class="box">
            <el-input v-model="user" label="用户名：" placeholder="请输入用户名"></el-input>
            <el-input v-model="password" label="密码：" show-password placeholder="请输入密码"></el-input>
            <el-input v-model="newPassword" label="密码：" show-password placeholder="请确认密码"></el-input>
            <div>
              <el-button type="primary" @click="register">注册</el-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      user: '',
      password: '',
      newPassword: ''
    }
  },
  methods:{
    register:function(){
    //   if(this.newPassword == this.password){
    //     alert("success")
    //   }else{
    //       alert("please input the same password")
    //   }
      this.$router.push('/login')
    }
  }
}
</script>
<style scoped>
    @import "../../static/css/login.css"
</style>