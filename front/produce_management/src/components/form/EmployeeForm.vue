<template>
  <div class="form-father">
        <el-dialog
        title="修改员工信息"
        :visible.sync="isVisiable"
        width="50%"
        top="5vh"
        :before-close="handleClose">
        <div class="form-son">
            <el-form ref="form" :model="form" label-width="80px">
                <el-col :span="11">
                    <el-form-item label-width="100px" label="职工ID：">
                        <el-input v-model="form.id" disabled></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="职工姓名：">
                        <el-input v-model="form.name" ></el-input>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="性别：">
                        <el-select v-model="form.gender">
                            <el-option v-for="(item) in gender" :key="item.id" :label="item.value" :value="item.id">
                                
                            </el-option>
                        </el-select>
                    </el-form-item>                    
                </el-col>  
                <el-col :span="11">
                    <el-form-item label-width="100px" label="职衔：">
                        <el-select v-model="form.titleID">
                            <el-option v-for="(item) in title" :key="item.id" :label="item.value" :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>                    
                </el-col>    
                <el-col :span="11">
                    <el-form-item label-width="100px" label="在职情况：">
                        <el-select v-model="form.ishere" disabled>
                            <el-option v-for="(item) in ishere" :key="item.id" :label="item.value" :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="入职日期：">
                        <el-date-picker disabled
                            v-model="form.workdate"
                            value-format="yyyy-MM-dd"
                            align="right"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="离职日期：">
                        <el-date-picker disabled
                            v-model="form.departdate"
                            value-format="yyyy-MM-dd"
                            align="right"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="身份证：">
                        <el-input v-model="form.card" disabled></el-input>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="所属单位">
                        <el-select v-model="form.unit">
                            <el-option v-for="(item) in unit" :key="item.id" :label="item.value" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>                    
                </el-col>
                <el-col :span="11">
                    <el-form-item label-width="100px" label="生日日期：">
                        <el-date-picker
                            v-model="form.birthday"
                            value-format="yyyy-MM-dd"
                            align="right"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>                                     
                </el-col>  
                <el-col :span="11">
                    <el-form-item label-width="100px" label="居住地：">
                        <el-input v-model="form.address"/>
                    </el-form-item>                    
                </el-col>  
                <el-col :span="11">
                    <el-form-item label-width="100px" label="手机号码：">
                        <el-input v-model="form.mobile"/>
                    </el-form-item>                    
                </el-col>  
                <el-col :span="11">
                    <el-form-item label-width="100px" label="微信：">
                        <el-input v-model="form.wechat"></el-input>
                    </el-form-item>                    
                </el-col>  
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="isVisiable = false">取 消</el-button>
            <el-button type="primary" @click="changeData">确 定</el-button>
        </span>
        </el-dialog>      
  </div>
</template>

<script>
import axios from 'axios';
import {mapMutations} from 'vuex'
import {MessageBox} from 'element-ui'

export default {
    name:'EmployeeForm',
    data() {
        return {
            isVisiable:false,
            form: {
                id:'',
                name: '',
                gender:'',
                titleID:'',
                title:'',
                ishere:'',
                workdate:'',
                departdate:'',
                card:'',
                unit:'',
                birthday:'',
                mobile:'',
                wechat:''
            },
            gender:[
                {id:1, value:'男'},
                {id:0, value:'女'},
            ],
            title:[
                {id:1, value:'主管'},
                {id:0, value:'技术员'},
            ],
            ishere:[
                {id:1, value:'在职'},
                {id:0, value:'离职'},
            ],
            unit:[
                {id:1, value:'一'},
                {id:2, value:'二'},
                {id:3, value:'三'},
                {id:4, value:'四'},
            ],
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                },
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                    picker.$emit('pick', new Date());
                    }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                    }
                }]
            },
        }
    },
    props:[
        'dialogVisible',
        'currentEmployee'
    ],
    methods:{
        ...mapMutations('global',{
            'changePageno':'SETPAGENO',
            'changePagenum':'SETPAGENUM',
        }),
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      changeData(){
          var that = this;
            axios({
                method: 'post',
                url: '/getEmployee',
                data:this.form
            })
            .then(function (response) {
                if(response.data.flag){
                    console.log(response.data)
                };
                that.changePageno(1);
                // that.changePageno(1);
                that.isVisiable = false
            }); 
          
      }
    },
    watch:{
        dialogVisible:{
            handler:function(){
                this.isVisiable=!this.isVisiable;
            }
        },
        currentEmployee:{
            deep:true, 
            handler:function(){
                let va = JSON.stringify(this.currentEmployee);
                this.form = JSON.parse(va)
            }
        }
    }
}
</script>

<style scoped>
    .form-father{
        height: 80%;
        width: 80%;
    }
    .form-son{
        height: 500px;
        text-align: left;
    }
    .el-select{
        width: 100%;
    }
    .el-date-editor.el-input{
        width:100%
    }
</style>