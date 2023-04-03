<template>
  <div class="materialInfo-father">
    <el-table
      :row-style="{height:'56px'}"
      v-if="tableData!=[]"
      :data="tableData"
      height="95%"
      style="width: 100%;">
      <el-table-column
        prop="id"
        label="原料id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="原料名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="type"
        label="原料种类"
        width="180">
      </el-table-column>
      <el-table-column
        prop="scale"
        label="原料规模"
        width="180">
      </el-table-column>
      <el-table-column
        prop="number"
        label="原料数量(斤)"
        width="180">
        <template slot-scope="props">
          {{props.row.number+'斤'}}
        </template>
      </el-table-column>
    </el-table>
    <el-pagination v-if="tableData!=undefined&&tableData!=null"
        background
        layout="prev, pager, next, sizes, total, jumper"
        :total="total"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pagenum"
        :current-page="pageno"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        >
    </el-pagination>
  </div>
</template>

<script>
  import axios from 'axios'
    export default {
        name:'MaterialInfo',
        data() {
            return {
              tableData: [],
              total:0,
              currentPage:1,
              pageno:1,
              pagenum:10
            }
        },
        methods:{
          changePageNum(value){
            this.pagenum=value;
          },
          changePageNo(value){
            this.pageno=value;
          },
          handleSizeChange(value){
            this.changePageNum(value)
          },
          handleCurrentChange(value){
            this.changePageNo(value)
          },
          deWeight(arr) {
              for (var i = 0; i < arr.length - 1; i++) {
                  for (var j = i + 1; j < arr.length; j++) {
                      if (arr[i].text == arr[j].text) {
                          arr.splice(j, 1);
                          j--;
                      }
                  }
              }
              return arr;
          },
        },
        mounted(){
          let that = this;
          axios({
            method:'get',
            url:'/material/getMaterialTotal'
          }).then(function(response){
            that.total=response.data.data;
          })
        },
        watch:{
          pageno:{
            handler:function(){
              let that = this;
              axios({
                method:'get',
                url:'/material/getMaterial',
                params:{
                  pageno:that.pageno,
                  pagenum:that.pagenum,
                }
              }).then(function(response){
                that.tableData=response.data.data;
              })
            },
            immediate:true
          },
          pagenum:{
            handler:function(){
              let that = this;
              axios({
                method:'get',
                url:'/material/getMaterial',
                params:{
                  pageno:that.pageno,
                  pagenum:that.pagenum,
                }
              }).then(function(response){
                that.tableData=response.data.data;
              })
            },
            immediate:true
          }
        }
    }
</script>

<style scoped>
  .materialInfo-father{
      width: 100%;
      height: 100%;
      overflow: hidden;
  }
</style>