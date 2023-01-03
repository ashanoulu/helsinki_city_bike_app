import { createWebHistory, createRouter } from 'vue-router'

import Journey from "./components/journey/Journey";
import Stations from "./components/station/Stations";
import Station from "./components/station/Station";

export const routes = [
    {
        path: '/journeys',
        name: 'journey-view',
        meta: {layout: 'journey-view'},
        component: Journey
    },
    {
        path: '/stations',
        name: 'stations-view',
        meta: {layout: 'stations-view'},
        component: Stations
    },
    {
        path: '/station/:station_id',
        name: 'station-view',
        meta: {layout: 'station-view'},
        component: Station
    },
    {
        path: '/station',
        name: 'station-view',
        meta: {layout: 'station-view'},
        component: Station
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes, //same --- > routes:routes
})
export default router
