<template>
    <div class="lingliao-form-father">
        111
    </div>
</template>

<script>
    export default {
        name:'LingliaoForm',
        props:[
            'formInfo'
        ],
        methods:{
            lingliao(assignid){
                let that = this;
                axios({
                    method: 'post',
                    url: '/processUnit/Unit12GetMaterialComplete',
                    data:{
                        'assignid':assignid
                    }
                })
                .then(function (response) {
                    that.updateData = !that.updateData;
                    if(that.unit==1||that.unit==2){
                        if(response.data.data!=-1){
                            that.$message({
                                message: '原料不足，还缺'+response.data.data+'斤，请注意补货',
                                type: 'warning'
                            });
                        }
                    }
                }); 
            },
        }
    }
</script>

<style scoped>
    .lingliao-form-father{
        width: 800px;
        height: 500px;
        position: fixed;
        top: 150px;
        left:500px;
        background-color: lightblue;
        z-index: 2000;
    }
</style>