import axios from 'axios'
const dealfile={
    namespaced:true,
    state:{
        file:{}
    },
    getters:{
        getFile(state){
            return state.file;
        }
    },
    actions:{
    },
    mutations:{
        SETFILE(state, value){
            state.file = value;
        }
    }
}
export default dealfile