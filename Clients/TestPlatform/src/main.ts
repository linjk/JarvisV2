import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import * as Icons from '@element-plus/icons-vue'
import store from  './store'

const app = createApp(App)
app.use(store).use(router).use(ElementPlus).mount('#app')

// 遍历Icons，以组件形式注册到app
Object.keys(Icons).forEach((key) => {
    app.component(key, Icons[key as keyof typeof Icons])
})
