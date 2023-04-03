<template>
  <amap
  ref="mymap"
  :center="center"
  :zoom="zoom"
  >
  <amap-marker :position="position" draggable 
  :label="{
          content: date+'  '+name+' 在此签到',
          direction: 'bottom',
        }"
  />
  <amap-polygon :path="path" 
  :fillColor="fillColor"
  :strokeWeight="strokeWeight"
  :strokeColor="strokeColor"
  />
  </amap>
</template>

<script>
  export default {
    name:'MapCard',
    data() {
      return {
        center:[120.351631,30.31372],
        zoom:16,
        path:[[120.348504,30.315731],[120.357859,30.315694],[120.357988,30.3111],[120.348418,30.310211]],
        strokeColor:'#000A58',
        fillColor:'rgb(255, 255, 255, 0.8)',
        strokeWeight:1,
        position:null
        // position:[120.348504,30.315731]
      }
    },
    props:[
      'date','name','myposition'
    ],
    watch:{
      'myposition':{
        handler:function(newVal){
          this.position = newVal;
          console.log(newVal)
        },
        // immediate:true,
        deep:true
      }
    },
    mounted(){

    }
  }
</script>

<style>

</style>