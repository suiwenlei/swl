<template>
  <footer class="site-footer">
    <div class="top">
      <div class="container">
        <div class="col">
          <h4>北京市鹿刃斧钺⽅科技有限公司</h4>
          <p>地址：北京市朝阳区天苑街道阳融国际8号楼</p>
          <p>电话：010-0000000</p>
          <p>邮箱：support@modao.cc</p>
        </div>
        <div class="col">
          <h5>企业介绍</h5>
          <ul>
            <li>品牌介绍</li>
            <li>团队介绍</li>
            <li>关于我们</li>
          </ul>
        </div>
        <div class="col">
          <h5>服务范围</h5>
          <ul>
            <li>产品介绍</li>
            <li>新闻动态</li>
            <li>服务品牌</li>
          </ul>
        </div>
      </div>
    </div>
    <div class="bottom">
      <div class="container">友情链接：百度｜新浪｜网易</div>
    </div>
  </footer>
</template>

<style scoped>
.site-footer {
  background: #f5f6f7;
  color: #666;
}
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}
.top .container {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  gap: 32px;
  padding: 28px 24px;
}
.top h4 { margin: 0 0 10px 0; color: #333; }
.top h5 { margin: 0 0 8px 0; color: #333; }
.top ul { list-style: none; padding: 0; margin: 0; }
.top li { margin: 6px 0; }
.bottom {
  border-top: 1px solid #e5e7eb;
  padding: 12px 0;
  font-size: 12px;
  color: #888;
}
</style>