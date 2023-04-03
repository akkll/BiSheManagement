import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router/index'
import store from './store/index'//引入store
import './mock/index.js'
import XLSX from 'xlsx'
import echarts from 'echarts'

import VueAMap from 'vue-amap'
Vue.use(VueAMap)
VueAMap.initAMapApiLoader({
  // 高德的key
  key: 'dcd9276b1916258414bcf1ec7dfc17f6',
  // 插件集合
  plugin: [
    'AMap.Autocomplete',
    'AMap.PlaceSearch',
    'AMap.Scale',
    'AMap.OverView',
    'AMap.ToolBar',
    'AMap.MapType',
    'AMap.PolyEditor',
    'AMap.CircleEditor',
    'AMap.Geocoder',
    'AMap.Geolocation'
  ],
  // 高德 sdk 版本，默认为 1.4.4
  v: '1.4.10'
})

// 完整引入elementui
// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// Vue.use(ElementUI);

import {Button, Select, Table, Container, Aside, Row, Col, Image, Menu, Submenu, MenuItemGroup, MenuItem, Header, Input, 
Badge, Dropdown, DropdownMenu, DropdownItem, Main, Tabs, TabPane, Footer, TableColumn, Pagination,Dialog, Form, FormItem, Option, DatePicker, Tag, Steps, Step, Link, Upload, Transfer, Switch, Message, Card, MessageBox} from 'element-ui'
Vue.component(MessageBox.name, MessageBox);
Vue.component(Card.name, Card);
Vue.component(Switch.name, Switch);
Vue.component(Steps.name, Steps);
Vue.component(Step.name, Step);
Vue.component(Link.name, Link);
Vue.component(Upload.name, Upload);
Vue.component(Transfer.name, Transfer);
Vue.component(Dialog.name, Dialog);
Vue.component(Form.name, Form);
Vue.component(FormItem.name, FormItem);
Vue.component(Option.name, Option);
Vue.component(DatePicker.name, DatePicker);
Vue.component(Tag.name, Tag);
Vue.component(TableColumn.name, TableColumn);
Vue.component(Pagination.name, Pagination);
Vue.component(Button.name, Button);
Vue.component(Select.name, Select);
Vue.component(Table.name, Table);
Vue.component(Container.name, Container);
Vue.component(Aside.name, Aside);
Vue.component(Row.name, Row);
Vue.component(Col.name, Col);
Vue.component(Image.name, Image);
Vue.component(Menu.name, Menu);
Vue.component(Submenu.name, Submenu);
Vue.component(MenuItemGroup.name, MenuItemGroup);
Vue.component(MenuItem.name, MenuItem);
Vue.component(Header.name, Header);
Vue.component(Input.name, Input);
Vue.component(Badge.name, Badge);
Vue.component(Dropdown.name, Dropdown);
Vue.component(DropdownMenu.name, DropdownMenu);
Vue.component(DropdownItem.name, DropdownItem);
Vue.component(Main.name, Main);
Vue.component(Tabs.name, Tabs);
Vue.component(TabPane.name, TabPane);
Vue.component(Footer.name, Footer);

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
Vue.use(Antd);

import AmapVue from '@amap/amap-vue';
AmapVue.config.version = '2.0'; // 默认2.0，这里可以不修改
AmapVue.config.key = 'dcd9276b1916258414bcf1ec7dfc17f6';
AmapVue.config.plugins = [
  'AMap.ToolBar',
  'AMap.MoveAnimation',
  // 在此配置你需要预加载的插件，如果不配置，在使用到的时候会自动异步加载
];
Vue.use(AmapVue);
Vue.prototype.$message = Message
Vue.prototype.$msgbox = MessageBox;
Vue.prototype.$confirm = MessageBox.confirm;




Vue.use(XLSX)
Vue.config.productionTip = false
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
