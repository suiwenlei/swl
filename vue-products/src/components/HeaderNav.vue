<script setup>
import { ref } from 'vue'

const navItems = ref([
  { label: '首页', to: '#' },
  { label: '产品服务', to: '/products' },
  { label: '企业介绍', to: '#' },
  { label: '团队介绍', to: '#' },
  { label: '新闻动态', to: '#' },
  { label: '联系我们', to: '#' },
])
</script>

<template>
  <header class="site-header">
    <div class="topbar">
      <span class="crumb">产品服务</span>
    </div>
    <div class="nav">
      <div class="logo">
        <span class="mark">工</span>
        <span class="brand">MOCKINGBOT</span>
      </div>
      <nav class="links">
        <RouterLink
          v-for="item in navItems"
          :key="item.label"
          :to="item.to"
          class="link"
          :class="{ active: item.to === '/products' }"
        >
          {{ item.label }}
        </RouterLink>
      </nav>
    </div>
  </header>
</template>

<style scoped>
.site-header {
  background: #fff;
  border-bottom: 1px solid #eee;
}
.topbar {
  height: 28px;
  display: flex;
  align-items: center;
  padding: 0 24px;
  color: #999;
  font-size: 12px;
  background: #fafafa;
}
.nav {
  max-width: 1200px;
  margin: 0 auto;
  height: 56px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-weight: 700;
}
.logo .mark {
  display: inline-flex;
  width: 28px;
  height: 28px;
  border-radius: 6px;
  align-items: center;
  justify-content: center;
  background: #e31d3b;
  color: #fff;
}
.logo .brand {
  letter-spacing: 1px;
}
.links {
  display: flex;
  gap: 18px;
}
.link {
  color: #444;
  text-decoration: none;
  padding: 6px 10px;
  border-radius: 4px;
}
.link:hover {
  background: #f5f5f5;
}
.link.active {
  background: #2a7cf6;
  color: white;
}
</style>