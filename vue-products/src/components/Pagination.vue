<script setup>
const props = defineProps({
  page: { type: Number, default: 1 },
  pages: { type: Number, default: 1 },
})
const emit = defineEmits(['update:page'])

function goTo(page) {
  const next = Math.min(Math.max(1, page), props.pages)
  emit('update:page', next)
}
</script>

<template>
  <div class="pagination" v-if="pages > 1">
    <button :disabled="page === 1" @click="goTo(page - 1)">上一页</button>
    <button class="current">{{ page }}</button>
    <button :disabled="page === pages" @click="goTo(page + 1)">下一页</button>
  </div>
</template>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  gap: 8px;
}
button {
  border: 1px solid #ddd;
  background: #fff;
  border-radius: 4px;
  padding: 6px 10px;
  color: #666;
}
.current {
  background: #2a7cf6;
  color: #fff;
  border-color: #2a7cf6;
}
button:disabled { opacity: .5; cursor: not-allowed; }
</style>