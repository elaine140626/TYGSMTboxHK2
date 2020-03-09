
<template>
  <a-tabs type="card" :activeKey="activeTabsKey" @change="clickTabs">

    <a-tab-pane tab="设备列表" key="1">
      <a-card>
        <a-row>
          <a-button style="margin-bottom: 8px;" icon="plus" type="primary" @click="addDeviceModal()">添加设备</a-button>
          <a-modal
            title="添加设备"
            :visible="showAddDevModal"
            :footer="null"
            :destroyOnClose="destroyOnClose"
            @cancel="()=>{this.showAddDevModal=false;}"
          >
            <a-form layout="vertical" :form="form" @submit="handleSubmit">
              <a-form-item label="序列号" :validate-status="deviceSerialError() ? 'error' : ''" :help="deviceSerialError() || ''">
                <a-input
                  v-decorator="[
                    'deviceSerial',
                    { rules: [{ required: true, message: '请输入位于摄像头底座的[序列号]' }] },
                  ]"
                  placeholder="设备序列号"
                >
                  <a-icon slot="prefix" type="tag" style="color:rgba(0,0,0,.25)" />
                </a-input>
              </a-form-item>
              <a-form-item label="验证码" :validate-status="validateCodeError() ? 'error' : ''" :help="validateCodeError() || ''">
                <a-input
                  v-decorator="[
                    'validateCode',
                    { rules: [{ required: true, message: '请输入位于摄像头底座的[验证码]' }] },
                  ]"
                  type="password"
                  placeholder="设备验证码"
                >
                  <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
                </a-input>
              </a-form-item>
              <a-form-item :style="{ textAlign: 'right' }">
                <a-button type="primary" html-type="submit" :disabled="hasErrors(form.getFieldsError())">
                  添加
                </a-button>
              </a-form-item>
            </a-form>
          </a-modal>
        </a-row>
        <a-row>
          <a-table bordered :dataSource="dataSource" :columns="columns" :rowSelection="rowSelection">
            <template slot="deviceName" slot-scope="text, record">
              <editable-cell :text="text" @change="onDeviceNameChange(record.key, 'deviceName', $event)" />
            </template>
            <span slot="status" slot-scope="status">
              <a-tag
                v-if="status == 0"
                :color="'red'"
              >不在线
              </a-tag>
              <a-tag
                v-if="status == 1"
                :color="'green'"
              >在线
              </a-tag>
            </span>
            <template slot="operation" slot-scope="text, record">
              <a @click="onPlay(record.deviceSerial)">直播</a>
              <a-divider type="vertical" />
              <a @click="playBack(record.deviceSerial)">回放</a>
              <a-divider type="vertical" />
              <a-popconfirm
                v-if="dataSource.length"
                title="确认删除该设备?"
                @confirm="() => onDevDelete(record.key)"
              >
                <a href="javascript:;">删除</a>
              </a-popconfirm>
            </template>
          </a-table>
        </a-row>
      </a-card>
    </a-tab-pane>
    <a-tab-pane tab="直播模式" key="2">
      <div style="margin-top: 5px">
        <a-card title="视频监控" :bordered="false" style="margin-top: 10px"  id="parents" >
          <!--直播-->
          <div id="myPlayer" style="margin: 0 auto"></div>
