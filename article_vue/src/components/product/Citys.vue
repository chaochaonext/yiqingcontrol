<template>
  <div class="common-layout" style="background-color: #66b2ff">
    <el-container>
      <el-header height="auto"><City></City></el-header>
      <el-main><city-map></city-map></el-main>
    </el-container>
  </div>
</template>

<script>
import City from './City.vue'
import CityMap from './CityMap.vue'
export default {
  components:{ City , CityMap }
}
</script>