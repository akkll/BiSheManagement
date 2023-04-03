import Vuex from 'vuex'
import Vue from 'vue'
import loginAbout from './login/login'
import global from './table/global'
import dealfile from './file/dealfile'
import axios from 'axios'
import createPersistedState from "vuex-persistedstate"
Vue.use(Vuex)
//用于相应组件里的动作
const actions = {
};
//用于操作数据(state)
const mutations = {
    UPDATE_INFO(state, value){
        state.info = value;
    }
};
//用于存储数据
const state = {
    info:{}
};
const store = new Vuex.Store({
    actions :actions,//或直接写actions
    mutations:mutations,
    state:state,
    modules:{
        loginAbout:loginAbout,
        global:global,
        dealfile:dealfile
        
    },
    // plugins:[createPersistedState()],
    plugins:[createPersistedState({
        storage:window.sessionStorage
    })]
})
//暴露store
export default store