<!--          <div style="margin: 0 auto;width: 100%;">-->
<!--           -->
<!--          </div>-->
<!--          <div style="text-align:center;width: 800px;height: 600px;">-->
<!--            <video ref="videoPlay" id="myPlayer" style="width: 100%;height:100%;object-fit: fill" :src="mvUrl"  controls playsInline webkit-playsinline autoplay></video>-->
<!--          </div>-->
        </a-card>
        <a-card title="云台控制">
          <a-button @mousedown="moveCtrl(0,'start')" @mouseup="moveCtrl(0,'stop')">上</a-button>
          <a-button @mousedown="moveCtrl(1,'start')" @mouseup="moveCtrl(1,'stop')">下</a-button>
          <a-button @mousedown="moveCtrl(2,'start')" @mouseup="moveCtrl(2,'stop')">左</a-button>
          <a-button @mousedown="moveCtrl(3,'start')" @mouseup="moveCtrl(3,'stop')">右</a-button>
        </a-card>
        <a-card title="今日录像">
          <span>选择日期:&nbsp;&nbsp;</span><a-date-picker :defaultValue="moment(getCurrentData(), 'YYYY-MM-DD')" @change="onDateChange" />
          <hr>
          <a-list class="demo-loadmore-list" :loading="recLoading" itemLayout="horizontal" :dataSource="recData">
            <!--            <div-->
            <!--              v-if="showRecLoadingMore"-->
            <!--              slot="loadMore"-->
            <!--              :style="{ textAlign: 'center', marginTop: '12px', height: '32px', lineHeight: '32px' }"-->
            <!--            >-->
            <!--              <a-spin v-if="recLoadingMore" />-->
            <!--              <a-button v-else @click="onLoadMore">加载更多</a-button>-->
            <!--            </div>-->
            <a-list-item slot="renderItem" slot-scope="item, index">
              <a slot="actions" @click="onRecPlay(index)">播放</a>
              <a-divider type="vertical" />
              <a slot="actions" @click="onRecDownload(index)">下载</a>
              <!--              <a slot="actions">more</a>-->
              <a-list-item-meta
                description=""
              >
                <a slot="title">{{moment(item.startTime).format('YYYY年M月DD日 HH:mm:ss')+'-'+moment(item.endTime).format('HH:mm:ss')}}</a>
                <a-avatar
                  shape="square"
                  slot="avatar"
                  src="/img/rec.png"
                />
              </a-list-item-meta>

              <div>{{item.crypt == 0 ? '' : '设备已加密'}}</div>

            </a-list-item>
          </a-list>
        </a-card>
      </div>
    </a-tab-pane>
    <!--<a-tab-pane tab="录像回放" key="3">
      <div style="margin-top: 5px">
        <a-card v-show="showCard" title="视频监控" :bordered="false" style=";margin-top: 10px">
          &lt;!&ndash;录播&ndash;&gt;
          <div id="myRecPlayer" style="width: 100%;height:100%;object-fit: fill"></div>
        </a-card>

      </div>
    </a-tab-pane>-->

  </a-tabs>



</template>

