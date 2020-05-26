<template>
  <div>
    <!-- 折线图 -->
    <div id="chartmainline" style="width:600px; height:400px;"></div>
    <!-- 柱状图 -->
    <div id="chartmainbar" style="width:600px; height:400px;"></div>
  </div>
</template>
<script>
  // 引入基本模板
  let echarts = require('echarts/lib/echarts')
  // 引入柱状图组件
  require('echarts/lib/chart/bar')
  require('echarts/lib/chart/line')
  // 引入提示框和title组件
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')
  require('echarts/lib/component/legend')

  export default {
    name: 'hello',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        statisPerDayConsume: {}
      }
    },
    mounted () {
      this.initData()
    },
    methods: {
      initData () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/my/myconsume/statisPerDayConsume'),
          method: 'post',
          params: this.$http.adornParams({})
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.statisPerDayConsume = data.statisPerDayConsume
            this.drawLine()
          } else {

          }
          this.dataListLoading = false
        })
        this.dataList = [
          { trafficVolume: 122, callVolume: 444, registrationVolume: 777 },
          { trafficVolume: 222, callVolume: 444, registrationVolume: 777 },
          { trafficVolume: 322, callVolume: 222, registrationVolume: 777 },
          { trafficVolume: 392, callVolume: 222, registrationVolume: 777 },
          { trafficVolume: 522, callVolume: 222, registrationVolume: 777 },
          { trafficVolume: 822, callVolume: 222, registrationVolume: 777 },
          { trafficVolume: 922, callVolume: 222, registrationVolume: 777 }
        ]
      },
      drawLine () {
        let trafficList = []
        let callList = []
        let registrationList = []
        for (let i = 0; i < this.statisPerDayConsume.length; i++) {
          trafficList.push(this.dataList[i].trafficVolume)
          callList.push(this.dataList[i].callVolume)
          registrationList.push(this.dataList[i].registrationVolume)
        }
        let option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['消费数量', '消费金额']
          },
          grid: {
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: this.statisPerDayConsume.dayList,
            axisLabel: {// 坐标轴刻度标签的相关设置。
              interval: 0,
              rotate: '0',
              show: true
            }
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '消费数量',
              type: 'line',
              data: this.statisPerDayConsume.consumeNumList
            },
            {
              name: '消费金额',
              type: 'line',
              data: this.statisPerDayConsume.consumeMoneyList
            }
          ]
        }
        // 基于准备好的dom，初始化echarts实例
        this.chartLine = echarts.init(document.getElementById('chartmainline'))
        // 绘制图表
        this.chartLine.setOption(option)
      }
    }
  }
</script>

