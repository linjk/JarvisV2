<script lang="ts" setup>
import {ref, computed} from 'vue'
import { useStore } from 'vuex'
import {Fold, Expand} from '@element-plus/icons-vue'

const store = useStore();

const localCollapse = ref(false)

const isCollapse = computed(() => {
    localCollapse.value = store.getters['getCollapse']
    return store.getters['getCollapse']
})

const changeCollapse = () => {
    store.commit('setCollapse', localCollapse.value = !localCollapse.value)
}
</script>

<template>
    <el-icon>
        <component class="icons" :is="isCollapse?Expand:Fold" @click="changeCollapse"></component>
    </el-icon>
</template>

<style scoped>
.el-icon {
    font-size: 22px;
    margin-right: 10px;
}
</style>
