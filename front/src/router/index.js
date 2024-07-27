import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import KategorijeView from "@/views/KategorijeView.vue";
import VestiPoKategorijama from "@/views/VestiPoKategorijama.vue";
import SingleVest from "@/views/SingleVest.vue";

Vue.use(VueRouter)

const routes = [
  /*  222222222222222222222222 RUTE ZA BEK 222222222222222222222222222222222 */
  {
    path: '/back',
    name: 'Home',
    component: KategorijeView
  },
  {
    path: '/back/vesti',
    name: 'SveVesti',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/SveVestiView.vue')
  },
  {
    path: '/back/vestiByCategory/:categoryId',
    name: 'VestiByCategory',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/VestiView.vue'),
    props: true
  },
  {
    path: '/back/kategorije',
    name: 'Kategorije',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/KategorijeView.vue')
  },
  {
    path: '/back/korisnici',
    name: 'Korisnici',
    meta: {
      authRequired: false,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/KorisniciView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  /*  222222222222222222222222 RUTE ZA FRONT 222222222222222222222222222222222 */
  {
    path: '/',
    name: 'HomePage',
    component: Home
  },
  {
    path: '/kategorije/:categoryId',
    name: 'VestiPoKategorijama',
    component: VestiPoKategorijama,
    props: true
  },
  {
    path: '/singlevest/:id',
    name: 'SingleVest',
    component: SingleVest,
    props: true
  },
  {
    path: '/najcitanije',
    name: 'Najcitanije',
    component: Home
  },
  {
    path: '/kategorije',
    name: 'PoKategorijama',
    component: Home
  },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    }
  }

  next();
});

export default router
