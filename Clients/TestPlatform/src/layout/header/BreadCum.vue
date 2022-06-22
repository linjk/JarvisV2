<script lang="ts" setup>
import { Ref, ref, watch } from 'vue'
import { useRoute, RouteLocationMatched } from 'vue-router'

// 获取当前路由信息
const route = useRoute();

const tabs : Ref<RouteLocationMatched[]> = ref([]);

const getBreadCum = () => {
  let matched = route.matched.filter((item) => item.meta && item.meta.title);
  const first = matched[0]
  if (first.path !== '/dashboard') {
    matched = [{path: '/dashboard', meta: {'title': '首页'}} as any ].concat(matched)
  }
  tabs.value = matched;
}

getBreadCum();

watch(() => route.path, () => getBreadCum());
</script>

<template>
    <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="item in tabs">
          {{item.meta.title}}
        </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<style scoped>

</style>
