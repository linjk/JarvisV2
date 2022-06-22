import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import layout from '../layout/index.vue'

const routes : Array<RouteRecordRaw> = [
    {
        path: '/',
        component: layout,
        redirect: '/dashboard',
        children: [
            {
                path: '/dashboard',
                component: () => import('../layout/views/index/dashboard.vue')
            }
        ]       
    },
    // 系统管理 【系统维护、维护日志】
    {
        path: '/system',
        component: layout,
        name: 'System',
        children: [
            {
                path: '/system/manage',
                component: () => import('../layout/views/systems/SystemManagement.vue')
            },
            {
                path: '/system/manageLog',
                component: () => import('../layout/views/systems/SystemManageLogs.vue')
            }
        ]
    },

    // 接口管理 【接口维护、接口测试、接口日志】
    {
        path: '/interface',
        component: layout,
        name: 'Interface',
        children: [
            {
                path: '/interface/manage',
                component: () => import('../layout/views/interfaces/InterfaceManagement.vue')
            },
            {
                path: '/interface/test',
                component: () => import('../layout/views/interfaces/InterfaceTest.vue')
            },
            {
                path: '/interface/log',
                component: () => import('../layout/views/interfaces/InterfaceTestLog.vue')
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router