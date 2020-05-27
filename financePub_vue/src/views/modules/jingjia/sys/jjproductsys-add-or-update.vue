<template>
  <el-dialog
    :title="!dataForm.sysUserId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="选择用户">
      <el-select  prop="sysUserId" placeholder="请选择" v-model="dataForm.sysUserId" clearable filterable>
        <el-option v-for="(item,index) in sysUserList" :key="item.userId" :label="item.username" :value="item.userId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="最大数量" prop="maxProductNum">
      <el-input v-model="dataForm.maxProductNum" placeholder="最大产品数量"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="mark">
      <el-input v-model="dataForm.mark" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="截止时间" prop="endTime">
      <el-date-picker
        v-model="dataForm.endTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="截止时间"
        align="right">
      </el-date-picker>
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
          sysUserId: '',
          maxProductNum: '',
          endTime: '',
          mark: '',
          createTime: '',
          updateTime: '',
          enabled: 1
        },
        isUpdate: false,
        sysUserList: [],
        dataRule: {
          maxProductNum: [
            { required: true, message: '最大产品数量不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '截止时间不能为空', trigger: 'blur' }
          ],
          enabled: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    mounted () {
      this.getDataList()
    },
    methods: {
      init (sysUserId) {
        this.dataForm.sysUserId = sysUserId || 0
        if (this.dataForm.sysUserId !== 0) {
          this.isUpdate = true
        }
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.sysUserId) {
            this.$http({
              url: this.$http.adornUrl(`/jingjia/jjproductsys/info/${this.dataForm.sysUserId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sysUserId = data.jjProductSys.sysUserId
                this.dataForm.maxProductNum = data.jjProductSys.maxProductNum
                this.dataForm.endTime = data.jjProductSys.endTime
                this.dataForm.mark = data.jjProductSys.mark
                this.dataForm.createTime = data.jjProductSys.createTime
                this.dataForm.updateTime = data.jjProductSys.updateTime
                this.dataForm.enabled = data.jjProductSys.enabled
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
              url: this.$http.adornUrl(`/jingjia/jjproductsys/${!this.isUpdate ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'sysUserId': this.dataForm.sysUserId || undefined,
                'maxProductNum': this.dataForm.maxProductNum,
                'endTime': this.dataForm.endTime,
                'mark': this.dataForm.mark,
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
      getDataList () {
        // 获取用户
        this.$http({
          url: this.$http.adornUrl('/sys/user/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': 1,
            'limit': 9999
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.sysUserList = data.page.list
          } else {
            this.sysUserList = []
          }
          this.dataListLoading = false
        })
      }
    }
  }
</script>
