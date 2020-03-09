<template>
    <div>
      <div style="padding: 0px;">
        <!--<h2>实时重量变化率柱状图</h2>-->
        <a-card title="实时重量变化率" :bordered="false" style="margin-top: 10px">
          <!-- 图 -->
          <div id="img1" style="display: none">
            <div id="chartTest" :style="{height: '400px', marginTop: '10px'}"></div>
          </div>
        <div id="img1-null" class="ant-table-placeholder">
          <div class="ant-empty ant-empty-normal">
            <div class="ant-empty-image">
              <img alt="暂无数据" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgIDxlbGxpcHNlIGZpbGw9IiNGNUY1RjUiIGN4PSIzMiIgY3k9IjMzIiByeD0iMzIiIHJ5PSI3Ii8+CiAgICA8ZyBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0iI0Q5RDlEOSI+CiAgICAgIDxwYXRoIGQ9Ik01NSAxMi43Nkw0NC44NTQgMS4yNThDNDQuMzY3LjQ3NCA0My42NTYgMCA0Mi45MDcgMEgyMS4wOTNjLS43NDkgMC0xLjQ2LjQ3NC0xLjk0NyAxLjI1N0w5IDEyLjc2MVYyMmg0NnYtOS4yNHoiLz4KICAgICAgPHBhdGggZD0iTTQxLjYxMyAxNS45MzFjMC0xLjYwNS45OTQtMi45MyAyLjIyNy0yLjkzMUg1NXYxOC4xMzdDNTUgMzMuMjYgNTMuNjggMzUgNTIuMDUgMzVoLTQwLjFDMTAuMzIgMzUgOSAzMy4yNTkgOSAzMS4xMzdWMTNoMTEuMTZjMS4yMzMgMCAyLjIyNyAxLjMyMyAyLjIyNyAyLjkyOHYuMDIyYzAgMS42MDUgMS4wMDUgMi45MDEgMi4yMzcgMi45MDFoMTQuNzUyYzEuMjMyIDAgMi4yMzctMS4zMDggMi4yMzctMi45MTN2LS4wMDd6IiBmaWxsPSIjRkFGQUZBIi8+CiAgICA8L2c+CiAgPC9nPgo8L3N2Zz4K"></div>
            <p class="ant-empty-description">暂无数据</p>
          </div>
        </div>
        </a-card>
        <!--<h1>长期重量变化率折线图</h1>-->
        <a-card title="长期重量变化率" :bordered="false" style="margin-top: 10px">
          <a-row>
            <a-col :xs="24" :md="4" style="margin-top: 2px">
              蜂箱号：
              <a-select style="width: 120px" v-model="longBeeBoxId" @change="updateLongChart">
                <a-select-option v-for="(item,index) in BeeBoxIdList" :key="index" :value='item.value'>{{item.name}}</a-select-option>
              </a-select>
            </a-col>
            <a-col :xs="24" :md="9" style="margin-top: 2px">
              起止日期：
              <a-range-picker @change="updateLongChart" v-model="timeRange"/>
            </a-col>
            <a-col :xs="24" :md="4" style="margin-top: 2px">
              时间点：
              <a-select style="width: 120px" v-model="longWeightNodeTime" @change="updateLongChart">
                <a-select-option v-for="(item,index) in allWeightNodeTimeList" :key="index" :value='item.value'>{{item.name}}</a-select-option>
              </a-select>
            </a-col>
          </a-row>
          <!-- 曲线图 -->
            <div id="img2" style="display: none">
              <div id="longWeightChangeRateChart" :style="{height: '400px', marginTop: '10px'}" ></div>
            </div>
            <div class="ant-table-placeholder" id="img2-null">
              <div class="ant-empty ant-empty-normal">
                <div class="ant-empty-image">
                  <img alt="暂无数据" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CiAgPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxKSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj4KICAgIDxlbGxpcHNlIGZpbGw9IiNGNUY1RjUiIGN4PSIzMiIgY3k9IjMzIiByeD0iMzIiIHJ5PSI3Ii8+CiAgICA8ZyBmaWxsLXJ1bGU9Im5vbnplcm8iIHN0cm9rZT0iI0Q5RDlEOSI+CiAgICAgIDxwYXRoIGQ9Ik01NSAxMi43Nkw0NC44NTQgMS4yNThDNDQuMzY3LjQ3NCA0My42NTYgMCA0Mi45MDcgMEgyMS4wOTNjLS43NDkgMC0xLjQ2LjQ3NC0xLjk0NyAxLjI1N0w5IDEyLjc2MVYyMmg0NnYtOS4yNHoiLz4KICAgICAgPHBhdGggZD0iTTQxLjYxMyAxNS45MzFjMC0xLjYwNS45OTQtMi45MyAyLjIyNy0yLjkzMUg1NXYxOC4xMzdDNTUgMzMuMjYgNTMuNjggMzUgNTIuMDUgMzVoLTQwLjFDMTAuMzIgMzUgOSAzMy4yNTkgOSAzMS4xMzdWMTNoMTEuMTZjMS4yMzMgMCAyLjIyNyAxLjMyMyAyLjIyNyAyLjkyOHYuMDIyYzAgMS42MDUgMS4wMDUgMi45MDEgMi4yMzcgMi45MDFoMTQuNzUyYzEuMjMyIDAgMi4yMzctMS4zMDggMi4yMzctMi45MTN2LS4wMDd6IiBmaWxsPSIjRkFGQUZBIi8+CiAgICA8L2c+CiAgPC9nPgo8L3N2Zz4K"></div>
                <p class="ant-empty-description">暂无数据</p>
              </div>
            </div>
        </a-card>
      </div>
    </div>
