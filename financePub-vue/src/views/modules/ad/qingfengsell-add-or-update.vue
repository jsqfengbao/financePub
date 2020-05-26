<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="销售时间" prop="sellTime">
      <el-date-picker
        v-model="dataForm.sellTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="销售时间"
        align="right">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="客户姓名" prop="sellUserName">
      <el-input v-model="dataForm.sellUserName" placeholder="客户姓名"></el-input>
    </el-form-item>
    <el-form-item label="项目名称" prop="projectName">
      <el-input v-model="dataForm.projectName" placeholder="项目名称"></el-input>
    </el-form-item>
    <el-form-item label="规格" prop="specs">
      <el-input v-model="dataForm.specs" placeholder="规格"></el-input>
    </el-form-item>
    <el-form-item label="销售数量" prop="sellNum">
      <el-input v-model="dataForm.sellNum" placeholder="销售数量"></el-input>
    </el-form-item>
    <el-form-item label="总价格" prop="totalPrice">
      <el-input v-model="dataForm.totalPrice" placeholder="总价格"></el-input>
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
      var validateNumber = (rule, value, callback) => {
        if (!isMoney(value)) {
          callback(new Error('请输入正确数字，可保留两位小数'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        dataForm: {
          id: 0,
          sysUserId: '',
          sysUserName: '',
          sellTime: '',
          sellUserName: '',
          projectName: '',
          specs: '',
          sellNum: '',
          totalPrice: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          sellTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          projectName: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' }
          ],
          sellNum: [
            { required: true, message: '销售数量不能为空', trigger: 'blur' },
            {validator: validateNumber, trigger: 'blur'}
          ],
          totalPrice: [
            { required: true, message: '总价格不能为空', trigger: 'blur' },
            {validator: validateNumber, trigger: 'blur'}
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
              url: this.$http.adornUrl(`/ad/qingfengsell/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sysUserId = data.qingfengSell.sysUserId
                this.dataForm.sysUserName = data.qingfengSell.sysUserName
                this.dataForm.sellTime = data.qingfengSell.sellTime
                this.dataForm.sellUserName = data.qingfengSell.sellUserName
                this.dataForm.projectName = data.qingfengSell.projectName
                this.dataForm.specs = data.qingfengSell.specs
                this.dataForm.sellNum = data.qingfengSell.sellNum
                this.dataForm.totalPrice = data.qingfengSell.totalPrice
                this.dataForm.createTime = data.qingfengSell.createTime
                this.dataForm.updateTime = data.qingfengSell.updateTime
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
              url: this.$http.adornUrl(`/ad/qingfengsell/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'sellTime': this.dataForm.sellTime,
                'sellUserName': this.dataForm.sellUserName,
                'projectName': this.dataForm.projectName,
                'specs': this.dataForm.specs,
                'sellNum': this.dataForm.sellNum,
                'totalPrice': this.dataForm.totalPrice,
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
