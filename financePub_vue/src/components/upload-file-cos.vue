<template>
  <div class="m-upload">
    <ul class="u-file-list" v-if="fileList.length">
      <li v-for="(item,i) in fileList" :index="i" :key="i" class="list-item">
        <a :href="item.src" target="_blank" :title="item.name">{{item.name}}</a>
        <i class="el-icon-close" @click="handleRemove(item.name)"></i>
      </li>
    </ul>
    <el-upload
      class="upload-demo"
      :action="url"
      :multiple="false"
      :before-upload="beforeUpload"
      :on-progress="handleProgress"
      :on-success="handleSuccess"
      :show-file-list="false"
      :on-remove="handleRemove"
      :data="{remotePath:'document'}">
      <el-tooltip class="item" effect="dark" :content="restrictiveRules" placement="right">
        <el-button class="upload-btn" size="small" type="primary" :loading="loading">上传文件</el-button>
      </el-tooltip>
    </el-upload>
  </div>
</template>

<script>
  export default {
    props: {
      // index: '',
      file: {
        type: Array,
        required: false
      },
      rule: {
        type: String,
        required: false
      }
    },
    data () {
      return {
        url: '',
        fileName: '',
        fileTypeList: ['pdf', 'PDF', 'jpg', 'jpeg', 'JPEG', 'png', 'PNG', 'doc', 'DOC', 'docx', 'DOCX'],
        fileList: [],
        loading: false,
        restrictiveRules: '只能上传pdf文件、word文件或图片，且不超过5MB,文件名不能含有\'.\'等特殊自符',
        isRestrictive: true
      }
    },
    created () {
      this.url = 'http://www.mobilesec110.com/manager/sys/oss/upload'
      // if (process.env.NODE_ENV === 'production') {
      //   this.url = '//rest.mingdeschool.org/upload/cos'
      // } else if (process.env.NODE_ENV === 'testing') {
      //   this.url = '//test.api.mingdeschool.org/upload/cos'
      // } else {
      //   this.url = '//dev.api.mingdeschool.com/upload/cos'
      // }
      if (this.file) {
        this.fileList = this.file
      }
      if (this.rule === '50MB') {
        this.restrictiveRules = '文件不超过50MB,文件名不能含有\'.\'等特殊自符'
      }
    },
    watch: {
      file: {
        handler (newValue, oldValue) {
          this.fileList = newValue
        },
        deep: true
      }
    },
    methods: {
      beforeUpload (file) {
        const fileSize = file.size
        const fileType = file.name.split('.')[1]
        // const fileName = file.name.split('.')[0]
        console.log(file)
        if (this.rule === '50MB') {
          if (fileSize > 52428800) {
            this.$message({
              type: 'error', message: '请上传小于50M的文件'
            })
            return false
          }
        } else {
          let flag = false
          for (let i = 0; i < this.fileTypeList.length; i += 1) {
            if (fileType === this.fileTypeList[i]) {
              flag = true
              break
            }
          }
          if (!flag) {
            this.$message({
              type: 'error', message: '文件格式不支持'
            })
            return false
          }
          if (fileSize > 5242880) {
            this.$message({
              type: 'error', message: '请上传小于5M的文件'
            })
            return false
          }
        }
        this.fileName = file.name
        return true
      },
      handleProgress (result) {
        this.loading = true
      },
      handleSuccess (result) {
        this.loading = false
        if (result.errno === 0) {
          this.fileList.push({
            src: result.data.url, name: this.fileName
          })
          this.$emit('input', this.fileList)
        } else {
          this.$message({
            type: 'error', message: '上传失败'
          })
        }
      },
      handleRemove (name) {
        for (let i = 0; i < this.fileList.length; i += 1) {
          if (this.fileList[i].name === name) {
            this.fileList.splice(i, 1)
            break
          }
        }
        this.$emit('input', this.fileList)
      }
    }
  }
</script>

<style rel="stylesheet/scss" type="text/css" lang="scss">

  .m-upload {
    > .upload-demo {
      /*width: 300px;*/
      margin-top: 20px;
      .upload-btn{
        border: 1px solid #89B7ED;
        background: #F9FCFF;
        color: #89B7ED;
        margin: 0;
        padding: 8px 18px;
        font-size: 16px;
        border-radius: 2px ;
        .svg-icon {
          padding-right: 10px;
          fill: currentColor;
        }
      }
    }
    > .u-file-list {
      > .list-item {
        border: 1px solid #d4d4d4;
        background: #f5f5f5;
        line-height: 30px;
        padding: 0 24px;
        border-radius: 2px;
        > a {
          color: #acacac;
          display: inline-block;
          vertical-align: middle;
          overflow:hidden;
          white-space:nowrap;
          text-overflow:ellipsis;
          max-width: 300px;
        }
        > .el-icon-close {
          font-size: 14px;
          vertical-align: middle;
          margin-left: 10px;
          cursor: pointer;
          &:hover {
            color: blue;
          }
        }
      }
    }
  }
</style>
