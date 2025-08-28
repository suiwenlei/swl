<template>
  <view class="customer-page">
    <!-- Header -->
    <view class="header">
      <view class="title">智能客服</view>
      <view class="indicator">
        <view class="dot active"></view>
        <view class="dot"></view>
        <view class="dot"></view>
      </view>
      <view class="avatar"></view>
    </view>

    <!-- Suggest blocks -->
    <view class="suggest-blocks">
      <view
        v-for="(s, i) in suggestBlocks"
        :key="i"
        class="s-block"
        @tap="onSuggestBlockTap(s)"
      >
        <text class="s-emoji">{{ s.emoji }}</text>
        <text class="s-text">{{ s.text }}</text>
      </view>
    </view>

    <!-- Quick question cards -->
    <view class="quick-wrap">
      <view
        v-for="(q, i) in quickQuestions"
        :key="i"
        class="q-card"
        @tap="sendQuick(q.title)"
      >
        <view class="q-left">
          <view class="q-badge">?</view>
          <view class="q-hot" v-if="q.hot">热门</view>
        </view>
        <view class="q-title">{{ q.title }}</view>
        <view class="q-arrow">→</view>
        <view class="q-sub">点击快速咨询</view>
      </view>
    </view>

    <!-- Scroll area: feed + chat examples -->
    <scroll-view class="content" scroll-y>
      <view class="card-img">
        <image class="power-img" src="https://img.cdn.aliyun.dcloud.net.cn/uni-ui/productDefault.jpg" mode="widthFix" />
        <view class="img-label">这</view>
      </view>

      <!-- Conversation -->
      <view class="chat">
        <view class="msg me">
          <view class="bubble">个能带吗</view>
        </view>

        <view class="msg bot">
          <view class="bubble">
            请问您的移动电源额定能量是多少Wh? 移动电源上是否有清晰的标识？
          </view>
        </view>

        <view v-for="(m, i) in messages" :key="'m-'+i" :class="['msg', m.role]">
          <view class="bubble">{{ m.text }}</view>
        </view>
      </view>
    </scroll-view>

    <!-- Quick consult row -->
    <view class="quick-row">
      <view v-for="(f, i) in fastActions" :key="i" class="pill" @tap="sendQuick(f)">{{ f }}</view>
    </view>

    <!-- Input bar -->
    <view class="input-bar">
      <view class="mic">◎</view>
      <input class="input" v-model="draft" placeholder="发送消息" confirm-type="send" @confirm="onSend" />
      <view class="plus" @tap="onSend">＋</view>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue'

const suggestBlocks = [
  { text: '猜你想问', emoji: '☁️' },
  { text: '猜你想办', emoji: '✈️' },
  { text: '猜你想买', emoji: '🛍️' },
]

const quickQuestions = [
  { title: '我还能携带其他容量', hot: true },
  { title: '携带充电宝需要注意', hot: true },
  { title: '如果我的充电宝超出', hot: true },
]

const fastActions = ['航班查询', '值机服务', '行李规定', '退改签政策']

const messages = ref([])
const draft = ref('')

function onSuggestBlockTap(item) {
  sendQuick(item.text)
}

function sendQuick(text) {
  messages.value.push({ role: 'me', text })
  replyFor(text)
}

function onSend() {
  if (!draft.value.trim()) return
  const text = draft.value.trim()
  draft.value = ''
  messages.value.push({ role: 'me', text })
  replyFor(text)
}

function replyFor(text) {
  const normalized = text.toLowerCase()
  let answer = ''
  if (normalized.includes('充电') || normalized.includes('power') || normalized.includes('电源')) {
    answer = '请提供移动电源的额定能量(Wh)或电压(V)与容量(mAh)，以便判断是否可携带上机。'
  } else if (normalized.includes('航班')) {
    answer = '请提供出发地、目的地与日期，我帮您查询航班信息。'
  } else if (normalized.includes('退改') || normalized.includes('改签')) {
    answer = '不同舱位退改政策不同，请提供订单或航司与舱位信息。'
  } else if (normalized.includes('行李')) {
    answer = '普通锂电池随身携带≤100Wh，无需航空公司批准；100~160Wh需批准；>160Wh禁止携带。'
  } else {
    answer = '已收到您的问题，我会尽快为您解答。'
  }
  messages.value.push({ role: 'bot', text: answer })
}
</script>

