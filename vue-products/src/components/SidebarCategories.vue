<script setup>
const props = defineProps({
  items: { type: Array, default: () => [] },
  current: { type: String, default: '' },
})
const emit = defineEmits(['select'])
</script>

<template>
  <aside class="sidebar">
    <h4>全部</h4>
    <ul>
      <li
        v-for="item in items"
        :key="item"
        :class="{ active: item === current }"
        @click="$emit('select', item)"
      >
        {{ item }}
      </li>
    </ul>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 180px;
}
.sidebar h4 {
  margin: 0 0 12px 0;
  font-size: 14px;
}
.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
  border: 1px solid #eee;
  border-radius: 6px;
  overflow: hidden;
}
.sidebar li {
  padding: 10px 14px;
  border-bottom: 1px solid #eee;
  cursor: pointer;
  font-size: 13px;
  color: #444;
  display: flex;
  justify-content: space-between;
}
.sidebar li:last-child { border-bottom: none; }
.sidebar li:hover { background: #f7f9ff; }
.sidebar li.active { color: #2a7cf6; background: #eef4ff; }
</style>