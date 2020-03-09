<template>
  <div>
<!--    <div>
      <a-breadcrumb>
        <a-breadcrumb-item>
          <router-link :to="{path: '/'}">
            <a-icon type="home" />
            首页
          </router-link>
        </a-breadcrumb-item>
        <a-breadcrumb-item>
          蜂箱控制
        </a-breadcrumb-item>
      </a-breadcrumb>
    </div>-->
    <div>
      <div class="ant-card ant-card-wider-padding ant-card-padding-transition">
      <div class="ant-card-head">
        <div class="ant-card-head-wrapper">
          <div class="ant-card-head-title">
            <a-row>
              <a-col :xs="24" :sm="6" :md="4" :lg="3" :xxl="2">我的蜂箱</a-col>
              <a-col :xs="8" :sm="6" :md="4" :lg="3" :xxl="2" style="text-align: center">
                <a-button type="primary" @click="()=>{this.$router.push({path: '/bee/new/BoxMonitor'})}"><a-icon type="eye" />视频监控</a-button>
              </a-col>
              <a-col :xs="8" :sm="6" :md="4" :lg="3" :xxl="2" style="text-align: center">
                <a-button type="primary"  @click="addBoxModal"><a-icon type="plus"/>添加蜂箱</a-button>
              </a-col>
              <!-- 添加蜂箱-->
              <a-modal
                title="添加蜂箱"
                :visible="addBoxVisible"
                @ok="addNewBox"
                :confirmLoading="confirmLoading2"
                @cancel="handleCancel2"
              >
                <a-form :layout="formLayout">
                  <!-- 蜂箱编号 -->
                  <a-form-item
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
                    label="蜂箱编号"
                    help="仅支持4位长度的编号，且为数字0-9与字符A-Z（大写）组合"
                  >
                    <a-input v-model="box_id" placeholder="请输入蜂箱编号"/>
                  </a-form-item>
                  <!-- 设置框数 -->
                  <a-form-item
                    label="框数(个)"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
                  >
                    <!--<a-input v-model="frame_num" placeholder="请输入框数"/>-->
                    <a-select defaultValue="0" style="width: 120px" v-model="frame_num">
                      <a-select-option value="0">0</a-select-option>
                      <a-select-option value="1">1</a-select-option>
                      <a-select-option value="2">2</a-select-option>
                      <a-select-option value="3">3</a-select-option>
                      <a-select-option value="4">4</a-select-option>
                      <a-select-option value="5">5</a-select-option>
                      <a-select-option value="6">6</a-select-option>
                      <a-select-option value="7">7</a-select-option>
                      <a-select-option value="8">8</a-select-option>
                      <a-select-option value="9">9</a-select-option>
                      <a-select-option value="10">10</a-select-option>
                    </a-select>
                  </a-form-item>
                  <!-- 蜂王状态 -->
                  <a-form-item
                    label="蜂王状态"
                    :label-col="formItemLayout.labelCol"
                    :wrapper-col="formItemLayout.wrapperCol"
                  >
                    <a-select v-model="bee_status">
                      <a-select-option v-for="(item,index) in beeStatusList" :key="index" :value='item.value'>{{item.name}}</a-select-option>
                    </a-select>
                  </a-form-item>
                </a-form>
              </a-modal>
            </a-row>
          </div>
        </div>
      </div>
      </div>


      <a-row>
        <template v-if="plainOptions.length !== 0">
          <a-card :bordered="false" style="border-top: 1px solid #e8e8e8">
            <a-col :xs="12" :sm="6" :md="4" :xl="3" v-for="item in plainOptions">
              <a-card-grid style="text-align: center;width: 100%;font-size: xx-large" @click="gotoBeeBoxInfo(item.boxId)">{{item.boxId}}</a-card-grid>
            </a-col>
          </a-card>
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

    <div style="margin-top: 5px">
      <!--批量操作对话框-->
      <oper-mult :checked-list="checkedList" ></oper-mult>

      <a-row>
        <a-card title="批量操作" :bordered="false" style="margin-top: 10px">
          <template>
            <div>
              <div :style="{ borderBottom: '1px solid #E9E9E9'}">
                <a-checkbox :indeterminate="indeterminate" @change="onCheckAllChange" :checked="checkAll" :style="{marginBottom: '10px' }">全选</a-checkbox>
                <a-button type="primary" :style="{marginLeft: '20px'}" @click="showModal" :disabled="checkedList.length===0">批量操作</a-button>
              </div>
              <br/>
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
      </a-row>
    </div>
  </div>