</template>

<script>
    import {getAction} from "../../../api/manage";
    import moment from 'moment';

    export default {
        name: "BeeWeightRate",
        data() {
            return {
                xAxisData: [],
                data1: [],
                beginDate: '',
                endDate: '',
                longWeightNodeTime: "6:00:00",
                allWeightNodeTimeList: [
                    {value: '6:00:00', name: '6:00:00'},
                    {value: '12:00:00', name: '12:00:00'},
                    {value: '18:00:00', name: '18:00:00'},
                ],
                BeeBoxIdList: [],
                longBeeBoxId: "",
                xAxisData2: [],
                data2: [],
                timeRange: [moment().subtract(60,'day'),moment()],
            }
        },
        mounted() {
          // 获取配置
          getAction('/conf/glb', {uid: this.$store.getters.userInfo.id}).then((res) => {
            if (res.success) {
              this.$store.state.temperatureMarkLine = res.result.hintTemper;
              this.$store.state.weightRateMarkLine = res.result.hintWeightChange;
              this.$store.state.weightMarkLine = res.result.hintWeightFrame;
              console.log(this.$store.state.temperatureMarkLine,",",this.$store.state.weightMarkLine,",",this.$store.state.weightRateMarkLine)
            } else {
              this.$message.warning(res.message);
            }
          });
          this.getRealTimeData();
          this.getBoxesId();
        },
        created() {

        },
        methods:{
            /* 获取蜂箱ID */
            getBoxesId() {
                getAction("/bee/bbids/uid",{uid:this.$store.getters.userInfo.id}).then((res) => {
                    if (res.success){
                        this.BeeBoxIdList = [];
                        for (let i = 0; i < res.result.length; i++) {
                            let obj = {};
                            obj.value = res.result[i];
                            obj.name =  res.result[i];
                            this.BeeBoxIdList.push(obj);
                        }
                        if (this.BeeBoxIdList.length > 0) {
                            this.longBeeBoxId = this.BeeBoxIdList[0].value;
                            this.updateLongChart();
                        }
                    } else{
                        this.$message.warning(res.message);
                    }
                });
            },
            updateLongChart() {
                let params = {
                    bbid: this.longBeeBoxId,
                    startDate: this.timeRange[0].format("YYYY-MM-DD"),
                    endDate: this.timeRange[1].format("YYYY-MM-DD"),
                    time: this.longWeightNodeTime,
                };
                console.log(">>", params);
                getAction("/bee/history/wcr/bbid",params).then((res) => {
                    console.log(">", res.result);
                    if (res.success){
                        this.xAxisData2 = [];
                        this.data2 = [];
                        for (let i = 0; i < res.result.length; i++) {
                            this.data2.push(res.result[i].weightChange);
                            this.xAxisData2.push(res.result[i].recvTime);
                        }
                        console.log(this.xAxisData2);
                        console.log(this.data2);
                        if (this.data2.length !== 0) {
                            document.getElementById('img2-null').style.display = 'none';
                            document.getElementById('img2').style.display = 'block';
                            this.drawLongLine();
                        } else {
                            document.getElementById('img2').style.display = 'none';
                            document.getElementById('img2-null').style.display = 'block';
                        }
                    } else{
                        document.getElementById('img2').style.display = 'none';
                        document.getElementById('img2-null').style.display = 'block';
                        this.$message.warning("服务器繁忙");
                    }
                });
            },
            getRealTimeData() {
                this.xAxisData = [];
                this.data1 = [];
                getAction('/bee/wcr/uid',{uid: this.$store.getters.userInfo.id}).then((res) => {
                    console.log(res);
                    if (res.success) {
                        if (res.result != null) {
                            for (let i = 0; i < res.result.length; i++) {
                                this.xAxisData.push(res.result[i].bbid);
                                this.data1.push(res.result[i].weightChange);
                            }
                            console.log(this.xAxisData);
                            console.log(this.data1);
                            if (this.data1.length !== 0) {
                                document.getElementById('img1-null').style.display = 'none';
                                document.getElementById('img1').style.display = 'block';
                                this.drawLine();
                            } else {
                              document.getElementById('img1').style.display = 'none';
                              document.getElementById('img1-null').style.display = 'block';
                            }
                        }else{
                          document.getElementById('img1').style.display = 'none';
                          document.getElementById('img1-null').style.display = 'block';
                        }
                    } else {
                        document.getElementById('img1').style.display = 'none';
                        document.getElementById('img1-null').style.display = 'block';
                        this.$message.warning("服务器繁忙");
                    }
                });

            },
            drawLine() {
                if (this.data1.length !== 0) {
                    let echarts = require('echarts');
                    // 基于准备好的dom，初始化echarts实例
                    let myChart = echarts.init(document.getElementById('chartTest'));
                    // 绘制图表
                    let option = {
                        title: {
                            left: 'left',
                            text: ''
                        },
                        legend: {
                            data: ['bar'],
                            align: 'left'
                        },
                        // toolbox: {
                        //     // y: 'bottom',
                        //     feature: {
                        //         saveAsImage: {
                        //             pixelRatio: 2
                        //         }
                        //     }
                        // },
                        tooltip: {},
                        xAxis: {
                            data: this.xAxisData,
                            silent: false,
                            splitLine: {
                                show: false
                            }
                        },
                        yAxis: {
                        },
                        series: [{
                            name: '',
                            type: 'bar',
                            data: this.data1,
                            animationDelay: function (idx) {
                                return idx * 10;
                            },
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
                                    yAxis: this.$store.state.weightRateMarkLine   // 警戒线的标注值，可以有多个yAxis,多条警示线   或者采用   {type : 'average', name: '平均值'}，type值有  max  min  average，分为最大，最小，平均值
                                }]
                            }
                        }],
                        animationEasing: 'elasticOut',
                        animationDelayUpdate: function (idx) {
                            return idx * 5;
                        }
                    };
                    myChart.setOption(option);
                    window.addEventListener("resize", function () {
                        myChart.resize();
                    });
                }

            },
            /* 长期折线图 */
            drawLongLine(){
                if (this.data2.length !== 0) {
                    let echarts = require('echarts');
                    // 基于准备好的dom，初始化echarts实例
                    let myChart = echarts.init(document.getElementById('longWeightChangeRateChart'));
                    // 绘制图表
                    myChart.setOption({
                      title: {
                        left: 'left',
                        text: ''
                      },
                      tooltip: {
                        trigger: 'axis'
                      },
                      xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: this.xAxisData2
                      },
                      yAxis: {
                        splitLine: {
                          show: false
                        }
                      },

                      dataZoom: [{
                        start: 0,
                        end: 100
                      }, {
                        type: 'inside'
                      }],
                      visualMap: {
                        // orient:"horizontal",
                        show:false,
                        top: 10,
                        right: 10,
                        precision:3,
                        pieces: [{
                          gt: 0,
                          lte: 0.003,
                          color: '#096'
                        }, {
                          gt: 0.003,
                          lte: 0.005,
                          color: '#ffde33'
                        }, {
                          gt: 0.005,
                          lte: 0.01,
                          color: '#ff9933'
                        }, {
                          gt: 0.01,
                          lte: 0.015,
                          color: '#cc0033'
                        }, {
                          gt: 0.015,
                          lte: 0.02,
                          color: '#660099'
                        }, {
                          gt: 0.02,
                          color: '#7e0023'
                        },{
                          gt: 0,
                          lte: -0.003,
                          color: '#096'
                        }, {
                          gt: -0.003,
                          lte: -0.005,
                          color: '#ffde33'
                        }, {
                          gt: -0.005,
                          lte: -0.01,
                          color: '#ff9933'
                        }, {
                          gt: -0.01,
                          lte: -0.015,
                          color: '#cc0033'
                        }, {
                          gt: -0.015,
                          lte: -0.02,
                          color: '#660099'
                        }, {
                          lt: -0.02,
                          color: '#7e0023'
                        }],
                        outOfRange: {
                          color: '#999'
                        }
                      },
                      series: {
                        name: '重量变化率',
                        type: 'line',
                        data: this.data2,
                        // data: data.map(function (item) {
                        //   return item[1];
                        // }),
                        markLine: {
                          symbol: 'none',
                          silent: true,
                          data: [{
                            yAxis: '0.003'
                          }, {
                            yAxis: '0.005'
                          }, {
                            yAxis: '0.010'
                          }, {
                            yAxis: '0.015'
                          }, {
                            yAxis: '0.02'
                          },{
                            yAxis: '-0.003'
                          }, {
                            yAxis: '-0.005'
                          }, {
                            yAxis: '-0.010'
                          }, {
                            yAxis: '-0.015'
                          }, {
                            yAxis: '-0.02'
                          }]
                        }
                      },
                      /*------------------------------old*/
                      /*
                        tooltip: {
                            trigger: 'axis',
                            position: function (pt) {
                                return [pt[0], '10%'];
                            }
                        },
                        title: {
                            left: 'left',
                            text: '长期重量变化率折线图'
                        },
                        toolbox: {
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: this.xAxisData2
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
                                name:'重量变化率',
                                type:'line',
                                //smooth:true,
                                symbol: 'none',
                                sampling: 'average',
                                itemStyle: {
                                    color: 'rgb(255, 70, 131)'
                                },
                                areaStyle: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 0,
                                        color: '#fff',
                                        //color: 'rgb(255, 158, 68)'
                                    }, {
                                        offset: 1,
                                        color: '#fff',
                                        //color: 'rgb(255, 70, 131)'
                                    }])
                                },
                                data: this.data2
                            }
                        ]
                        */
                    });
                    window.addEventListener("resize", function () {
                        myChart.resize();
                    });
                }
            },
        }
    }
</script>

<style scoped>

</style>