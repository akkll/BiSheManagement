<template>
    <div class="unitzhipai-father">
        <el-table
        height="100%"
        :data="tableData"
        style="width: 100%">
        <el-table-column
            prop="id"
            label="车间派工单编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="assignid"
            label="派工单编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="accdate"
            label="接收时间"
            width="150"
            >
        </el-table-column>
        <el-table-column
            prop="date"
            label="截止时间"
            width="150">
        </el-table-column>
        <el-table-column label="安排流水线">
            <template slot-scope="props">
                <!-- <el-select v-model="myselect[props.$index]" :disabled="myselect[props.$index]!=undefined">
                    <el-option v-for="(employ, index) in employee" :key="index" :label="employ.name" :value="employ.id">
                    </el-option>
                </el-select> -->
                <el-select v-model="myselect[props.$index]" :disabled="myselect[props.$index]!=undefined">
                    <el-option v-for="(unitl, index) in unitlist" :key="index" :label="unitl.label" :value="unitl.value">

                    </el-option>
                </el-select>
            </template>
        </el-table-column>
        <el-table-column
            label="编辑">
            <template slot-scope="props">
                <el-button 
                    :type="!(props.row.start==true)?'warning':'success'" 
                    :disabled="myselect[props.$index]==undefined||props.row.start==true" 
                    @click="addEmployeeUnitProcess(myselect[props.$index],props.row.id)">
                    {{props.row.start==false?'分配':'已分配'}}
                </el-button>
                <el-button type="success" :disabled="props.row.start==false||props.row.complete==true"
                    @click="completeUnitProcess(props.row.id, props.row.assignid)"
                    >
                    {{complete(props.$index)}}
                </el-button>
            </template>
            
        </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'UnitZhipai',
        props:[
            'updateDepart',
            'unit'
        ],
        data() {
            return {
                updateData:true,
                tableData: [],
                employee:[],
                myselect:[],
                unitlist:[{'label':'第1流水线','value':1},{'label':'第2流水线','value':2},{'label':'第3流水线','value':3},{'label':'第4流水线','value':4}]
            }
        },
        computed:{
            complete:function(){
                return (index)=>{
                    if(this.tableData[index]['start']==false)
                        return '未分配'
                    else if(this.tableData[index]['start']==true&&this.tableData[index]['complete']==false)
                        return '完成'
                    else 
                        return '已完成'
                }
            }
        },
        methods:{
            completeUnitProcess(id, assignid){
                let that = this;
                axios({
                    method: 'post',
                    url: '/processUnit/completeUnitProcess',
                    data:{
                        assignid:assignid,
                        id:id
                    }
                })
                .then(function (response) {
                    console.log(response.data);
                    that.updateData = !that.updateData;
                });
            },
            addEmployeeUnitProcess(employeeid, id){
                let that = this;
                axios({
                    method: 'post',
                    url: '/processUnit/addEmployeeUnitProcess',
                    data:{
                        employeeid:employeeid,
                        id:id
                    }
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                });
            },
            getEmployeeByUnit(){
                let that = this;
                axios({
                    method: 'get',
                    url: '/processUnit/getUnitEmployee',
                    params:{
                        unit:that.unit
                    }
                })
                .then(function (response) {
                    that.employee=(response.data.data)
                });
            },
            getUnitProcessByUnit(){
                let that = this;
                axios({
                    method: 'get',
                    url: '/processUnit/getUnitProcessByUnit',
                    params:{
                        unit:this.unit
                    }
                })
                .then(function (response) {
                    that.tableData=response.data.data;
                    for(let p in that.tableData){
                        if(that.tableData[p]['start']==true){
                            that.myselect[p]=that.tableData[p]['employeeid']
                        }
                    }
                });
            }
        },
        watch:{
            updateDepart:{
                deep:true,
                immediate:true,
                handler:function(){
                    this.getEmployeeByUnit();
                    this.getUnitProcessByUnit();
                    this.myselect.lenth=100;
                }
            },
            updateData:{
                deep:true,
                immediate:true,
                handler:function(){
                    this.getEmployeeByUnit();
                    this.getUnitProcessByUnit();
                    this.myselect.lenth=100;
                }
            }
        }
    }
</script>

<style scoped>
    .unitzhipai-father{
        width: 100%;
        height: 580px;
    }
</style>