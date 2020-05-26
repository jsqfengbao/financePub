<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="购买日期" prop="buyTime">
      <el-date-picker
        v-model="dataForm.buyTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="收入时间"
        align="right">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="材料" prop="material">
      <el-input v-model="dataForm.material" placeholder="材料"></el-input>
    </el-form-item>
    <el-form-item label="购买数量" prop="buyNum">
      <el-input v-model="dataForm.buyNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="price">
      <el-input v-model="dataForm.price" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="供货商" prop="supplier">
      <el-input v-model="dataForm.supplier" placeholder="供货商"></el-input>
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
          buyTime: '',
          material: '',
          buyNum: '',
          price: '',
          supplier: '',
          totalPrice: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          buyTime: [
            { required: true, message: '购买时间不能为空', trigger: 'blur' }
          ],
          material: [
            { required: true, message: '材料不能为空', trigger: 'blur' }
          ],
          buyNum: [
            { required: true, message: '购买数量不能为空', trigger: 'blur' },
            {validator: validateNumber, trigger: 'blur'}
          ],
          price: [
            { required: true, message: '单价不能为空', trigger: 'blur' },
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
              url: this.$http.adornUrl(`/ad/qingfengbuy/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sysUserId = data.qingfengBuy.sysUserId
                this.dataForm.sysUserName = data.qingfengBuy.sysUserName
                this.dataForm.buyTime = data.qingfengBuy.buyTime
                this.dataForm.material = data.qingfengBuy.material
                this.dataForm.buyNum = data.qingfengBuy.buyNum
                this.dataForm.price = data.qingfengBuy.price
                this.dataForm.supplier = data.qingfengBuy.supplier
                this.dataForm.totalPrice = data.qingfengBuy.totalPrice
                this.dataForm.createTime = data.qingfengBuy.createTime
                this.dataForm.updateTime = data.qingfengBuy.updateTime
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
              url: this.$http.adornUrl(`/ad/qingfengbuy/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sysUserId': this.dataForm.sysUserId,
                'sysUserName': this.dataForm.sysUserName,
                'buyTime': this.dataForm.buyTime,
                'material': this.dataForm.material,
                'buyNum': this.dataForm.buyNum,
                'price': this.dataForm.price,
                'supplier': this.dataForm.supplier,
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
