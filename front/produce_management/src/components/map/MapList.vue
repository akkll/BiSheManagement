<template>
  <div style="width:100%;height:100%">
    <el-table
        :data="tableData"
        style="width: 200px;position:absolute;left:400px;top:100px;height:300px;z-index:2000;overflow-y:scroll;border:1px solid #ccc"
        @row-click="clickData"
        >
        <el-table-column
            prop="date"
            label="日期"
            sortable
            :filters="myfilterdate"
            filter-multiple
            :filter-method="filterHandler"
            width="95">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            sortable
            :filters="myfiltername"
            filter-multiple
            :filter-method="filterHandler"
            width="84">
        </el-table-column>
    </el-table>
    <MapCard :date="date" :name="name" :myposition="myposition"/>
  </div>
</template>

<script>
    import MapCard from './MapCard'
    import axios from 'axios'
    export default {
        name:'MapList',
        components:{
            MapCard
        },
        data() {
            return {
                tableData:[
                    {'id':'111','date':'2023-01-01', 'name':'朱玉奇', 'myposition':[120.34928788159178,30.31566543276294]},
                    {'id':'112','date':'2023-01-02', 'name':'朱奇', 'myposition':[120.35297860119627,30.313461111711124]},
                    {'id':'113','date':'2023-01-03', 'name':'奇', 'myposition':[120.361999,30.3173397]}
                ],
                date:'',
                name:'',
                myposition:null,
                filterdate:[],
                filtername:[]
            }
        },
        computed:{
            myfilterdate(){
                return this.deWeight(this.filterdate)
            },
            myfiltername(){
                return this.deWeight(this.filtername)
            }
        },
        methods:{
            clickData(row, event, column) {   
                this.date = row.date;
                this.name = row.name;
                this.myposition = row.myposition;
            },
            deWeight(arr) {
                for (var i = 0; i < arr.length - 1; i++) {
                    for (var j = i + 1; j < arr.length; j++) {
                        if (arr[i].text == arr[j].text) {
                            arr.splice(j, 1);
                            j--;
                        }
                    }
                }
                return arr;
            },
            quchong(mydata, type){
                let arr=[];
                for(let p of mydata){
                    arr.push({'text':p[type],'value':p[type]});
                }
                let myset = arr.reduce((cur,next)=>cur = cur.filter((item)=>item[type] === next[type]).length === 0?cur.concat([next]):cur,[]);
                // let myset = arr.reduce((cur,next)=>cur = cur.filter((item)=>item.id === next.id && item.date === next.date).length === 0?cur.concat([next]):cur,[]);
                return myset;
            },
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            }
        },
        mounted(){
            let that = this;
            axios({
                url:'/getEmployee/getKaoqin',
                method:'get',
            }).then(function(response){
                let mydata = response.data.data;
                for(let p of mydata){
                    p.myposition=[p.longitude,p.latitude];
                    p.date=p.shangban;
                    that.filterdate.push({'text':p.date,'value':p.date})
                    that.filtername.push({'text':p.name,'value':p.name})
                }
                that.tableData = response.data.data;
            })
        }
    }
</script>

<style>

</style>