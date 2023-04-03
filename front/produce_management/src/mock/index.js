import Mock from 'mockjs'
import myall from './json/mydata'
import getEmployee from './json/getEmployee'
import updateEmployee from './json/updateEmployee'
// RegExp('/managerByName' + ".*")
// Mock.mock(RegExp('/getEmployee' + ".*"), 'get', (config) => {
//     //需要注意返回的 json 格式数据
//     const obj = JSON.parse(config.body)
//     return getEmployee
// }),

// Mock.mock(RegExp('/updateEmployee' + ".*"), 'get', (config) => {
//   //需要注意返回的 json 格式数据
//   const obj = JSON.parse(config.body)
//   return updateEmployee
// })

// Mock.mock(RegExp('/managerByName' + ".*"), 'get', (config) => {
//   //需要注意返回的 json 格式数据
//   const obj = JSON.parse(config.body)
//   return myall.login
// })
