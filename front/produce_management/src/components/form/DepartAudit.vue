<template>
  <div class="audit-father">
    <el-table
        v-show="tableData!=[]"
        :data="tableData"
        ref="abc"
        :row-key="getRowKeys"
        @row-click="clickRowHandle"
        :expand-row-keys="expands"
        @expand-change="handleExpandChange"
        style="width: 100%">
        <el-table-column type="expand">
            <template>
                <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="工号：">
                    <span>{{ currentEmployee.id }}</span>
                </el-form-item>
                <el-form-item label="姓名：">
                    <span>{{ currentEmployee.name }}</span>
                </el-form-item>
                <el-form-item label="职位：">
                    <span>{{ currentEmployee.titleID==0?'操作员':'主管' }}</span>
                </el-form-item>
                <el-form-item label="性别：">
                    <span>{{ currentEmployee.gender=='1'?'男':'女' }}</span>
                </el-form-item>
                <el-form-item label="身份证号码：">
                    <span>{{ currentEmployee.card }}</span>
                </el-form-item>
                <el-form-item label="入职时间：">
                    <span>{{ currentEmployee.workdate }}</span>
                </el-form-item>
                <el-form-item label="手机号：">
                    <span>{{ currentEmployee.mobile }}</span>
                </el-form-item>
                <el-form-item label="微信：">
                    <span>{{ currentEmployee.wechat }}</span>
                </el-form-item>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column
            label="工号"
            prop="employeeID">
        </el-table-column>
        <el-table-column
            label="姓名"
            prop="employeename">
        </el-table-column>
        <el-table-column
            label="申请时间"
            prop="apptime">
        </el-table-column>
        <el-table-column
            label="预计离职日期"
            prop="time">
        </el-table-column>
        <el-table-column
            label="编辑"
        >
            <template slot-scope="props">
                <el-button type="success" @click.stop="pass(props)">通过</el-button>
            </template>
            
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'DepartAudit',
        data() {
            return {
                tableData: [],
                getRowKeys(row){
                    return row.employeeID;
                },
                expands:[],//存放当前展开行数据
                currentEmployee:{},
                updateData:true
            }
        },
        methods:{
            handleExpandChange(row, expandedRows){
                this.clickRowHandle(row, null);
            },
            clickRowHandle(row, column) {
                if (this.expands.includes(row.employeeID)) {
                    this.expands = this.expands.filter(val => val !== row.employeeID);
                    this.currentEmployee = {};
                } else {
                    //判断是否已经存在展开的行
                    if (this.expands.length != 0) {
                        let that = this;
                        axios({
                            method: 'get',
                            url: '/getEmployee/getEmployeeById',
                            params:{
                                'id':row.employeeID
                            }
                        })
                        .then(function (response) {
                            that.currentEmployee = response.data.data;
                            //如果存在展开行,清空expands数组,使它关闭
                            that.expands.splice(0, that.expands.length);
                            //打开点击的行
                            that.expands.push(row.employeeID);
                        }); 
                    } else {
                    //如果不存在展开行,直接push打开点击的行
                        let that = this;
                        axios({
                            method: 'get',
                            url: '/getEmployee/getEmployeeById',
                            params:{
                                'id':row.employeeID
                            }
                        })
                        .then(function (response) {
                            that.currentEmployee = response.data.data;
                            that.expands.push(row.employeeID);
                        }); 
                        
                    }
                }
            },
            pass(row){
                let that = this;
                axios({
                    method: 'post',
                    url: '/getEmployee/updateDepartStatePass',
                    params:{
                        id:row.row.employeeID
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
                        url: '/getEmployee/getDepart',
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
                        url: '/getEmployee/getDepart',
                    })
                    .then(function (response) {
                        console.log(response.data.data)
                        that.tableData = response.data.data
                    });
                },
                deep:true
            },
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