<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型" prop="fundTypeId">
      <el-select  prop="fundTypeId" placeholder="请选择" v-model="dataForm.fundTypeId" clearable filterable>
        <el-option v-for="(item,index) in fundTypeNameList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="具体内容" prop="content">
      <el-input v-model="dataForm.content" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="金额" prop="money">
      <el-input v-model="dataForm.money" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="支付方式" prop="payType">
      <el-select prop="payType" placeholder="请选择" v-model="dataForm.payType">
        <el-option v-for="(item,index) in payTypeList" :key="item.payType" :label="item.payTypeName" :value="item.payTypeName">
        </el-option>
      </el-select>
      <!--<el-input v-model="dataForm.payType" placeholder=""></el-input>-->
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="支付凭证" prop="picUrl">
      <upload-file :file-url="url" v-model="dataForm.picUrl"
                   :index="urlData.key"></upload-file>
      <!--<el-input v-model="dataForm.picUrl" placeholder=""></el-input>-->
    </el-form-item>
    <el-form-item label="支出日期" prop="payTime">
      <el-date-picker
        v-model="dataForm.payTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="收入时间"
        align="right">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="是否有效" prop="enabled">
      <el-radio-group v-model="dataForm.enabled">
        <el-radio-button :label="0">禁用</el-radio-button>
        <el-radio-button :label="1">正常</el-radio-button>
      </el-radio-group>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { isMoney } from '@/utils/validate'
  import UploadFile from '../../../components/upload-file'
  import UploadFileCos from '../../../components/upload-file-cos'
  export default {
    components: {
      UploadFile,
      UploadFileCos
    },
    data () {
      var validateMoney = (rule, value, callback) => {
        if (!isMoney(value)) {
          callback(new Error('请输入金额'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          id: 0,
          fundTypeId: '',
          fundTypeName: '',
          sysUserId: '',
          sysUserName: '',
          content: '',
          money: '',
          payType: '',
          remark: '',
          payTime: '',
          picUrl: '',
          monthNum: '',
          createTime: '',
          updateTime: '',
          enabled: 1
        },
        fundTypeNameList: [{fundTypeId: '', fundTypeName: ''}],
        payTypeList: [
          {payType: '1', payTypeName: '现金'},
          {payType: '2', payTypeName: '支付宝'},
          {payType: '3', payTypeName: '微信'},
          {payType: '4', payTypeName: '储蓄卡'},
          {payType: '5', payTypeName: '信用卡'},
          {payType: '6', payTypeName: '其他'}
        ],
        url: 'http://www.mobilesec110.com/manager/sys/oss/upload',
        urlData: {src: '', index: ''},
        dataRule: {
          fundTypeId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '不能为空', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ],
          payType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          payTime: [
            {required: true, message: '不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    created () {
      this.getFundTypeList()
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/fund/fundpay/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.fundTypeId = data.fundPay.fundTypeId
                this.dataForm.fundTypeName = data.fundPay.fundTypeName
                this.dataForm.sysUserId = data.fundPay.sysUserId
                this.dataForm.sysUserName = data.fundPay.sysUserName
                this.dataForm.content = data.fundPay.content
                this.dataForm.money = data.fundPay.money
                this.dataForm.payType = data.fundPay.payType
                this.dataForm.remark = data.fundPay.remark
                this.dataForm.picUrl = data.fundPay.picUrl
                this.dataForm.payTime = data.fundPay.payTime
                this.dataForm.monthNum = data.fundPay.monthNum
                this.dataForm.createTime = data.fundPay.createTime
                this.dataForm.updateTime = data.fundPay.updateTime
                this.dataForm.enabled = data.fundPay.enabled
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/fund/fundpay/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'fundTypeId': this.dataForm.fundTypeId,
                'fundTypeName': this.dataForm.fundTypeName,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'content': this.dataForm.content,
                'money': this.dataForm.money,
                'payType': this.dataForm.payType,
                'remark': this.dataForm.remark,
                'payTime': this.dataForm.payTime,
                'picUrl': this.dataForm.picUrl,
                'monthNum': this.dataForm.monthNum,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'enabled': this.dataForm.enabled
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      getFundTypeList () {
        this.$http({
          url: this.$http.adornUrl('/fund/fundtype/list'),
          method: 'get',
          params: {
            'type': '1'
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.fundTypeNameList = data.page.list
          } else {
            this.fundTypeNameList = []
          }
        })
      }
    }
  }
</script>
