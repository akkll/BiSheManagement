<template>
  <div class="table-father">
    <el-table
      :data="myAllData"
      height="95%"
      :row-style="{height:'56px'}"
      style="width: 100%;height:100%;">
        <el-table-column
            prop="id"
            label="ID"
            fixed="left"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            fixed="left"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="title"
            label="职衔"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="ishere"
            label="在职情况"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="workdate"
            label="入职日期"
            width="120px"
            >
        </el-table-column>
        <el-table-column
            prop="departdate"
            label="离职日期"
            width="120px"
            >
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.date==null?'无':scope.row.date }}</span>
                </template>
        </el-table-column>
        <el-table-column
            prop="card"
            label="身份证号码"
            width="180px"
            >
        </el-table-column>
        <el-table-column
            prop="unit"
            label="所属单位"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="liushui"
            label="所属单位"
            width="80px"
            >
        </el-table-column>
        <el-table-column
            prop="birthday"
            label="出生日期"
            width="120px"
            >
        </el-table-column>
        <el-table-column
            prop="address"
            label="居住地"
            width="180px"
            >
        </el-table-column>
        <el-table-column
            prop="mobile"
            label="手机号码"
            width="120px"
            >
        </el-table-column>
        <el-table-column
            prop="wechat"
            label="微信"
            width="180px"
            >
        </el-table-column>
    </el-table>
    <el-pagination v-if="myAllData!=undefined&&myAllData!=null"
        background
        layout="prev, pager, next, sizes, total, jumper"
        :total="total"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="10"
        :current-page="currentPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        >
    </el-pagination>
  </div>
</template>

<script>
    import {mapMutations, mapGetters, mapState, mapActions} from 'vuex'
    export default {
        name:'GetEmployee',
        data() {
          return {
            'currentPage':1
          }
        },
        methods: {
          ...mapMutations('global',{
            'changePageno':'SETPAGENO',
            'changePagenum':'SETPAGENUM',
          }),
          handleSizeChange(value){
              this.changePagenum(value);
          },
          handleCurrentChange(value){
              this.changePageno(value);
          }
        },
        computed:{
          ...mapGetters('global',{
            'myAllData':'getMyAllData',
            'total':'getTotle'
          }),
        },
        mounted(){
          this.changePagenum(10);
          this.changePageno(1);
        }
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
</style>