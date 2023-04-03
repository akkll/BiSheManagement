<template>
  <div class="headfather">
      <div id="head-search">
            <el-input
                placeholder="搜索..."
                v-model="input4"
                size="medium">
                <i slot="prefix" class="el-input__icon el-icon-search" ></i>
            </el-input>           
      </div>
      <div id="head-other">
            <el-badge :value="12" class="head-item">
            <el-button size="small">等待处理</el-button>
            </el-badge>
            <el-badge :value="3" class="head-item">
            <el-button size="small">正在生产</el-button>
            </el-badge> 
            <el-image
            style="width: 40px;float:right;vertical-align: top;position: relative;top: 10px;"
            :src="require('../../assets/pic/avator.png')"
            fit="center"></el-image>          
            <el-dropdown class="head-drop">
                <span class="el-dropdown-link">
                    {{$store.state.info.name}}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="back">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
      </div>
         
  </div>
</template>

<script>
    export default {
        name:'HeadBanner',
        data() {
            return {
                input4:''
            };
        },
        methods: {
            back(){
                this.$router.replace({
                    name:'welcome'
                });
            }
        },
    }
</script>

<style scoped>
    .headfather{
        width: 90%;
        height: 100%;
        /* background-color: pink; */
        text-align: left;
        padding-left: 10%;
        /* padding-right: 10%; */
        /* padding: 0 10%; */
    }
    #head-search{
        width: 30%;
        height: 100%;
        display: inline-block;
        /* vertical-align: top; */
    }
    /* 样式穿透 */
    #head-search>>>.el-input__inner{
        border-radius: 20px;
    }
    #head-other{
        height: 100%;
        /* display: inline-block; */
        float: right;
    }
    .el-icon-user-solid{
        line-height: 3.5;
    }
    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
    .head-drop{
        float: right;
        /* margin-left: 10px; */
        /* width: 90px; */
        text-align: center;
        border-left: 1px solid #ccc;
        position: relative;
        height: 50%;
        top: 25%;
        line-height: 25px;
        font-size: 15px;
        margin-right: 10px;
        padding-left: 10px;
    }
    .head-drop>>>.el-dropdown-link{
        color: rgb(78, 159, 206);
    }
    .head-item{
        margin-right: 40px;
    }
    .head-item>>>.el-badge__content.is-fixed{
        top:18px
    }
</style>