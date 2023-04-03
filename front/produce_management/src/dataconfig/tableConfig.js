const MyConfig={
    getEmployee:{
        fixed:[true, true, false, false, false, false, false, false, false, false, false, false, false, false],//14个属性，固定列
        isCheck:false,//多选栏
        width:['80px','80px','80px','80px','80px','80px','120px','120px','180px','80px','120px','170px','120px','120px'],
        // edit:false
    },
    updateEmployee:{
        fixed:[true, true, false, false, false, false, false, false, false, false, false, false, false, false],//14个属性，固定列
        isCheck:true,//多选栏
        width:['80px','80px','80px','80px','80px','80px','120px','120px','180px','80px','120px','170px','120px','120px'],
        edit:true
    }
}
export default MyConfig;