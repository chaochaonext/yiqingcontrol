<template>
    <el-container style="background-color: #66b2ff">
        <el-header height="auto"><china-data></china-data></el-header>
        <el-main><Map></Map></el-main>
        <el-footer>Footer</el-footer>
    </el-container>
</template>

<script>
import Map from './Map.vue'
import chinaData from './ChinaData.vue'
export default {
    components:{ Map , chinaData },
}
</script>