const loginAbout={
    namespaced:true,
    state:{
        options:{
            canvasId:'mycanvas',
            width:200,
            height:50,
            txt:'' 
        }
    },
    getters:{

    },
    actions:{

    },
    mutations:{
        setTxt(state, value){
            state.options.txt = value;
        }
    }
}
export default loginAbout