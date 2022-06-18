import Vue from 'vue';
import Vuex from 'vuex'

Vue.use(Vuex)
const store = new Vuex.Store({
    state:{
        QQData:{

        },
        provinces:[],
    },
    mutations:{
        INIT_QQ_DATA(state,data){
            state.QQData = data;
            state.provinces = data.data.diseaseh5Shelf.areaTree[0].children
        },
    },
    actions:{
        initQQData(context,data){
            context.commit("INIT_QQ_DATA",data);
        },
    },
})


export default store;