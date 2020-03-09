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
          配置信息
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <a-spin :spinning="loading" tip="正在加载">
      <a-card title="系统配置信息" :bordered="false" style="margin-top: 5px">
      <a-row>
        <a-form>
          <a-row>
            <a-col :xs="24" :xl="12" >
              <!--<h2 style="color: #00A0E9">重量设置</h2>-->
              <a-card title="重量设置" :bordered="true" style="margin: 10px">
                <!-- 飞逃重量(g) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="飞逃重量(g)"
                  :validate-status="flyFledWeight.validateStatus"
                  :help="flyFledWeight.errorMsg || flyFledWeight.tips"
                >
                  <a-input placeholder="请输入飞逃重量(g)" v-model="flyFledWeight.value" @change="handleFlyFledWeightChange"/>
                </a-form-item>
                <!-- 产出误差(g) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="产出误差(g)"
                  :validate-status="outputError.validateStatus"
                  :help="outputError.errorMsg || outputError.tips"
                >
                  <a-input placeholder="请输入产出误差(g)" v-model="outputError.value" @change="handleOutputErrorChange"/>
                </a-form-item>
                <!-- 标准脾重(g) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="标准脾重(g)"
                  :validate-status="stdSpleenWeight.validateStatus"
                  :help="stdSpleenWeight.errorMsg || stdSpleenWeight.tips"
                >
                  <a-input placeholder="请输入标准脾重(g)" v-model="stdSpleenWeight.value" @change="handleStdSpleenWeightChange"/>
                </a-form-item>
              </a-card>

            </a-col>
            <a-col :xs="24" :xl="12">
              <!--<h2 style="color: #00A0E9">巢门控制设置</h2>-->
              <a-card title="巢门控制设置" :bordered="true" style="margin: 10px">
                <!-- 标准重量/脾数值百分比 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="标准重量/脾数值百分比(%)"
                  :validate-status="stdWeightAndSpleenPercent.validateStatus"
                  :help="stdWeightAndSpleenPercent.errorMsg || stdWeightAndSpleenPercent.tips"
                >
                  <a-input placeholder="请输入标准重量/脾数值百分比(%)" v-model="stdWeightAndSpleenPercent.value" @change="handleStdWeightAndSpleenPercentChange"/>
                </a-form-item>
                <!-- 时间间隔 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="时间间隔"
                  :validate-status="interval.validateStatus"
                  :help="interval.errorMsg || interval.tips"
                >
                  <a-input placeholder="请输入时间间隔" v-model="interval.value" @change="handleIntervalChange"/>
                </a-form-item>
                <!-- 减少百分比(%) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="减少百分比(%)"
                  :validate-status="reducePercent.validateStatus"
                  :help="reducePercent.errorMsg || reducePercent.tips"
                >
                  <a-input placeholder="请输入减少百分比(%)" v-model="reducePercent.value" @change="handleReducePercentChange"/>
                </a-form-item>
              </a-card>

            </a-col>
          </a-row>
          <a-row style="margin-top: 10px">
            <a-col :xs="24" :xl="12">
              <!--<h2 style="color: #00A0E9">显示设置</h2>-->
              <a-card title="显示设置" :bordered="true" style="margin: 10px">
                <!-- 蜂箱状态 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="低重量-中等 临界值"
                  :validate-status="lowToMidWeightCriticalValue.validateStatus"
                  :help="lowToMidWeightCriticalValue.errorMsg || lowToMidWeightCriticalValue.tips"
                >
                  <a-input placeholder="请输入低重量-中等 临界值" v-model="lowToMidWeightCriticalValue.value" @change="handleLowToMidWeightCriticalValueChange"/>
                </a-form-item>
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="中等-正常 临界值"
                  :validate-status="midToNorWeightCriticalValue.validateStatus"
                  :help="midToNorWeightCriticalValue.errorMsg || midToNorWeightCriticalValue.tips"
                >
                  <a-input placeholder="请输入中等-正常 临界值" v-model="midToNorWeightCriticalValue.value" @change="handleMidToNorWeightCriticalValueChange"/>
                </a-form-item>
                <!-- 温度提示线 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="温度提示线"
                  :validate-status="hintTemper.validateStatus"
                  :help="hintTemper.errorMsg || hintTemper.tips"
                >
                  <a-input placeholder="请输入温度提示线" v-model="hintTemper.value" @change="handleHintTemperChange"/>
                </a-form-item>
                <!-- 重量变化率提示线 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="重量变化率提示线"
                  :validate-status="hintWeightChange.validateStatus"
                  :help="hintWeightChange.errorMsg || hintWeightChange.tips"
                >
                  <a-input placeholder="请输入重量变化率提示线" v-model="hintWeightChange.value" @change="handleHintWeightChangeChange"/>
                </a-form-item>
                <!-- 重量/框数提示线 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="重量/框数提示线"
                  :validate-status="hintWeightFrame.validateStatus"
                  :help="hintWeightFrame.errorMsg || hintWeightFrame.tips"
                >
                  <a-input placeholder="请输入重量/框数提示线" v-model="hintWeightFrame.value" @change="handleHintWeightFrameChange"/>
                </a-form-item>
                <!-- 历史记录显示天数 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="历史记录显示天数(天)"
                  :validate-status="showDaysOfHistory.validateStatus"
                  :help="showDaysOfHistory.errorMsg || showDaysOfHistory.tips"
                >
                  <a-input placeholder="请输入历史记录显示天数(天)" v-model="showDaysOfHistory.value" @change="handleShowDaysOfHistoryChange"/>
                </a-form-item>
              </a-card>

            </a-col>
            <a-col :xs="24" :xl="12">
              <!--<h2 style="color: #00A0E9">操作设置</h2>-->
              <a-card title="操作设置" :bordered="true" style="margin: 10px">
                <!-- 上限温度(℃) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="上限温度(℃)"
                  :validate-status="ceilingTemperature.validateStatus"
                  :help="ceilingTemperature.errorMsg || ceilingTemperature.tips"
                >
                  <a-input placeholder="请输入上限温度(℃)" v-model="ceilingTemperature.value" @change="handleCeilingTemperatureChange"/>
                </a-form-item>
                 <!--下限温度(℃)-->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="下限温度(℃)"
                  :validate-status="floorTemperature.validateStatus"
                  :help="floorTemperature.errorMsg || floorTemperature.tips"
                >
                  <a-input placeholder="请输入下限温度(℃)" v-model="floorTemperature.value" @change="handleFloorTemperatureChange"/>
                </a-form-item>
                <!-- 奖励喂食时长 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="奖励喂食时长"
                  :validate-status="jianLiFeedTime.validateStatus"
                  :help="jianLiFeedTime.errorMsg || jianLiFeedTime.tips"
                >
                  <a-input placeholder="请输入奖励喂食时长" v-model="jianLiFeedTime.value" @change="handleJianLiFeedTimeChange"/>
                </a-form-item>
                <!-- 补充喂食时长 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="补充喂食时长"
                  :validate-status="buChongFeedTime.validateStatus"
                  :help="buChongFeedTime.errorMsg || buChongFeedTime.tips"
                >
                  <a-input placeholder="请输入补充喂食时长" v-model="buChongFeedTime.value" @change="handleBuChongFeedTimeChange"/>
                </a-form-item>
                <!-- 清洗时长 -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="清洗时长"
                  :validate-status="cleanLong.validateStatus"
                  :help="cleanLong.errorMsg || cleanLong.tips"
                >
                  <a-input placeholder="请输入清洗时长" v-model="cleanLong.value" @change="handleCleanLongChange"/>
                </a-form-item>
                <!-- 自动喂食重量(g) -->
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="自动喂食重量(g)"
                  :validate-status="autoFeedWeight.validateStatus"
                  :help="autoFeedWeight.errorMsg || autoFeedWeight.tips"
                >
                  <a-input placeholder="请输入标准脾重(g)" v-model="autoFeedWeight.value" @change="handleAutoFeedWeightChange"/>
                </a-form-item>
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="自动喂食开始时间(H)"
                  :validate-status="autoFeedStartHour.validateStatus"
                  :help="autoFeedStartHour.errorMsg || autoFeedStartHour.tips"
                >
                  <a-input placeholder="自动喂食开始时间(小时)" v-model="autoFeedStartHour.value" @change="handleAutoFeedStartHourChange"/>
                </a-form-item>
                <a-form-item
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                  label="自动喂食结束时间(H)"
                  :validate-status="autoFeedEndHour.validateStatus"
                  :help="autoFeedEndHour.errorMsg || autoFeedEndHour.tips"
                >
                  <a-input placeholder="自动喂食结束时间(小时)" v-model="autoFeedEndHour.value" @change="handleAutoFeedEndHourChange"/>
                </a-form-item>
              </a-card>

            </a-col>

          </a-row>
        </a-form>
      </a-row>
      <a-row type="flex" justify="space-around">
        <a-col :xs="24" :sm="6">
          <a-button type="primary" style="width: 100%;margin-top: 20px" @click="saveConfigInfo">保 存</a-button>
        </a-col>
        <a-col :xs="24" :sm="6">
          <a-popconfirm
            title="确定重置配置？"
            @confirm="resetConfigInfo"
            @cancel="cancel"
            okText="确定"
            cancelText="取消"
          >
            <a-button type="primary" style="width: 100%;margin-top: 20px">重 置</a-button>
          </a-popconfirm>
        </a-col>
      </a-row>
    </a-card>
    </a-spin>
  </div>
