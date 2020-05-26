<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="产品名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="产品名称"></el-input>
    </el-form-item>
    <el-form-item label="微信号" prop="name">
      <el-input v-model="dataForm.wechat" placeholder="微信号"></el-input>
    </el-form-item>
    </el-form>
    <el-tag  v-model="dataForm.remainNum" type="danger" style="float:right">您还剩余：{{remainNum}}个</el-tag>
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
          userId: '',
          name: '',
          wechat: '',
          codeUrl: '',
          createTime: '',
          updateTime: '',
          enabled: ''
        },
        remainNum: '',
        dataRule: { }
      }
    },
    created () {
      this.getDataList()
    },
    methods: {
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjproduct/remainNum'),
          method: 'post',
          params: this.$http.adornParams({})
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.remainNum = data.remainNum
          } else {

          }
          this.dataListLoading = false
        })
      },
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/jingjia/jjproduct/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.jjProduct.userId
                this.dataForm.name = data.jjProduct.name
                this.dataForm.wechat = data.jjProduct.wechat
                this.dataForm.codeUrl = data.jjProduct.codeUrl
                this.dataForm.createTime = data.jjProduct.createTime
                this.dataForm.updateTime = data.jjProduct.updateTime
                this.dataForm.enabled = data.jjProduct.enabled
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
              url: this.$http.adornUrl(`/jingjia/jjproduct/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'name': this.dataForm.name,
                'wechat': this.dataForm.wechat,
                'codeUrl': this.dataForm.codeUrl,
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
