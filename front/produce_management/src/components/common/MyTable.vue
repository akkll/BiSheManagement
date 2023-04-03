<template>
  <div class="table-father">
    <el-table
      :data="myAllData"
      height="100%"
      :row-style="{height:'100px'}"
      ref="mytables"
      style="width: 100%;height:100%;">
      <el-table-column v-if="isCheck"
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column v-for="(mydata ,index) in myAllData.column" :key="index"
        :fixed="myfixed[index]"
        :prop="myAllData.column[index]"
        :label="myAllData.columnName[index]"
        :width="mywidth[index]"
        >
      </el-table-column>
      <el-table-column
        v-show="myedit"
        fixed="right"
        prop="123456"
        label="编辑"
        width="100px"
        >
        <el-button >修改</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
    import {mapMutations, mapGetters, mapState} from 'vuex'
    import myConfig from '../../dataconfig/tableConfig'
    export default {
        name:'MyTable',
        data() {
          return {
            config:myConfig,
            myAllData:{},
          }
        },
        methods: {
          select(){
            console.log(this.$route.path)
          }
        },
        computed:{
          ...mapState('global',{
            'myAllDatas':'myAllData'
          }),
          configName(){
            var path = this.$route.path;
            var index = path.lastIndexOf('/')

            return path.slice(index+1)
          },
          myfixed(){
            return myConfig[this.configName].fixed;
          },
          isCheck(){
            console.log(myConfig[this.configName])
            return myConfig[this.configName].isCheck;
          },
          mywidth(){
            return myConfig[this.configName].width;
          },
          myedit(){
            return myConfig[this.configName].edit;
          }
        },
        watch:{
          myAllDatas:{
            deep:true,
            handler:function(newValue){
              this.myAllData=this.$store.getters['global/getMyAllData'];
            }
          },
        },
    }
</script>

<style scoped>
    .table-father{
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
    ::-webkit-scrollbar {
      width: 8px; 
    }
    ::-webkit-scrollbar-thumb {
      background-color: #eaecf1;
      border-radius: 3px;
    }
    /* ::-webkit-scrollbar{display:none} */
</style>