import { createRouter, createWebHistory } from 'vue-router';
import axios from 'axios';
import LoginView from '../views/LoginView.vue';
import SignupView from '../views/SignupView.vue';
import MainView from '@/views/MainView.vue';

const routes = [
  { path: '/', redirect: '/main' }, // 기본 경로를 로그인으로 리다이렉트
  { path: '/login', component: LoginView },
  { path: '/signup', component: SignupView},
  { path: '/main', component: MainView},
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// router.beforeEach(async (to, from, next) => {
//   const isAuthPage = to.path === '/login' || to.path === '/signup';


//   try {
//     const res = await axios.get('/auth/check', { withCredentials: true });
    
//     if (res.status === 200 && isAuthPage) {
//       return next('/main');
//     }

//     next();
//   } catch (e) {
//     if (!isAuthPage) {
//       return next('/main');
//     }
//     next();
//   }
// });


export default router;
