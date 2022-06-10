<script lang="ts" setup>
</script>

<template>
    <el-menu
        default-active="2"
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
</style>
