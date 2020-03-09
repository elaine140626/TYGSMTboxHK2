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
          蜂王信息
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <!--<div style="background-color: #ececec; padding: 20px;">
      <a-row>
        <h1>蜂王状态</h1>
      </a-row>
      <a-row :gutter="16" >
        <a-col :span="6" :xs="24" :sm="6">
          <a-card title="种王" :bordered="false" style="margin-top: 5px">
            <template v-for="item of zhongwang">
              <a-tag color='pink' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
            </template>
          </a-card>
        </a-col>
        <a-col :span="6" :xs="24" :sm="6">
          <a-card title="产卵王" :bordered="false" style="margin-top: 5px">
            <template v-for="item of chanluanwang">
              <a-tag color='cyan' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
            </template>
          </a-card>
        </a-col>
        <a-col :span="6" :xs="24" :sm="6">
          <a-card title="处女王" :bordered="false" style="margin-top: 5px">
            <template v-for="item of chunvwang">
              <a-tag color='blue' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
            </template>
          </a-card>
        </a-col>
        <a-col :span="6" :xs="24" :sm="6">
          <a-card title="矢王" :bordered="false" style="margin-top: 5px">
            <template v-for="item of shiwang">
              <a-tag color='purple' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
            </template>
          </a-card>
        </a-col>
      </a-row>
    </div>-->
    <div style="margin-top: 5px">
      <a-collapse accordion @change="selectBoxesByKingStatus">
        <a-collapse-panel :header="zhengchangHead" key="1" :disabled="zhengchang.length === 0" >
          <template v-for="item of zhengchang">
            <a-tag color='blue' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="zhongwangHead" key="2" :disabled="zhongwang.length === 0">
          <template v-for="item of zhongwang">
            <a-tag color='yellow' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="bingwangHead" key="3" :disabled="bingwang.length === 0">
          <template v-for="item of bingwang">
            <a-tag color='gray' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="chunvwangHead" key="4" :disabled="chunvwang.length === 0">
          <template v-for="item of chunvwang">
            <a-tag color='LightPink' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="laowangHead" key="5" :disabled="laowang.length === 0">
          <template v-for="item of laowang">
            <a-tag color='DarkBlue' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="chanluanwangHead" key="6" :disabled="chanluanwang.length === 0">
          <template v-for="item of chanluanwang">
            <a-tag color='Orange' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
        <a-collapse-panel :header="shiwangHead" key="7" :disabled="shiwang.length === 0">
          <template v-for="item of shiwang">
            <a-tag color='Maroon' class='largeTag' @click="gotoBeeBoxInfo(item)" style="margin-top: 2px;font-size: x-large">{{item}}</a-tag>
          </template>
        </a-collapse-panel>
      </a-collapse>
    </div>

    <!--批量操作对话框-->
    <oper-mult :checked-list="checkedList" ></oper-mult>

    <!-- 批量操作 -->
    <a-card title="批量操作" :bordered="false" style="margin-top: 10px">
      <!--<a-row>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('正常')">正常</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('种王')" >种王</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('病王')">病王</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('处女王')">处女王</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('老王')">老王</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('产卵王')">产卵王</a-button>
        <a-button type="primary" :style="{marginLeft: '5px',marginTop: '5px'}" @click="selectBoxesByKingStatus('失王')">失王</a-button>
      </a-row>-->
      <template>
        <div>
          <div :style="{ borderBottom: '1px solid #E9E9E9'}">
            <a-row>
              <a-checkbox :indeterminate="indeterminate" @change="onCheckAllChange" :checked="checkAll" :style="{marginBottom: '10px' }">全选</a-checkbox>
              <a-button type="primary" :style="{marginLeft: '20px',marginTop: '5px'}" @click="showModal" :disabled="checkedList.length===0">批量操作</a-button>
            </a-row>
          </div>
          <br/>
          <!--<a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange"/>-->
          <a-row>
            <template v-if="plainOptions.length !== 0">
              <a-col :xs="24" :sm="6" style="margin-bottom: 5px" v-for="item in plainOptions">
                <a-checkbox @change="onChange" :value="item.boxId" :checked="item.checked" :disabled="item.netStatus !== 0">
                  <div style="display: inline;">
                    <span style="margin-right: 5px" @click="gotoBeeBoxInfo(item.boxId)">{{item.boxId}}</span>
                    <template v-if="item.netStatus === 0">
                      <a-tag color="#2db7f5" v-if="item.feedStatus === 1">喂食中</a-tag>
                      <a-tag color="#f50" v-if="item.heatStatus === 1">加热中</a-tag>
                      <a-tag color="#108ee9" v-if="item.cleanStatus === 1">清洗中</a-tag>
                    </template>
                    <a-tag color="#DD001B" v-else>断网</a-tag>
                  </div>
                </a-checkbox>
              </a-col>
            </template>
            <template v-else>
              <div class="ant-table-placeholder">
                <div class="ant-empty ant-empty-normal">
                  <div class="ant-empty-image">
                    <img alt="暂无数据" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgIDxlbGxpcHNlIGZpbGw9IiNGNUY1RjUiIGN4PSIzMiIgY3k9IjMzIiByeD0iMzIiIHJ5PSI3Ii8+CiAgICA8ZyBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0iI0Q5RDlEOSI+CiAgICAgIDxwYXRoIGQ9Ik01NSAxMi43Nkw0NC44NTQgMS4yNThDNDQuMzY3LjQ3NCA0My42NTYgMCA0Mi45MDcgMEgyMS4wOTNjLS43NDkgMC0xLjQ2LjQ3NC0xLjk0NyAxLjI1N0w5IDEyLjc2MVYyMmg0NnYtOS4yNHoiLz4KICAgICAgPHBhdGggZD0iTTQxLjYxMyAxNS45MzFjMC0xLjYwNS45OTQtMi45MyAyLjIyNy0yLjkzMUg1NXYxOC4xMzdDNTUgMzMuMjYgNTMuNjggMzUgNTIuMDUgMzVoLTQwLjFDMTAuMzIgMzUgOSAzMy4yNTkgOSAzMS4xMzdWMTNoMTEuMTZjMS4yMzMgMCAyLjIyNyAxLjMyMyAyLjIyNyAyLjkyOHYuMDIyYzAgMS42MDUgMS4wMDUgMi45MDEgMi4yMzcgMi45MDFoMTQuNzUyYzEuMjMyIDAgMi4yMzctMS4zMDggMi4yMzctMi45MTN2LS4wMDd6IiBmaWxsPSIjRkFGQUZBIi8+CiAgICA8L2c+CiAgPC9nPgo8L3N2Zz4K"></div>
                  <p class="ant-empty-description">暂无蜂箱</p>
                </div>
              </div>
            </template>
          </a-row>
        </div>
      </template>
    </a-card>

  </div>
