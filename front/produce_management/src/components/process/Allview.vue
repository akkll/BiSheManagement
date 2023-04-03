<template>
    <el-table
      :data="tableData"
      style="width: 100%;height:100%;background: #FFF;
            border: 1px solid #DCDFE6;
            box-shadow: 0 2px 4px 0 rgb(0 0 0 / 12%), 0 0 6px 0 rgb(0 0 0 / 4%);">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
</template>

<script>
    export default {
        name:'AllView'
    }
</script>

<style>

</style>