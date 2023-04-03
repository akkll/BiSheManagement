<template>
  <div>
    <el-container id="father">
        <el-main id="mymain">
            <div class="mydiv1" style="width:60%;border-radius:20px">
                <img src="../../assets/pic/2.png" style="width:90%;border-radius:100px">
            </div>
            <div class="mydiv1" style="width:25%;">
                <Login/>
            </div>
        </el-main>
        <el-footer>
            <Banner/>
        </el-footer>
    </el-container>
  </div>
</template>

<script>
    import Banner from '../common/Banner'
    import Login from '../regist/Login'
    export default {
        name:'Welcome',
        components:{
            Banner,
            Login
        },
beforeRouteEnter(to, from, next){
    next(vm=>{
        vm.$store.commit('UPDATE_INFO', {});
    })
    next();
},
    }
</script>

<style scoped>
  .el-header, .el-footer {
    outline: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 150px !important;
  }
  .el-main {
    outline: #E9EEF3;
    color: #333;
    text-align: center;
    height: 600px;
    display:flex;
    justify-content:space-around;
    align-items: center;
  }
  #father{
      background: linear-gradient(to top left,rgb(95, 132, 146), rgb(105, 190, 151));
  }
  .mydiv1{
      /* outline: 1px solid #ccc; */
      height: 80%;
  }
    img{
        position: relative;
        top: 25px;
        left: 36px;
    }
</style>