</template>

<script>
    /* 整数校验 */
    import {getAction, putAction} from "../../../api/manage";

    function validateIntegerNumber(number) {
        if (/^[0-9]*[1-9][0-9]*$/.test(number)) {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
        return {
            validateStatus: 'error',
            errorMsg: '输入的值必须为大于0的整数！',
        };
    }
    /* 非负数校验 */
    function validateNonNegativeNumber(number) {
        if (/^\d+(\.\d+)?$/.test(number)) {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
        return {
            validateStatus: 'error',
            errorMsg: '输入的值必须为非负数！',
        };
    }
    /* 百分比范围数校验 */
    function validatePercentRange(number) {
        if (/^\d+(\.\d+)?$/.test(number) && parseFloat(number)>=0 && parseFloat(number)<=100) {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
        return {
            validateStatus: 'error',
            errorMsg: '输入的值必须在0~100之间！',
        };
    }
    function validateAutoFeedHour(number) {
        if (/^[0-9]*$/.test(number) && number >= 0 && number <= 23){
          return {
            validateStatus: 'success',
            errorMsg: null,
          }
        }
      return {
        validateStatus: 'error',
        errorMsg: '输入的值必须在0~23之间！',
      };
    }
    export default {

        name: "BeeSystemConfig",
        data() {
            return {
                labelCol: {
                    xs: { span: 24 },
                    sm: { span: 8 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 12 },
                },
                flyFledWeight: {value: '',tips:'若瞬间减少重量超过[飞逃重量]，则认定发生蜜蜂飞逃或蜜蜂被盗窃，系统发出风险警报'},
                ceilingTemperature: {value: '',tips:'温度超过上限温度系统停止加热'},
                floorTemperature: {value: '',tips:'温度低于下限温度系统开启加热'},
                outputError: {value: '',tips:'蜂蜜产出低于产出误差，自动忽略'},
                showDaysOfHistory: {value: '',tips:'历史操作记录显示天数'},
                stdSpleenWeight: {value: '',tips:'正常养殖时，单脾的重量'},
                lowToMidWeightCriticalValue: {value: '',tips:'低重量-中等 临界值'},
                midToNorWeightCriticalValue: {value: '',tips:'中等-正常 临界值'},
                stdWeightAndSpleenPercent: {value: '',tips:'重量/脾数低于标准重量/脾数的50%，蜂群处于亚健康状态，调整至控王档位，防止飞逃'},
                interval: {value: '',tips:'重量/脾数于[时间间隔]内减少的百分比；防止蜜蜂应急飞逃或分蜂'},
                reducePercent: {value: '',tips:'重量/脾数于时间间隔内[减少的百分比]；防止蜜蜂应急飞逃或分蜂'},
                cleanLong: {value: '',tips:'箱底清洗的分钟数'},
                hintTemper: {value: '',tips:'温度提示线'},
                hintWeightChange: {value: '',tips:'重量变化率提示线'},
                hintWeightFrame: {value: '',tips:'重量/框数提示线'},
                autoFeedWeight: {value: '', tips: '重量/框数低于设定的[自动喂食重量]，系统开启自动喂食'},
                autoFeedStartHour: {value :'' ,tips:'自动喂食开始时间设定'},
                autoFeedEndHour:{value:'',tips:'自动喂食结束时间设定'},
                jianLiFeedTime:{value:'',tips:'奖励喂食的分钟数'},
                buChongFeedTime:{value:'',tips:'补充喂食时的分钟数'},

                loading:true,
            }
        },
        created() {
            this.getSystemConfigInfo();
        },
        methods: {
            getSystemConfigInfo() {
                getAction('/conf/glb', {uid: this.$store.getters.userInfo.id}).then((res) => {
                    if (res.success) {
                        this.flyFledWeight.value = res.result.escapeWeight;
                        this.ceilingTemperature.value = res.result.heatUpper;
                        this.floorTemperature.value = res.result.heatLower;
                        this.outputError.value = res.result.outputIgnoreWeight;
                        this.showDaysOfHistory.value = res.result.hisShowDays;
                        this.stdSpleenWeight.value = res.result.stdWeight;
                        this.lowToMidWeightCriticalValue.value = res.result.boxLowWeight;
                        this.midToNorWeightCriticalValue.value = res.result.boxNormalWeight;
                        this.stdWeightAndSpleenPercent.value = res.result.kingCtrlRate;
                        this.interval.value = res.result.draftInterval;
                        this.reducePercent.value = res.result.decreseRate;
                        this.cleanLong.value = res.result.cleanLong;
                        this.hintTemper.value = res.result.hintTemper;
                        this.hintWeightChange.value = res.result.hintWeightChange;
                        this.hintWeightFrame.value = res.result.hintWeightFrame;
                        this.autoFeedWeight.value = res.result.feedWeight;
                        this.autoFeedStartHour.value = res.result.autoFeedStartHour;
                        this.autoFeedEndHour.value = res.result.autoFeedEndHour;
                        this.jianLiFeedTime.value = res.result.feedRewordLong;
                        this.buChongFeedTime.value = res.result.feedSuppLong;
                        this.loading = false;
                        //this.$message.success("配置信息获取成功");
                    } else {
                        this.$message.warning("系统配置加载失败");
                    }
                });
            },
            resetConfigInfo() {
                this.flyFledWeight.value = '1000';
                this.ceilingTemperature.value = '20';
                this.floorTemperature.value = '8';
                this.outputError.value = '500';
                this.showDaysOfHistory.value = '3';
                this.stdSpleenWeight.value = '2000';
                this.lowToMidWeightCriticalValue.value = '500';
                this.midToNorWeightCriticalValue.value = '2000';
                this.stdWeightAndSpleenPercent.value = '0.5';
                this.interval.value = '40';
                this.reducePercent.value = '0.4';
                this.cleanLong.value = '5';
                this.hintTemper.value = '20';
                this.hintWeightChange.value = '0';
                this.hintWeightFrame.value = '2000';
                this.autoFeedWeight.value = '1000';
                this.autoFeedStartHour.value = '21';
                this.autoFeedEndHour.value = '23';
                this.buChongFeedTime.value = '20';
                this.jianLiFeedTime.value = '5';
                let params = {};
                params.uid = this.$store.getters.userInfo.id;
                params.escapeWeight=this.flyFledWeight.value;
                params.heatUpper=this.ceilingTemperature.value;
                params.heatLower=this.floorTemperature.value;
                params.outputIgnoreWeight=this.outputError.value;
                params.hisShowDays=this.showDaysOfHistory.value;
                params.stdWeight=this.stdSpleenWeight.value;
                params.boxLowWeight=this.lowToMidWeightCriticalValue.value;
                params.boxNormalWeight=this.midToNorWeightCriticalValue.value;
                params.kingCtrlRate=this.stdWeightAndSpleenPercent.value;
                params.draftInterval=this.interval.value;
                params.decreseRate=this.reducePercent.value;
                params.cleanLong=this.cleanLong.value;
                params.hintTemper=this.hintTemper.value;
                params.hintWeightChange=this.hintWeightChange.value;
                params.hintWeightFrame=this.hintWeightFrame.value;
                params.feedWeight = this.autoFeedWeight.value;
                params.autoFeedStartHour = this.autoFeedStartHour.value;
                params.autoFeedEndHour = this.autoFeedEndHour.value;
                params.feedRewordLong = this.jianLiFeedTime.value;
                params.feedSuppLong = this.buChongFeedTime.value;
                putAction('/conf/glb', params).then((res) => {
                    if (res.success) {
                        this.$message.success("重置配置信息成功");
                        /*this.flyFledWeight.errorMsg = null;
                        this.ceilingTemperature.errorMsg = null;
                        this.floorTemperature.errorMsg = null;
                        this.outputError.errorMsg = null;
                        this.showDaysOfHistory.errorMsg = null;
                        this.stdSpleenWeight.errorMsg = null;
                        this.lowToMidWeightCriticalValue.errorMsg = null;
                        this.midToNorWeightCriticalValue.errorMsg = null;
                        this.stdWeightAndSpleenPercent.errorMsg = null;
                        this.interval.errorMsg = null;
                        this.reducePercent.errorMsg = null;
                        this.cleanLong.errorMsg = null;
                        this.hintTemper.errorMsg = null;
                        this.hintWeightChange.errorMsg = null;
                        this.autoFeedWeight.errorMsg = null;*/
                        this.$router.go(0);
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            saveConfigInfo() {
                if (this.flyFledWeight.errorMsg != null ||
                    this.ceilingTemperature.errorMsg != null ||
                    this.floorTemperature.errorMsg != null ||
                    this.outputError.errorMsg != null ||
                    this.showDaysOfHistory.errorMsg != null ||
                    this.stdSpleenWeight.errorMsg != null ||
                    this.lowToMidWeightCriticalValue.errorMsg != null ||
                    this.midToNorWeightCriticalValue.errorMsg != null ||
                    this.stdWeightAndSpleenPercent.errorMsg != null ||
                    this.interval.errorMsg != null ||
                    this.reducePercent.errorMsg != null ||
                    this.cleanLong.errorMsg != null ||
                    this.hintTemper.errorMsg != null ||
                    this.hintWeightChange.errorMsg != null ||
                    this.autoFeedWeight.errorMsg != null ||
                    this.autoFeedStartHour.errorMsg != null ||
                  this.buChongFeedTime.errorMsg != null ||
                  this.jianLiFeedTime.errorMsg != null ||
                    this.autoFeedEndHour.errorMsg != null) {
                    this.$message.warning('存在错误配置信息，请更正');
                    return;
                }
                let params = {};
                params.uid = this.$store.getters.userInfo.id;
                params.escapeWeight=this.flyFledWeight.value;
                params.heatUpper=this.ceilingTemperature.value;
                params.heatLower=this.floorTemperature.value;
                params.outputIgnoreWeight=this.outputError.value;
                params.hisShowDays=this.showDaysOfHistory.value;
                params.stdWeight=this.stdSpleenWeight.value;
                params.boxLowWeight=this.lowToMidWeightCriticalValue.value;
                params.boxNormalWeight=this.midToNorWeightCriticalValue.value;
                params.kingCtrlRate=this.stdWeightAndSpleenPercent.value;
                params.draftInterval=this.interval.value;
                params.decreseRate=this.reducePercent.value;
                params.cleanLong=this.cleanLong.value;
                params.hintTemper=this.hintTemper.value;
                params.hintWeightChange=this.hintWeightChange.value;
                params.hintWeightFrame=this.hintWeightFrame.value;
                params.feedWeight = this.autoFeedWeight.value;
                params.autoFeedStartHour = this.autoFeedStartHour.value;
                params.autoFeedEndHour = this.autoFeedEndHour.value;
              params.feedRewordLong = this.jianLiFeedTime.value;
              params.feedSuppLong = this.buChongFeedTime.value;
                putAction('/conf/glb', params).then((res) => {
                    if (res.success) {
                        this.$message.success("保存配置信息成功");
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            /* 数据检验回调 */
            handleFlyFledWeightChange(e) {
                let rs = validateNonNegativeNumber(this.flyFledWeight.value);
                this.flyFledWeight.errorMsg = rs.errorMsg;
                this.flyFledWeight.validateStatus = rs.validateStatus;
            },
            handleCeilingTemperatureChange(e) {
                let rs = validatePercentRange(this.ceilingTemperature.value);
                this.ceilingTemperature.errorMsg = rs.errorMsg;
                this.ceilingTemperature.validateStatus = rs.validateStatus;
                if (parseFloat(this.floorTemperature.value) > parseFloat(this.ceilingTemperature.value)) {
                    this.ceilingTemperature.errorMsg = '上限温度必须高于下限温度！';
                    this.ceilingTemperature.validateStatus = 'error';
                }
            },
            handleFloorTemperatureChange(e) {
                let rs = validatePercentRange(this.floorTemperature.value);
                this.floorTemperature.errorMsg = rs.errorMsg;
                this.floorTemperature.validateStatus = rs.validateStatus;
                if (parseFloat(this.floorTemperature.value) > parseFloat(this.ceilingTemperature.value)) {
                    this.floorTemperature.errorMsg = '下限温度必须低于上限温度！';
                    this.floorTemperature.validateStatus = 'error';
                }
            },
            handleOutputErrorChange(e) {
                let rs = validateNonNegativeNumber(this.outputError.value);
                this.outputError.errorMsg = rs.errorMsg;
                this.outputError.validateStatus = rs.validateStatus;
            },
            handleShowDaysOfHistoryChange(e) {
                let rs = validateIntegerNumber(this.showDaysOfHistory.value);
                this.showDaysOfHistory.errorMsg = rs.errorMsg;
                this.showDaysOfHistory.validateStatus = rs.validateStatus;
            },
            handleStdSpleenWeightChange(e) {
                let rs = validateNonNegativeNumber(this.stdSpleenWeight.value);
                this.stdSpleenWeight.errorMsg = rs.errorMsg;
                this.stdSpleenWeight.validateStatus = rs.validateStatus;
            },
            handleLowToMidWeightCriticalValueChange(e) {
                let rs = validateNonNegativeNumber(this.lowToMidWeightCriticalValue.value);
                this.lowToMidWeightCriticalValue.errorMsg = rs.errorMsg;
                this.lowToMidWeightCriticalValue.validateStatus = rs.validateStatus;
                if (parseFloat(this.lowToMidWeightCriticalValue.value) > parseFloat(this.midToNorWeightCriticalValue.value)) {
                    this.lowToMidWeightCriticalValue.errorMsg = '“低重量-中等”临界值必须低于“中等-正常”临界值！';
                    this.lowToMidWeightCriticalValue.validateStatus = 'error';
                }
            },
            handleMidToNorWeightCriticalValueChange(e) {
                let rs = validateNonNegativeNumber(this.midToNorWeightCriticalValue.value);
                this.midToNorWeightCriticalValue.errorMsg = rs.errorMsg;
                this.midToNorWeightCriticalValue.validateStatus = rs.validateStatus;
                if (parseFloat(this.lowToMidWeightCriticalValue.value) > parseFloat(this.midToNorWeightCriticalValue.value)) {
                    this.midToNorWeightCriticalValue.errorMsg = '“中等-正常”临界值必须高于“低重量-中等”临界值！';
                    this.midToNorWeightCriticalValue.validateStatus = 'error';
                }
            },
            handleStdWeightAndSpleenPercentChange(e) {
                let rs = validatePercentRange(this.stdWeightAndSpleenPercent.value);
                this.stdWeightAndSpleenPercent.errorMsg = rs.errorMsg;
                this.stdWeightAndSpleenPercent.validateStatus = rs.validateStatus;
            },
            handleIntervalChange(e) {
                let rs = validateIntegerNumber(this.interval.value);
                this.interval.errorMsg = rs.errorMsg;
                this.interval.validateStatus = rs.validateStatus;
            },
            handleReducePercentChange(e) {
                let rs = validatePercentRange(this.reducePercent.value);
                this.reducePercent.errorMsg = rs.errorMsg;
                this.reducePercent.validateStatus = rs.validateStatus;
            },
            handleCleanLongChange(e) {
                let rs = validateIntegerNumber(this.cleanLong.value);
                this.cleanLong.errorMsg = rs.errorMsg;
                this.cleanLong.validateStatus = rs.validateStatus;
            },
          handleJianLiFeedTimeChange(e) {
            let rs = validateIntegerNumber(this.jianLiFeedTime.value);
            this.jianLiFeedTime.errorMsg = rs.errorMsg;
            this.jianLiFeedTime.validateStatus = rs.validateStatus;
          },
          handleBuChongFeedTimeChange(e) {
            let rs = validateIntegerNumber(this.buChongFeedTime.value);
            this.buChongFeedTime.errorMsg = rs.errorMsg;
            this.buChongFeedTime.validateStatus = rs.validateStatus;
          },
            handleHintTemperChange(e) {
                let rs = validateNonNegativeNumber(this.hintTemper.value);
                this.hintTemper.errorMsg = rs.errorMsg;
                this.hintTemper.validateStatus = rs.validateStatus;
            },
            handleHintWeightChangeChange(e) {
                let rs = validateNonNegativeNumber(this.hintWeightChange.value);
                this.hintWeightChange.errorMsg = rs.errorMsg;
                this.hintWeightChange.validateStatus = rs.validateStatus;
            },
            handleHintWeightFrameChange(e) {
                let rs = validateNonNegativeNumber(this.hintWeightFrame.value);
                this.hintWeightFrame.errorMsg = rs.errorMsg;
                this.hintWeightFrame.validateStatus = rs.validateStatus;
            },
            handleAutoFeedWeightChange(e) {
                let rs = validateNonNegativeNumber(this.autoFeedWeight.value);
                this.autoFeedWeight.errorMsg = rs.errorMsg;
                this.autoFeedWeight.validateStatus = rs.validateStatus;
            },
            handleAutoFeedStartHourChange(e) {
              let rs = validateAutoFeedHour(this.autoFeedStartHour.value);
              this.autoFeedStartHour.errorMsg = rs.errorMsg;
              this.autoFeedStartHour.validateStatus = rs.validateStatus;
              if (rs.validateStatus !== 'success'){
                this.autoFeedEndHour.errorMsg = rs.errorMsg;
              }else if (parseInt(this.autoFeedStartHour.value) >= parseInt(this.autoFeedEndHour.value)) {
                this.autoFeedEndHour.errorMsg = '自动喂食开始时间必须大于结束时间';
                this.autoFeedEndHour.validateStatus = 'error';
              }else{
                this.autoFeedEndHour.errorMsg = null;
                this.autoFeedEndHour.validateStatus = 'success';
              }
            },
            handleAutoFeedEndHourChange(e) {
              let rs = validateAutoFeedHour(this.autoFeedEndHour.value);
              this.autoFeedEndHour.errorMsg = rs.errorMsg;
              this.autoFeedEndHour.validateStatus = rs.validateStatus;
              if (rs.validateStatus !== 'success'){
                this.autoFeedEndHour.errorMsg = rs.errorMsg;
              }else if (parseInt(this.autoFeedStartHour.value) >= parseInt(this.autoFeedEndHour.value)) {
                this.autoFeedEndHour.errorMsg = '自动喂食开始时间必须大于结束时间';
                this.autoFeedEndHour.validateStatus = 'error';
              }else{
                this.autoFeedEndHour.errorMsg = null;
                this.autoFeedEndHour.validateStatus = 'success';
              }
            },
            /* 取消删除 */
            cancel(e) {
            },
        }
    }
</script>

<style scoped>

</style>