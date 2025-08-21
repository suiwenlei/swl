<template>
  <view class="page">
    <!-- Top categories bar -->
    <view class="top-bar bg-primary">
      <view class="city">贵州省</view>
      <view class="search">
        <input class="search-input" placeholder="户外背包" />
      </view>
      <view class="menu">≡</view>
    </view>

    <!-- Banner -->
    <view class="banner-card">
      <image class="banner-img" src="https://img.cdn.aliyun.dcloud.net.cn/uni-ui/banner.jpg" mode="aspectFill" />
    </view>

    <!-- Quick actions -->
    <view class="quick-actions">
      <view v-for="(item, idx) in quickList" :key="idx" class="action-item">
        <view class="dot" :style="{ background: item.color }"></view>
        <text class="action-text">{{ item.text }}</text>
      </view>
    </view>

    <!-- Coupon strip -->
    <view class="coupon-strip">
      <text class="headline">优惠券, 我们走</text>
      <view class="go">GO</view>
    </view>

    <!-- Promo grid images -->
    <view class="promo-grid">
      <image class="promo" src="https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uniapp.png" mode="aspectFill" />
      <image class="promo" src="https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni.png" mode="aspectFill" />
    </view>

    <!-- Tabs -->
    <view class="tabs">
      <view v-for="(tab, idx) in tabs" :key="idx" :class="['tab', { active: idx === activeTab }]" @tap="activeTab = idx">{{ tab }}</view>
    </view>

    <!-- Product list -->
    <view class="product-list">
      <ProductItem
        v-for="item in products"
        :key="item.id"
        :title="item.title"
        :price="item.price"
        :origin-price="item.originPrice"
        :image="item.image"
      />
    </view>
  </view>
  
</template>

<script setup>
import { ref } from 'vue'
import ProductItem from '../../shared/ProductItem.vue'

const quickList = [
  { text: '每日签到', color: '#ffa75e' },
  { text: '积分商城', color: '#5db2ff' },
  { text: '抽奖', color: '#ff6c5b' },
  { text: '今日推荐', color: '#25d4c1' },
  { text: '淘抢购', color: '#ff5da9' },
]

const tabs = ['淘宝', '京东', '拼多多', '附近']
const activeTab = ref(0)

const products = ref(
  Array.from({ length: 6 }).map((_, i) => ({
    id: i + 1,
    title: `Apple 2019款 Macbook Pro 13.3（带触控栏）`,
    price: 89,
    originPrice: 120,
    image: 'https://img.cdn.aliyun.dcloud.net.cn/uni-ui/grid-1.png',
  }))
)
</script>

<style scoped>
.page {
  padding-bottom: 40rpx;
}

.top-bar {
  height: 120rpx;
  padding: 20rpx 24rpx;
  display: flex;
  align-items: center;
  color: #fff;
}
.city { font-size: 28rpx; margin-right: 16rpx; }
.menu { margin-left: 16rpx; font-size: 36rpx; }
.search { flex: 1; }
.search-input {
  height: 68rpx;
  border-radius: 34rpx;
  background: rgba(255,255,255,0.95);
  padding: 0 24rpx;
  font-size: 26rpx;
}

.banner-card {
  margin: 24rpx;
  border-radius: 16rpx;
  overflow: hidden;
}
.banner-img { width: 100%; height: 260rpx; display: block; }

.quick-actions {
  display: flex;
  justify-content: space-around;
  padding: 12rpx 12rpx 0;
}
.action-item { display: flex; align-items: center; }
.dot {
  width: 44rpx; height: 44rpx; border-radius: 50%; margin-right: 12rpx;
}
.action-text { color: #666; font-size: 24rpx; }

.coupon-strip {
  margin: 24rpx;
  background: #fff3f3;
  border-radius: 60rpx;
  padding: 20rpx 28rpx;
  display: flex; align-items: center; justify-content: space-between;
}
.coupon-strip .headline { color: #ff3e3e; font-weight: 600; }
.coupon-strip .go {
  width: 84rpx; height: 84rpx; background: #ffcf45; border-radius: 50%;
  display: flex; align-items: center; justify-content: center; color: #333; font-weight: 700;
}

.promo-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20rpx; padding: 0 24rpx; }
.promo { width: 100%; height: 260rpx; border-radius: 16rpx; overflow: hidden; }

.tabs { display: flex; background: #fff; margin-top: 24rpx; }
.tab { flex: 1; text-align: center; padding: 24rpx 0; color: #666; }
.tab.active { color: #ff3e3e; font-weight: 600; border-bottom: 4rpx solid #ff3e3e; }

.product-list { padding: 0 24rpx; }
</style>