</template>

<script>
    import {getAction, postAction, putAction} from "../../../api/manage";
    import OperMult from '@/components/operMult/operMult';
    import Utils from '@/api/utils';

    export default {
        name: "BeeKingInfo",
      components: {OperMult},
      data() {
            return {
                zhengchang: [],
                zhongwang: [],
                bingwang: [],
                chunvwang: [],
                laowang: [],
                chanluanwang: [],
                shiwang: [],
                zhengchangHead: '',
                zhongwangHead: '',
                bingwangHead: '',
                chunvwangHead: '',
                laowangHead: '',
                shiwangHead:'',
                chanluanwangHead: '',
                Head: '',
                /* 多选框 */
                checkedList: [],// 选中项
                indeterminate: true,
                checkAll: false,
                plainOptions: [],// 所有项
                /* 对话框 */
                modalTitle: '',
                ModalText: '',
                visible: false,
                confirmLoading: false,
                /* 批量操作加载 */
                autoHeatOnLoading: false,
                autoHeatOffLoading: false,
                autoFeedOnLoading: false,
                autoFeedOffLoading: false,
                buChangFeedLoading: false,
                jianLiFeedLoading: false,
                cleanLoading: false,
                doorCleanLoading: false,
                doorKongWangLoading: false,
                doorZhengChangLoading: false,
                doorTongFengLoading: false,

            };
        },
        created() {
            this.getAllBeeKingStatus();

        },
        methods: {
            gotoBeeBoxInfo(boxId) {
                this.$router.push({path: '/bee/new/BeeBoxInfo', query: {boxId:boxId,prePath:'/bee/new/BeeBoxControl',preName:'蜂箱控制'}});
            },
            getAllBeeKingStatus() {
                getAction('/bee/boxStatus/uid', {uid: this.$store.getters.userInfo.id}).then((res) => {
                    if (res.success) {
                        let obj = [];
                        for (let i = 0; i < res.result.length; i++) {
                            let tmp = {};
                            tmp.boxId = res.result[i].bbid;
                            tmp.kingStatus = res.result[i].kingStatus;
                            if (tmp.kingStatus === '正常') {
                                this.zhengchang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '种王') {
                                this.zhongwang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '病王') {
                                this.bingwang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '处女王') {
                                this.chunvwang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '老王') {
                                this.laowang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '产卵王') {
                                this.chanluanwang.push(tmp.boxId);
                            }else if (tmp.kingStatus === '失王') {
                                this.shiwang.push(tmp.boxId);
                            }
                            tmp.netStatus = res.result[i].netStatus;
                            tmp.feedStatus = res.result[i].feeding;
                            tmp.heatStatus = res.result[i].heating;
                            tmp.cleanStatus = res.result[i].cleaning;
                            tmp.checked = false;
                            obj.push(tmp);
                        }
                        this.plainOptions = obj;
                    } else {
                        this.$message.warning(res.message);
                    }
                    this.zhengchangHead= '正常（'+this.zhengchang.length+'）';
                    this.zhongwangHead= '种王（'+this.zhongwang.length+'）';
                    this.bingwangHead= '病王（'+this.bingwang.length+'）';
                    this.chunvwangHead= '处女王（'+this.chunvwang.length+'）';
                    this.laowangHead= '老王（'+this.laowang.length+'）';
                    this.chanluanwangHead= '产卵王（'+this.chanluanwang.length+'）';
                    this.shiwangHead='失王（'+this.shiwang.length+'）';
                });
            },
            // gotoBeeBoxInfo(bbid) {
            //     this.$router.push({path: '/bee/new/BeeBoxInfo', query: {boxId:bbid,prePath:'/bee/new/BeeKingInfo',preName:'蜂王信息'}});
            // },
            selectBoxesByKingStatus(status) {
                this.checkedList = [];
                for (let i = 0; i < this.plainOptions.length; i++) {
                    this.plainOptions[i].checked = false;
                }
                if (status === '1') {
                    this.filterKingStatusOnline('正常');
                }else if (status === '2') {
                    this.filterKingStatusOnline('种王');
                }else if (status === '3') {
                    this.filterKingStatusOnline('病王');
                }else if (status === '4') {
                    this.filterKingStatusOnline('处女王');
                }else if (status === '5') {
                    this.filterKingStatusOnline('老王');
                }else if (status === '6') {
                    this.filterKingStatusOnline('产卵王');
                }else if (status === '7') {
                    this.filterKingStatusOnline('失王');
                }

                for (let i = 0; i <= this.checkedList.length; i++) {
                    for (let j = 0; j < this.plainOptions.length; j++) {
                        if (this.plainOptions[j].boxId === this.checkedList[i]) {
                            this.plainOptions[j].checked = true;
                        }
                    }
                }
                this.indeterminate = !!this.checkedList.length && this.checkedList.length < this.plainOptions.length;
                this.checkAll = this.checkedList.length === this.plainOptions.length;

            },
            filterKingStatusOnline(status) {
                for (let i = 0; i < this.plainOptions.length; i++) {
                    if (this.plainOptions[i].netStatus === 0 && this.plainOptions[i].kingStatus === status) {
                        this.checkedList.push(this.plainOptions[i].boxId);
                    }
                }
            },
            /* 多选框 */
            onChange(checkedValue) {
                if (checkedValue.target.checked) {
                    this.checkedList.push(checkedValue.target.value);
                    for (let i = 0; i < this.plainOptions.length; i++) {
                        if (this.plainOptions[i].boxId === checkedValue.target.value) {
                            this.plainOptions[i].checked = true;
                            break;
                        }
                    }
                } else {
                    this.checkedList = this.checkedList.filter(function (element, index) {
                        return element !== checkedValue.target.value;
                    });
                    for (let i = 0; i < this.plainOptions.length; i++) {
                        if (this.plainOptions[i].boxId === checkedValue.target.value) {
                            this.plainOptions[i].checked = false;
                            break;
                        }
                    }
                }
                this.indeterminate = !!this.checkedList.length && this.checkedList.length < this.plainOptions.length;
                this.checkAll = this.checkedList.length === this.plainOptions.length;
            },
            onCheckAllChange(e) {
                this.checkedList = [];
                if (e.target.checked) {
                    for (let i = 0; i < this.plainOptions.length; i++) {
                        if (this.plainOptions[i].netStatus === 0) {
                            this.plainOptions[i].checked = true;
                            this.checkedList.push(this.plainOptions[i].boxId)
                        }
                    }
                    this.indeterminate = true;
                } else {
                    this.plainOptions = this.plainOptions.map(function (item, index, arr) {
                        item['checked'] = false;
                        return item;
                    });
                    this.indeterminate = false;

                }
                this.checkAll = e.target.checked;

            },
            /* 批量操作对话框 */
            showModal() {
                Utils.$emit('showModal');
            },
        }
    }
</script>

<style scoped>
  .largeTag {
    font-size: large;
  }
</style>