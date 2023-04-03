<template>
        <!-- 这里需要给每个车间设置一个tab，显示每个车间的派工状况，并着重标出距离车间任务结束只剩五天以内的任务 -->
    <el-tabs type="border-card" class="warning-father" @tab-click="myclick">
        <el-tab-pane v-for="(chejian, index) in chejianlist" :key="index" :label="chejian">
            <el-row>
                <el-col :span="24">
                    <el-table
                        :data="tableData"
                        style="width: 100%;overflow-y: scroll;height:580px">
                        <el-table-column
                            prop="assignid"
                            label="派工单"
                            width="80">
                        </el-table-column>
                        <el-table-column
                            prop="liushui"
                            :filters="liushuiFilters"
                            :filter-method="filterHandler"
                            label="流水线号">
                            <template slot-scope="props">
                                <el-tag :type="props.row.liushui!=null?'success':'warning'">
                                    {{props.row.liushui!=null?"第"+props.row.liushui+"流水线":"暂未安排"}}
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                            prop="productName"
                            label="产品名">
                        </el-table-column>
                        <el-table-column
                            prop="date"
                            label="截止时间">
                        </el-table-column>
                        <el-table-column
                            prop="number"
                            label="数量">
                        </el-table-column>
                        <el-table-column
                            :filters="stateFilters"
                            :filter-method="filterState"
                            label="完成状态">
                            <template slot-scope="props">
                                <el-tag>{{state(props.row)}}</el-tag>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'Warning',
        data() {
            return {
                chejianlist:['弯管车间','冲压车间','焊接车间','安装车间'],
                unit:0,
                completeState:'',
                liushui:'',
                tableData:[],
                liushuiFilters:[{'text':'第1流水线','value':1},{'text':'第2流水线','value':2},{'text':'第3流水线','value':3},{'text':'第4流水线','value':4}],
                stateFilters:[{'text':'未开始','value':'未开始'},{'text':'未备料','value':'未备料'},{'text':'未安排流水线','value':'未安排流水线'},{'text':'流水线未完成','value':'流水线未完成'},{'text':'未产出完成','value':'未产出完成'},{'text':'未交货','value':'未交货'},{'text':'完成','value':'完成'}]
            }
        },
        computed:{
            state:function(){
                return (obj)=>{
                    return this.getState(obj);
                }
            }
        },
        methods:{
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            filterState(value,row,column){
                let state = this.getState(row);
                if(state==value)return true;
                else return false;
            },
            myclick(tab, event){
                this.unit=+tab.index+1
                this.day=""
                this.liushui=""
                this.completeState=""
                this.getAssign();
            },
            getAssign(){
                let that = this;
                axios({
                    method: 'get',
                    url: '/processUnit/getAssignUnit123',
                    params:{
                        unit:that.unit,
                    }
                })
                .then(function (response) {
                    that.tableData=response.data.data
                    console.log(response.data.data);
                })
            },
            getState(obj){
                if(obj['unitProcessComplete']==null) obj['unitProcessComplete']=false;
                if(obj['unitProcessStart']==null) obj['unitProcessStart']=false;
                let state=''
                if(obj['assignStart']==0) state="未开始"
                else if(obj['assignMateComplete']==0) state="未备料"
                else if(obj['unitProcessStart']==0) state="未安排流水线"
                else if(obj['unitProcessComplete']==0) state="流水线未完成"
                else if(obj['assignProComplete']==0) state="未产出完成"
                else if(obj['assignComplete']==0) state="未交货"
                else state="完成"
                return state;
            }
        },
        mounted(){
            this.unit=1;
            this.getAssign();
        },
        watch:{
            unit:{
                immediate:true,
                handler:function (newV,oldV) {
                    
                }
            }
        }
    }
</script>

<style scoped>
    .warning-father{
        width: 100%;
        height: 98%;
        text-align: left;
        overflow: hidden;
    }
    .search{
        display: flex;
        align-items: center;
        justify-content: left;
        padding-left: 30px;
    }
</style>