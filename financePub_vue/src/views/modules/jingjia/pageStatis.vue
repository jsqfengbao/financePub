<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-button type="success" v-model="dataForm.yesterday" @click="getYesterday()">昨日</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" v-model="dataForm.today" @click="getToday()">今日</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" v-model="dataForm.thisMonth" @click="getThisMonth()">本月</el-button>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.startTime"
          type="datetime"
          value-format="yyyy-MM-dd"
          placeholder="开始时间"
          align="right">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.endTime"
          type="datetime"
          value-format="yyyy-MM-dd"
          placeholder="结束时间"
          align="right">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-select prop="sortType" placeholder="选择排序方式" v-model="dataForm.sortType">
          <el-option v-for="(item,index) in sortTypeList" :key="item.sortType" :label="item.sortName" :value="item.sortType">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
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
        prop="visitUrl"
        header-align="center"
        align="center"
        label="访问链接">
      </el-table-column>
      <el-table-column
        prop="visitNum"
        header-align="center"
        align="center"
        label="访问次数">
      </el-table-column>
      <el-table-column
        prop="copyNum"
        header-align="center"
        align="center"
        label="复制次数">
      </el-table-column>
      <el-table-column
        prop="copyRatio"
        header-align="center"
        align="center"
        label="复制比率">
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
  import moment from 'moment'
  export default {
    data () {
      return {
        dataForm: {
          startTime: '',
          endTime: '',
          sortType: '',
          today: '',
          yesterday: '',
          thisMonth: ''
        },
        dataList: [],
        sortTypeList: [{sortType: 'visitNum', sortName: '访问次数'}, {sortType: 'copyNum', sortName: '复制次数'}],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    activated () {
      this.getDataList()
    },
    created () {
      this.dataForm.startTime = moment().subtract('month', 0).format('YYYY-MM') + '-01'
      this.dataForm.endTime = moment(new Date()).format('YYYY-MM-DD')
    },
    methods: {
      // 设置时间
      getToday () {
        this.dataForm.startTime = moment().subtract('days', 0).format('YYYY-MM-DD')
        this.dataForm.endTime = moment().subtract('days', -1).format('YYYY-MM-DD')
      },
      getYesterday () {
        this.dataForm.startTime = moment().subtract('days', 1).format('YYYY-MM-DD')
        this.dataForm.endTime = moment().subtract('days', 0).format('YYYY-MM-DD')
      },
      getThisMonth () {
        this.dataForm.startTime = moment().subtract('month', 0).format('YYYY-MM') + '-01'
        this.dataForm.endTime = moment(new Date()).format('YYYY-MM-DD')
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjgetinfo/visitUrlList'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'startTime': this.dataForm.startTime,
            'endTime': this.dataForm.endTime,
            'sortType': this.dataForm.sortType
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
            url: this.$http.adornUrl('/jingjia/jjproduct/delete'),
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
          url: this.$http.adornUrl('/jingjia/jjgetinfo/exportPage'),
          method: 'get',
          responseType: 'blob',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'startTime': this.dataForm.startTime,
            'endTime': this.dataForm.endTime,
            'sortType': this.dataForm.sortType
          })
        }).then(({data}) => {
          const blob = new Blob([data])
          const fileName = '页面转换统计.xlsx'
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
