<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="消费额度" prop="consumeMoney">
      <el-input v-model="dataForm.consumeMoney" placeholder="消费额度"></el-input>
    </el-form-item>
    <el-form-item label="消费名称" prop="consumeName">
      <el-input v-model="dataForm.consumeName" placeholder="消费名称"></el-input>
    </el-form-item>
    <el-form-item label="消费类型" prop="consumeType">
      <el-select prop="consumeType" placeholder="消费类型" v-model="dataForm.consumeType">
        <el-option v-for="(item,index) in consumeTypeList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="消费时间" prop="consumeTime">
      <el-date-picker
        v-model="dataForm.consumeTime"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="消费时间"
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
          id: 0,
          sysUserId: '',
          consumeMoney: '',
          consumeName: '',
          consumeTime: '',
          createTime: '',
          updateTime: '',
          enabled: '1'
        },
        consumeTypeList: [{id: 1, name: '购物'}, {id: 2, name: '吃饭'}, {id: 3, name: '公交'}, {id: 4, name: '旅游'}, {id: 5, name: '其他'}],
        dataRule: {
          consumeMoney: [
            { required: true, message: '消费额度不能为空', trigger: 'blur' }
          ],
          consumeName: [
            { required: true, message: '消费名称不能为空', trigger: 'blur' }
          ],
          consumeTime: [
            { required: true, message: '消费时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/my/myconsume/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sysUserId = data.myConsume.sysUserId
                this.dataForm.consumeMoney = data.myConsume.consumeMoney
                this.dataForm.consumeName = data.myConsume.consumeName
                this.dataForm.consumeType = data.myConsume.consumeType
                this.dataForm.consumeTime = data.myConsume.consumeTime
                this.dataForm.createTime = data.myConsume.createTime
                this.dataForm.updateTime = data.myConsume.updateTime
                this.dataForm.enabled = data.myConsume.enabled
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
              url: this.$http.adornUrl(`/my/myconsume/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sysUserId': this.dataForm.sysUserId,
                'consumeMoney': this.dataForm.consumeMoney,
                'consumeName': this.dataForm.consumeName,
                'consumeType': this.dataForm.consumeType,
                'consumeTime': this.dataForm.consumeTime,
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
