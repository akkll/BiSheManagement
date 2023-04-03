<template>
    <div>
        <canvas id="mycanvas"></canvas>
        <a href="#" @click="code(options1)" style="text-decoration: none">看不清?点我刷新</a>
    </div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex';
    export default {
        name:'Yzm',
        computed:{
            ...mapState('loginAbout',{
                'options1':'options'
            }),
        },
        props:[
            'isChange'
        ],
        mounted(){
            this.code(this.$store.state.loginAbout.options);
        },
        watch:{
            isChange(){
                // this.code(this.$store.state.loginAbout.options);
                this.code(this.options1);
            }
        },
        methods:{
          //生产验证码
          produceCode(options){
            var code="";
            var random=new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
              'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
            for(var i=0;i<4;i++){
              var index=Math.floor(Math.random()*62);
              code+=random[index];
            }
            this.$store.commit('loginAbout/setTxt',code);
            // options.txt=code;
          },
          //产生随机数字
          randomNum(min,max){
            return Math.floor(Math.random()*(max-min)+min);
          },
          //产生随机颜色
          randomColor(min,max){
            var r=this.randomNum(min,max);
            var g=this.randomNum(min,max);
            var b=this.randomNum(min,max);
            return "rgb("+r+","+g+","+b+")";
          },
          //生产验证码背景
          code(options){
            this.produceCode(options);
            var canvas=document.getElementById(options.canvasId);
            canvas.width=options.width||300;//画布的宽
            canvas.height=options.height||150;//画布的高
            var ctx=canvas.getContext("2d");//创建一个canvas对象
            ctx.textBaseline="middle";
            ctx.fillStyle=this.randomColor(60,255);
            ctx.fillRect(0,0,canvas.width,canvas.height);
            for(var i=0;i<options.txt.length;i++){
              var txt=options.txt.charAt(i);
              ctx.font='50px SimHei';
              ctx.fillStyle = this.randomColor(60, 180); /**随机生成字体颜色*/
              ctx.shadowOffsetY = this.randomNum(-3, 3);
              ctx.shadowBlur = this.randomNum(-3, 3);
              ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
              var x = options.width / (options.txt.length+1) * (i+1);
              var y = options.height / 2;
              var deg = this.randomNum(-30, 30);
              /**设置旋转角度和坐标原点*/
              ctx.translate(x, y);
              ctx.rotate(deg * Math.PI / 180);
              ctx.fillText(txt, 0, 0);
              /**恢复旋转角度和坐标原点*/
              ctx.rotate(-deg * Math.PI / 180);
              ctx.translate(-x, -y);
            }
            /**1~4条随机干扰线随机出现*/
            for (var i = 0; i < this.randomNum(1,4); i++) {
              ctx.strokeStyle = this.randomColor(40, 180);
              ctx.beginPath();
              ctx.moveTo(this.randomNum(0, options.width), this.randomNum(0, options.height));
              ctx.lineTo(this.randomNum(0, options.width), this.randomNum(0, options.height));
              ctx.stroke();
            }
            /**绘制干扰点*/
            for (var i = 0; i < options.width / 6; i++) {
              ctx.fillStyle = this.randomColor(0, 255);
              ctx.beginPath();
              ctx.arc(this.randomNum(0, options.width), this.randomNum(0, options.height), 1, 0, 2 * Math.PI);
              ctx.fill();
            }
          }
        }
    }
</script>

<style scoped>
    #mycanvas{
        width: 130px;
        margin-right: 20px;
        border-radius: 4px;
    }

</style>