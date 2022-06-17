  <template>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="confirm"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="aaa"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="vvv"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="fff"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="ddd"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="dassste"
        label="日期">
      </el-table-column>
      <el-table-column
        prop="daaaate"
        label="日期">
      </el-table-column>
      
    </el-table>
  </template>

  <script>
    export default {
      data() {
        return {
          tableData: []
        }
      },
      
    }
  </script>