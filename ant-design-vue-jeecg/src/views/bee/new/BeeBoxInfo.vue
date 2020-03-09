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
          <router-link :to="{path: prePath}">
            {{preName}}
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          蜂箱信息
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <a-spin :spinning="loading" tip="正在加载">
    <div id="boxInfo">
      <a-card title="蜂箱信息" :bordered="false" style="margin-top: 5px;font-size: large">
        <a-row>
          <a-col :xs="18" :md="21">
            <h2>蜂箱 {{beeBoxInfo.boxId}}</h2>
          </a-col>
          <a-col :xs="6" :md="3">
            <a-popconfirm
              title="确定删除该蜂箱？"
              @confirm="deleteBox(beeBoxInfo.boxId)"
              @cancel="cancel"
              okText="确定"
              cancelText="取消"
            >
              <a-button type="danger" style="margin-top: 13px">
                <a-icon type="delete" />删除
              </a-button>
            </a-popconfirm>
          </a-col>
        </a-row>
        <a-row>
          <a-col :sm="12">
            <a-row>
              <span>联网状态：</span>
              <a-tag color="green" class="largeFontSize" v-if="beeBoxInfo.netStatus === CONSTANT.NORMAL">正常</a-tag>
              <a-tag color="red" class="largeFontSize" v-else>异常</a-tag>
            </a-row>
            <a-row>
              <span>蜂箱状态：</span>
              <a-popover title="提示">
                <template slot="content">
                  <p>低重量: 重量 <= {{lowerWeight}}</p>
                  <p>中等: {{lowerWeight}} < 重量 < {{midWeight}}</p>
                  <p>正常: 重量 >= {{midWeight}}</p>
                </template>
                <a-tag color="red" class="largeFontSize" v-if="beeBoxInfo.beeBoxStatus === CONSTANT.DISNET">异常</a-tag>
                <a-tag color="green" class="largeFontSize" v-else-if="beeBoxInfo.beeBoxStatus === CONSTANT.NORMAL">正常</a-tag>
                <a-tag color="orange" class="largeFontSize" v-else-if="beeBoxInfo.beeBoxStatus === CONSTANT.WARNING">中等</a-tag>
                <a-tag color="red" class="largeFontSize" v-else>低重量</a-tag>
              </a-popover>
              <a-tag color="#108ee9" class="largeFontSize" v-if="beeBoxInfo.cleaning === 1">清洗中</a-tag>
              <a-tag color="#2db7f5" class="largeFontSize" v-if="beeBoxInfo.feeding === 1">喂食中</a-tag>
              <a-tag color="#f50" class="largeFontSize" v-if="beeBoxInfo.heating === 1">加热中</a-tag>
            </a-row>
            <a-row>
              <span >蜂王状态：</span>
              <a-select style="width: 120px" v-model="beeBoxInfo.beeKingStatus" @change="changeKingStatus">
                <a-select-option v-for="(item,index) in kingStatusArray" :key="index" :value='item.value'>{{item.name}}</a-select-option>
              </a-select>
            </a-row>
            <a-row>
              <span>巢门状态：</span>
              <a-select style="width: 120px" :disabled="netDisable" v-model="beeBoxInfo.doorStatus" @change="changeBoxDoorStatus">
                <a-select-option v-for="(item,index) in boxDoorStatusArray" :key="index" :value='item.value'>{{item.name}}</a-select-option>
              </a-select>
            </a-row>
            <a-row>
              <span style="float: left;">重量:</span>
              <div style="margin-left:20px;width:185px;float:left;height: 36px;line-height: 36px;">
                <a-progress :format="percent => calcWeight(percent)" :percent="weightProgress" size="default" status="active" :strokeColor="weightStrokeColor"/>
              </div>
            </a-row>
            <a-row>
              <span style="float: left;">温度:</span>
                <div style="margin-left:20px;width:185px;float:left;height: 36px;line-height: 36px;">
                    <a-progress :format="percent => calcTemper(percent)" :percent="tempProgress" size="default" status="active" :strokeColor="tempStrokeColor"/>
                </div>
            </a-row>
            <a-row>
              <span>框数：</span>
              <a-button-group default-value="horizontal">
                <a-button @click="changeFrameNumber(-1)">
                  <a-icon type="minus" />
                </a-button>
                <a-button>{{beeBoxInfo.frameNumber}}</a-button>
                <a-button @click="changeFrameNumber(1)">
                  <a-icon type="plus" />
                </a-button>
              </a-button-group>
              <a-button type="primary" style="margin-left: 5px" @click="boxOutput" :disabled="beeBoxInfo.frameNumber === 0">产出
                <a-spin style="margin-left: 10px" :spinning="outputLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-row>
          </a-col>
          <a-col :sm="12">
            <a-row>
              <span>自动喂食：</span>
              <a-switch  checkedChildren="开" unCheckedChildren="关" v-model="beeBoxInfo.autoFeed" @change="changeAutoFeed" />
            </a-row>
            <a-row>
              <span>自动加热：</span>
              <a-switch  checkedChildren="开" unCheckedChildren="关" v-model="beeBoxInfo.autoHeat" @change="changeAutoHeat" />
            </a-row>
            <a-row>
              <span>喂食：</span>
              <a-button :disabled="netDisable" type="primary" style="margin-left: 5px" @click="feed(1)">奖励喂食
                <a-spin style="margin-left: 10px" :spinning="jiangLiLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
              <a-button :disabled="netDisable" type="primary" style="margin-left: 5px" @click="feed(0)">补偿喂食
                <a-spin style="margin-left: 10px" :spinning="buChangLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-row>
            <a-row>
              <span>清洗：</span>
              <a-button :disabled="netDisable" type="primary" style="margin-left: 5px" @click="clean">清洗蜂箱
                <a-spin style="margin-left: 10px" :spinning="cleanLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-row>
            <a-row>
              <span>备注：</span>
              <a-textarea placeholder="请输入备注信息" :rows="4" v-model="beeBoxInfo.note"/>
              <a-button type="primary" style="margin-top: 20px" @click="updateNote">修改备注</a-button>
            </a-row>
          </a-col>
        </a-row>
      </a-card>
      <!-- 历史记录 -->
      <a-card title="历史记录" :bordered="false" style="margin-top: 15px;">
        <a-tabs type="card" >
          <a-tab-pane tab="喂食" key="1">
            <a-table :columns="feedColumns" :dataSource="beeBoxInfo.feedData" bordered/>
          </a-tab-pane>
          <a-tab-pane tab="加热" key="2">
            <a-table :columns="heatColumns" :dataSource="beeBoxInfo.heatData" bordered/>
          </a-tab-pane>
          <a-tab-pane tab="清洗" key="3">
            <a-table :columns="cleanColumns" :dataSource="beeBoxInfo.cleanData" bordered/>
          </a-tab-pane>
          <a-tab-pane tab="产出" key="4">
            <a-table :columns="outputColumns" :dataSource="beeBoxInfo.outputData" bordered/>
          </a-tab-pane>
        </a-tabs>
      </a-card>
    </div>
    </a-spin>
  </div>
