<template>
    <el-tabs type="border-card" class="depart-father" @tab-click="getDepart">
        <el-tab-pane label="离职申请表下载">
            <el-steps :active="step" align-center finish-status="success" class="step">
                <el-step title="下载并填写离职申请表" description="请按规范填写"></el-step>
                <el-step title="提交离职申请表" description="提交时请将文件重命名为:(工号)-离职申请表"></el-step>
                <el-step title="等待审核" description="或许需要一段时间，请耐心等待"></el-step>
                <el-step title="审核通过" description="审核通过，离职手续办理完成"></el-step>
            </el-steps>
            <el-row :gutter="20">
                <el-col :span="12" class="mycol"><el-link href="http://localhost:5000/employee/departForm?type=depart" target="_blank">下载</el-link></el-col>
                <el-col :span="12" class="mycol">
                    <el-upload
                        class="upload-demo"
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :on-change="handleChange"
                        :limit="1"
                        :auto-upload="false"
                        :on-remove="handlerRemove"
                        :file-list="fileList">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                    </el-upload>
                    <el-button style="position:absolute;top:100px;left:700px" @click="trueUpload">上传到服务器</el-button>
                </el-col>
            </el-row>
        </el-tab-pane>
        <el-tab-pane label="离职申请审核" @click="getDepart">
            <DepartAudit :updateDepart="updateDepart" />
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import '../../assets/js/xlsx.core.min.js'
    import axios from 'axios'
    import DepartAudit from './DepartAudit'
    import {mapState, mapGetters, mapActions, mapMutations} from 'vuex'
    export default {
        name:'DepartForm',
        components:{
            DepartAudit,
        },
        data() {
            return {
                step:0,
                fileList: [],
                myfile:'',
                updateDepart:true
            }
        },
        computed:{
            ...mapGetters('dealfile',{
                'getFile':'getFile'
            })
        },
        watch:{
            fileList(value){
            }
        },
        methods:{
            getDepart(tab, event){
                if(tab.index==1){
                    this.updateDepart = !this.updateDepart;
                }
            },
            ...mapMutations('dealfile',{
                'setFile':'SETFILE'
            }),
            trueUpload(){
                if(this.fileList.length==1){
                    let that = this;
                    this.step=2;
                    axios({
                        method: 'post',
                        url: '/getEmployee/insertDepart',
                        data:this.myfile
                    })
                    .then(function (response) {
                        console.log(response.data)
                    });
                } else {
                    this.$message.error("请先上传文件");
                }
            },
            handlerRemove(file, fileList){
                this.step=0;
                this.fileList = fileList;
            },
            handleChange(file, fileList) {
                this.step=1;
                this.fileList = fileList;
                this.fileTemp = file.raw;
                if(this.fileTemp){
                    if((this.fileTemp.type == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') 
                        || (this.fileTemp.type == 'application/vnd.ms-excel')){
                        this.importfxx(this.fileTemp);
                    } else {
                        this.$message({
                            type:'warning',
                            message:'附件格式错误，请删除后重新上传！'
                        })
                    }
                } else {
                    this.$message({
                        type:'warning',
                        message:'请上传附件！'
                    })
                }
            },
            importfxx(obj) {
                let _this = this;
                let inputDOM = this.$refs.inputer;
                // 通过DOM取文件数据
    
                this.file = event.currentTarget.files[0];
    
                var rABS = false; //是否将文件读取为二进制字符串
                var f = this.file;
    
                var reader = new FileReader();
                //if (!FileReader.prototype.readAsBinaryString) {
                FileReader.prototype.readAsBinaryString = function(f) {
                    var binary = "";
                    var rABS = false; //是否将文件读取为二进制字符串
                    var pt = this;
                    var wb; //读取完成的数据
                    var outdata;
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        var bytes = new Uint8Array(reader.result);
                        var length = bytes.byteLength;
                        for (var i = 0; i < length; i++) {
                            binary += String.fromCharCode(bytes[i]);
                        }
                        //此处引入，用于解析excel
                        var XLSX = require("xlsx");
                        if (rABS) {
                            wb = XLSX.read(btoa(fixdata(binary)), {
                            //手动转化
                            type: "base64"
                            });
                        } else {
                            wb = XLSX.read(binary, {
                            type: "binary"
                            });
                        }
                        outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]); 
                        //outdata就是读取的数据（不包含标题行即表头，表头会作为对象的下标
                        console.log(outdata);
                        // console.log(outdata)
                        //此处可对数据进行处理
                        let arr = [];
                        let obj = {}
                        obj.employeeID=outdata[0]['__EMPTY']
                        obj.employeename = outdata[0]['__EMPTY_2']
                        obj.department=outdata[0]['__EMPTY_4']
                        obj.titleid=outdata[0]['__EMPTY_6']
                        obj.workdate=outdata[1]['__EMPTY']
                        obj.apptime=outdata[1]['__EMPTY_2']
                        obj.time=outdata[1]['__EMPTY_4']
                        let mytype = outdata[2]['__EMPTY']
                        switch (mytype) {
                            case '辞职':
                                obj.type=0
                                break;
                            case '辞退':
                                obj.type=1
                                break;
                            case '开除':
                                obj.type=2
                                break;
                            default:
                                obj.type=3
                                break;
                        }
                        obj.reason = outdata[2]['__EMPTY_2']
                        _this.myfile = obj;
                        console.log(obj)
                        console.log(_this.myfile)
                        _this.da=arr;
                        _this.dalen=arr.length;
                    };
                    reader.readAsArrayBuffer(f);
                };
                if (rABS) {
                    reader.readAsArrayBuffer(f);
                } else {
                    reader.readAsBinaryString(f);
                }
            }
        }
    }
</script>

<style scoped>
    .depart-father{
        width: 100%;
        height: 98%;
    }
    .el-tabs--border-card{
        border: 1px solid rgb(208, 211, 216);
    }
    .step{
        margin-top: 30px;
    }
    .mycol{
        height: 300px;
    }
</style>