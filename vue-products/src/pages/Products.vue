<script setup>
import HeaderNav from '../components/HeaderNav.vue'
import HeroBanner from '../components/HeroBanner.vue'
import SidebarCategories from '../components/SidebarCategories.vue'
import ProductCard from '../components/ProductCard.vue'
import Pagination from '../components/Pagination.vue'
import SiteFooter from '../components/SiteFooter.vue'
import { ref, computed } from 'vue'

const categories = ['工业系统', '工程设计', '工程管理', '工程测试']
const current = ref('工业系统')
const page = ref(1)
const pageSize = 9

const allProducts = Array.from({ length: 12 }, (_, i) => ({
  id: i + 1,
  title: '工业系统',
  subtitle: '新式工程管理系统',
  imageUrl:
    'https://images.unsplash.com/photo-1501594907352-04cda38ebc29?q=80&w=1200&auto=format&fit=crop',
}))

const filtered = computed(() => allProducts)
const pages = computed(() => Math.max(1, Math.ceil(filtered.value.length / pageSize)))
const visible = computed(() => {
  const start = (page.value - 1) * pageSize
  return filtered.value.slice(start, start + pageSize)
})

function onSelectCategory(cat) {
  current.value = cat
}
</script>

<template>
  <div class="page">
    <HeaderNav />
    <HeroBanner />

    <main class="main container">
      <SidebarCategories
        :items="categories"
        :current="current"
        @select="onSelectCategory"
      />

      <section class="content">
        <div class="grid">
          <ProductCard v-for="p in visible" :key="p.id" :product="p" />
        </div>

        <div class="pager">
          <Pagination v-model:page="page" :pages="pages" />
        </div>
      </section>
    </main>

    <SiteFooter />
  </div>
</template>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}
.main {
  display: grid;
  grid-template-columns: 200px 1fr;
  gap: 24px;
  margin: 24px auto;
}
.content .grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
}
.pager { display: flex; justify-content: center; margin: 20px 0; }

@media (max-width: 960px) {
  .main { grid-template-columns: 1fr; }
  .content .grid { grid-template-columns: repeat(2, 1fr); }
}
@media (max-width: 640px) {
  .content .grid { grid-template-columns: 1fr; }
}
</style>