<template>
  <div>
    <div >
      <a-spin :spinning="loading" tip="正在加载">
        <div  style="padding: 0px;">
        <!-- 图 -->
        <a-card title="实时温度" :bordered="false" style="margin-top: 10px">
          <div id="img1">
            <div id="chartTest" :style="{height: '400px', marginTop: '10px'}"></div>
          </div>
          <div id="img1-null" class="ant-table-placeholder" style="display: none">
            <div class="ant-empty ant-empty-normal">
              <div class="ant-empty-image">
                <img alt="暂无数据" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgIDxlbGxpcHNlIGZpbGw9IiNGNUY1RjUiIGN4PSIzMiIgY3k9IjMzIiByeD0iMzIiIHJ5PSI3Ii8+CiAgICA8ZyBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0iI0Q5RDlEOSI+CiAgICAgIDxwYXRoIGQ9Ik01NSAxMi43Nkw0NC44NTQgMS4yNThDNDQuMzY3LjQ3NCA0My42NTYgMCA0Mi45MDcgMEgyMS4wOTNjLS43NDkgMC0xLjQ2LjQ3NC0xLjk0NyAxLjI1N0w5IDEyLjc2MVYyMmg0NnYtOS4yNHoiLz4KICAgICAgPHBhdGggZD0iTTQxLjYxMyAxNS45MzFjMC0xLjYwNS45OTQtMi45MyAyLjIyNy0yLjkzMUg1NXYxOC4xMzdDNTUgMzMuMjYgNTMuNjggMzUgNTIuMDUgMzVoLTQwLjFDMTAuMzIgMzUgOSAzMy4yNTkgOSAzMS4xMzdWMTNoMTEuMTZjMS4yMzMgMCAyLjIyNyAxLjMyMyAyLjIyNyAyLjkyOHYuMDIyYzAgMS42MDUgMS4wMDUgMi45MDEgMi4yMzcgMi45MDFoMTQuNzUyYzEuMjMyIDAgMi4yMzctMS4zMDggMi4yMzctMi45MTN2LS4wMDd6IiBmaWxsPSIjRkFGQUZBIi8+CiAgICA8L2c+CiAgPC9nPgo8L3N2Zz4K"></div>
              <p class="ant-empty-description">暂无数据</p>
            </div>
          </div>
        </a-card>

        <!--批量操作对话框-->
        <oper-mult :checked-list="checkedList" ></oper-mult>

        <!-- 批量操作 -->
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
      </div>
      </a-spin>
    </div>
  </div>
</template>