</template>
<script>
    import {getAction, postAction, putAction} from "../../../api/manage";
    import OperMult from '@/components/operMult/operMult';
    import Utils from '@/api/utils';
    import ACol from "ant-design-vue/es/grid/Col";
    import ARow from "ant-design-vue/es/grid/Row";

    export default {
        name: "BeeBoxControl",
        components: {ARow, ACol, OperMult},
        data() {
            return {
                boxes: [],
                /* 多选框 */
                checkedList: [],// 选中项
                indeterminate: true,
                checkAll: false,
                plainOptions: [],//所有项
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
                /* 添加蜂箱 */
                formLayout: 'horizontal',
                addBoxVisible: false,
                confirmLoading2: false,
                box_id: '',
                frame_num: 0,
                bee_status: '正常',
                beeStatusList: [
                    {value: '正常', name: '正常'},
                    {value: '种王', name: '种王'},
                    {value: '病王', name: '病王'},
                    {value: '处女王', name: '处女王'},
                    {value: '老王',name: '老王'},
                    {value: '产卵王',name: '产卵王'},
                    {value: '失王',name: '失王'},
                ],


            };
        },
        computed: {
            formItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        labelCol: { span: 8 },
                        wrapperCol: { span: 14 },
                    }
                    : {};
            },
            buttonItemLayout() {
                const { formLayout } = this;
                return formLayout === 'horizontal'
                    ? {
                        wrapperCol: { span: 14, offset: 4 },
                    }
                    : {};
            },
        },
        created() {
            this.getALLBeeBoxes();
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
        beforeDestroy(){
        },
        methods: {
            getALLBeeBoxes() {
                getAction('/bee/boxStatus/uid', {uid: this.$store.getters.userInfo.id}).then((res) => {
                    if (res.success) {
                        let obj = [];
                        for (let i = 0; i < res.result.length; i++) {
                            let tmp = {};
                            tmp.boxId = res.result[i].bbid;
                            this.boxes.push(tmp.boxId);
                            tmp.netStatus = res.result[i].netStatus;
                            tmp.feedStatus = res.result[i].feeding;
                            tmp.heatStatus = res.result[i].heating;
                            tmp.cleanStatus = res.result[i].cleaning;
                            tmp.checked = false;
                            obj.push(tmp);
                        }
                        this.plainOptions = obj;
                        // console.log(">>>", this.plainOptions);
                    } else {
                        this.$message.warning(res.message);
                    }
                });

            },
            gotoBeeBoxInfo(boxId) {
                this.$router.push({path: '/bee/new/BeeBoxInfo', query: {boxId:boxId,prePath:'/bee/new/BeeBoxControl',preName:'蜂箱控制'}});
            },
            /* 批量操作对话框 */
            showModal() {
              Utils.$emit('showModal');
            },
            addBoxModal() {
                this.addBoxVisible = true;
            },
            handleCancel2(e) {
                this.addBoxVisible = false;
            },
            addNewBox() {
                if (/^[A-Z0-9]{4}$/.test(this.box_id) === false) {
                    this.$message.warning('编号格式错误！仅支持4位长度的编号，且为数字0-9与字符A-Z（大写）组合');
                    return;
                }
                let params = {
                    bbid: this.box_id,
                    uid: this.$store.getters.userInfo.id,
                    kingStatus: this.bee_status,
                    frameNum: this.frame_num,
                    remark: ""
                };
                postAction('/bee/addbox', params).then((res) => {
                    if (res.success) {
                        this.$message.success(res.message);
                        this.getALLBeeBoxes();
                      this.addBoxVisible=false;
                      this.box_id='';
                    } else {
                        this.$message.warning(res.message);
                    }
                });
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
                /*Object.assign(this, {
                    checkedList: e.target.checked ? this.plainOptions : [],
                    indeterminate: false,
                    checkAll: e.target.checked,
                });*/
            },
        }
    }
</script>

<style scoped>

</style>
