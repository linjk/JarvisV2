<script lang="ts" setup>
import {ref, computed} from 'vue'
import menuLogoVue from './menuLogo.vue';
import MenuItem from './menuItem.vue';
import { useStore } from 'vuex'

const store = useStore();

const isCollapse = computed(() => {
  return store.getters['getCollapse']
})
</script>

<template>
    <menuLogoVue v-if="!isCollapse" class="layout-logo"></menuLogoVue>
    <!-- 侧边导航栏 -->
    <el-menu
        active-text-color="#ffd04b"
        background-color="#304156"
        default-active="2"
        text-color="#fff"
        :collapse = "isCollapse"
        class="el-menu-vertical-demo">
        <MenuItem></MenuItem>
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
