<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="名称" prop="name">
      <el-input v-model="dataForm.name" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="连接" prop="imgLogUrl">
      <!--<el-input v-model="dataForm.imgLogUrl" placeholder=""></el-input>-->
      <upload-file :file-url="url" v-model="dataForm.imgLogUrl"
                   :index="urlData.key"></upload-file>
    </el-form-item>
    <el-form-item label="作品描述" prop="desc">
      <el-input v-model="dataForm.imgDesc" placeholder=""></el-input>
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
  import UploadFile from '../../../components/upload-file'
  import UploadFileCos from '../../../components/upload-file-cos'
  export default {
    components: {
      UploadFile,
      UploadFileCos
    },
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          imgLogUrl: '',
          imgDesc: '',
          createTime: '',
          updateTime: '',
          enabled: 1
        },
        url: 'http://www.mobilesec110.com/manager/sys/oss/upload',
        urlData: {src: '', index: ''},
        dataRule: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ad/qingfengimg/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.qingfengImg.name
                this.dataForm.imgLogUrl = data.qingfengImg.imgLogUrl
                this.dataForm.imgDesc = data.qingfengImg.imgDesc
                this.dataForm.createTime = data.qingfengImg.createTime
                this.dataForm.updateTime = data.qingfengImg.updateTime
                this.dataForm.enabled = data.qingfengImg.enabled
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
              url: this.$http.adornUrl(`/ad/qingfengimg/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'imgLogUrl': this.dataForm.imgLogUrl,
                'imgDesc': this.dataForm.imgDesc,
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
