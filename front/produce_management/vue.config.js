const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    port: 8080, // 此处修改你想要的端口号
    proxy:{
      '/managerByName':{
        target:'http://localhost:5000/manager/getByName',
        pathRewrite:{'^/managerByName':''},
      },
      '/getEmployee':{
        target:'http://localhost:5000/employee/',
        pathRewrite:{'^/getEmployee':''},
      },
      '/order':{
        target:'http://localhost:5000/order/',
        pathRewrite:{'^/order':''},
      },
      '/processUnit':{
        target:'http://localhost:5000/processUnit/',
        pathRewrite:{'^/processUnit':''},
      },
      '/view':{
        target:'http://localhost:5000/view/',
        pathRewrite:{'^/view':''},
      },
      '/liushui':{
        target:'http://localhost:5000/liushui/',
        pathRewrite:{'^/liushui':''},
      },
      '/material':{
        target:'http://localhost:5000/material/',
        pathRewrite:{'^/material':''},
      },
    },
    // before:require('./src/mock/index.js')
  },
})
