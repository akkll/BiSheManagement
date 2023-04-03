<template>
  <div class='homefather'>
      <el-container>
        <el-container>
            <el-aside width="250px">
                <Navigation/>
            </el-aside>
            <el-container>
                <el-header>
                    <HeadBanner/>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>   
        <el-footer>
            <Banner/>
        </el-footer>       
      </el-container>

  </div>
</template>

<script>
    import Banner from '../common/Banner'
    import MyTable from '../common/MyTable'
    import Navigation from './Navigation'
    import HeadBanner from '../home/HeadBanner'
    export default {
        name:'Home',
        components:{
            Banner,
            Navigation,
            HeadBanner,
            MyTable
        },
        methods: {
            back(){
                this.$router.push({
                    name:'welcome'
                });
            }
        },
    }
</script>

<style scoped>
    .homefather{
        /* outline: 1px solid red; */
        width:86%;
        /* height: 700px; */
        margin-left: 7%;
    }
    .el-header, .el-footer {
        /* background-color: rgb(166, 185, 207); */
        color: #333;
        text-align: center;
        line-height: 60px;
    }
    .el-header{
        height: 60px !important;
    }
    .el-footer{
        height: 79px !important;
    }
  
  .el-aside {
    background-color: rgb(40, 45, 149);
    color: #333;
    text-align: center;
    height: 735px;
    overflow: hidden;
  }
  
  .el-main {
    /* background-color: #E9EEF3; */
    color: #333;
    text-align: center;
    height: 675px;
    display:flex;
    justify-content:space-around;
    align-items: center;
    padding: 0;
    padding-left: 20px;
    padding-top: 20px;
    /* overflow: hidden; */
  }
  
  
</style>