<template>
  <div>
    <!--<div>
      <a-breadcrumb>
        <a-breadcrumb-item>
          <router-link :to="{path: '/'}">
            <a-icon type="home" />
            首页
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          系统信息
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <div style="margin-top: 20px;margin-left: 20px">
      <!-- 信息 -->
      <!--<div>
        <h1>天鹰谷智能养蜂设备T001S版</h1>
        <h2>联系电话：XXXXXXXXXXX</h2>
        <h2>生产厂家：屏南县乙天生物科技有限公司</h2>
        <h2>出厂日期：XX年XX月XX日</h2>
      </div>
      &lt;!&ndash; 二维码 &ndash;&gt;
      <div>
        <img src="http://localhost:3000/erweima.png"/>
      </div>-->


      <a-row  style="margin: 0 auto; width:30%;" v-if="this.$store.getters.device === 'desktop'">
        <a-col>
          <h2 style="text-align: center">系统信息</h2>
          <a-list size="large" bordered style="background-color: white">
            <a-list-item>
              <div class="list-ui">
                <span>设备名称</span>
                <span>天鹰谷智能养蜂设备T001S版</span>
              </div>
            </a-list-item>
            <a-list-item>
              <div class="list-ui">
                <span>联系电话</span>
                <span>XXXXXXXXXXX</span>
              </div>
            </a-list-item>
            <a-list-item>
              <div class="list-ui">
                <span>生产厂家</span>
                <span>屏南县乙天生物科技有限公司</span>
              </div>
            </a-list-item>
            <a-list-item>
              <div class="list-ui">
                <span>出厂日期</span>
                <span>XX年XX月XX日</span>
              </div>
            </a-list-item>
            <a-list-item>
              <div class="list-ui">
                <span>二维码</span>
                <img src="../../../../public/logo.png"/>
              </div>
            </a-list-item>
          </a-list>
        </a-col>
      </a-row>
      <a-row v-else>
        <h2 style="text-align: center">系统信息</h2>
        <a-list size="large" bordered style="background-color: white">
          <a-list-item>
            <div class="list-ui">
              <span>设备名称</span>
              <span>天鹰谷智能养蜂设备T001S版</span>
            </div>
          </a-list-item>
          <a-list-item>
            <div class="list-ui">
              <span>联系电话</span>
              <span>XXXXXXXXXXX</span>
            </div>
          </a-list-item>
          <a-list-item>
            <div class="list-ui">
              <span>生产厂家</span>
              <span>屏南县乙天生物科技有限公司</span>
            </div>
          </a-list-item>
          <a-list-item>
            <div class="list-ui">
              <span>出厂日期</span>
              <span>XX年XX月XX日</span>
            </div>
          </a-list-item>
          <a-list-item>
            <div class="list-ui">
              <span>二维码</span>
              <img src="../../../../public/logo.png"/>
            </div>
          </a-list-item>
        </a-list>
      </a-row>
    </div>
  </div>
</template>

<script>

    export default {
        name: "BeeSystemInfo",
        data() {
            return {
            }
        },
    }
</script>

<style scoped>
.list-ui {
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  width: 100%
}
</style>