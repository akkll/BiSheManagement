<template>
    <el-tabs type="border-card" class="unitarrange-father" @tab-click="getData">
        <el-tab-pane v-for="(chejian, index) in chejianlist" :key="index" :label="chejian">
            <UnitZhipai :updateDepart="tabs[index]" :unit="index+1"/>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import UnitZhipai from './UnitZhipai'
    export default {
        name:'UnitArrange',
        components:{
            UnitZhipai
        },
        data() {
            return {
                tableData:[],
                chejianlist:['弯管车间', '冲压车间', '焊接车间', '安装车间'],
                tabs:[true,true,true,true]
            }
        },
        methods: {
            getData(tab, event){
                this.$set(this.tabs, tab.index, !this.tabs[tab.index])
            }
        },
    }
</script>

<style scoped>
    .unitarrange-father{
        width: 100%;
        height: 98%;
        text-align: left;
        overflow: hidden;
    }
</style>