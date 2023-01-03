import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

import router from './route'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';


const app = createApp(App)
app.use(router)
app.use(VueAxios, axios) // 👈
app.use(VueSweetalert2)
app.mount('#app')

