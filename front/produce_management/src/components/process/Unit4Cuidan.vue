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
                <el-button type="warning" @click.stop="cuidan(props.row.id)" :disabled="props.row.start">{{props.row.start?'已催单':'催单'}}</el-button>
                <el-button type="success" @click.stop="" >交货</el-button>
            </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'Unit4Cuidan',
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
                axios({
                    method: 'post',
                    url: '/processUnit/Unit4CuidanStart',
                    data:{
                        'assignid':assignid
                    }
                })
                .then(function (response) {
                    console.log(response.data.data)
                    that.updateData = !that.updateData;
                }); 
            }
        },
        props:[
            'updateDepart'
        ],
        watch:{
            updateDepart:{
                handler:function(){
                    let that = this;
                    axios({
                        method: 'get',
                        url: '/processUnit/getAssignProductByUnit',
                        params:{
                            unit:4
                        }
                    })
                    .then(function (response) {
                        console.log(response.data.data)
                        that.tableData = response.data.data
                    });
                },
                deep:true
            },
            updateData:{
                handler:function(){
                    let that = this;
                    axios({
                        method: 'get',
                        url: '/processUnit/getAssignProductByUnit',
                        params:{
                            unit:4
                        }
                    })
                    .then(function (response) {
                        console.log(response.data.data)
                        that.tableData = response.data.data
                    });
                },
                deep:true
            },
        },
        mounted(){
            this.updateData = !this.updateData;
        }
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