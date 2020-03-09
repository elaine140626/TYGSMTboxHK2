<template>
  <a-modal
    title="批量操作"
    :visible="visible"
    @ok="handleOk"
    :confirmLoading="confirmLoading"
    @cancel="handleCancel"
    cancelText="关闭"
  >
    <template slot="footer">
      <a-button @click.stop="handleCancel">关闭</a-button>
    </template>
    <div>
      <!-- 显示所有选中蜂箱 -->
      <a-row>
        <h2>本次操作蜂箱</h2>
          <a-tag color="blue" v-for="beeBox in checkedList" keys="1" style="margin-top: 2px;font-size: x-large">{{beeBox}}</a-tag>
        <hr/>
      </a-row>
      <!--加热操作-->
      <a-row>
        <h3>加热操作</h3>
        <a-row type="flex" justify="space-around" >
          <a-col :xs="24" :sm="24">
            开/关自动加热：
            <a-button type="primary" @click="batchAutoHeat(1)" >开
              <a-spin style="margin-left: 10px" :spinning="autoHeatOnLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
            <a-button style="margin-left: 5px" type="primary" @click="batchAutoHeat(0)">关
              <a-spin style="margin-left: 10px" :spinning="autoHeatOffLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
          </a-col>
        </a-row>
        <hr/>
      </a-row>

      <!-- 喂食操作 -->
      <a-row>
        <h3>喂食操作</h3>
        <a-row type="flex" justify="space-around" align="middle">
          <a-col :xs="24" :sm="12" style="margin-top: 5px">
            <a-button type="primary" @click="batchFeed(0)">补偿喂食
              <a-spin style="margin-left: 10px" :spinning="buChangFeedLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
            <a-button style="margin-left: 5px" type="primary" @click="batchFeed(1)">奖励喂食
              <a-spin style="margin-left: 10px" :spinning="jianLiFeedLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
          </a-col>
          <a-col :xs="24" :sm="12" style="margin-top: 5px">
            开/关自动喂食：
            <a-button type="primary" @click="batchAutoFeed(1)">开
              <a-spin style="margin-left: 10px" :spinning="autoFeedOnLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
            <a-button style="margin-left: 5px" type="primary" @click="batchAutoFeed(0)">关
              <a-spin style="margin-left: 10px" :spinning="autoFeedOffLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
          </a-col>
        </a-row>
        <hr/>
      </a-row>
      <!-- 清洗 -->
      <a-row>
        <h3>清洗操作</h3>
        <a-row type="flex">
          <a-col>
            <a-button type="primary" @click="batchClean">清洗
              <a-spin style="margin-left: 10px" :spinning="cleanLoading">
                <a-icon slot="indicator" type="loading" style="color: white" spin />
              </a-spin>
            </a-button>
          </a-col>
        </a-row>
        <hr/>
      </a-row>
      <!-- 巢门控制 -->
      <a-row>
        <h3>巢门控制</h3>
        <a-row type="flex" justify="space-around" align="middle">
          <a-col :xs="24" :sm="12" style="margin-top: 5px">
            <a-col :span="10">
              <a-button type="primary" @click="batchDoor(0)">清洗
                <a-spin style="margin-left: 10px" :spinning="doorCleanLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-col>
            <a-col :span="10">
              <a-button type="primary" @click="batchDoor(1)">控王
                <a-spin style="margin-left: 10px" :spinning="doorKongWangLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-col>
          </a-col>
          <a-col :xs="24" :sm="12" style="margin-top: 5px">
            <a-col :span="10">
              <a-button type="primary" @click="batchDoor(2)">正常
                <a-spin style="margin-left: 10px" :spinning="doorZhengChangLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-col>
            <a-col :span="10">
              <a-button type="primary" @click="batchDoor(3)">通风
                <a-spin style="margin-left: 10px" :spinning="doorTongFengLoading">
                  <a-icon slot="indicator" type="loading" style="color: white" spin />
                </a-spin>
              </a-button>
            </a-col>
          </a-col>
        </a-row>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
  import {getAction, postAction, putAction} from "@/api/manage";
  import Utils from '@/api/utils';

  export default {
      name: "operMult",
      props:{
        checkedList:{
          type:Array,
          required:true
        },
        // visible:{
        //   type:Boolean,
        //   default:false
        // }
      },
      data() {
        return {
          visible:false,
          /* 批量操作加载 */
          confirmLoading:false,
          autoHeatOnLoading: false,
          autoHeatOffLoading: false,
          autoFeedOnLoading:false,
          autoFeedOffLoading:false,

          buChangFeedLoading: false,//补偿喂食
          jianLiFeedLoading: false,//奖励喂食
          cleanLoading:false,
          doorCleanLoading:false, // 巢门
          doorKongWangLoading:false,//控王
          doorZhengChangLoading:false,//正常
          doorTongFengLoading:false//通风
        }
      },
      mounted(){
        var that = this;
        Utils.$on('showModal', function () {
          that.visible = true;
        });
      },
      methods:{
        handleOk(e) {
          this.visible = false;
        },
        handleCancel(e) {
          this.visible = false;
        },
        /* 清洗 */
        batchClean() {
          if (this.cleanLoading) {
            this.$message.warning('清洗操作请求正在处理中');
            return;
          }
          this.cleanLoading = true;
          let params = {};
          params.uid = this.$store.getters.userInfo.id;
          params.bbids = this.checkedList;
          postAction('/oper/clean/mult', params).then((res) => {
            if (res.success) {
              let flag = true;
              for (let i = 0; i < res.result.length; i++) {
                if (res.result[i].errorMessage != null) {
                  this.$message.error(res.result[i].errorMessage);
                  flag = false;
                }
              }
              if (flag) {
                this.$message.success("操作成功");
              }
              this.cleanLoading = false;

            } else {
              this.$message.warning(res.message);
              this.cleanLoading = false;
            }
          });
        },
        /* 加热 */
        batchAutoHeat(mode) {
          if (this.autoHeatOnLoading || this.autoHeatOffLoading) {
            this.$message.warning("自动加热开/关请求正在处理中");
            return;
          }
          if (mode === 1) {
            this.autoHeatOnLoading = true;
          }else if (mode === 0) {
            this.autoHeatOffLoading = true;
          }
          let params = {
            userBeeboxes: []
          };
          for (let i = 0; i < this.checkedList.length; i++) {
            let obj = {
              bbid: this.checkedList[i],
              autoHeat: mode
            };
            params.userBeeboxes.push(obj);
          }

          putAction('/bee/boxStatus/mult', params).then((res) => {
            if (res.success) {
              let flag = true;
              for (let i = 0; i < res.result.length; i++) {
                if (res.result[i].errorMessage != null) {
                  this.$message.error(res.result[i].errorMessage);
                  flag = false;
                }
              }
              if (flag) {
                this.$message.success("操作成功");
              }
              if (mode === 1) {
                this.autoHeatOnLoading = false;
              }else if (mode === 0) {
                this.autoHeatOffLoading = false;
              }
            } else {
              this.$message.warning(res.message);
              if (mode === 1) {
                this.autoHeatOnLoading = false;
              }else if (mode === 0) {
                this.autoHeatOffLoading = false;
              }
            }
          });
        },
        /* 喂食 */
        batchFeed(mode) {
          if (this.buChangFeedLoading || this.jianLiFeedLoading) {
            this.$message.warning('喂食操作请求正在处理中');
            return;
          }
          if (mode === 1) {
            this.jianLiFeedLoading = true;
          }else if (mode === 0) {
            this.buChangFeedLoading = true;
          }
          let params = {};
          params.uid = this.$store.getters.userInfo.id;
          params.bbids = this.checkedList;
          params.feedOrder = mode;
          postAction('/oper/feed/mult', params).then((res) => {
            if (res.success) {
              let flag =  true;
              for (let i = 0; i < res.result.length; i++) {
                if (res.result[i].errorMessage != null) {
                  this.$message.error(res.result[i].errorMessage);
                  flag = false;
                }
              }
              if (flag) {
                this.$message.success("操作成功");
              }
              if (mode === 1) {
                this.jianLiFeedLoading = false;
              }else if (mode === 0) {
                this.buChangFeedLoading = false;
              }
            } else {
              this.$message.warning(res.message);
              if (mode === 1) {
                this.jianLiFeedLoading = false;
              }else if (mode === 0) {
                this.buChangFeedLoading = false;
              }
            }
          });
        },
        batchAutoFeed(mode) {
          if (this.autoFeedOnLoading || this.autoFeedOffLoading) {
            this.$message.warning("自动喂食开/关请求正在处理中");
            return;
          }
          if (mode === 1) {
            this.autoFeedOnLoading = true;
          }else if (mode === 0) {
            this.autoFeedOffLoading = true;
          }
          let params = {
            userBeeboxes: []
          };
          for (let i = 0; i < this.checkedList.length; i++) {
            let obj = {
              bbid: this.checkedList[i],
              autoFeed: mode
            };
            params.userBeeboxes.push(obj);
          }
          putAction('/bee/boxStatus/mult', params).then((res) => {
            if (res.success) {
              let flag = true;
              for (let i = 0; i < res.result.length; i++) {
                if (res.result[i].errorMessage != null) {
                  this.$message.error(res.result[i].errorMessage);
                  flag = false;
                }
              }
              if (flag) {
                this.$message.success("操作成功");
              }
              if (mode === 1) {
                this.autoFeedOnLoading = false;
              }else if (mode === 0) {
                this.autoFeedOffLoading = false;
              }
            } else {
              this.$message.warning(res.message);
              if (mode === 1) {
                this.autoFeedOnLoading = false;
              }else if (mode === 0) {
                this.autoFeedOffLoading = false;
              }
            }
          });
        },
        /* 巢门控制 */
        batchDoor(mode) {
          if (this.doorCleanLoading || this.doorKongWangLoading || this.doorZhengChangLoading || this.doorTongFengLoading) {
            this.$message.warning('巢门控制请求正在处理中');
            return;
          }
          if (mode === 0) {
            this.doorCleanLoading = true;
          } else if (mode === 1) {
            this.doorKongWangLoading = true;
          } else if (mode === 2) {
            this.doorZhengChangLoading = true;
          } else if (mode === 3) {
            this.doorTongFengLoading = true;
          }
          let params = {};
          params.uid = this.$store.getters.userInfo.id;
          params.bbids = this.checkedList;
          params.doorStatus = mode;
          postAction('/oper/door/mult', params).then((res) => {
            if (res.success) {
              let flag = true;
              for (let i = 0; i < res.result.length; i++) {
                if (res.result[i].errorMessage != null) {
                  this.$message.error(res.result[i].errorMessage);
                  flag = false;
                }
              }
              if (flag) {
                this.$message.success("操作成功");
              }
              if (mode === 0) {
                this.doorCleanLoading = false;
              } else if (mode === 1) {
                this.doorKongWangLoading = false;
              } else if (mode === 2) {
                this.doorZhengChangLoading = false;
              } else if (mode === 3) {
                this.doorTongFengLoading = false;
              }
            } else {
              this.$message.warning(res.message);
              if (mode === 0) {
                this.doorCleanLoading = false;
              } else if (mode === 1) {
                this.doorKongWangLoading = false;
              } else if (mode === 2) {
                this.doorZhengChangLoading = false;
              } else if (mode === 3) {
                this.doorTongFengLoading = false;
              }
            }
          });
        }
      }
    }

</script>



