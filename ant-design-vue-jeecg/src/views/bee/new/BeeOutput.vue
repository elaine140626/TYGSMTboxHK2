<template>
  <div>
    <!--<div>
      <a-breadcrumb>
        <a-breadcrumb-item>
          <router-link :to="{path: '/'}">
            <a-icon type="home" />
            <span>首页</span>
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          产出统计
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <a-row>
      <div class="card-container">
        <h2>产出统计</h2>
        <a-tabs type="card" :activeKey="activeTabsKey" @change="clickTabs">
          <a-tab-pane tab="蜜产出" key="1">
            <a-row>
<!--              <span style="font-size: large">操作：</span>-->
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="honeyOutputSwitchOn" @click="countHoney(1)">
                <a-spin style="margin-right: 10px" :spinning="honeyOpenLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>开始统计
              </a-button>
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="honeyOutputSwitchOff" @click="countHoney(0)">
                <a-spin style="margin-right: 10px" :spinning="honeyCloseLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>结束统计
              </a-button>
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="!honeyOutputCancelSwitch" @click="outputCancel(1)">
                <a-spin style="margin-right: 10px" :spinning="honeyCancelLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>取消统计
              </a-button>
            </a-row>
            <hr/>
            <a-row v-if="honeyCountStatus === CONSTANT.COUNTING">
              <div style="font-size: xx-large;text-align: center">正在统计中······</div>
            </a-row>
            <a-row v-else-if="honeyCountStatus === CONSTANT.COUNTED">
              <a-card title="统计结果">
                <a-col :xs="24" :sm="4" v-for="result in countHoneyResult">
                  <a-card-grid style="textAlign:'center';width: 100%">
                    <h3># {{result.boxId}}</h3>
                    <span>产出蜜：{{result.output}}g</span>
                  </a-card-grid>
                </a-col>
              </a-card>
            </a-row>
          </a-tab-pane>
          <a-tab-pane tab="脾产出" key="2">
            <a-row>
<!--              <span style="font-size: large">操作：</span>-->
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="spleenOutputSwitchOn" @click="countSpleen(1)">
                <a-spin style="margin-right: 10px" :spinning="frameOpenLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>开始统计
              </a-button>
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="spleenOutputSwitchOff" @click="countSpleen(0)">
                <a-spin style="margin-right: 10px" :spinning="frameCloseLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>结束统计
              </a-button>
              <a-button type="primary" style="margin-left: 5px;margin-bottom: 5px;" :disabled="!spleenOutputCancelSwitch" @click="outputCancel(2)">
                <a-spin style="margin-right: 10px" :spinning="frameCancelLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>取消统计
              </a-button>
            </a-row>
            <hr/>
            <a-row v-if="spleenCountStatus === CONSTANT.COUNTING">
              <div style="font-size: xx-large;text-align: center">正在统计中······</div>
            </a-row>
            <a-row v-else-if="spleenCountStatus === CONSTANT.COUNTED">
              <div style="font-size: x-large;text-align: center">{{outputMsg}}</div>
              <a-card title="统计结果">
                <a-col :xs="24" :sm="6" v-for="result in countSpleenResult">
                  <a-card-grid style="textAlign:'center';width: 100%">
                    <h3># {{result.boxId}}</h3>
                    <span>产出脾(个)：</span>
                    <a-button-group default-value="horizontal">
                      <a-button @click="changeSpleenNumber(result.key, -1)" v-if="checkBtn">
                        <a-icon type="minus" />
                      </a-button>
                      <a-button>{{result.spleenNumber}}</a-button>
                      <a-button @click="changeSpleenNumber(result.key, 1)" v-if="checkBtn">
                        <a-icon type="plus" />
                      </a-button>
                    </a-button-group>
                  </a-card-grid>
                </a-col>
              </a-card>
              <a-button type="primary" style="width: 100%;margin-top: 20px" @click="submitResult" v-if="checkBtn">确 定</a-button>
            </a-row>
          </a-tab-pane>
          <a-tab-pane tab="历史记录" key="3">
            起止时间：
            <a-range-picker @change="updateOutput" v-model="timeRange" />
            <a-table :columns="columns2" :dataSource="outputData" style="margin-top: 10px">
              <template slot="footer" slot-scope="currentPageData">
                产出总计：脾:{{totalFrame}} (个) <a-divider type="vertical" />  蜜:{{totalHoney}} (g)
              </template>
            </a-table>
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-row>
  </div>
