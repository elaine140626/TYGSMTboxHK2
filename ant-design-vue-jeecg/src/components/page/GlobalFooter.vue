<template>
  <div class="footer">
    <div class="links">
      <!--      <a href="http://www.jeecg.org" target="_blank">JEECG 首页</a>-->
      <a @click="gotoAnalysis">首页</a>
      <!--      <a href="https://github.com/zhangdaiscott/jeecg-boot" target="_blank">-->
      <!--        <a-icon type="github"/>-->
      <!--      </a>-->
      <a>Ant Design</a>
      <a>Vue Antd</a>
      <!--      <a href="https://vuecomponent.github.io/ant-design-vue/docs/vue/introduce-cn/">Vue Antd</a>-->
    </div>
    <div class="copyright">
      Copyright
      <a-icon type="copyright"/>
      2019 <span>天鹰谷智能蜂箱 出品</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "LayoutFooter",
    methods: {
      gotoAnalysis() {
        this.$router.push({path: '/dashboard/Analysis'});
      }
    }
  }

</script>

<style lang="scss" scoped>
  .footer {
    padding: 0 16px;
    margin: 48px 0 24px;
    text-align: center;

    .links {
      margin-bottom: 8px;

      a {
        color: rgba(0, 0, 0, .45);

        &:hover {
          color: rgba(0, 0, 0, .65);
        }

        &:not(:last-child) {
          margin-right: 40px;
        }
      }
    }
    .copyright {
      color: rgba(0, 0, 0, .45);
      font-size: 14px;
    }
  }
</style>