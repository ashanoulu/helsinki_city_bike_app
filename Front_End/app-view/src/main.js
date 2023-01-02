import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
// import {createRouter} from "vue-router"

// import { routes } from './route'
import router from './route'

// const router = VueRouter.createRouter({
//     history: VueRouter.createWebHashHistory(),
//     routes,
// })

// const router = createRouter({
//     history,
//     // mode: 'history', //VueRouter.createWebHashHistory(),
//     routes
// });

// createApp.router = router



const app = createApp(App)
app.use(router)
app.use(VueAxios, axios) // 👈
app.mount('#app')

