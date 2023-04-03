<template>
    <div class="materialGet-father">
    <el-table
      :row-style="{height:'56px'}"
      v-if="tableData!=[]"
      :data="tableData"
      height="95%"
      style="width: 100%;">
      <el-table-column
      fixed
        prop="id"
        label="领料id"
        width="180">
      </el-table-column>
      <el-table-column
        fixed
        prop="materialid"
        label="原料id"
        :filters="filters"
        :filter-method="filterHandler"
        width="180">
      </el-table-column>
      <el-table-column
        fixed
        prop="materialname"
        label="原料名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="materialtype"
        label="原料类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="materialscale"
        label="原料规格"
        width="180">
        <template slot-scope="props">
          {{props.row.materialscale==null?'暂无描述':props.row.materialscale}}
        </template>
      </el-table-column>
      <el-table-column
        prop="unit"
        label="领料单位"
        :filters="[{'text':'第1车间','value':1},{'text':'第2车间','value':2}]"
        :filter-method="filterHandler"
        width="180">
          <template slot-scope="props">
              {{'第'+props.row.unit+'车间'}}
          </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="原料数量(斤)"
        width="180">
        <template slot-scope="props">
          {{props.row.number+'斤'}}
        </template>
      </el-table-column>
      <el-table-column
          prop="date"
          label="领料时间"
          width="180">
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
        name:'MaterialGet',
        data() {
            return {
              tableData: [],
              total:0,
              currentPage:1,
              pageno:1,
              pagenum:10,
              filters:[]
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
          setFilters(){
            let filter=[];
            for(let i=0;i<this.tableData.length;i++){
              filter.push({'text':this.tableData[i]['materialname']+'-'+this.tableData[i]['materialtype']+'-'+this.tableData[i]['materialscale'],'value':this.tableData[i]['materialid']});
            }
            this.filters=this.deWeight(filter);
            console.log(this.filters)
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
          filterHandler(value, row, column) {
            const property = column['property'];
            return row[property] === value;
          }
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
                url:'/material/getMaterialGetMaterial',
                params:{
                  pageno:that.pageno,
                  pagenum:that.pagenum,
                }
              }).then(function(response){
                that.tableData=response.data.data;
                console.log(that.tableData);
                that.setFilters();
              })
            },
            immediate:true
          },
          pagenum:{
            handler:function(){
              let that = this;
              axios({
                method:'get',
                url:'/material/getMaterialGetMaterial',
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
    .materialGet-father{
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
</style>