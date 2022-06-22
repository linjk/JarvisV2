<script lang="ts" setup>
    import {reactive} from 'vue';

    let menuList = reactive([
        {
            path: '/',
            meta: {
                title: '首页',
                icon: 'HomeFilled'
            }
        },
        {
            path: '/system',
            meta: {
                title: '系统管理',
                icon: 'Management'
            },
            children: [
                {
                    path: '/system/manage',
                    meta: {
                        title: '系统维护',
                        icon: 'Edit'
                    }
                },
                {
                    path: '/system/manageLog',
                    meta: {
                        title: '维护日志',
                        icon: 'Document'
                    }
                }
            ]
        },
        {
            path: '/interface',
            meta: {
                title: '接口管理',
                icon: 'Management'
            },
            children: [
                {
                    path: '/interface/manage',
                    meta: {
                        title: '接口维护',
                        icon: 'Edit'
                    }
                },
                {
                    path: '/interface/test',
                    meta: {
                        title: '接口测试',
                        icon: 'Watch'
                    }
                },
                {
                    path: '/interface/log',
                    meta: {
                        title: '接口日志',
                        icon: 'Document'
                    }
                }
            ]
        }
    ]);
</script>

<template>
    <template v-for="mn in menuList">
        <el-sub-menu v-if="mn.children && mn.children.length>0" :index="mn.path">
            <template #title style="padding-left: 40px">
                <el-icon>
                    <component class="icons" :is="mn.meta.icon"></component>
                </el-icon>
                <span>{{mn.meta.title}}</span>
            </template>
            <!-- 二级菜单 -->
            <template v-for="ch in mn.children">
                <el-menu-item :index="ch.path">
                    <el-icon>
                        <component class="icons" :is="ch.meta.icon"></component>
                    </el-icon>
                    <span>{{ch.meta.title}}</span>
                </el-menu-item>
            </template>
        </el-sub-menu>
        <el-menu-item v-else :index="mn.path">
            <el-icon>
                <component class="icons" :is="mn.meta.icon"></component>
            </el-icon>
            <span>{{mn.meta.title}}</span>
        </el-menu-item>
    </template>
</template>

<style scoped>
.el-menu-item {
    display: block;
    text-align: center;
    padding-right: 80px;
}    
</style>
