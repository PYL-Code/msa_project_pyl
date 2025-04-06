import { createRouter, createWebHistory } from 'vue-router'
import MyInfo from '../views/myinfo.vue'
import MyReservation from '../views/reservation.vue'
import MyPage from '../views/mypage.vue'
import Update from '../views/update.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/mypage',
            name: 'mypage',
            component: MyPage
        },
        {
            path: '/mypage/myinfo',
            name: 'myinfo',
            component: MyInfo
        },
        {
            path: '/mypage/myinfo/update',
            name: 'update',
            component: Update
        },
        {
            path: '/mypage/reservations',
            name: 'myreservation',
            component: MyReservation
        }
    ]
})

export default router