<script>
  import moment from 'moment';
  import {postActionYs} from "@/api/manage";
  import EditableCell from "@/components/table/EditableCell";
  import ACol from "ant-design-vue/es/grid/Col";

  var player;

  // const rowSelection = {
  //   onChange: (selectedRowKeys, selectedRows) => {
  //     console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  //   },
  //   onSelect: (record, selected, selectedRows) => {
  //     console.log(record, selected, selectedRows);
  //   },
  //   onSelectAll: (selected, selectedRows, changeRows) => {
  //     console.log(selected, selectedRows, changeRows);
  //   },
  // };

  function hasErrors(fieldsError) {
    return Object.keys(fieldsError).some(field => fieldsError[field]);
  }

  export default {

      name: "BoxMonitor",
      components:{
        ACol,
        EditableCell,
      },
      data(){
        return{
          /*-表单-*/
          destroyOnClose:true,
          hasErrors,
          form: this.$form.createForm(this, { name: 'horizontal_login' }),

          // 显示添加设备modal
          showAddDevModal: false,
          // 显示监控card
          showCard:false,
          // 当前直播序列号
          selectedDevSerial:'',
          isEncrypt:0,//是否加密
          selectedNum:0,
          mvUrl: '',
          rowSelection:{
            onChange: (selectedRowKeys, selectedRows) => {
              console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
            },
            onSelect: (record, selected, selectedRows) => {
              console.log(record, selected, selectedRows);
              this.selectedDevSerial = record.deviceSerial;
              this.selectedNum = selectedRows.length;
            },
            onSelectAll: (selected, selectedRows, changeRows) => {
              console.log(selected, selectedRows, changeRows);
            },
          },
          activeTabsKey:'1',
          dataSource:[],
          columns: [
            {
              title: '设备序列号',
              dataIndex: 'deviceSerial',
              // width: '10%',
              align: 'center'
            },
            {
              title: '设备名称',
              dataIndex: 'deviceName',
              // width: '10%',
              align: 'center',
              scopedSlots: { customRender: 'deviceName' },
            },
            {
              title: '设备类型',
              dataIndex: 'deviceType',
              // width: '10%',
              align: 'center'
            },
            {
              title: '在线状态',
              dataIndex: 'status',
              // width: '10%',
              align: 'center',
              scopedSlots: { customRender: 'status' },
            },
            {
              title: '操作',
              dataIndex: 'operation',
              // width: '10%',
              align: 'center',
              scopedSlots: { customRender: 'operation' },
            },
          ],
          // 录像
          recLoading: true,
          recLoadingMore: false,
          showRecLoadingMore: true,
          recData: [],
        }
      },
      mounted(){
        // setTimeout(()=>{
        //   this.initEzui();
        // },500);
        this.loadDevList('at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8');

        this.$nextTick(() => {
          // To disabled submit button at the beginning.
          this.form.validateFields();
        });
      },
      beforeDestroy(){
        if (player != null){
          player.stop();
        }
      },
      methods:{
        moment,
        getCurrentData(){
          return new Date().toLocaleDateString();
        },
        showConfirm() {
          this.$confirm({
            title: '提示',
            content: h => <div style="color:red;">您未选择要播放的设备</div>,
            onOk() {
              console.log('确认');
            },
            onCancel() {
              console.log('Cancel');
            },
            // class: 'test',
          });
        },
        // 获取设备列表
        loadDevList(accessToken){
          let params = {
            accessToken:accessToken
          };
          postActionYs("https://open.ys7.com/api/lapp/device/list",params).then((res)=>{
            this.dataSource=[];
            if (res.code == 200){
              let data = res.data;
              if (data != null && data.length > 0 ){
                for (let i=0;i<data.length;i++){
                  let tmp = {};
                  tmp.key = i;
                  tmp.deviceSerial = data[i].deviceSerial;
                  tmp.deviceName = data[i].deviceName;
                  tmp.deviceType = data[i].deviceType;
                  tmp.status = data[i].status;//0不在线  1在线
                  this.dataSource.push(tmp);
                }
              }

            }
          });
        },
        initEzui(id,url,accessToken,w,h){
          // 直播模式
          // player = new EZUIPlayer('myPlayer');
          // player.play();
          // 监控模式
          player = new EZUIPlayer({
            id: id,
            url: url,//'ezopen://open.ys7.com/D86638112/1.rec?begin=20200227185920&end=20200227185934',
            autoplay: true,
            accessToken: accessToken,//'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
            decoderPath: '/js/EZUIKit-JavaScript',
            width: document.getElementById('parents').clientWidth * 0.9,
            height: document.getElementById('parents').clientWidth * 0.9 / 16 * 9,
          });
        },
        moveCtrl(dir,flag){
          if (flag == 'start'){
            // 开始
            let params={
              'accessToken':'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
              'deviceSerial':"D86638112",
              'channelNo':1,
              'direction':dir,
              'speed':1
            };
            postActionYs('https://open.ys7.com/api/lapp/device/ptz/start',params).then((res)=>{
              if (res.code == 200){
              } else {
                this.$message.warning(res.msg);
              }
            });
          }else if (flag == 'stop'){
            // 停止
            let params2={
              'accessToken':'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
              'deviceSerial':"D86638112",
              'channelNo':1,
            };
            postActionYs('https://open.ys7.com/api/lapp/device/ptz/stop',params2).then((res)=>{
              if (res.code == 200){
              } else {
                this.$message.warning(res.msg);
              }
            });
          }
        },
        clickTabs(activeKey) {
          if (activeKey == 1){
            this.activeTabsKey = activeKey;
          }else if (activeKey == 2){
            if (player != null){
              player.stop();
            }
            if (!this.selectedDevSerial || this.selectedDevSerial == '') {
              this.showConfirm();
              return;
            }
            this.activeTabsKey = activeKey;
            this.onPlay(this.selectedDevSerial);
          }
         /* else if (activeKey == 3){
            if (player != null){
              player.stop();
            }
            // TODO: 显示当天录像记录
            if (!this.selectedDevSerial || this.selectedDevSerial == '') {
              this.showConfirm();
              return;
            }
            this.activeTabsKey = activeKey;

          }*/
        },
        addDeviceModal(){
          this.showAddDevModal = true;
        },
        addDevice(){
          // TODO:添加设备

        },
        onDeviceNameChange(key, dataIndex, value){
          let dataSource = this.dataSource;
          let target = dataSource.find(item => item.key === key);
          if (target) {
            target[dataIndex] = value;
            this.dataSource = dataSource;
          }
        },
        // 直播
        onPlay(deviceSerial){
          // TODO:跳转至直播
          this.selectedDevSerial = deviceSerial;
          this.activeTabsKey = '2';

          this.playBack(this.selectedDevSerial);

          let src = "ezopen://WYSICD@open.ys7.com/"+deviceSerial+"/1.live";
          this.$nextTick(() => {
            this.initEzui('myPlayer',
              src,
              'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
            );
          });
          /*
          // 获取accessToken（目前先用开发者的测试）
          // 通道号默认1
          let params={
            'accessToken':'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
            'source':deviceSerial+':1'
          };
          postActionYs("https://open.ys7.com/api/lapp/live/address/get",params).then((res)=>{
            console.log(res);
            if (res.code == 200){
              if (!!res.data){
                let rtmpUrl =  res.data[0].rtmp;
                this.mvUrl = rtmpUrl;
                this.$refs.videoPlay.src = rtmpUrl;
                // 直播模式
                player = new EZUIPlayer('myPlayer');

                if (this.selectedDevSerial != '') {
                  this.onDateChange(this.getCurrentData(),'');
                }
              }
              console.log(this.mvUrl);
            } else{
              this.$message.warning("请刷新重试!");
            }
          });*/
        },
        playBack(deviceSerial){
          // TODO :录像回放
          // 跳转至录像回放tag
          // this.selectedDevSerial = deviceSerial;
          // this.activeTabsKey = '3';
          // 显示设备序列号、名称
          console.log(moment(this.getCurrentData(), 'YYYY-MM-DD').valueOf());
          if (this.selectedDevSerial != '') {
            this.getRecData("at.4230875y9j9qvylcc14xl0v5a7h1yevi-2o9nqe0r7f-0kl8vu6-rjcwihbfs",this.selectedDevSerial,moment(this.getCurrentData(), 'YYYY-MM-DD').valueOf())
          }
        },
        onDateChange(date, dateString){
          let sdateLong = moment(dateString, 'YYYY-MM-DD').valueOf();
          let edateLong = moment(dateString, 'YYYY-MM-DD').add(1,'days').add(-1,'seconds').valueOf();
          // TODO 获取当天录像
          this.getRecData("at.4230875y9j9qvylcc14xl0v5a7h1yevi-2o9nqe0r7f-0kl8vu6-rjcwihbfs",this.selectedDevSerial,sdateLong,edateLong)
        },
        // 获取录像列表
        getRecData(accessToken,deviceSerial,startTime,endTime) {
          console.log(startTime)
          console.log(endTime)
          if (!startTime){
            return;
          }
          this.recData=[];
          let params = {
            accessToken:accessToken,
            deviceSerial:deviceSerial,
            startTime:startTime,
            endTime:endTime
          };
          this.recLoading = true;
          // 获取加密状态
          let params2={
            accessToken:accessToken,
            deviceSerial:deviceSerial
          };
          postActionYs("https://open.ys7.com/api/lapp/device/info",params2).then((res)=>{
            if (res.code == 200){
              let data = res.data;
              if (!!data.isEncrypt){
                this.isEncrypt = data.isEncrypt;
                //获取录像记录
                postActionYs("https://open.ys7.com/api/lapp/video/by/time",params).then((res)=>{
                  let data = res.data;
                  if (res.code == 200){
                    if (data != null && data!=undefined && data.length > 0 ){
                      for (let i = data.length-1;i>=0;i--){
                        let tmp = {};
                        let stime = data[i].startTime;
                        let etime = data[i].endTime;
                        // let time = moment(data[i].startTime).format('YYYY年M月DD日 HH:mm:ss');
                        tmp.startTime = stime;
                        tmp.endTime = etime;
                        // tmp.crypt = data[i].crypt;//0不加密  1加密
                        tmp.crypt = this.isEncrypt;//0不加密  1加密
                        tmp.deviceSerial = data[i].deviceSerial;
                        tmp.channelNo = data[i].channelNo;
                        this.recData.push(tmp);
                        // if (data[i].keyChecksum != null){
                        //   console.log("data"+i+":"+JSON.stringify(data[i]));
                        // }
                      }
                      console.log("data:"+JSON.stringify(data[0]));
                    }
                  } else {
                    this.$message.warning("获取失败，请刷新重试");
                  }
                  this.recLoading = false;
                })
              }
            }  else{
              this.$message.warning(res.msg);
            }
          });

        },
        onRecPlay(index){
          if (player!=null){
            player.stop();
          }


          this.showCard = true;
          let data = this.recData[index];
          let stime = moment(data.startTime).format('YYYYMMDDHHmmss');
          let etime = moment(data.endTime).format('YYYYMMDDHHmmss');
          // ezopen://open.ys7.com/D86638112/1.rec?begin=20200227185920&end=20200227185934
          // ezopen:// [验证码@] open.ys7.com/[deviceSerial]/[channelNo] [.hd].rec[?begin= yyyyMMddhhmmss&end= yyyyMMddhhmmss]

          let src = "";
          // console.log(this.recData[index].crypt);
          if (!!this.recData[index].crypt && this.recData[index].crypt != 0){
            // 加密视频
            src = "ezopen://WYSICD@open.ys7.com/"+data.deviceSerial+"/"+data.channelNo+".rec?begin="+stime+"&end="+etime;
          } else{
            src = "ezopen://open.ys7.com/"+data.deviceSerial+"/"+data.channelNo+".rec?begin="+stime+"&end="+etime;
          }
          console.log(src);
          this.$nextTick(() => {
            this.initEzui('myPlayer',
              src,
              'at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8',
              800,
              600
            );
          });
        },
        onRecDownload(index){

        },
        /*-------------------表单-------------------*/
        // Only show error after a field is touched.
        deviceSerialError() {
          const { getFieldError, isFieldTouched } = this.form;
          return isFieldTouched('deviceSerial') && getFieldError('deviceSerial');
        },
        // Only show error after a field is touched.
        validateCodeError() {
          const { getFieldError, isFieldTouched } = this.form;
          return isFieldTouched('validateCode') && getFieldError('validateCode');
        },
        handleSubmit(e) {
          e.preventDefault();
          this.form.validateFields((err, values) => {
            if (!err) {
              let params={
                accessToken:"at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8",
                deviceSerial:values.deviceSerial,
                validateCode:values.validateCode,
              };
              postActionYs('https://open.ys7.com/api/lapp/device/add',params).then((res)=>{
                console.log(res)
                if (res.code == 200){
                  this.$message.success("添加成功!");
                } else if(res.code == 20002){
                  this.$message.warning("设备不存在!");
                } else if(res.code == 20007){
                  this.$message.warning("设备不在线，请检查设备是否通电及联网!");
                } else if(res.code == 20010){
                  this.$message.warning("验证码错误!");
                } else if(res.code == 20011){
                  this.$message.warning("请检查设备是否联网!");
                } else if(res.code == 20013){
                  this.$message.warning("该设备已被别的账号添加!");
                }else if(res.code == 20013){
                  this.$message.warning("该设备已被别的账号添加!");
                }else if(res.code == 20013){
                  this.$message.warning("该设备已被别的账号添加!");
                }else if(res.code == 20014){
                  this.$message.warning("序列号格式不合法!");
                }else if(res.code == 20017){
                  this.$message.warning("您已添加该设备!");
                }else{
                  this.$message.warning("服务器内部错误,请刷新重试!");
                }
              });

              console.log('Received values of form: ', values);
            }
          });
        },
        onDevDelete(key){
          console.log(this.dataSource[key]);
          let params={
            accessToken:"at.8rrnwx1i0tjtz8iod0nhm0f5bwn0g43y-2h6g0plfxs-0kwfpsy-umc1tt3k8",
            deviceSerial:this.dataSource[key].deviceSerial
          };
          postActionYs("https://open.ys7.com/api/lapp/device/delete",params).then((res)=>{
            if (res.code == 200){
              this.$message.warning("删除成功,请刷新页面!");
            } else if (res.code == 20002) {
              this.$message.warning("设备不存在!");
            }else if (res.code == 20018) {
              this.$message.warning("该用户不拥有该设备，请刷新重试!");
            }else {
              this.$message.warning("服务器内部错误,请联系管理员!");
            }
          });
        },
        getDevInfo(accessToken,deviceSerial){


        }
      },
        // onLoadMore() {
        //   this.recLoadingMore = true;
        //   this.getRecData(res => {
        //     this.data = this.data.concat(res.results);
        //     this.recLoadingMore = false;
        //     this.$nextTick(() => {
        //       window.dispatchEvent(new Event('resize'));
        //     });
        //   });
        // },

    }

</script>

<style>
  .editable-cell {
    position: relative;
  }

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    line-height: 18px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell:hover .editable-cell-icon {
    display: inline-block;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
  /* a-list */
  .demo-loadmore-list {
    min-height: 350px;
  }
</style>