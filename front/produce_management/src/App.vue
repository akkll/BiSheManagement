<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
  }
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
  /* 这个用于设置导航栏的小标题 */
  .el-menu-item-group__title{
    padding: 7px 28px 7px 20px !important;
  }
  /* ::-webkit-scrollbar{display:none} */
  /* ::-webkit-scrollbar{display: none;} */
</style>