</template>

<script>
    import {deleteAction, getAction, postAction, putAction} from "../../../api/manage";
    import Utils from '@/api/utils'


    const CONSTANT = {
        DISNET : -1,
        NORMAL: 0,
        WARNING: 1,
        EXCEPTION: 2,
    };
    const outputColumns = [
        {
            title: '产出蜜(g)',
            dataIndex: 'honey',
        },
        {
            title: '产出脾(脾)',
            dataIndex: 'frame',
        },
        {
            title: '产出时间',
            dataIndex: 'time',
        },
    ];
    const feedColumns = [
        {
            title: '喂食命令',
            dataIndex: 'feedOrder',
        },
        {
            title: '喂食状态',
            dataIndex: 'feedStatus',
        },
        {
            title: '喂食类型',
            dataIndex: 'feedType',
        },
        {
            title: '喂食时间',
            dataIndex: 'feedTime',
        },

    ];
    const heatColumns = [
        {
            title: '加热状态',
            dataIndex: 'heatStatus',
        },
        {
            title: '起始温度(℃)',
            dataIndex: 'heatStartTemper',
        },
        {
            title: '结束温度(℃)',
            dataIndex: 'heatEndTemper',
        },
        {
            title: '起始加热时间',
            dataIndex: 'heatStarttime',
        },
        {
            title: '结束加热时间',
            dataIndex: 'heatEndtime',
        },
    ];
    const cleanColumns = [
        {
            title: '清洗状态',
            dataIndex: 'cleanStatus',
        },
        {
            title: '清洗时间',
            dataIndex: 'cleanTime',
        },
        {
            title: '清洗时长',
            dataIndex: 'cleanLong',
        },

    ];

    export default {
        name: "BeeBoxInfo",
        data() {
            return {
                CONSTANT,
                preName: "",
                prePath: "",
                kingStatusArray: [
                    {value: '正常', name: '正常'},
                    {value: '种王', name: '种王'},
                    {value: '病王', name: '病王'},
                    {value: '处女王', name: '处女王'},
                    {value: '老王',name: '老王'},
                    {value: '产卵王',name: '产卵王'},
                    {value: '失王',name: '失王'},
                ],
                boxDoorStatusArray: [
                    {name: "清洗", value: 0},
                    {name: "控王", value: 1},
                    {name: "正常", value: 2},
                    {name: "通风", value: 3},
                ],
                outputColumns,
                heatColumns,
                cleanColumns,
                feedColumns,

                beeBoxInfo: {
                    boxId:"",
                    netStatus:"",
                    beeBoxStatus:-1,
                    beeKingStatus:"",
                    doorStatus:"",
                    weight:0,
                    temperature:0,
                    frameNumber:"",
                    note:"",
                    autoFeed: null,
                    autoHeat: null,
                    outputData:[],
                    heatData:[],
                    cleanData:[],
                    feedData:[],
                    cleaning:0,
                    feeding:0,
                    heating:0,
                },

                lowerWeight: 0,
                midWeight: 0,
                /* 操作等待 */
                outputLoading: false,
                jiangLiLoading: false,
                buChangLoading: false,
                cleanLoading: false,

                loading:true,
                /*重量进度条*/
                weightProgress:0,
                weightStrokeColor:null,
                /*温度进度条*/
                tempProgress:0,
                tempStrokeColor:null,
                /*断网时关闭按钮*/
                netDisable:true,
                /*定时器*/
                timer:null,
                feedStartTimer:null,
                cleanStartTimer:null,
            };
        },
        created() {
            this.beeBoxInfo.boxId = this.$route.query.boxId;
            this.preName = this.$route.query.preName;
            this.prePath = this.$route.query.prePath;
            this.getAllHistory();
            getAction('/conf/glb', {uid: this.$store.getters.userInfo.id}).then((res) => {
              if (res.success) {
                console.log(res.result.boxLowWeight)
                console.log(res.result.boxNormalWeight)
                this.lowerWeight = res.result.boxLowWeight;
                this.midWeight = res.result.boxNormalWeight;
                this.getBeeBoxStatusById(this.beeBoxInfo.boxId);
                this.loading = false;
              } else {
                this.$message.warning(res.message);
              }
            });
            this.timer = setInterval(()=>{
              this.getBeeBoxInfo(this.beeBoxInfo.boxId);
            },10000);
            this.$once('hook:beforeDestroy', () => {
              clearInterval(this.timer);
              // Utils.$off('oper');
            });
        },
        mounted(){
          var that = this;
          Utils.$on('clean', function (bbid) {
            that.cleanStart(bbid);
          });
          Utils.$on('feed', function (bbid) {
            that.feedStart(bbid);
          });
          Utils.$on('cleanOk', function (bbid) {
            that.cleanComplete(bbid);
          });
          Utils.$on('feedOk', function (bbid) {
            that.feedComplete(bbid);
          });
        },
        methods: {
            feedStart(bbid){
              if (this.beeBoxInfo.boxId == bbid){
                // this.netDisable = false;
                this.jiangLiLoading = false;
                this.buChangLoading = false;
                this.beeBoxInfo.feeding = 1;
                clearTimeout(this.feedStartTimer);
              }
            },
            cleanStart(bbid){
              if (this.beeBoxInfo.boxId == bbid) {
                // this.netDisable = false;
                this.cleanLoading = false;
                this.beeBoxInfo.cleaning = 1;
                clearTimeout(this.cleanStartTimer);
              }
            },
            feedComplete(bbid){
              if (this.beeBoxInfo.boxId == bbid) {
                this.beeBoxInfo.feeding = 0;
                this.getAllHistory();
              }
            },
            cleanComplete(bbid){
              if (this.beeBoxInfo.boxId == bbid) {
                this.beeBoxInfo.cleaning = 0;
                this.getAllHistory();
              }
            },

            calcWeight(){
              let weight = this.beeBoxInfo.weight;
              let frame = this.beeBoxInfo.frameNumber;
              if (weight === null){
                return 'NaN'
              }
              if ( weight > this.midWeight * frame){
                this.weightStrokeColor = 'green'
              } else if(weight < this.lowerWeight * frame){
                this.weightStrokeColor = 'red'
              } else {
                this.weightStrokeColor = 'DeepSkyBlue '
              }
              return (weight).toFixed(2) + ' g';
            },
            calcTemper(){
              let temper = this.beeBoxInfo.temperature;
              if (temper === null){
                return 'NaN'
              }
              if ( temper > 20){
                this.tempStrokeColor = 'red'
              } else if(temper < 5){
                this.tempStrokeColor = 'DeepSkyBlue'
              } else {
                this.tempStrokeColor = 'orange'
              }
              return (temper).toFixed(2) + ' ℃';
            },
            getBeeBoxInfo(boxId){
              getAction('/bee/boxInfo/bbid', {bbid:boxId}).then((res) => {
                if (res.success) {
                  if (res.result != null) {
                    this.beeBoxInfo.weight = res.result.weight;
                    this.weightProgress = ((res.result.weight)/25000) * 100;
                    this.beeBoxInfo.temperature = res.result.temper;
                    this.tempProgress = ((res.result.temper)/40) * 100;
                    if (res.result.weightFrameRate < this.lowerWeight){
                      this.beeBoxInfo.beeBoxStatus = CONSTANT.EXCEPTION;
                    } else if(res.result.weightFrameRate >= this.midWeight){
                      this.beeBoxInfo.beeBoxStatus = CONSTANT.NORMAL;
                    } else{
                      this.beeBoxInfo.beeBoxStatus = CONSTANT.WARNING;
                    }
                  }
                } else {
                  this.$message.warning(res.message);
                }
              });
            },
            getBeeBoxStatusById(boxId) {
                getAction('/bee/boxStatus/bbid', {bbid:boxId}).then((res) => {
                    if (res.success) {
                        console.log(res.result);
                        this.beeBoxInfo.netStatus = res.result.netStatus;
                        if (this.beeBoxInfo.netStatus == 0){
                          this.netDisable = false;
                        } else {
                          this.netDisable = true;
                        }
                        // this.beeBoxInfo.beeBoxStatus = res.result.boxStatus;
                        this.beeBoxInfo.beeKingStatus = res.result.kingStatus;
                        this.beeBoxInfo.doorStatus = res.result.doorStatus;
                        this.beeBoxInfo.note = res.result.remark;
                        this.beeBoxInfo.autoFeed = res.result.autoFeed === 1;
                        this.beeBoxInfo.autoHeat = res.result.autoHeat === 1;
                        this.beeBoxInfo.frameNumber = res.result.frameNum;
                        this.beeBoxInfo.cleaning = res.result.cleaning;
                        this.beeBoxInfo.feeding = res.result.feeding;
                        this.beeBoxInfo.heating = res.result.heating;
                        this.getBeeBoxInfo(boxId);
                    } else {
                        this.$message.warning(res.message);
                    }
                });

            },
            changeKingStatus(value) {
                let params = {
                    bbid: this.beeBoxInfo.boxId,
                    kingStatus: value,
                };
                putAction('/bee/boxStatus/bbid', params).then((res) => {
                    if (res.success) {
                        this.$message.success("修改成功");
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            changeBoxDoorStatus(value) {
                if (this.beeBoxInfo.netStatus == 1){
                  this.$message.error("蜂箱未联网");
                  return;
                }
                let params = {
                    uid: this.$store.getters.userInfo.id,
                    bbids :[this.beeBoxInfo.boxId],
                    doorStatus: value,
                };
                postAction('/oper/door/mult', params).then((res) => {
                    if (res.success) {
                        this.$message.success("修改成功");
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            changeFrameNumber(number) {
                let tmp = this.beeBoxInfo.frameNumber + number;
                if (tmp >= 0 && tmp <= 10) {
                    this.beeBoxInfo.frameNumber = tmp;
                    let params = {
                        bbid: this.beeBoxInfo.boxId,
                        frameNum: this.beeBoxInfo.frameNumber,
                    };
                    putAction('/bee/boxStatus/bbid', params).then((res) => {
                        if (res.success) {
                            this.$message.success("信息修改成功");
                        } else {
                            this.$message.warning(res.message);
                        }
                    });
                }
                this.$forceUpdate();
            },
            boxOutput() {
                if (this.outputLoading) {
                    this.$message.warning('正在进行产出操作');
                    return;
                }
                this.outputLoading = true;
                this.beeBoxInfo.frameNumber -= 1;
                this.$forceUpdate();
                postAction('/oper/output/frame/bbid', {bbid:this.beeBoxInfo.boxId}).then((res) => {
                    if (res.success) {
                        this.$message.success(res.message);
                        this.outputLoading = false;
                    } else {
                        this.$message.warning(res.message);
                        this.outputLoading = false;

                    }
                });
            },
            changeAutoFeed(checked) {
                let params = {
                    bbid: this.beeBoxInfo.boxId,
                    autoFeed: checked === true ? 1 : 0,
                };
                putAction('/bee/boxStatus/bbid', params).then((res) => {
                    if (res.success) {
                        if (checked) {
                            this.$message.success("开启自动喂食成功");
                        } else {
                            this.$message.success("关闭自动喂食成功");
                        }
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            changeAutoHeat(checked) {
                let params = {
                    bbid: this.beeBoxInfo.boxId,
                    autoHeat: checked === true ? 1 : 0,
                };
                putAction('/bee/boxStatus/bbid', params).then((res) => {
                    if (res.success) {
                        if (checked) {
                            this.$message.success("开启自动加热成功");
                        } else {
                            this.$message.success("关闭自动加热成功");
                        }
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            feed(mode) {
                if (this.buChangLoading || this.jiangLiLoading) {
                    this.$message.warning('正在进行喂食操作');
                    return;
                }
                if (mode === 0) {
                    this.buChangLoading = true;
                }else if (mode === 1) {
                    this.jiangLiLoading = true;
                }
                // 喂食定时器 5s延迟
                this.feedStartTimer = setTimeout(()=>{
                  this.$message.warning('喂食失败:超时');
                  this.buChangLoading = false;
                  this.jiangLiLoading = false;
                },5000);
                let params = {};
                params.uid = this.$store.getters.userInfo.id;
                params.bbids = [this.beeBoxInfo.boxId];
                params.feedOrder = mode;
                postAction('/oper/feed/mult', params).then((res) => {
                    if (res.success) {
                        let flag =  true;
                        for (let i = 0; i < res.result.length; i++) {
                            if (res.result[i].errorMessage != null) {
                                this.$message.error(res.result[i].errorMessage);
                                flag = false;
                                this.buChangLoading = false;
                                this.jiangLiLoading = false;
                                clearTimeout(this.feedStartTimer);
                            }
                        }
                        if (flag) {
                            if (mode === 1) {
                                // this.$message.success("奖励喂食成功");
                            } else {
                                // this.$message.success("补偿喂食成功");
                            }
                            // this.beeBoxInfo.feeding = 1;
                        }
                        if (mode === 0) {
                            // this.buChangLoading = false;
                        }else if (mode === 1) {
                            // this.jiangLiLoading = false;
                        }
                    } else {
                        this.$message.warning(res.message);
                        if (mode === 0) {
                            this.buChangLoading = false;
                        }else if (mode === 1) {
                            this.jiangLiLoading = false;
                        }
                    }
                });
            },
            clean() {
                if (this.cleanLoading) {
                    this.$message.warning('正在进行清洗操作');
                    return;
                }
                this.cleanLoading = true;
                // 清洗定时器 5s延迟
                this.cleanStartTimer = setTimeout(()=>{
                  this.$message.warning('清洗失败:超时');
                  this.cleanLoading = false;
                },5000);

                let params = {};
                params.uid = this.$store.getters.userInfo.id;
                params.bbids = [this.beeBoxInfo.boxId];
                postAction('/oper/clean/mult', params).then((res) => {
                    if (res.success) {
                        let flag = true;
                        for (let i = 0; i < res.result.length; i++) {
                            if (res.result[i].errorMessage != null) {
                                this.$message.error(res.result[i].errorMessage);
                                flag = false;
                                this.cleanLoading = false;
                                clearTimeout(this.cleanStartTimer);
                            }
                        }
                        if (flag) {
                            // this.$message.success("清洗成功");
                            // this.beeBoxInfo.cleaning = 1;
                        }
                      // this.cleanLoading = false;
                    } else {
                        this.$message.warning(res.message);
                        this.cleanLoading = false;
                    }
                });
            },
            // 修改备注
            updateNote() {
                let params = {
                    bbid: this.beeBoxInfo.boxId,
                    remark: this.beeBoxInfo.note
                };
                putAction('/bee/boxStatus/bbid', params).then((res) => {
                    if (res.success) {
                        this.$message.success("修改备注成功");
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            getAllHistory() {
                getAction('/oper/history/bbid', {uid:this.$store.getters.userInfo.id,bbid:this.beeBoxInfo.boxId}).then((res) => {
                    console.log(res.result);
                    if (res.success) {
                        this.beeBoxInfo.cleanData = [];
                        this.beeBoxInfo.heatData = [];
                        this.beeBoxInfo.feedData = [];
                        for (let i = 0; i < res.result.length; i++) {
                            if (res.result[i].cleanTime != null) {
                                let tmp = res.result[i].cleanStatus;
                                if (tmp === 0) {
                                    tmp = '成功';
                                } else if (tmp === 1) {
                                    tmp = '失败';
                                }else if (tmp === 2) {
                                    tmp = '清洗中';
                                }
                                let clean = {
                                    cleanTime: res.result[i].cleanTime,
                                    cleanLong: res.result[i].cleanLong,
                                    cleanStatus: tmp
                                };
                                this.beeBoxInfo.cleanData.push(clean);
                            } else if (res.result[i].heatStatus != null) {
                                let tmp = res.result[i].heatStatus;
                                if (tmp === 0) {
                                    tmp = '成功';
                                } else if (tmp === 1) {
                                    tmp = '失败';
                                }else if (tmp === 2) {
                                    tmp = '加热中';
                                }
                                let heat = {
                                    heatStarttime: res.result[i].heatStarttime,
                                    heatEndtime: res.result[i].heatEndtime,
                                    heatStartTemper: res.result[i].heatStartTemper,
                                    heatEndTemper: res.result[i].heatEndTemper,
                                    heatStatus: tmp
                                };
                                this.beeBoxInfo.heatData.push(heat);
                            } else if (res.result[i].feedTime != null) {
                                let tmp = res.result[i].feedStatus;
                                if (tmp === 0) {
                                    tmp = '成功';
                                } else if (tmp === 1) {
                                    tmp = '失败';
                                }else if (tmp === 2) {
                                    tmp = '喂食中';
                                }
                                let tmp2 = res.result[i].feedType;
                                if (tmp2 === 0) {
                                    tmp2 = '自动';
                                } else if (tmp2 === 1) {
                                    tmp2 = '手动';
                                }
                                let tmp3 = res.result[i].feedOrder;
                                if (tmp3 === 0) {
                                    tmp3 = '补偿喂食';
                                } else if (tmp3 === 1) {
                                    tmp3 = '奖励喂食';
                                }
                                let feed = {
                                    rowKey: i,
                                    feedTime: res.result[i].feedTime,
                                    feedType: tmp2,
                                    feedOrder: tmp3,
                                    feedStatus: tmp
                                };
                                this.beeBoxInfo.feedData.push(feed);
                            }
                        }
                    } else {
                        this.$message.warning(res.message);
                    }
                });
                getAction('/oper/output/history/bbid', {bbid: this.beeBoxInfo.boxId}).then((res) => {
                    if (res.success) {
                        this.beeBoxInfo.outputData = [];
                        this.beeBoxInfo.outputData = res.result;
                    } else {
                        this.$message.warning(res.message);
                    }
                });

            },
            deleteBox(boxId) {
                deleteAction('/bee/delbox', {bbid:boxId}).then((res) => {
                    if (res.success) {
                        this.$message.success(res.message);
                        this.$router.push({path: '/bee/new/BeeBoxControl'});
                    } else {
                        this.$message.warning(res.message);
                    }
                });
            },
            /* 取消删除 */
            cancel(e) {
            },
        }
    }
</script>

<style scoped>
  #boxInfo span {
    font-size: x-large
  }
  .largeFontSize {
    font-size: large
  }
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