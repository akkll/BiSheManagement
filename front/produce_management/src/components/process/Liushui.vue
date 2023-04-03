<template>
    <el-tabs type="border-card" class="liushui-father" @tab-click="myclick">
        <el-tab-pane v-for="(chejian, index) in chejianlist" :key="index" :label="chejian">
            <el-table
                :data="gongxu"
                style="width: 100%">
                <el-table-column
                    prop="id"
                    label="工艺id"
                    width="80">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="工艺名称"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="address"
                    label="流水线"
                    width="200">
                    <template slot-scope="props">
                        <el-select v-model="liushui[props.$index]" @change="selectChange(liushui[props.$index],props.row.id, props.$index)">
                            <el-option label="1号流水线" value="1"></el-option>
                            <el-option label="2号流水线" value="2"></el-option>
                            <el-option label="3号流水线" value="3"></el-option>
                            <el-option label="4号流水线" value="4"></el-option>
                        </el-select>
                    </template>
                </el-table-column>
                <el-table-column
                    prop="address"
                    label="员工"
                    width="200">
                    <template slot-scope="props">
                        <el-select v-model="submitEmployee[props.$index]">
                            <el-option v-for="(emp, index) in myemployee[liushui[props.$index]-1]" :key="index" :label="emp.name" :value="emp.id"></el-option>
                        </el-select>
                    </template>
                </el-table-column>
                <el-table-column
                    prop="address"
                    label="编辑"
                    width="200">
                    <template slot-scope="props">
                        <el-button @click="updateOrInsert(props.row.id,props.$index)"
                        :disabled="!JudgeupdateOrInsert(props.row.id,props.$index)"
                        :type="JudgeupdateOrInsert(props.row.id,props.$index)?'success':'warning'"
                        >
                            {{JudgeupdateOrInsert(props.row.id,props.$index)?"修改":"不可修改"}}
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import axios from 'axios'
    export default {
        name:'Liushui',
        data() {
            return {
                chejianlist:['弯管车间', '冲压车间', '焊接车间', '安装车间'],
                unit:1,
                employee:[],
                tableData:[],
                gongxu:[],
                liushui:[],
                employees:[],
                employee:[],
                submitEmployee:[]
            }
        },
        computed:{
            myemployee(){
                let employee1=this.employees.filter(function(abc){
                    return abc.liushui===1;
                })
                let employee2=this.employees.filter(function(abc){
                    return abc.liushui===2;
                })
                let employee3=this.employees.filter(function(abc){
                    return abc.liushui===3;
                })
                let employee4=this.employees.filter(function(abc){
                    return abc.liushui===4;
                })
                return [employee1,employee2,employee3,employee4]
            }
        },
        methods:{
            myclick(tab, event){
                this.unit=+tab.index+1;
                this.getEmployeeByUnit();
                this.getGongxuByUnit();
                this.liushui=[]
                this.submitEmployee=[];
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
                    that.employees=(response.data.data)
                });
            },
            getGongxuByUnit(){
                let that = this;
                axios({
                    method: 'get',
                    url: '/liushui/getGongxuByUnit',
                    params:{
                        unit:that.unit
                    }
                })
                .then(function (response) {
                    that.gongxu=(response.data.data)
                    that.liushui.length=that.gongxu.length;
                    that.submitEmployee.length=that.gongxu.length
                });
            },
            getLiushuiEmployee(id,gongxu, index){
                let that = this;
                axios({
                    method: 'get',
                    url: '/liushui/getLiushuiEmployee',
                    params:{
                        unit:that.unit,
                        id:id,//流水线号
                        gongxu:gongxu
                    }
                })
                .then(function (response) {
                    if(response.data.data!=null){
                        console.log(response.data.data)
                        that.$set(that.submitEmployee,index,response.data.data.employeeid)
                    }
                });
            },
            selectChange(id,gongxu, index){
                this.submitEmployee[index]=undefined;
                this.getLiushuiEmployee(id,gongxu, index);
            },
            JudgeupdateOrInsert(id, index){
                let unit=this.unit;
                let employeeid = this.submitEmployee[index];
                let liushui = this.liushui[index]
                if(unit==undefined||employeeid==undefined||liushui==undefined)
                    return false;
                else 
                    return true;
            },
            updateOrInsert(id,index){
                let unit=this.unit;
                let employeeid = this.submitEmployee[index];
                let liushui = this.liushui[index];
                let that = this;
                axios({
                    method: 'post',
                    url: '/liushui/updateOrInsertLiushui',
                    params:{
                        unit:unit,
                        id:liushui,//流水线号
                        gongxu:id,
                        employeeid:employeeid
                    }
                })
                .then(function (response) {
                    console.log(response.data.data)
                    let yesOrNo = response.data.data
                    if(yesOrNo){
                        that.$message({
                            showClose: true,
                            message: '修改成功',
                            type: 'success'
                        });
                    } else{
                        that.$message({
                            showClose: true,
                            message: '新增成功',
                            type: 'success'
                        });
                    }
                });
            }
        },
        mounted(){
            this.unit=1;
            this.getEmployeeByUnit();
            this.getGongxuByUnit();
        },
    }
</script>

<style scoped>
    .liushui-father{
        width: 100%;
        height: 98%;
        text-align: left;
        overflow: hidden;
    }
</style>