</template>

<script>
    import {getAction, postAction} from "../../../api/manage";
    import moment from 'moment';
    const CONSTANT = {
        COUNTING: 1,
        COUNTED: 2,
        UNCOUNT: 0,
    };

    export default {
        name: "BeeOutput",
        data() {
            return {
                outputMsg: "统计完成，请核对信息",
                CONSTANT,
                honeyOutputSwitchOn: false,
                honeyOutputSwitchOff: true,
                spleenOutputSwitchOn: false,
                spleenOutputSwitchOff: true,
                honeyOutputCancelSwitch: false,
                spleenOutputCancelSwitch: false,
                honeyOpenLoading: false,
                honeyCloseLoading: false,
                honeyCancelLoading: false,
                frameOpenLoading: false,
                frameCloseLoading: false,
                frameCancelLoading: false,
                honeyCountStatus: CONSTANT.UNCOUNT,
                spleenCountStatus: CONSTANT.UNCOUNT,
                countHoneyResult: [],
                countSpleenResult: [],

                activeTabsKey: '1',

                checkBtn: false,
                timeRange: [moment().subtract(7,'day'),moment()],
                columns2: [
                    {
                        title: '蜂箱号',
                        dataIndex: 'beeBoxId',
                        width: '10%',
                        align: 'center'
                    },
                    {
                        title: '产出脾',
                        dataIndex: 'outputFrame',
                        width: '13%',
                        align: 'center',
                        sorter: (a, b) => a.outputFrame - b.outputFrame,
                    },
                    {
                        title: '产出蜜/g',
                        dataIndex: 'outputHoney',
                        width: '77%',
                        align: 'left',
                        sorter: (a, b) => a.outputHoney - b.outputHoney,
                    },
                ],
                outputData: [],
                totalFrame: 0,
                totalHoney: 0,
            };
        },
        created() {
            getAction('/oper/output', {uid: this.$store.getters.userInfo.id}).then((res) => {
                if (res.success) {
                    if (res.result === 1) {
                        this.activeTabsKey = '1';
                        this.honeyOutputSwitchOn = true;
                        this.honeyOutputSwitchOff = false;
                        this.spleenOutputSwitchOn = true;
                        this.spleenOutputSwitchOff = true;
                        this.honeyOutputCancelSwitch = true;
                        this.honeyCountStatus = CONSTANT.COUNTING;
                    } else if (res.result === 2) {
                        this.activeTabsKey = '2';
                        this.spleenOutputSwitchOn = true;
                        this.spleenOutputSwitchOff = false;
                        this.honeyOutputSwitchOn = true;
                        this.honeyOutputSwitchOff = true;
                        this.spleenOutputCancelSwitch = true;
                        this.spleenCountStatus = CONSTANT.COUNTING;
                    } else if (res.result === 3) {
                        this.activeTabsKey = '2';
                        this.spleenOutputSwitchOn = true;
                        this.spleenOutputSwitchOff = true;
                        this.honeyOutputSwitchOn = true;
                        this.honeyOutputSwitchOff = true;
                        this.honeyOutputCancelSwitch = true;
                        this.spleenCountStatus = CONSTANT.COUNTED;
                        alert("您还未确认上次脾数产出结果");
                        this.countSpleen(0);
                    }
                } else {
                    this.$message.warning(res.message);
                }
            });
            this.updateOutput();
        },
        methods: {
            clickTabs(activeKey) {
                this.activeTabsKey = activeKey;
                if (activeKey == 3){
                  this.updateOutput();
                }
            },
            countHoney(mode) {
                if (mode === 1) {
                    if (this.honeyOpenLoading) {
                      return;
                    }
                    this.honeyOpenLoading = true;
                    getAction('/oper/output/honey/start', {uid: this.$store.getters.userInfo.id}).then((res) => {
                        if (res.success) {
                            this.honeyCountStatus = CONSTANT.COUNTING;
                            this.honeyOutputSwitchOn = true;
                            this.honeyOutputSwitchOff = false;
                            this.spleenOutputSwitchOn = true;
                            this.spleenOutputSwitchOff = true;
                            this.honeyOutputCancelSwitch = true;
                            this.$message.success(res.message);
                        } else {
                            this.$message.warning(res.message);
                        }
                        this.honeyOpenLoading = false;
                    });
                } else if (mode === 0) {
                    if (this.honeyCloseLoading) {
                      return;
                    }
                    this.honeyCloseLoading = true;
                    getAction('/oper/output/honey/end', {uid: this.$store.getters.userInfo.id}).then((res) => {
                        if (res.success) {
                            this.honeyCountStatus = CONSTANT.COUNTED;
                            this.spleenOutputSwitchOn = false;
                            this.spleenOutputSwitchOff = true;
                            this.honeyOutputSwitchOn = false;
                            this.honeyOutputSwitchOff = true;
                            this.honeyOutputCancelSwitch = false;
                            this.$message.success(res.message);
                            this.countHoneyResult = [];
                            for (let i = 0; i < res.result.length; i++) {
                                let obj = {
                                    boxId: res.result[i].bbid,
                                    output: res.result[i].honey,
                                };
                                this.countHoneyResult.push(obj);
                            }
                        } else {
                            this.$message.warning(res.message);
                        }
                       this.honeyCloseLoading = false;
                    });
                }
            },
            countSpleen(mode) {
                if (mode === 1) {
                    if (this.frameOpenLoading) {
                      return;
                    }
                    this.frameOpenLoading = true;
                    getAction('/oper/output/frame/start', {uid: this.$store.getters.userInfo.id}).then((res) => {
                        if (res.success) {
                            this.spleenCountStatus = CONSTANT.COUNTING;
                            this.spleenOutputSwitchOn = true;
                            this.spleenOutputSwitchOff = false;
                            this.honeyOutputSwitchOn = true;
                            this.honeyOutputSwitchOff = true;
                            this.spleenOutputCancelSwitch = true;
                            this.$message.success(res.message);
                            this.outputMsg = "统计完成，请核对信息";
                        } else {
                            this.$message.warning(res.message);
                        }
                      this.frameOpenLoading = false;
                    });
                } else if (mode === 0) {
                    if (this.frameCloseLoading) {
                      return;
                    }
                    this.frameCloseLoading = true;
                    getAction('/oper/output/frame/end', {uid: this.$store.getters.userInfo.id}).then((res) => {
                        if (res.success) {
                            this.spleenCountStatus = CONSTANT.COUNTED;
                            this.spleenOutputSwitchOn = true;
                            this.spleenOutputSwitchOff = true;
                            this.honeyOutputSwitchOn = true;
                            this.honeyOutputSwitchOff = true;
                            this.checkBtn = true;
                            this.$message.success(res.message);
                            this.countSpleenResult = [];
                            console.log(res.result);
                            for (let i = 0; i < res.result.length; i++) {
                                let obj = {
                                    key: i,
                                    boxId: res.result[i].bbid,
                                    spleenNumber: res.result[i].frame,
                                    time: res.result[i].time
                                };
                                this.countSpleenResult.push(obj);
                            }
                        } else {
                            this.$message.warning(res.message);
                        }
                        this.frameCloseLoading = false;
                    });
                }
            },
            changeSpleenNumber(key, value) {
                let tmp = this.countSpleenResult[key].spleenNumber + value;
                if (tmp >= 0 && tmp <= 10) {
                    this.countSpleenResult[key].spleenNumber = tmp;
                }
            },
            submitResult() {
                let tmp = [];
                for (let i = 0; i < this.countSpleenResult.length; i++) {
                    if (this.countSpleenResult[i].spleenNumber != 0){
                      tmp.push({
                        bbid: this.countSpleenResult[i].boxId,
                        frame: this.countSpleenResult[i].spleenNumber,
                        honey: null,
                        time: this.countSpleenResult[i].time
                      });
                    }
                }
                let param = {
                    uid: this.$store.getters.userInfo.id,
                    type: 2,
                    historyOutputs: tmp
                };
                postAction('/oper/output/confirm', param).then((res) => {
                    if (res.success) {
                        this.spleenOutputSwitchOn = false;
                        this.spleenOutputSwitchOff = true;
                        this.honeyOutputSwitchOn = false;
                        this.honeyOutputSwitchOff = true;
                        this.spleenOutputCancelSwitch = false;
                        this.checkBtn = false;
                        this.$message.success(res.message);
                        this.outputMsg= "";
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            outputCancel(mode) {
                if (mode ===1 && this.honeyCancelLoading) {
                  return;
                }
                if (mode ===2 && this.frameCancelLoading) {
                  return;
                }
                if (mode === 1){
                  this.honeyCancelLoading = true;
                } else if (mode === 2){
                  this.frameCancelLoading = true;
                }
                getAction('/oper/output/cancel', {uid: this.$store.getters.userInfo.id,type:mode}).then((res) => {
                    if (res.success) {
                        if (mode === 1) {
                            this.honeyOutputSwitchOn = false;
                            this.honeyOutputSwitchOff = true;
                            this.spleenOutputSwitchOn = false;
                            this.spleenOutputSwitchOff = true;
                            this.honeyOutputCancelSwitch = false;
                            this.honeyCountStatus = CONSTANT.UNCOUNT;
                        }else if (mode === 2) {
                            this.honeyOutputSwitchOn = false;
                            this.honeyOutputSwitchOff = true;
                            this.spleenOutputSwitchOn = false;
                            this.spleenOutputSwitchOff = true;
                            this.spleenOutputCancelSwitch = false;
                            this.checkBtn = false;
                            this.spleenCountStatus = CONSTANT.UNCOUNT;
                            this.countSpleenResult= [];
                        }
                        this.$message.success(res.message);
                    } else {
                        this.$message.warning(res.message);
                    }
                    this.honeyCancelLoading = false;
                    this.frameCancelLoading = false;
                });
            },
            /* 更新产出统计 */
            updateOutput() {
                console.log("aaaa")
                let param = {
                    uid:this.$store.getters.userInfo.id,
                    startDate: this.timeRange[0].format("YYYY-MM-DD"),
                    endDate: this.timeRange[1].format("YYYY-MM-DD")
                };
                console.log(param);
                getAction("/oper/output/history/uid",param).then((res) => {
                    if (res.success){
                        console.log(res.result);
                        this.outputData = [];
                        this.totalFrame = 0;
                        this.totalHoney = 0;
                        for (let i = 0; i < res.result.length; i++) {
                            let obj = {};
                            obj.key = i;
                            obj.beeBoxId = res.result[i].bbid;
                            obj.outputFrame = res.result[i].frame;
                            if (res.result[i].honey == null){
                              obj.outputHoney = 0;
                            } else{
                              obj.outputHoney = res.result[i].honey;
                            }
                            this.outputData.push(obj);
                            this.totalFrame += res.result[i].frame;
                            this.totalHoney += res.result[i].honey;
                        }
                    } else{
                        this.$message.warning("服务器繁忙");
                    }
                });
            },
        }
    }
</script>

<style scoped>
  .card-container {
    background: #f5f5f5;
    overflow: hidden;
    padding: 24px;
  }
  .card-container > .ant-tabs-card > .ant-tabs-content {
    height: 120px;
    margin-top: -16px;
  }

  .card-container > .ant-tabs-card > .ant-tabs-content > .ant-tabs-tabpane {
    background: #fff;
    padding: 16px;
  }

  .card-container > .ant-tabs-card > .ant-tabs-bar {
    border-color: #fff;
  }

  .card-container > .ant-tabs-card > .ant-tabs-bar .ant-tabs-tab {
    border-color: transparent;
    background: transparent;
  }

  .card-container > .ant-tabs-card > .ant-tabs-bar .ant-tabs-tab-active {
    border-color: #fff;
    background: #fff;
  }


</style>