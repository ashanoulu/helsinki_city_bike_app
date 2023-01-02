import { createWebHistory, createRouter } from 'vue-router'

import Journey from "./components/journey/Journey";

export const routes = [
    {
        path: '/journeys',
        name: 'journey-view',
        meta: {layout: 'journey-view'},
        component: Journey
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes, //same --- > routes:routes
})
export default router
