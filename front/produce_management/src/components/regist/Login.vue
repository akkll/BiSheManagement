<template>
  <div class="mydiv">
      <div class="head">
        登录
      </div>
      <div class="divinput iden">
        <el-switch
          style="display: inline-block"
          v-model="userinfo.iden"
          active-color="#13ce66"
          inactive-color="#13ce66"
          active-text="管理员"
          inactive-text="操作员">
        </el-switch>
      </div>

      <el-form label-position="right" label-width="70px">
        <el-form-item label="用户名:" class="divinput">
          <el-input class="myinput" v-model="userinfo.username" placeholder="请输入用户名" prefix-icon="el-icon-user-solid">
          </el-input>
        </el-form-item>

        <el-form-item label="密码:" class="divinput">
          <el-input type="password" :show-password='true' class="myinput" v-model="userinfo.password" placeholder="请输入密码" prefix-icon="el-icon-lock">
          </el-input>
        </el-form-item>
        <div class="small" v-if="!userinfo.iden">
          <a href="#" style="text-decoration: none">忘记密码？</a>
        </div>

          <el-form-item class="divinput" label="验证码:">
            <el-input class="myinput" v-model="yzm" @keyup.enter.native="judge"></el-input>
          </el-form-item> 
            
          <div class="small">
            <!-- <canvas id="mycanvas"></canvas> -->
            <Yzm :isChange='isChange' />
          </div>
        <div class="divinput" style="text-align:center">
          <el-button type="success" @click="judge" >登录</el-button>
        </div>
        
      </el-form>
  </div>
</template>

<script>
    import {mapState, mapActions, mapMutations} from 'vuex'
    import { Message } from "element-ui";
    import Yzm from '../common/Yzm'
    import axios from 'axios'
    export default {
        name:'Login',
        components:{
          Yzm
        },
        computed:{
          ...mapState('loginAbout', {
            'options':'options'
          }),
        },
        data() {
          return {
            userinfo:{
              username:'',
              password:'',
              iden:true,//true为管理员，false为操作员
            },
            yzm:'',
            //这个用于给验证码组件传递一个变量，验证码组件会监视这个变量的变化，一旦变化，验证码就会刷新，每次需要刷新时用这个
            //this.isChange=!this.isChange;
            isChange:true
          }
        },
        methods:{
          success(value){
            this.$message({
              message: value,
              type: 'success'
            });
          },
          warn(value){
            this.$message({
              message: value,
              type: 'warning'
            });
          },
          fault(value){
            this.$message.error(value);
          },
          ...mapMutations({
            'UPDATE_INFO':'UPDATE_INFO'
          }),
          judgeYzm(){
            return this.options.txt.toUpperCase()===this.yzm.toUpperCase();
          },
          judge(){
            if(!this.judgeYzm()){
              this.fault("验证码有误，请重新输入！")
              this.isChange=!this.isChange;
              this.yzm='';
            }else{
              // axios.get('http://localhost:8080/managerByName',{
              axios.get('/managerByName',{
                  params:{
                      name:this.userinfo.username,
                      password:this.userinfo.password,
                      iden:(this.userinfo.iden===true)?1:0,
                  }
              }).then((res)=>{
                if(res.data.flag){
                  this.UPDATE_INFO(res.data.data);
                  this.success('登录成功')
                  setTimeout(() => {
                    this.$router.push({
                      name:'home'
                    })
                  }, 1000);
                }else{
                  this.isChange=!this.isChange;
                  this.fault('账号或密码或登录身份错误')
                }
              }).catch(error=>{
                this.isChange=!this.isChange;
                this.warn("可能是网络出现故障，请稍后再试")
              })
            }
          }
        }
    }
</script>

<style scoped>
  .mydiv{
    height:100%;
    width:100%;
    /* border:1px solid #ccc; */
    border-radius: 20px;
    background-color:rgb(255, 255, 255, 0.3);
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding-top:30px;
  }
  .head{
    font-size: 30px;
  }
  .divinput{
    text-align: left;
    padding: 10px 20px;
    margin: 10px 0;
  }
  .iden{
    display: flex;
    justify-content: flex-end;
    font-size: 10px;
  }
  .myinput{
    width: 90%;
  }
  .mycanvas{
    width: 100%;
    height: 60px;
  }
  .small{
    font-size: 13px;
    text-align: right;
    width: 90%;
  }
  .fresh{
    width: 50%;
    display: inline-block;
  }
</style>