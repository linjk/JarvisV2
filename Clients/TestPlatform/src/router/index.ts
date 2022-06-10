import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import index from '../layout/index.vue'

const routes : Array<RouteRecordRaw> = [
    {
        path: '/',
        component: index
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router