<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型" prop="type">
      <el-select prop="type" placeholder="请选择" v-model="dataForm.type">
        <el-option v-for="(item,index) in typeList" :key="index" :label="item.desc" :value="item.value">
        </el-option>
      </el-select>
      <!--<el-input v-model="dataForm.type" placeholder="类型"></el-input>-->
    </el-form-item>
    <el-form-item label="标题" prop="name">
      <el-input v-model="dataForm.name" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="内容" prop="content">
      <template>
        <quill-editor
          v-model="dataForm.content"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
          @change="onEditorChange($event)">
        </quill-editor>
      </template>
    </el-form-item>
  <!--  </el-form-item label="内容" prop="content">
      <el-form-item >
      <el-input v-model="dataForm.content" placeholder="内容"></el-input>
    </el-form-item>-->
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
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          type: '',
          name: '',
          content: '',
          createTime: '',
          updateTime: '',
          enabled: '1'
        },
        typeList: [{value: 1, desc: '公告信息'}, {value: 2, desc: '商家帮助'}, {value: 3, desc: '买家帮助'}, {value: 4, desc: '协议条款'}, {value: 5, desc: '动态消息'}],
        editorOption: {
        },
        dataRule: {
          type: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          enabled: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      onEditorBlur () { // 失去焦点事件
      },
      onEditorFocus () { // 获得焦点事件
      },
      onEditorChange () { // 内容改变事件
      },
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/share/sharehelp/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.type = data.shareHelp.type
                this.dataForm.name = data.shareHelp.name
                this.dataForm.content = data.shareHelp.content
                this.dataForm.createTime = data.shareHelp.createTime
                this.dataForm.updateTime = data.shareHelp.updateTime
                this.dataForm.enabled = data.shareHelp.enabled
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
              url: this.$http.adornUrl(`/share/sharehelp/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'type': this.dataForm.type,
                'name': this.dataForm.name,
                'content': this.dataForm.content,
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
