<template>
    <el-tabs type="border-card" class="myview-father" @tab-click="updateChartsData">
        <el-tab-pane v-for="(chejian, index) in chejianlist" :key="index" :label="chejian">
            <el-row>
                <el-col :span="12"><div class="view-mian" ref="container"></div></el-col>
                <el-col :span="12"><div class="view-mian" ref="container2"></div></el-col>
            </el-row>
        </el-tab-pane>
    </el-tabs>
    <!-- <div>
        <div id="echartsdemo" ref="container"></div>
        <el-button size="mini" @click="changeData">改变数据</el-button>
    </div> -->
</template>

<script>
    import * as ec from 'echarts'
    import axios from 'axios'
    export default {
        name:'MyView',
        data() {
            return {
                chejianlist:['弯管车间', '冲压车间', '焊接车间', '安装车间'],
                echartsinstance:null,
                unit:0,
                options:{
                    // 标题设置
                    title:{text:'备料情况'},
                    tooltip:{},//提示框
                    legend:{// 数据标识
                        data:["派单数"]
                    },
                    // x轴标记
                    xAxis:{
                        data:["已备料","未备料"]
                    },
                    // y轴标记
                    yAxis:{},
                    // 数据系列
                    series:[
                        {
                            name:'派单数',
                            type:'bar',
                            data:[
                                {value:0,itemStyle: {
                                    color: '#a90000'
                                }},
                                {value:0,itemStyle: {
                                    color: '#5470c6'
                                }}
                            ]
                        }
                    ]
                },
                options2:{
                    // 标题设置
                    title:{text:'派工进行情况'},
                    tooltip:{},//提示框
                    legend:{// 数据标识
                        data:["派单数"]
                    },
                    // x轴标记
                    xAxis:{
                        data:["进行中","已完成(未交货)"]
                    },
                    // y轴标记
                    yAxis:{},
                    // 数据系列
                    series:[
                        {
                            name:'派单数',
                            type:'bar',
                            data:[
                                {value:0,itemStyle: {
                                    color: '#a90000'
                                }},
                                {value:0,itemStyle: {
                                    color: '#5470c6'
                                }}
                            ]
                        }
                    ]
                }
            }
        },
        methods:{
            updateChartsData(tab, event){
                this.echartsinstance = ec.init(this.$refs.container[tab.index])
                this.getBeiliao();
                // this.echartsinstance.setOption(this.options)
                this.echartsinstance2 = ec.init(this.$refs.container2[tab.index])
                this.getProcess();
                // this.echartsinstance2.setOption(this.options2)
                this.unit=tab.index;
            },
            getBeiliao(){
                let that = this;
                axios('/view/getBeiliao',{
                    method:'get',
                    params:{
                        unit:+that.unit+1,
                    } 
                }).then((res)=>{
                    console.log(res.data.data)
                    let t,f;
                    if(res.data.data[0]['complete']==true){
                        that.options.series[0]['data'][0]['value']=res.data.data[0]['num']
                        that.options.series[0]['data'][1]['value']=res.data.data[1]['num']
                    } else {
                        that.options.series[0]['data'][1]['value']=res.data.data[0]['num']
                        that.options.series[0]['data'][0]['value']=res.data.data[1]['num']
                    }
                    that.echartsinstance.setOption(that.options);
                })
            },
            getProcess(){
                let that = this;
                axios('/view/getProcess',{
                    method:'get',
                    params:{
                        unit:+that.unit+1,
                    } 
                }).then((res)=>{
                    console.log(res.data.data)
                    let t,f;
                    if(res.data.data[0]['complete']==true){
                        that.options2.series[0]['data'][0]['value']=res.data.data[0]['num']
                        that.options2.series[0]['data'][1]['value']=res.data.data[1]['num']
                    } else {
                        that.options2.series[0]['data'][1]['value']=res.data.data[0]['num']
                        that.options2.series[0]['data'][0]['value']=res.data.data[1]['num']
                    }
                    that.echartsinstance2.setOption(that.options2);
                })
            }
        },
        mounted(){
            this.updateChartsData({"index":0},'')
            // this.echartsinstance = ec.init(this.$refs.container[0])
            // this.getBeiliao();
            // this.getProcess();
        },
        watch:{
            // options(){
            //     this.echartsinstance.setOption(this.options)
            // },

            unit(){
                this.getBeiliao();
                this.getProcess();
            }
        }
    }
</script>

<style scoped>
    .myview-father{
        width: 100%;
        height: 98%;
        text-align: left;
        overflow: hidden;
    }
    .view-mian{
        width: 502px;
        height: 580px;
    }
    #echartsdemo{
        width:600px;
        height: 400px;
    }
</style>