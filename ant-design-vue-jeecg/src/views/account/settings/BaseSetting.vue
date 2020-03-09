<template>
  <div class="account-settings-info-view">
    <a-row :gutter="16">
      <a-col :md="24" :lg="16">

        <a-form :form="form">
          <a-form-item label="用户名字" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input id="realname" placeholder="请输入用户名称"  v-decorator="[ 'realname', validatorRules.realname]"  /><!---->
          </a-form-item>

          <a-form-item label="生日" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-date-picker
              id="birthday"
              style="width: 100%"
              placeholder="请选择生日"
              v-decorator="['birthday', validatorRules.birthday ]"
            /><!---->
          </a-form-item>

          <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-select id="sex" v-decorator="[ 'sex', validatorRules.sex]"   placeholder="请选择性别" ><!---->
              <a-select-option :value="1">男</a-select-option>
              <a-select-option :value="2">女</a-select-option>
            </a-select>
          </a-form-item>

          <a-form-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input id="email" placeholder="请输入邮箱"  v-decorator="[ 'email' , validatorRules.email]" /><!---->
          </a-form-item>

          <a-form-item label="手机号码" :labelCol="labelCol" :wrapperCol="wrapperCol" ><!--v-decorator="[ user.phone, validatorRules.phone]"-->
            <a-input id="phone" placeholder="请输入手机号码"  :disabled="isDisabledAuth('user:form:phone')" v-decorator="[ 'phone', validatorRules.phone]"  />
          </a-form-item>

          <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-textarea id="address" placeholder="请输入地址" :rows="4" v-decorator="[ 'address', validatorRules.address ]" /><!---->
          </a-form-item>

          <a-form-item label="确认无误点击保存" :labelCol="labelCol" :wrapperCol="wrapperCol" >
<!--            <a-button type="primary">提交</a-button>-->
            <a-button type="primary" @click="testEditUser">保存</a-button>
          </a-form-item>

        </a-form>
        <!--
        <a-form layout="vertical">
          <a-form-item
            label="昵称"
            v-model="realname"
          >
            <a-input placeholder="给自己起个名字" />
          </a-form-item>
          <a-form-item
            label="Bio"
          >
            <a-textarea rows="4" placeholder="You are not alone."/>
          </a-form-item>

          <a-form-item
            label="电子邮件"
            :required="false"
          >
            <a-input placeholder="exp@admin.com"/>
          </a-form-item>
          <a-form-item
            label="加密方式"
            :required="false"
          >
            <a-select defaultValue="aes-256-cfb">
              <a-select-option value="aes-256-cfb">aes-256-cfb</a-select-option>
              <a-select-option value="aes-128-cfb">aes-128-cfb</a-select-option>
              <a-select-option value="chacha20">chacha20</a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item
            label="连接密码"
            :required="false"
          >
            <a-input placeholder="h3gSbecd"/>
          </a-form-item>
          <a-form-item
            label="登陆密码"
            :required="false"
          >
            <a-input placeholder="密码"/>
          </a-form-item>

          <a-form-item
            label="地址"
            :required="false"
          >
            <a-textarea placeholder="请输入地址" :rows="4" />
          </a-form-item>

          <a-form-item>
            <a-button type="primary">提交</a-button>
            <a-button type="primary" style="margin-left: 8px" onclick="testEditUser()">保存</a-button>
          </a-form-item>
        </a-form>
        -->
      </a-col>
<!--      <a-col :md="24" :lg="8" :style="{ minHeight: '180px' }">-->
<!--        <div class="ant-upload-preview" @click="$refs.modal.edit(1)" >-->
<!--          <a-icon type="cloud-upload-o" class="upload-icon"/>-->
<!--          <div class="mask">-->
<!--            <a-icon type="plus" />-->
<!--          </div>-->
<!--          <img :src="option.img"/>-->
<!--        </div>-->
<!--      </a-col>-->

    </a-row>

    <avatar-modal ref="modal">

    </avatar-modal>
  </div>
</template>

