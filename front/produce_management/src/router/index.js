import VueRouter from 'vue-router'
import Welcome from '../components/home/Welcome'
import Home from '../components/home/Home'
import GetEmployee from '../components/table/GetEmployee'
import UpdateEmployee from '../components/table/UpdateEmployee'
import DepartForm from '../components/form/DepartForm'
import JoinForm from '../components/form/JoinForm'
import Arrange from '../components/process/Arrange'
import UnitArrange from '../components/process/UnitArrange'
import MyView from '../components/visiable/MyView'
import MapList from '../components/map/MapList'
import Warning from '../components/process/Warning'
import Liushui from '../components/process/Liushui'
import Allview from '../components/process/Allview'
import MaterialInfo from '../components/material/MaterialInfo'
import MaterialGet from '../components/material/MaterialGet'
const router = new VueRouter({
    routes:[
        {
            path:'/',
            name:'welcome',
            component:Welcome,
            meta:{title:'欢迎登录'}
        },
        {
            name:'home',
            path:'/home',
            component:Home,
            meta:{title:'主页'},
            children:[
                {
                    path:'getEmployee',
                    name:'GetEmployee',
                    component:GetEmployee,
                    meta:{title:'查询职员信息'}
                },
                {
                    path:'updateEmployee',
                    name:'UpdateEmployee',
                    component:UpdateEmployee,
                    meta:{title:'修改职员信息'}
                },
                {
                    path:'departForm',
                    name:'DepartForm',
                    component:DepartForm,
                    meta:{title:'离职申请办理'}
                },
                {
                    path:'joinForm',
                    name:'JoinForm',
                    component:JoinForm,
                    meta:{title:'入职申请办理'}
                },
                {
                    path:'arrange',
                    name:'Arrange',
                    component:Arrange,
                    meta:{title:'生产任务安排'}
                },
                {
                    path:'unitarrange',
                    name:'UnitArrange',
                    component:UnitArrange,
                    meta:{title:'车间生产任务安排'}
                },
                {
                    path:'liushui',
                    name:'Liushui',
                    component:Liushui,
                    meta:{title:'流水线管理'}
                },
                {
                    path:'myview',
                    name:'MyView',
                    component:MyView,
                    meta:{title:'流程进度可视化'}
                },
                {
                    path:'mapcard',
                    name:'MapCard',
                    component:MapList,
                    meta:{title:'地理位置考勤'}
                },
                {
                    path:'warning',
                    name:'Warning',
                    component:Warning,
                    meta:{title:'进度预警'}
                },
                {
                    path:'allView',
                    name:'Allview',
                    component:Allview,
                    meta:{title:'订单总览'}
                },
                {
                    path:'materialInfo',
                    name:'MaterialInfo',
                    component:MaterialInfo,
                    meta:{title:'原料查询'}
                },
                {
                    path:'materialGet',
                    name:'MaterialGet',
                    component:MaterialGet,
                    meta:{title:'出库管理'}
                },
            ]
        }
    ]
})

router.afterEach((to, from)=>{//没有next
	document.title=to.meta.title;//将浏览器标签页上的字符串改为meta的title
})
export default router;