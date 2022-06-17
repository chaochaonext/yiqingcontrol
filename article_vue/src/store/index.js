import Vue from 'vue';
import Vuex from 'vuex'

Vue.use(Vuex)
const store = new Vuex.Store({
    state:{
        QQData:{

        },
    },
    mutations:{
        INIT_QQ_DATA(state,data){
            state.QQData = data;
        },
        GET_QQ_DATA(state){
            console.log(state.QQData);
            return state.QQData;
            
        }
    },
    actions:{
        initQQData(context,data){
            context.commit("INIT_QQ_DATA",data);
        },
        getQQData(context){
            return context.commit("GET_QQ_DATA");
        }
    },
})


export default store;