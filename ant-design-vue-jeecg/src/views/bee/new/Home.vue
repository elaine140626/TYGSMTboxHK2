<!-- 首页 -->
<template>
  <div>
    <a-row style="text-align: center">
      <h1 style="font-size: 80px">天鹰谷智能蜂箱</h1>
    </a-row>
    <a-row>
      <a-col :md="8">
        <div class="div">温度</div>
      </a-col>
      <a-col :md="8">
        <div class="div"></div>
      </a-col>
      <a-col :md="8">
        <div class="div"></div>
      </a-col>
    </a-row>
    <a-row>
      <a-col :md="8">
        <div class="div"></div>
      </a-col>
      <a-col :md="8">
        <div class="div"></div>
      </a-col>
      <a-col :md="8">
        <div class="div"></div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
  .div {
    position: relative;
    width: 100px;
    height: 173.2px;
    margin: 100px auto;
    background-color: red;
    text-align:center;
    line-height:160px;
    font-size: 50px;
  }
  .div:before {
    content: '';
    display: block;
    position: absolute;
    width: 0;
    height: 0;
    right:100px;
    border-width: 86.6px 50px;
    border-style: solid;
    border-color: transparent red transparent transparent;
  }
  .div:after {
    content: '';
    display: block;
    position: absolute;
    width: 0;
    height: 0;
    left:100px;
    border-width: 86.6px 50px;
    border-style: solid;
    border-color: transparent transparent transparent red;
    top:0;
  }
</style>