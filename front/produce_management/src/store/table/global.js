import axios from 'axios'
const global={
    namespaced:true,
    state:{
        myAllData:{},
        pageno:1,
        pagenum:10,
        isChange:true,//navigation组件监视着这个属性，当这个属性发生变化，就会向后端发送表格数据的请求，从而更新表格数据
        totle:0
    },
    getters:{
        getMyAllData(state){
            return state.myAllData.data;
        },
        getTotle(state){
            return state.totle;
        }

    },
    actions:{
        getTableData(context, value){
            if(value.method!=undefined&&value.method!=null){
                axios({
                    method: value.method,
                    url: '/'+value.req,
                    params:{
                        'pageno':context.state.pageno,
                        'pagenum':context.state.pagenum
                    }
                })
                .then(function (response) {
                    if(response.data.flag){
                        context.commit('SETDATA',response.data)
                    }
                });
                axios({
                    method: value.method,
                    url: '/'+value.req+'/count',
                })
                .then(function (response) {
                    if(response.data.flag){
                        context.commit('SETTOTLE',response.data.data)
                    }
                });   
            }
        },
    },
    mutations:{
        SETDATA(state, value){
            state.myAllData = value;
        },
        SETTOTLE(state, value){
            state.totle = value;
        },
        SETPAGENO(state, value){
            state.pageno = value;
            state.isChange = !state.isChange;
        },
        SETPAGENUM(state, value){
            state.pagenum = value;
            state.isChange = !state.isChange;
        },
    }
}
export default global