import { createStore } from "vuex";

export interface pageState {
    // 控制侧边栏收缩与展开
    collapse: boolean;
}

export const store = createStore<pageState>({
    state: {
        collapse: false,
    },

    mutations: {
        setCollapse(state: pageState, collapse: boolean) {
            state.collapse = collapse;
        }
    },

    getters: {
        getCollapse(state: pageState) {
            return state.collapse;
        }
    }
})

export default store;