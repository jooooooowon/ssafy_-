import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/user/Loginview.vue'
import RegisterView from '../views/user/RegisterView.vue'
import MyPageView from '../views/user/MyPageView.vue'
import RankListView from '../views/RankListView.vue'
import VideoListView from '../views/VideoListView.vue'
import UpdateOrDeleteView from '../components/user/myPage/UpdateOrDelete.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },
    {
      path: '/myPage',
      name: 'myPage',
      component: MyPageView,
    },
    {
      path: '/ranklist',
      name: 'ranklist',
      component: RankListView,
    },
    {
      path: '/videolist',
      name: 'videolist',
      component: VideoListView,
    },
  ],
})

export default router
