<template>
    <div class="mydiv">
        <div class="foot">版权所有：浙江理工大学朱玉奇</div>
        <div class="foot">
            Copyright © 2004-2023  ZSTU 朱玉奇 版权所有
        </div>
    </div>
</template>

<script>
    export default {
        name:'Banner',
        data() {
            return {
            }
        },
    }
</script>

<style scoped>
    .mydiv{
        /* background-color: pink; */
        height: 100%;
        display:flex;
        flex-direction: column;
        justify-content:flex-end;
        align-items: center;
        flex-wrap: wrap;
    }
    .foot{
        font-size: 10px;
        width: 100%;
        height: 35px;
        line-height: 40px;
        display: flex;
        justify-content:center;
        align-items: flex-end;
        opacity:0.7;
    }
</style>