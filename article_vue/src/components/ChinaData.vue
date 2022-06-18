  <template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="localConfirmAdd" label="本土确诊"> </el-table-column>
    <el-table-column prop="localWzzAdd" label="本土无症状"> </el-table-column>
    <el-table-column prop="confirmAdd" label="确诊病例"> </el-table-column>
    <el-table-column prop="localConfirm" label="现有本土确诊">
    </el-table-column>
    <el-table-column prop="nowLocalWzz" label="现有本土无症状">
    </el-table-column>
    <el-table-column prop="nowConfirm" label="现有确诊病例"> </el-table-column>
    <el-table-column prop="highRiskAreaNum" label="高风险地区">
    </el-table-column>
    <el-table-column prop="mediumRiskAreaNum" label="中风险地区">
    </el-table-column>
    <el-table-column prop="dead" label="累计死亡"> </el-table-column>
    <el-table-column prop="children" label="各省份信息"><a @click="toProvinces()">详情</a> </el-table-column>
  </el-table>
</template>

  <script>
export default {
  data() {
    return {
      tableData: [],
    };
  },
  methods: {
    initData() {
      var data = this.$store.state.QQData.data.diseaseh5Shelf.chinaTotal;
      this.tableData.push(data);
    },
    toProvinces(){
      this.$router.push('/province')
    }
  },
  mounted() {
    setTimeout(()=>{
      this.initData();
    },1000)
  },
};
</script>