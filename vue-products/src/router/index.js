import { createRouter, createWebHistory } from 'vue-router'

const Products = () => import('../pages/Products.vue')

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/products' },
    { path: '/products', component: Products, name: 'products' },
  ],
  scrollBehavior() {
    return { top: 0 }
  },
})

export default router