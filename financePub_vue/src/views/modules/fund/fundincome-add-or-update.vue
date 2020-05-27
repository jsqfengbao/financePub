<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型名" prop="fundTypeName">
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
    <el-form-item label="地址" prop="address">
      <el-input v-model="dataForm.address" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="收入日期" prop="incomeTime">
      <el-date-picker
        v-model="dataForm.incomeTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="收入时间"
        align="right">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="收入图片" prop="picUrl">
      <upload-file :file-url="url" v-model="dataForm.picUrl"
                   :index="urlData.key"></upload-file>
      <!--<el-input v-model="dataForm.picUrl" placeholder=""></el-input>-->
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
          content: '',
          money: '',
          sysUserId: '',
          sysUserName: '',
          address: '',
          remark: '',
          incomeTime: '',
          picUrl: '',
          monthNum: '',
          createTime: '',
          updateTime: '',
          enabled: 1
        },
        fundTypeNameList: [],
        url: 'http://www.mobilesec110.com/manager/sys/oss/upload',
        urlData: {src: '', index: ''},
        dataRule: {
          fundTypeId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '不能为空', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ],
          incomeTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/fund/fundincome/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.fundTypeId = data.fundIncome.fundTypeId
                this.dataForm.fundTypeName = data.fundIncome.fundTypeName
                this.dataForm.content = data.fundIncome.content
                this.dataForm.money = data.fundIncome.money
                this.dataForm.sysUserId = data.fundIncome.sysUserId
                this.dataForm.sysUserName = data.fundIncome.sysUserName
                this.dataForm.address = data.fundIncome.address
                this.dataForm.remark = data.fundIncome.remark
                this.dataForm.incomeTime = data.fundIncome.incomeTime
                this.dataForm.picUrl = data.fundIncome.picUrl
                this.dataForm.monthNum = data.fundIncome.monthNum
                this.dataForm.createTime = data.fundIncome.createTime
                this.dataForm.updateTime = data.fundIncome.updateTime
                this.dataForm.enabled = data.fundIncome.enabled
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
              url: this.$http.adornUrl(`/fund/fundincome/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              params: this.$http.adornParams(),
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'fundTypeId': this.dataForm.fundTypeId,
                'fundTypeName': this.dataForm.fundTypeName,
                'content': this.dataForm.content,
                'money': this.dataForm.money,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'address': this.dataForm.address,
                'remark': this.dataForm.remark,
                'incomeTime': this.dataForm.incomeTime,
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
            'type': '0'
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            // console.log(data.page.list)
            this.fundTypeNameList = data.page.list
          } else {
            this.fundTypeNameList = []
          }
        })
      }
    }
  }
</script>
