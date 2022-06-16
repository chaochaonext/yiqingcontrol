import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Main from "@/components/product/Main"
import Map from "@/components/product/Map"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },{
      path: '/main',
      name: 'Main',
      component: Main
    },{
      path: '/map',
      name: 'Map',
      component: Map
    },{
      path: '/index',
      name: 'Index',
      component: () => import('@/components/Index')
    }
  ],
})
