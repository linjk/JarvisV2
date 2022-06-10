<script lang="ts" setup>
    import Header from './header/header.vue'
    import Menu from './menu/menu.vue'
</script>

<template>
  <el-container class="layout">
      <el-aside class="aside" width="200px"><Menu></Menu></el-aside>
      <el-container>
        <el-header class="header"><Header></Header></el-header>
        <el-main class="main"></el-main>
      </el-container>
    </el-container>
</template>

<style scoped>
.layout{
    height: 100%;
}
.header{
    
}
.aside{
    
}

.main{
    
}
</style>