<template>
  <div style="width=100%;height:100%">
    <el-row class="tac">
    <el-col :span="24">
        <div class="el-img">
            <el-image
                style="width:75px;"
                :src="require('../../assets/pic/avator.png')"
                fit="center">
            </el-image>            
        </div>
        <div style="height: 100px;"></div>
        <el-menu
        class="el-menu-vertical-demo"
        :router=true
        :unique-opened=true
        :default-active="activeIndex"
        @open="handleOpen"
        @close="handleClose"
        background-color="#282d95"
        text-color="#fff"
        style="height:100%;overflow-y:auto;"
        active-text-color="#000">
        <el-submenu v-for="navi in navis" :key="navi.id" :index="navi.id">
            <template slot="title">
                <i :class="navi.icon"></i>
                <span>{{navi.title}}</span>
            </template>
            <el-menu-item-group v-for="children in navi.children" :key="children.id" :title="children.title">
                <el-menu-item 
                    v-for="(child, index) in children.children" :key="index" 
                    :index="child.path" 
                    @click="getData(child)">
                    {{child.name}}
                </el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        </el-menu>
    </el-col>
    </el-row>            
  </div>
</template>

<script>
    import axios from 'axios'
    import {mapMutations, mapActions, mapState} from 'vuex'
    export default {
        name:'Navigation',
        data(){
            return {
                currentValue:'',
                navis:[
                    {
                        id:'1',title:"员工管理", icon:"el-icon-user-solid", children:[
                            {
                                id:'101',title:'基本管理', children:[
                                    // {id:'10101',name:'员工基本信息查询', path:'/home/getEmployee', req:'getEmployee', method:'get'},
                                    {id:'10102',name:'员工基本信息修改', path:'/home/updateEmployee', req:'getEmployee', method:'get'},
                                    {id:'10103',name:'员工离职申请', path:'/home/departForm', req:'getEmployee', method:'get'},
                                    {id:'10104',name:'员工招聘审核', path:'/home/joinForm'}
                                ]
                            },
                            {
                                id:'102',title:'考勤', children:[
                                    {id:'10202',name:'位置考勤', path:'/home/mapcard'}
                                ]
                            },
                            {
                                id:'103',title:'绩效录入', children:[
                                    {id:'10301',name:'车间绩效录入', path:'...'},
                                ]
                            },                            
                        ]
                    },
                    {
                        id:'2',title:"进度管理", icon:"el-icon-s-order", children:[
                            {
                                id:'201',title:'任务安排', children:[
                                    {id:'20101',name:'生产任务安排', path:'/home/arrange'},
                                    {id:'20102',name:'车间派工（产品流程单）', path:'/home/unitarrange'},
                                    {id:'20103',name:'流水线管理', path:'/home/liushui'},
                                ]   
                            },
                            {
                                id:'202',title:'可视化及预警', children:[
                                    {id:'20201',name:'流程进度可视化', path:'/home/myview'},
                                    {id:'20202',name:'进度预警', path:'/home/warning'}
                                ]
                            }                            
                        ]
                    },
                    {
                        id:'3',title:"原料管理", icon:"el-icon-setting", children:[
                            {
                                id:'301',title:'基本信息', children:[
                                    {id:'30101',name:'基本信息查询', path:'/home/materialInfo'},
                                ]
                            },
                            {
                                id:'302',title:'出库信息', children:[
                                    {id:'30201',name:'出库管理', path:'/home/materialGet'},
                                ]
                            }                            
                        ]
                    },
                    {
                        id:'4',title:"质量管理", icon:"el-icon-document-checked", children:[
                            {
                                id:'401',title:'质量检测', children:[
                                    {id:'40101',name:'质量排查', path:'...'},
                                ]
                            },
                            {
                                id:'402',title:'废料管理', children:[
                                    {id:'40201',name:'废料管理', path:'...'},
                                ]
                            }                            
                        ]
                    },
                    {
                        id:'5',title:"成品管理", icon:"el-icon-takeaway-box", children:[
                            {
                                id:'501',title:'入库', children:[
                                    {id:'50101',name:'入库管理', path:'...'},
                                ]
                            },
                            {
                                id:'502',title:'返修', children:[
                                    {id:'50201',name:'返修管理', path:'...'},
                                ]
                            }                            
                        ]
                    },
                    {
                        id:'6',title:"系统设置", icon:"el-icon-takeaway-box", children:[
                            {
                                id:'501',title:'入库', children:[
                                    {id:'50101',name:'入库管理', path:'...'},
                                ]
                            },
                            {
                                id:'502',title:'返修', children:[
                                    {id:'50201',name:'返修管理', path:'...'},
                                ]
                            }                            
                        ]
                    }

                ]
            }
        },
        computed:{
          ...mapState('global',{
            'isChange':'isChange'
          }),         
            activeIndex(){
                return this.$route.path;
            }   
        },
        methods: {
            ...mapActions('global',{
                'change':'getTableData'
            }),
            handleOpen(key, keyPath) {
                // console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                // console.log(key, keyPath);
            },
            getData(value){
                this.currentValue = value;
                this.change(value)
            }
        },
        watch:{
          isChange:{
              handler:function(){
                  this.change(this.currentValue)
              }
          }
        }
    }
</script>

<style scoped>
    .tac{
        padding-top: 30px;
    }
    .el-menu-item{
        text-align: left;
        padding-left: 50px !important;
    }
    .el-submenu>>>.el-submenu__title{
        text-align: left;
    }
    .el-menu-item{
        font-size: 13px;
    }
    .el-menu-item.is-active {
      background-color: #e4e4e4 !important;
    }
    .el-menu{
        width: 250px;
    }
    .el-img{
        position: fixed;
        width:250px;
        height: 100px;
        z-index: 1000;
        background-color: #282d95;
        top: 0px;
        /* border-bottom:1px solid #ccc */
    }
    .el-menu>>>.el-menu-item-group>>>.el-submenu__title{
        padding: 7px 28px 7px 20px !important;

    }

    .el-submenu__title i{
        color:white
    }
    ::-webkit-scrollbar{display:none}
</style>