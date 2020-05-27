<template>
  <div class="m-upload">
    <el-upload
      class="upload-demo"
      :action="url"
      :multiple="false"
      :before-upload="beforeUpload"
      :on-progress="handleProgress"
      :on-success="handleSuccess"
      :show-file-list="false"
      :on-remove="handleRemove">
      <el-tooltip class="item" effect="dark" content="只能上传pdf文件、word文件或图片，且不超过5MB,文件名不能含有'.'等特殊自符" placement="right">
        <el-button size="small" type="primary" :loading="loading">上传文件</el-button>
      </el-tooltip>
    </el-upload>
    <ul class="u-file-list" v-if="fileList.src">
      <!--<li v-for="(item,i) in fileList" :index="i" class="list-item">-->
      <li class="list-item">
        <a :href="fileList.src" target="_blank">{{fileList.name}}</a>
        <i class="el-icon-delete" @click="handleRemove(fileList.name)"></i>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    props: {
      fileUrl: '',
      index: '',
      list: ''
    },
    data () {
      return {
        url: '',
        fileName: '',
        fileTypeList: ['pdf', 'PDF', 'jpg', 'jpeg', 'JPEG', 'png', 'PNG', 'doc', 'DOC', 'docx', 'DOCX'],
        fileList: {},
        loading: false
      }
    },
    created () {
      if (this.fileUrl) {
        this.url = this.fileUrl
      }
      if (this.list) {
        this.fileList = this.list
      }
    },
    methods: {
      beforeUpload (file) {
        const fileSize = file.size
        const fileType = file.name.split('.')[1]
        // const fileName = file.name.split('.')[0]
        let flag = false
        for (let i = 0; i < this.fileTypeList.length; i += 1) {
          if (fileType === this.fileTypeList[i]) {
            flag = true
            break
          }
        }
        if (!flag) {
          this.$message({ type: 'error', message: '文件格式不支持' })
          return false
        }
        if (fileSize > 5242880) {
          this.$message({ type: 'error', message: '请上传小于5M的文件' })
          return false
        }
        this.fileName = file.name
        return true
      },
      handleProgress (result) {
        this.loading = true
      },
      handleSuccess (result) {
        this.loading = false
        if (result.code === 0) {
          console.log(result)
          this.fileList = { src: result.url, name: this.fileName, index: this.index }
          console.log(this.fileList)
          this.$emit('input', this.fileList.src)  // + '?x-oss-process=image/resize,m_fill,h_200,w_200'
        } else {
          // this.$store.dispatch('toast', { type: 'error', content: '上传失败' })
          this.$message({ type: 'error', message: '上传失败' })
        }
      },
      handleRemove (name) {
        // for (let i = 0; i < this.fileList.length; i += 1) {
        //   if (this.fileList[i].name === name) {
        //     this.fileList.splice(i, 1)
        //     break
        //   }
        // }
        this.fileList = {}
        this.$emit('input', this.fileList)
      }
    }
  }
</script>

<style rel="stylesheet/scss" type="text/css" lang="scss">

  .m-upload {
    > .upload-demo {
      width: 300px;
    }
    > .u-file-list {
      > .list-item {
        > a {
          text-decoration: underline;
        }
        > .el-icon-delete {
          margin-left: 20px;
          cursor: pointer;
          &:hover {
            color: blue;
          }
        }
      }
    }
  }
</style>
