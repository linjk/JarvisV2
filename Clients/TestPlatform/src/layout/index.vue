<script lang="ts" setup>
    import Header from './header/header.vue'
    import Menu from './menu/menu.vue'
</script>

<template>
  <el-container class="layout">
      <el-aside class="aside" width="auto"><Menu></Menu></el-aside>
      <el-container>
        <el-header class="header"><Header></Header></el-header>
        <el-main class="main"><router-view></router-view></el-main>
      </el-container>
    </el-container>
</template>

<style scoped>
.layout{
    height: 100%;
}
.header{
    height: 50px;
    border-bottom: 1px solid #e5e5e5;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.aside{
    background-color: #304156;
}

.main{
    
}
</style>