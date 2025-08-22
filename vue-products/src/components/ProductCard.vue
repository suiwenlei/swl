<script setup>
const props = defineProps({
  product: { type: Object, required: true },
})
</script>

<template>
  <div class="card">
    <img :src="product.imageUrl" :alt="product.title" />
    <div class="meta">
      <div class="title">{{ product.title }}</div>
      <div class="sub">{{ product.subtitle }}</div>
      <div class="arrow">→</div>
    </div>
  </div>
</template>

<style scoped>
.card {
  border: 1px solid #eee;
  border-radius: 6px;
  overflow: hidden;
  background: white;
}
.card img {
  width: 100%;
  height: 160px;
  object-fit: cover;
  display: block;
}
.meta {
  padding: 10px 12px;
  display: grid;
  grid-template-columns: 1fr auto;
  row-gap: 6px;
}
.title { font-size: 14px; color: #222; }
.sub { font-size: 12px; color: #9aa1a9; grid-column: 1 / -1; }
.arrow { color: #9aa1a9; }
</style>