<script>
    import {postAction, putAction, getAction} from "../../../api/manage";
    import OperMult from '@/components/operMult/operMult';
    import Utils from '@/api/utils';

    export default {
      name: "BeeTemperatureInfo",
      components: {OperMult},
      data() {
            return {
                loading: true,
                /* 图表数据 */
                bbids: [],
                temperData: [],
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

                loading: true,
            };
        },
        mounted() {
            this.getAllBoxesTempInfo();
            this.getOnlineBoxesInfo();
        },
        created() {

            // 获取配置
            getAction('/conf/glb', {uid: this.$store.getters.userInfo.id}).then((res) => {
                if (res.success) {
                    this.$store.state.temperatureMarkLine = res.result.hintTemper;
                    this.$store.state.weightRateMarkLine = res.result.hintWeightChange;
                    this.$store.state.weightMarkLine = res.result.hintWeightFrame;
                    this.loading = false;
                    console.log(this.$store.state.temperatureMarkLine,",",this.$store.state.weightMarkLine,",",this.$store.state.weightRateMarkLine)
                } else {
                    this.$message.warning(res.message);
                }
            });
        },
        methods: {
            gotoBeeBoxInfo(boxId) {
                this.$router.push({path: '/bee/new/BeeBoxInfo', query: {boxId:boxId,prePath:'/bee/new/BeeBoxControl',preName:'蜂箱控制'}});
            },
            getAllBoxesTempInfo() {
                getAction('/bee/boxStatus/uid', {uid: this.$store.getters.userInfo.id}).then((res) => {
                    if (res.success) {
                        let obj = [];
                        for (let i = 0; i < res.result.length; i++) {
                            let tmp = {};
                            tmp.boxId = res.result[i].bbid;
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
                });

            },
            /* 获取联网蜂箱温度信息 */
            getOnlineBoxesInfo() {

                getAction('/bee/boxInfo/uid',{uid: this.$store.getters.userInfo.id,orderBy:'temper'}).then((res) => {
                    if (res.success) {
                        console.log(res.result);
                        this.bbids = [];
                        this.temperData = [];
                        console.log('>', this.temperData);
                        for (let i = 0; i < res.result.length; i++) {
                            this.bbids.push(res.result[i].bbid);
                            this.temperData.push(res.result[i].temper);
                        }
                        if (this.temperData.length !== 0) {
                            document.getElementById('img1-null').style.display = 'none';
                            document.getElementById('img1').style.display = 'block';
                            this.drawLine();

                        } else {
                            document.getElementById('img1').style.display = 'none';
                            document.getElementById('img1-null').style.display = 'block';
                        }
                    } else {
                      document.getElementById('img1').style.display = 'none';
                      document.getElementById('img1-null').style.display = 'block';
                      this.$message.warning("服务器繁忙");
                    }
                    console.log('>', this.temperData);

                });
            },
            /* 画曲线 */
            drawLine() {
                if (this.temperData.length !== 0) {
                    let echarts = require('echarts');
                    // 基于准备好的dom，初始化echarts实例
                    let myChart = echarts.init(document.getElementById('chartTest'));
                    // 绘制图表
                    let option = {
                        tooltip: {
                            trigger: 'axis',
                            position: function (pt) {
                                return [pt[0], '10%']
                            }
                        },
                        title: {
                            left: 'left',
                            text: ''
                        },
                        toolbox: {
                            feature: {
                                restore: {},
                                brush: {
                                    type: ['lineX', 'clear']
                                }
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: this.bbids
                        },
                        yAxis: {
                            type: 'value',
                            boundaryGap: [0, '100%']
                        },
                        dataZoom: [{
                            type: 'inside',
                            start: 0,
                            end: 100
                        }, {
                            start: 0,
                            end: 10,
                            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                            handleSize: '80%',
                            handleStyle: {
                                color: '#fff',
                                shadowBlur: 3,
                                shadowColor: 'rgba(0, 0, 0, 0.6)',
                                shadowOffsetX: 2,
                                shadowOffsetY: 2
                            }
                        }],
                        series: [
                            {
                                name: '温度℃',
                                type: 'line',
                                smooth: true,
                                // symbol: 'none',
                                sampling: 'average',
                                itemStyle: {
                                    color: 'rgb(255, 70, 131)'
                                },
                                areaStyle: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 0,
                                        color: 'rgb(255, 158, 68)'
                                    }, {
                                        offset: 1,
                                        color: 'rgb(255, 70, 131)'
                                    }])
                                },
                                data: this.temperData,
                                markLine: {
                                    symbol: 'none',               //去掉警戒线最后面的箭头
                                    label: {
                                        position: 'end'          //将警示值放在哪个位置，三个值“start”,"middle","end"  开始  中点 结束
                                    },
                                    data: [{
                                        silent: false,             //鼠标悬停事件  true没有，false有
                                        lineStyle: {               //警戒线的样式  ，虚实  颜色
                                            type: 'solid',
                                            color: '#FA3934'
                                        },
                                        yAxis: this.$store.state.temperatureMarkLine   // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值
                                    }]
                                }
                            }
                        ],
                        brush: {
                            xAxisIndex: 'all',
                            brushLink: 'all',
                            outOfBrush: {
                                colorAlpha: 0.1
                            },
                            throttleDelay: 500
                        },
                    };
                    myChart.setOption(option);
                    myChart.on('brushSelected', this.selectArea);
                    myChart.dispatchAction({
                        type: 'takeGlobalCursor',
                        // 如果想变为“可刷选状态”，必须设置。不设置则会关闭“可刷选状态”。
                        key: 'brush',
                        brushOption: {
                            // 参见 brush 组件的 brushType。如果设置为 false 则关闭“可刷选状态”。
                            brushType: 'lineX',
                            // 参见 brush 组件的 brushMode。如果不设置，则取 brush 组件的 brushMode 设置。
                            //brushMode: string
                        }
                    });
                    window.addEventListener("resize", function () {
                        myChart.resize();
                    });
                }
            },
            selectArea(params) {
                let areas = params.batch[0].areas;
                if (areas.length > 0) {
                    let range = areas[0].coordRange;
                    this.checkedList = [];
                    for (let i = 0; i < this.plainOptions.length; i++) {
                        this.plainOptions[i].checked = false;
                    }
                    for (let i = range[0]; i <= range[1]; i++) {
                        if (this.bbids[i] != null) {
                            for (let j = 0; j < this.plainOptions.length; j++) {
                                if (this.plainOptions[j].boxId === this.bbids[i] && this.plainOptions[j].netStatus === 0) {
                                    this.plainOptions[j].checked = true;
                                    this.checkedList.push(this.bbids[i]);
                                }
                            }
                        }
                    }
                    this.indeterminate = !!this.checkedList.length && this.checkedList.length < this.plainOptions.length;
                    this.checkAll = this.checkedList.length === this.plainOptions.length;
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
                /*Object.assign(this, {
                    checkedList: e.target.checked ? this.plainOptions : [],
                    indeterminate: false,
                    checkAll: e.target.checked,
                });*/
            },
            /* 批量操作对话框 */
            showModal() {
              Utils.$emit('showModal');
            },
        }
    }
</script>

<style scoped>

</style>
