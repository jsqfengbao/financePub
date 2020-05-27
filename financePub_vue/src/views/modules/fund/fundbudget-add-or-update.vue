<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型" prop="fundTypeName">
      <el-select  prop="fundTypeId" placeholder="请选择" v-model="dataForm.fundTypeId" clearable filterable>
        <el-option v-for="(item,index) in fundTypeNameList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="金额" prop="money">
      <el-input v-model="dataForm.money" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="预算月份" prop="monthNum">
      <el-input v-model="dataForm.monthNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="预算年份" prop="yearNum">
      <el-input v-model="dataForm.yearNum" placeholder=""></el-input>
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
          money: '',
          monthNum: '',
          yearNum: '',
          createTime: '',
          updateTime: ''
        },
        fundTypeNameList: [{fundTypeId: '', fundTypeName: ''}],
        dataRule: {
          fundTypeId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '不能为空', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ],
          monthNum: [
            {required: true, message: '不能为空', trigger: 'blur'}
          ],
          yearNum: [
            {required: true, message: '不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    created () {
      this.getFundTypeList()
      this.getThisYear()
    },
    methods: {
      getThisYear () {
        var today
        today = new Date()
        this.dataForm.yearNum = today.getFullYear()
      },
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/fund/fundbudget/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.fundTypeId = data.fundBudget.fundTypeId
                this.dataForm.fundTypeName = data.fundBudget.fundTypeName
                this.dataForm.money = data.fundBudget.money
                this.dataForm.monthNum = data.fundBudget.monthNum
                this.dataForm.yearNum = data.fundBudget.yearNum
                this.dataForm.createTime = data.fundBudget.createTime
                this.dataForm.updateTime = data.fundBudget.updateTime
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
              url: this.$http.adornUrl(`/fund/fundbudget/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'fundTypeId': this.dataForm.fundTypeId,
                'fundTypeName': this.dataForm.fundTypeName,
                'money': this.dataForm.money,
                'monthNum': this.dataForm.monthNum,
                'yearNum': this.dataForm.yearNum,
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
