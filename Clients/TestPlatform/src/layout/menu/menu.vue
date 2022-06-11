<script lang="ts" setup>
import menuLogoVue from './menuLogo.vue';
</script>

<template>
    <menuLogoVue class="layout-logo"></menuLogoVue>
    <!-- 侧边导航栏 -->
    <el-menu
        active-text-color="#ffd04b"
        background-color="#304156"
        default-active="2"
        text-color="#fff"
        class="el-menu-vertical-demo">
        <el-sub-menu index="1">
            <template #title>
                <el-icon><location /></el-icon>
                <span>系统管理</span>
            </template>
            <el-menu-item index="1-1">系统维护</el-menu-item>
            <el-menu-item index="1-2">系统日志</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="2">
            <template #title>
                <el-icon><icon-menu /></el-icon>
                <span>接口管理</span>
            </template>
            <el-menu-item index="2-1">接口维护</el-menu-item>
            <el-menu-item index="2-2">接口测试</el-menu-item>
            <el-menu-item index="2-3">接口日志</el-menu-item>
        </el-sub-menu>
        
        <el-sub-menu index="2">
            <template #title>
                <el-icon><icon-menu /></el-icon>
                <span>系统审计</span>
            </template>
            <el-menu-item index="3-1">操作记录</el-menu-item>
        </el-sub-menu>
    </el-menu>
</template>

<style scoped>
.el-menu-item {
    display: block;
    text-align: center
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 230px;
  min-height: 400px;
}
.el-menu {
  border-right: none;
}

.el-menu-item {
  color: #f4f4f5 !important;
}

:deep(.el-sub-menu .el-sub-menu__title) {
  color: #f4f4f5 !important;
}
/* .el-submenu .is-active .el-submenu__title {
  border-bottom-color: #1890ff;
} */
:deep(.el-menu .el-menu-item) {
  color: #bfcbd9;
}

/* 菜单点中文字的颜色 */
:deep(.el-menu-item.is-active) {
  color: #409eff !important;
}
/* 当前打开菜单的所有子菜单颜色 */
:deep(.is-opened .el-menu-item) {
  background-color: #1f2d3d !important;
}
/* 鼠标移动菜单的颜色 */
:deep(.el-menu-item:hover) {
  background-color: #001528 !important;
}
/* Logo CSS部分的动画 */
@keyframes logoAnimation {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1);
  }
  100% {
    transform: scale(1);
  }
}
.layout-logo {
  animation: logoAnimation 1s ease-out;
}
</style>
