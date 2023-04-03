<template>
  <div style="width:100%;height:580px">
    <el-button class="mybutton" type="success" @click="success">确认</el-button>
    <el-transfer
        v-model="value"
        :titles="['待进行订单', '正在进行订单']"
        :props="{
            key: 'value',
            label: 'desc'
        }"
        :data="data">
    </el-transfer>          
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'Zhipai',
        data() {
            return {
                value:[],
                updateData:true,
                allData:[],
                products:[]
            }
        },
        computed:{
            data(){
                const data = [];
                for (let i = 0; i < this.allData.length; i++) {
                    var d1 = new Date();
                    var d2 = new Date(this.allData[i]['delivery']);
                    let productname = this.allData[i]['productname'];
                    let id = this.allData[i]['id'];
                    let number = this.allData[i]['number'];
                    let day = parseInt((d2 - d1)/1000/60/60/24)
                    let scale = this.allData[i]['scale'];
                    data.push({
                        value: this.allData[i]['id'],
                        desc: "订单号:"+id+"\u3000\u3000型号："+productname+'('+scale+')\u3000\u3000数量：'+number,
                        disabled: this.allData[i].arrange==true
                    });
                    if(this.allData[i].arrange===true){
                        this.value.push(this.allData[i]['id'])
                    }
                }
                return data;
            },
        },
        watch:{
            updateData:{
                handler:function(){
                    let that = this;
                    axios({
                        method: 'get',
                        url: '/order'
                    })
                    .then(function (response) {
                        that.allData = response.data.data
                        that.value = [];
                        for(let i=0;i<that.allData.length;i++){
                            let p = that.allData[i];
                            that.$set(p, 'productname', that.products[p.produceid].name)
                            that.$set(p, 'scale', that.products[p.produceid].scale)
                        }
                    });
                }
            },
            unit:{
                handler:function(){
                    let that = this;
                    axios({
                        method: 'get',
                        url: '/order'
                    })
                    .then(function (response) {
                        that.allData = response.data.data
                        that.value = [];
                    });
                }
            },
        },
        mounted(){
            this.updateData = !this.updateData;
            let that = this;
            axios({
                method: 'get',
                url: '/order/products',
            })
            .then(function (response) {
                that.products = response.data.data;
            });
        },
        methods: {
            success(){
                let that = this;
                axios({
                    method: 'post',
                    url: '/order/updateArrange',
                    headers:{
                        'Content-Type':'application/json;charset=utf-8'
                    },
                    data:JSON.stringify(that.value)
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                    that.$message({
                        message: '订单即将进行',
                        type: 'success'
                    });
                });
            }
        },
    }
</script>

<style scoped>
    .el-transfer>>>.el-transfer-panel{
        width:40%;
        height:580px;
    }
    .el-transfer /deep/ .el-transfer-panel__list{
        height: 537px;
    }
    .selectbox{
        position: absolute;
        top: 20px;
        left: 432px;
        width: 150px;
    }
    .mybutton{
        position: absolute;
        top: 400px;
        left: 436px;
        width: 150px;
    }
</style>