<style scoped>
.customer-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background: #f5f7fb;
}

.header {
  height: 160rpx;
  padding: 40rpx 28rpx 24rpx;
  background: #1a73e8;
  color: #fff;
  display: flex;
  align-items: flex-end;
}
.title { font-size: 36rpx; font-weight: 700; }
.indicator { flex: 1; display: flex; justify-content: center; gap: 12rpx; }
.indicator .dot { width: 10rpx; height: 10rpx; border-radius: 50%; background: rgba(255,255,255,0.45); }
.indicator .dot.active { background: #fff; }
.avatar { width: 56rpx; height: 56rpx; border-radius: 50%; background: rgba(255,255,255,0.35); }

.suggest-blocks {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20rpx;
  padding: 24rpx;
}
.s-block {
  background: #ffffff;
  border-radius: 16rpx;
  height: 140rpx;
  display: flex; flex-direction: column; align-items: center; justify-content: center;
  box-shadow: 0 6rpx 16rpx rgba(0,0,0,0.06);
}
.s-emoji { font-size: 44rpx; }
.s-text { margin-top: 10rpx; font-size: 26rpx; color: #333; }

.quick-wrap { display: grid; grid-template-columns: 1fr; gap: 16rpx; padding: 0 24rpx; }
.q-card {
  background: #ffffff;
  border-radius: 16rpx;
  padding: 20rpx 24rpx;
  position: relative;
}
.q-left { position: absolute; left: 16rpx; top: -10rpx; display: flex; align-items: center; gap: 10rpx; }
.q-badge { width: 40rpx; height: 40rpx; border-radius: 50%; background: #1a73e8; color: #fff; display:flex; align-items:center; justify-content:center; font-weight: 700; }
.q-hot { background: #ff7b31; color: #fff; font-size: 20rpx; padding: 4rpx 10rpx; border-radius: 20rpx; }
.q-title { font-size: 28rpx; color: #333; padding-left: 0rpx; margin-right: 56rpx; }
.q-arrow { position: absolute; right: 24rpx; top: 24rpx; color: #1a73e8; }
.q-sub { color: #9aa3b2; font-size: 22rpx; margin-top: 6rpx; }

.content { flex: 1; padding: 16rpx 24rpx 0; }
.card-img { background: #ffffff; border-radius: 16rpx; overflow: hidden; padding: 16rpx; margin-top: 16rpx; }
.power-img { width: 100%; border-radius: 12rpx; }
.img-label { text-align: right; color: #666; margin-top: 8rpx; }

.chat { padding: 12rpx 0 120rpx; }
.msg { display: flex; margin-top: 20rpx; }
.msg.me { justify-content: flex-end; }
.msg.bot { justify-content: flex-start; }
.bubble {
  max-width: 70%;
  padding: 18rpx 22rpx;
  border-radius: 16rpx;
  font-size: 28rpx;
  line-height: 1.5;
  background: #ffffff;
  color: #333;
  box-shadow: 0 2rpx 8rpx rgba(0,0,0,0.06);
}
.me .bubble { background: #ffefe0; color: #633a00; }

.quick-row { display: flex; flex-wrap: wrap; gap: 16rpx; padding: 12rpx 16rpx; background: #f5f7fb; }
.pill { padding: 14rpx 22rpx; background: #ffffff; border-radius: 28rpx; color: #1a73e8; font-size: 24rpx; box-shadow: 0 2rpx 8rpx rgba(0,0,0,0.06); }

.input-bar {
  height: 100rpx;
  display: flex;
  align-items: center;
  gap: 16rpx;
  padding: 0 16rpx 16rpx;
  background: #f5f7fb;
}
.mic { width: 64rpx; height: 64rpx; border-radius: 50%; background: #ffffff; display:flex; align-items:center; justify-content:center; color: #9aa3b2; }
.input { flex: 1; height: 64rpx; background: #ffffff; border-radius: 32rpx; padding: 0 24rpx; font-size: 26rpx; }
.plus { width: 64rpx; height: 64rpx; border-radius: 50%; background: #1a73e8; color: #fff; display:flex; align-items:center; justify-content:center; }
</style>

