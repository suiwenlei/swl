<script setup>
const props = defineProps({
  title: { type: String, default: '产品介绍' },
  subtitle: { type: String, default: 'Products' },
  imageUrl: {
    type: String,
    default:
      'https://images.unsplash.com/photo-1501631259223-89d4e246ed23?q=80&w=1920&auto=format&fit=crop',
  },
})
</script>

<template>
  <section
    class="hero"
    :style="{ backgroundImage: `url(${imageUrl})` }"
  >
    <div class="overlay">
      <div class="container">
        <h2>{{ title }}</h2>
        <p>{{ subtitle }}</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.hero {
  background-size: cover;
  background-position: center;
  height: 220px;
  position: relative;
}
.overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(0deg, rgba(0,0,0,.25), rgba(0,0,0,.15));
  display: flex;
  align-items: center;
}
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  color: white;
}
.container h2 {
  font-size: 22px;
  margin-bottom: 6px;
}
.container p {
  opacity: .9;
}
</style>