<script>
  import pick from 'lodash.pick'
  import moment from 'moment'
  import Vue from 'vue'

  import AvatarModal from './AvatarModal'
  import {putAction , getAction} from "@api/manage";
  import { disabledAuthFilter } from "@/utils/authFilter"
  import {duplicateCheck } from '@/api/api'
  import store from '@/store/'

  export default {
    components: {
      AvatarModal,
    },
    data () {
      return {
        user:{
          realname:'',
          birthday:'',
          sex:'',
          email:'',
          phone:'',
          address:''
        },
        // cropper
        preview: {},

        option: {
          img: '/avatar2.jpg',
          info: true,
          size: 1,
          outputType: 'jpeg',
          canScale: false,
          autoCrop: true,
          // 只有自动截图开启 宽度高度才生效
          autoCropWidth: 180,
          autoCropHeight: 180,
          fixedBox: true,
          // 开启宽度和高度比例
          fixed: true,
          fixedNumber: [1, 1]
        },


        // 表单
        userId:store.getters.userInfo.id,
        dateFormat:"YYYY-MM-DD",
        model:{},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        validatorRules:{
          // username:{
          //   rules: [{
          //     required: false, message: '请输入用户账号!'
          //   },{
          //     validator: this.validateUsername,
          //   }],
          //   initialValue:"",
          // },
          birthday:{
            initialValue:null,
            name:"birthday"
          },
          sex:{
            initialValue:"",
          },
          realname:{
            rules: [{
              required: false, message: '请输入用户名称!'
            }],
            name:"realname",
            initialValue:"",
          },
          phone:{
            rules: [{
              validator: this.validatePhone
            }],
            name:"phone",
            initialValue:"",
          },
          email:{
            rules: [{
              validator: this.validateEmail
            }],
            name:"email",
            initialValue:"",
          },
          roles:{},
          //  sex:{initialValue:((!this.model.sex)?"": (this.model.sex+""))}
          address:{
            rules: [
              { pattern: /^(?=[\S\s]{0,50}$)[\S\s]*/,message: '输入的地址字数过多'  },
            ],
            name:"address",
            initialValue:"",
          }
        },
        form:this.$form.createForm(this),
      }
    },
    created(){
      this.initFormDate();
    },
    methods: {

      initFormDate(){
        getAction("/sys/user/queryById",{id:store.getters.userInfo.id}).then((res)=>{
          let data = res.result;
          if (res.success){
            this.user.realname=data.realname;
            this.user.sex=data.sex;
            this.user.birthday=data.birthday;
            this.user.email=data.email;
            this.user.address=data.address;
            this.user.phone=data.phone;
          }
          // console.log(res);
          this.dealRuleValue(this.user);

        })
      },
      testEditUser(){
        // 触发表单验证
        /*
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let avatar = that.model.avatar;
            if(!values.birthday){
              values.birthday = '';
            }else{
              values.birthday = values.birthday.format(this.dateFormat);
            }
            let formData = Object.assign(this.model, values);
            formData.avatar = avatar;
            formData.selectedroles = this.selectedRole.length>0?this.selectedRole.join(","):'';
            formData.selecteddeparts = this.userDepartModel.departIdList.length>0?this.userDepartModel.departIdList.join(","):'';

            // that.addDepartsToUser(that,formData); // 调用根据当前用户添加部门信息的方法
            let obj;
            if(!this.model.id){
              formData.id = this.userId;
              obj=addUser(formData);
            }else{
              obj=editUser(formData);
            }
            obj.then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.checkedDepartNames = [];
              that.userDepartModel.departIdList = {userId:'',departIdList:[]};

              that.close();
            })

          }
        })
        */
        this.form.validateFields((err, values) => {
          if (!err){
            let params={};
            params.id=store.getters.userInfo.id;
            params.realname=document.getElementById("realname").value;

            let birthday = document.getElementById("birthday").value;
            // let moment1 = moment(birthday,this.dateFormat);
            // var youWant=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
            let resDate = null;
            if (birthday === '' || birthday === undefined){

            } else{
              let d = new Date(birthday);
              let resTime = this.p(d.getHours()) + ':' + this.p(d.getMinutes()) + ':' + this.p(d.getSeconds());
              resDate = d.getFullYear() + '-' + this.p((d.getMonth() + 1)) + '-' + this.p(d.getDate()) + ' ' + resTime;
            }
            params.birthday = resDate;
            params.sex=document.getElementById("sex").value;
            params.email=document.getElementById("email").value;
            // params.phone=document.getElementById("phone").value;
            params.address=document.getElementById("address").value;

            putAction("/sys/user/edituser",params).then((res) => {
              if (res.success){
                this.$message.success(res.message);
              }else{
                this.$message.warning(res.message);
              }
            })
          }
        })
      },
      dealRuleValue(datas){
        var date={};
        for (var b in this.validatorRules){
          if (datas[b] != null){
            if (this.validatorRules[b].name === "birthday"){
              this.validatorRules[b].initialValue= (datas[b]=null? null:moment(this.user.birthday,this.dateFormat));
            }else{
              this.validatorRules[b].initialValue=datas[b];
            }
          }
        }
      },
      isDisabledAuth(code){
        return disabledAuthFilter(code);
      },
      validatePhone(rule, value, callback){
        if(!value){
          callback()
        }else{
          //update-begin--Author:kangxiaolin  Date:20190826 for：[05] 手机号不支持199号码段--------------------
          if(new RegExp(/^1[3|4|5|7|8|9][0-9]\d{8}$/).test(value)){
            //update-end--Author:kangxiaolin  Date:20190826 for：[05] 手机号不支持199号码段--------------------
            var params = {
              tableName: 'sys_user',
              fieldName: 'phone',
              fieldVal: value,
              dataId: this.userId
            };
            duplicateCheck(params).then((res) => {
              if (res.success) {
                callback()
              } else {
                callback("手机号已存在!")
              }
            })
          }else{
            callback("请输入正确格式的手机号码!");
          }
        }
      },
      validateEmail(rule, value, callback){
        if(!value){
          callback()
        }else{
          if(new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(value)){
            var params = {
              tableName: 'sys_user',
              fieldName: 'email',
              fieldVal: value,
              dataId: this.userId
            };
            duplicateCheck(params).then((res) => {
              if (res.success) {
                callback()
              } else {
                callback("邮箱已存在!")
              }
            })
          }else{
            callback("请输入正确格式的邮箱!")
          }
        }
      },
      // 根据屏幕变化,设置抽屉尺寸
      resetScreenSize(){
        let screenWidth = document.body.clientWidth;
        if(screenWidth < 500){
          this.drawerWidth = screenWidth;
        }else{
          this.drawerWidth = 700;
        }
      },
      p(s) {
        return s < 10 ? '0' + s : s
      },
    }
  }
</script>

<style lang="scss" scoped>

  .avatar-upload-wrapper {
    height: 200px;
    width: 100%;
  }

  .ant-upload-preview {
    position: relative;
    margin: 0 auto;
    width: 100%;
    max-width: 180px;
    border-radius: 50%;
    box-shadow: 0 0 4px #ccc;

    .upload-icon {
      position: absolute;
      top: 0;
      right: 10px;
      font-size: 1.4rem;
      padding: 0.5rem;
      background: rgba(222, 221, 221, 0.7);
      border-radius: 50%;
      border: 1px solid rgba(0, 0, 0, 0.2);
    }
    .mask {
      opacity: 0;
      position: absolute;
      background: rgba(0,0,0,0.4);
      cursor: pointer;
      transition: opacity 0.4s;

      &:hover {
        opacity: 1;
      }

      i {
        font-size: 2rem;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -1rem;
        margin-top: -1rem;
        color: #d6d6d6;
      }
    }

    img, .mask {
      width: 100%;
      max-width: 180px;
      height: 100%;
      border-radius: 50%;
      overflow: hidden;
    }
  }
</style>