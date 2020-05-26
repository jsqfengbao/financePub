<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="信用卡号" prop="cardNo">
      <el-input v-model="dataForm.cardNo" placeholder="信用卡号"></el-input>
    </el-form-item>
    <el-form-item label="信用卡名" prop="cardName">
      <el-input v-model="dataForm.cardName" placeholder="信用卡名"></el-input>
    </el-form-item>
    <el-form-item label="还款时间" prop="repayTime">
      <el-date-picker
        v-model="dataForm.repayTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="还款时间"
        align="right">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="还款金额" prop="repayMoney">
      <el-input v-model="dataForm.repayMoney" placeholder="还款金额"></el-input>
    </el-form-item>
    <el-form-item label="是否有效" prop="enabled">
      <el-radio-group v-model="dataForm.enabled">
        <el-radio-button :label="0">无效</el-radio-button>
        <el-radio-button :label="1">有效</el-radio-button>
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
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          cardNo: '',
          cardName: '',
          sysUserId: '',
          sysUserName: '',
          repayTime: '',
          repayMoney: '',
          createTime: '',
          updateTime: '',
          enabled: 1
        },
        dataRule: {
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
              url: this.$http.adornUrl(`/consume/mycreditcardconsume/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.cardNo = data.myCreditcardConsume.cardNo
                this.dataForm.cardName = data.myCreditcardConsume.cardName
                this.dataForm.sysUserId = data.myCreditcardConsume.sysUserId
                this.dataForm.sysUserName = data.myCreditcardConsume.sysUserName
                this.dataForm.repayTime = data.myCreditcardConsume.repayTime
                this.dataForm.repayMoney = data.myCreditcardConsume.repayMoney
                this.dataForm.createTime = data.myCreditcardConsume.createTime
                this.dataForm.updateTime = data.myCreditcardConsume.updateTime
                this.dataForm.enabled = data.myCreditcardConsume.enabled
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
              url: this.$http.adornUrl(`/consume/mycreditcardconsume/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'cardNo': this.dataForm.cardNo,
                'cardName': this.dataForm.cardName,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'repayTime': this.dataForm.repayTime,
                'repayMoney': this.dataForm.repayMoney,
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
      }
    }
  }
</script>
