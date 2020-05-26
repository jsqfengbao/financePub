<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="年份" prop="yearNum">
      <el-input v-model="dataForm.yearNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="年度总收入" prop="fundYearIncome">
      <el-input v-model="dataForm.fundYearIncome" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="年度总支出" prop="fundYearPay">
      <el-input v-model="dataForm.fundYearPay" placeholder=""></el-input>
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
  export default {
    data () {
      var validateMoney = (rule, value, callback) => {
        if (!isMoney(value)) {
          callback(new Error('请输入正确金额，可保留两位小数'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          id: 0,
          yearNum: '',
          fundYearIncome: '',
          fundYearPay: '',
          sysUserId: '',
          sysUserName: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          yearNum: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          fundYearIncome: [
            { required: true, message: '不能为空', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ],
          fundYearPay: [
            { required: true, message: '不能为空', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/fund/fundyearbudget/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.yearNum = data.fundYearBudget.yearNum
                this.dataForm.fundYearIncome = data.fundYearBudget.fundYearIncome
                this.dataForm.fundYearPay = data.fundYearBudget.fundYearPay
                this.dataForm.sysUserId = data.fundYearBudget.sysUserId
                this.dataForm.sysUserName = data.fundYearBudget.sysUserName
                this.dataForm.createTime = data.fundYearBudget.createTime
                this.dataForm.updateTime = data.fundYearBudget.updateTime
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
              url: this.$http.adornUrl(`/fund/fundyearbudget/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'yearNum': this.dataForm.yearNum,
                'fundYearIncome': this.dataForm.fundYearIncome,
                'fundYearPay': this.dataForm.fundYearPay,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
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
      }
    }
  }
</script>
