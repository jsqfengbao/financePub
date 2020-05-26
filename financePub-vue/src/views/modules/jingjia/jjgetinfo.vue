<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.visitUrl" placeholder="访问URL" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.visitIp" placeholder="访问IP" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.wechat" placeholder="微信号" clearable></el-input>
      </el-form-item>
      <el-select prop="operateType" placeholder="请选择操作类型" v-model="dataForm.operateType">
        <el-option v-for="(item,index) in operateTypeList" :key="item.operateType" :label="item.operateTypeName" :value="item.operateType">
        </el-option>
      </el-select>
      <el-select prop="productId" placeholder="选择产品" v-model="dataForm.productId">
        <el-option v-for="(item,index) in productList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('jingjia:jjgetinfo:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('jingjia:jjgetinfo:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
        <!--<el-button type="success" icon="el-icon-upload" size="medium" @click="exportInfo()">导出</el-button>-->
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="wechat"
        header-align="center"
        align="center"
        label="微信号">
      </el-table-column>
      <el-table-column
        prop="operateType"
        header-align="center"
        align="center"
        label="操作类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.operateType === 1" size="small" >仅访问</el-tag>
          <el-tag v-else="scope.row.operateType ===2" size="small" type="danger">复制</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="keyword"
        header-align="center"
        align="center"
        label="关键字">
      </el-table-column>
      <el-table-column
        prop="visitUrl"
        header-align="center"
        align="center"
        label="访问链接">
      </el-table-column>
      <el-table-column
        prop="visitIp"
        header-align="center"
        align="center"
        label="IP">
      </el-table-column>
      <el-table-column
        prop="isp"
        header-align="center"
        align="center"
        label="运营商">
      </el-table-column>
      <el-table-column
        prop="systemName"
        header-align="center"
        align="center"
        label="操作系统名称">
      </el-table-column>
      <el-table-column
        prop="appName"
        header-align="center"
        align="center"
        label="浏览器名称">
      </el-table-column>
      <el-table-column
        prop="visitAddress"
        header-align="center"
        align="center"
        label="访客地址">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="访问时间">
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './jjgetinfo-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          wechat: '',
          visitUrl: '',
          visitIp: '',
          operateType: '',
          productId: ''
        },
        operateTypeList: [{operateType: '', operateTypeName: '所有'}, {operateType: 1, operateTypeName: '仅访问'}, {operateType: 2, operateTypeName: '复制'}],
        productList: [{id: '', name: '所有'}],
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    mounted () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjgetinfo/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'wechat': this.dataForm.wechat,
            'visitUrl': this.dataForm.visitUrl,
            'visitIp': this.dataForm.visitIp,
            'operateType': this.dataForm.operateType,
            'productId': this.dataForm.productId
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
        // 选择产品
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjproduct/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': 1,
            'limit': 9999
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.productList = data.page.list
          } else {
            this.productList = []
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/jingjia/jjgetinfo/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      // 导出
      exportInfo () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjgetinfo/exportInfo'),
          method: 'get',
          responseType: 'blob',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'wechat': this.dataForm.wechat,
            'visitUrl': this.dataForm.visitUrl,
            'visitIp': this.dataForm.visitIp,
            'operateType': this.dataForm.operateType,
            'productId': this.dataForm.productId
          })
        }).then(({data}) => {
          const blob = new Blob([data])
          const fileName = '实时统计.xlsx'
          const elink = document.createElement('a')
          elink.download = fileName
          elink.style.display = 'none'
          elink.href = URL.createObjectURL(blob)
          document.body.appendChild(elink)
          elink.click()
          URL.revokeObjectURL(elink.href) // 释放URL 对象
          document.body.removeChild(elink)
          if (data && data.code === 0) {
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.getDataList()
          this.dataListLoading = false
        })
      }
    }
  }
</script>
