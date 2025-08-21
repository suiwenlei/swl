<template>
  <view class="card">
    <image class="thumb" :src="image" mode="aspectFill" />
    <view class="meta">
      <text class="title">{{ title }}</text>
      <view class="price-row">
        <text class="price">￥{{ price.toFixed(2) }}</text>
        <text class="origin">￥{{ originPrice.toFixed(0) }}</text>
      </view>
      <view class="footer">
        <view class="tag">20元券</view>
        <text class="reward">分享赚 ¥12.3</text>
      </view>
    </view>
  </view>
</template>

<script setup>
defineProps({
  title: { type: String, default: '' },
  price: { type: Number, default: 0 },
  originPrice: { type: Number, default: 0 },
  image: { type: String, default: '' },
})
</script>

<style scoped>
.card {
  display: flex;
  background: #fff;
  border-radius: 16rpx;
  margin: 20rpx 0;
  overflow: hidden;
}
.thumb {
  width: 220rpx;
  height: 220rpx;
  background: #f2f2f2;
}
.meta { flex: 1; padding: 20rpx; display: flex; flex-direction: column; }
.title { color: #333; font-size: 28rpx; line-height: 1.5; }
.price-row { margin-top: auto; display: flex; align-items: baseline; gap: 12rpx; }
.price { color: #ff3e3e; font-size: 34rpx; font-weight: 700; }
.origin { color: #999; text-decoration: line-through; font-size: 24rpx; }
.footer { margin-top: 12rpx; display: flex; align-items: center; justify-content: space-between; }
.tag { background: #ff3e3e; color: #fff; padding: 6rpx 12rpx; border-radius: 8rpx; font-size: 22rpx; }
.reward { color: #ff8900; font-size: 24rpx; }
</style>

