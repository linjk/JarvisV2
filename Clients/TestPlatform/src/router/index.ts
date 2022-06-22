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
                component: () => import('../layout/views/index/dashboard.vue'),
                meta: {
                    title: '首页' 
                }
            }
        ]       
    },
    // 系统管理 【系统维护、维护日志】
    {
        path: '/system',
        component: layout,
        name: 'System',
        meta: {
            title: '系统管理' 
        },
        children: [
            {
                path: '/system/manage',
                component: () => import('../layout/views/systems/SystemManagement.vue'),
                meta: {
                    title: '系统维护' 
                }
            },
            {
                path: '/system/manageLog',
                component: () => import('../layout/views/systems/SystemManageLogs.vue'),
                meta: {
                    title: '系统日志' 
                }
            }
        ]
    },

    // 接口管理 【接口维护、接口测试、接口日志】
    {
        path: '/interface',
        component: layout,
        name: 'Interface',
        meta: {
            title: '接口管理' 
        },
        children: [
            {
                path: '/interface/manage',
                component: () => import('../layout/views/interfaces/InterfaceManagement.vue'),
                meta: {
                    title: '接口维护' 
                }
            },
            {
                path: '/interface/test',
                component: () => import('../layout/views/interfaces/InterfaceTest.vue'),
                meta: {
                    title: '接口测试' 
                }
            },
            {
                path: '/interface/log',
                component: () => import('../layout/views/interfaces/InterfaceTestLog.vue'),
                meta: {
                    title: '接口日志' 
                }
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router