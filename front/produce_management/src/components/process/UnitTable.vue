<template>
  <div class="audit-father">
    <el-table
        v-show="tableData!=[]"
        :data="tableData"
        ref="abc"
        :row-key="getRowKeys"
        :expand-row-keys="expands"
        style="width: 100%">
        <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="对应订单号：">
                        <span>{{ props.row.orderid }}</span>
                    </el-form-item>
                    <el-form-item label="产品id：">
                        <span>{{ props.row.productid }}</span>
                    </el-form-item>
                    <el-form-item label="产品名称：">
                        <span>{{ props.row.name }}</span>
                    </el-form-item>
                    <el-form-item label="接收时间：">
                        <span>{{ props.row.accdate }}</span>
                    </el-form-item>
                    <el-form-item label="数量：">
                        <span>{{ props.row.number}}</span>
                    </el-form-item>
                    <el-form-item label="产品规格：">
                        <span>{{ props.row.scale }}</span>
                    </el-form-item>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column
            label="任务编号"
            prop="id"
            width ="120">
        </el-table-column>
        <el-table-column
            label="产品名称"
            prop="name"
            width ="120">
        </el-table-column>
        <el-table-column
            label="规格"
            prop="scale"
            width ="120">
        </el-table-column>
        <el-table-column
            label="需求数量"
            prop="number"
            width ="120">
        </el-table-column>
        <el-table-column
            label="截止时间"
            prop="date">
        </el-table-column>
        <el-table-column
            label="编辑"
        >
            <template slot-scope="props">
                <el-button type="warning" @click.stop="cuidan(props.row.id)" :disabled="props.row.start" v-if="unit>2">
                    {{props.row.matecomplete?"已备料":props.row.start?'已催单':'催单'}}
                </el-button>
                <el-button type="warning" @click.stop="lingliao(props.row.id)" :disabled="props.row.matecomplete" v-else>
                    {{props.row.matecomplete?"已备料":props.row.start?'已通知领料':'领料'}}
                </el-button>
                <el-button type="success" @click.stop="jiaohuo(props.row.id)" :disabled="(!props.row.procomplete)||props.row.complete">
                    {{props.row.complete?'完成':(props.row.procomplete?'交货':'货未备齐')}}
                </el-button>
            </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
    import axios from 'axios'
    import { Message,Alert } from "element-ui";
    export default {
        name:'UnitTable',
        data() {
            return {
                tableData: [],
                getRowKeys(row){
                    return row.id;
                },
                expands:[],//存放当前展开行数据
                currentEmployee:{},
                updateData:true
            }
        },
        methods:{
            cuidan(assignid){
                let that = this;
                let urls;
                if(this.unit==4) urls = "/processUnit/Unit4CuidanStart";
                else if(this.unit==3) urls = "/processUnit/Unit3CuidanStart"
                axios({
                    method: 'post',
                    url: urls,
                    data:{
                        'assignid':assignid
                    }
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                }); 
            },
            lingliao(assignid){
                let that = this;
                axios({
                    method: 'post',
                    url: '/processUnit/Unit12GetMaterialComplete',
                    data:{
                        'assignid':assignid
                    }
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                    if(that.unit==1||that.unit==2){
                        if(response.data.data!=-1){
                            that.$message({
                                message: '原料不足，还缺'+response.data.data+'斤，请注意补货',
                                type: 'warning'
                            });
                        }
                    }
                }); 
            },
            jiaohuo(assignid){
                let that = this;
                let url ='';
                if(this.unit!=4) url = '/order/Unit123GiveMidComplete';
                else url = '/order/Unit4GiveProComplete'
                axios({
                    method: 'post',
                    url: url,
                    data:{
                        'assignid':assignid
                    }
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                }); 
            }
        },
        props:[
            'updateDepart',
            'unit'
        ],
        watch:{
            updateDepart:{
                handler:function(){
                    this.updateData = !this.updateData;
                },
                deep:true
            },
            updateData:{
                handler:function(){
                    let that = this;
                    let myurl;
                    if(that.unit==3||that.unit==4) myurl = 'getAssignProductByUnit'
                    else myurl = 'getAssignMidProductByUnit';
                    axios({
                        method: 'get',
                        url: '/processUnit/'+myurl,
                        params:{
                            unit:that.unit
                        }
                    })
                    .then(function (response) {
                        that.tableData = response.data.data
                    });
                },
                deep:true
            },
        },
    }
</script>

<style scoped>
    .audit-father{
        width: 100%;
        height: 580px;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }

</style>