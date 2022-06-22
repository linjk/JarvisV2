<script lang="ts" setup>
import Collapse from './Collapse.vue'
import BraedCum from './BreadCum.vue'
import UserInfo from './UserInfo.vue'
</script>

<template>
    <div style="display:flex; align-items:center">
        <Collapse></Collapse>    
        <BraedCum></BraedCum>
    </div>
    <UserInfo userName="Hi"></UserInfo>
</template>

<style scoped>

</style>
