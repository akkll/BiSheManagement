<template>
    <el-tabs type="border-card" class="arrange-father" @tab-click="getData">
        <el-tab-pane label="订单查看">
            <template>
                <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                    prop="id"
                    label="订单id"
                    width="80">
                </el-table-column>
                <el-table-column
                    prop="productname"
                    label="成品名称"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="scale"
                    label="成品规格"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="number"
                    label="数量"
                    width="80">
                </el-table-column>
                <el-table-column
                    label="是否开始">
                    <template slot-scope="props">
                        <el-tag :type="props.row.arrange!=true?'warning':'success'">
                            {{props.row.arrange==true?'是':'否'}}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                    label="是否完成">
                    <template slot-scope="props">
                        <el-tag :type="props.row.complete!=true?'warning':'success'">
                            {{props.row.complete==true?'是':'否'}}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                    label="接收日期"
                    prop="accdate">
                </el-table-column>
                <el-table-column
                    label="备注">
                    <template slot-scope="props">
                        {{props.row.beizhu==undefined?'无':props.row.beizhu}}
                    </template>
                </el-table-column>
                <el-table-column
                    prop="delivery"
                    label="预期完成时间">
                </el-table-column>
                <el-table-column
                    label="实际完成时间">
                    <template slot-scope="props">
                        {{props.row.completeDate==undefined?'未完成':props.row.completeDate}}
                    </template>
                </el-table-column>
                </el-table>
            </template>
        </el-tab-pane>
        <el-tab-pane :disabled="info.iden!=0" label="订单指派">
            <Zhipai/>
        </el-tab-pane>
        <el-tab-pane v-for="(chejian, index) in chejianLists" :key="index" :label="chejian">
            <UnitTable :updateDepart="tabs[index]" :unit="index+1"/>
        </el-tab-pane>
    </el-tabs>
</template>

<script>    
    import axios from 'axios'
    import Zhipai from './Zhipai'
    import Unit4Cuidan from './Unit4Cuidan'
    import Unit3Cuidan from './Unit3Cuidan'
    import UnitTable from './UnitTable'
    import { mapState } from 'vuex'
    export default {
        name:'Arrange',
        components:{
            Zhipai,
            Unit4Cuidan,
            Unit3Cuidan,
            UnitTable
        },
        data() {
            return {
                updateData:true,
                tableData: [],
                chejianList:['弯管车间','冲压车间', '焊接车间', '安装车间'],
                tabs:[true, true, true, true]
            }
        },
        computed:{
            ...mapState({
                'info':'info'
            }),
            chejianLists(){
                let p=[];
                if(this.info.iden!=0){
                    p.push(this.chejianList[this.info.iden-1]);
                } else {
                    p=this.chejianList;
                }
                return p;
            }
        },
        methods:{
            getData(tab, event){
                if(tab.index==0){
                    this.updateData = !this.updateData;
                };
                if(tab.index>=2) this.$set(this.tabs, tab.index-2, !this.tabs[tab.index-2])
            }
        },
        mounted(){
            this.updateData = !this.updateData;
        },
        watch:{
            updateData:{
                handler:function(){
                    let that = this;
                    axios({
                        method: 'get',
                        url: '/order',
                    })
                    .then(function (response) {
                        that.tableData = response.data.data;
                        axios({
                            method: 'get',
                            url: '/order/products',
                        })
                        .then(function (response) {
                            if(that.tableData!=null){
                                for(let i=0;i<that.tableData.length;i++){
                                    let p = that.tableData[i];
                                    that.$set(p, 'productname', response.data.data[p.produceid].name);
                                    that.$set(p, 'scale', response.data.data[p.produceid].scale);
                                }
                            }
                        });
                    });
                }
            }
        }
    }
</script>

<style scoped>
    .arrange-father{
        width: 100%;
        height: 98%;
        text-align: left;
        overflow: hidden;
    }
    >>>.el-tabs__content{
        height: 575px;
        overflow-y: scroll;
        overflow-x: hidden;
    }
</style>