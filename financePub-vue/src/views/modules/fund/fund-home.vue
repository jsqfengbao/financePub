<template>
    <div class="fund-home">
      <div class="banner-caption clearfix">
        <div id="title" style="height: 88.8px;">
          <div class="caption-title clearfix">
            <i class="title-left"><img src="./img/title-left.png"></i>
            <p class="title-left-title-font">收支数据统计</p>
            <i class="title-left"><img src="./img/title-right.png"></i>
          </div>
          <div class="title-date" style="height: 40px;">
            <p id="DateTime" style="height: 40px;text-align: center;" v-html="DateTime"></p>
          </div>
        </div>
      </div>
      <div id="main" style="height: 799.2px;">
        <div class="col-md-12">
          <div class="row">
            <div id="title01" class="col-xs-12 col-sm-12 col-md-3">
              <div class="data-box1 clearfix" id="box01" style="height: 519.35px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title">
                  <b class="data-title-left">[</b>
                  <span>今日数据分析</span>
                  <b class="data-title-right">]</b>
                </div>
                <div class="total-mn" id="total-mn1" style="height: 10.38px;">
                  <span>本月支出分析</span>
                </div>
                <div id="box1_top">
                  <div id="barOne" class="box-echart box-bottom" _echarts_instance_="ec_1562842953153" style="width: 428.16px; height: 197.22px; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;">
                  </div>
                </div>
                <div class="total-mn" id="total-mn2" style="height: 10.38px;">
                  <span>支出与预算</span>
                </div>
                <div id="box1_bottom">
                  <div id="box4" class="box-echart" _echarts_instance_="ec_1562842953154" style="width: 428.16px; height: 181.65px; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;"><div style="position: relative; overflow: hidden; width: 428px; height: 182px; padding: 0px; margin: 0px; border-width: 0px;"><canvas width="428" height="182" data-zr-dom-id="zr_0" style="position: absolute; left: 0px; top: 0px; width: 428px; height: 182px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;"></canvas></div><div></div></div>
                </div>
                <ul class="live-box" id="live-box" style="height: 25.95px;">
                  <li style="margin-right: 2%;">
                    <p>年支出总额</p>
                    <p class="live-box-font1"><span style="font-size: 20px;">{{thisYearTotalPayMoney}}</span> 元</p>
                  </li>
                  <li>
                    <p>年支出完成率</p>
                    <p class="live-box-font1"><span style="font-size: 20px;">{{thisYearTotalPayBudgetRatio}}</span></p>
                  </li>
                </ul>
              </div>
              <div class="data-box1" id="box8-box" style="margin-top: 30px; height: 239.7px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title">
                  <b class="data-title-left">[</b>
                  <span>本月收入类别</span>
                  <b class="data-title-right">]</b>
                </div>
                <div id="box8" class="box-echart" _echarts_instance_="ec_1562842953155" style="width: 432px; height: 208.8px; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;">

                </div>
              </div>
            </div>
            <div id="title02" class="col-xs-12 col-sm-12 col-md-6 panel-top panel-bottom">
              <div class="data-box1 box1-back" id="box02" style="height: 495.38px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title">
                  <b class="data-title-left">[</b>
                  <span>当年收支统计</span>
                  <b class="data-title-right">]</b>
                </div>
                <ul class="data-box1-data clearfix">
                  <p class="data-box1-font1">当年收入总额 <span style="float:right">当年支出总额</span> </p>
                  <li class="data-box1-panel">{{this.thisYearTotalIncomeMoney}}</li>
                  <li class="data-box1-font1" style="padding-top: 1rem;">元</li>

                  <li class="data-box1-font1" style="padding-top: 1rem;float:right">元</li>
                  <li class="data-box1-panel" style="float:right">{{this.thisYearTotalPayMoney}}</li>
                </ul>
                <div id="box1" class="box-echart-china" _echarts_instance_="ec_1562842953159" style="width: 829.8px; height: 405.9px; -webkit-tap-highlight-color: transparent; user-select: none; background: transparent;"><div style="position: relative; overflow: hidden; width: 830px; height: 406px; padding: 0px; margin: 0px; border-width: 0px; cursor: default;"><canvas width="830" height="406" data-zr-dom-id="zr_0" style="position: absolute; left: 0px; top: 0px; width: 830px; height: 406px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;"></canvas></div><div></div></div>
              </div>
              <div class="data-box1" id="box9-box" style="margin-top: 30px; height: 239.7px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title" style="width: 227px">
                  <b class="data-title-left">[</b>
                  <span>当年收支列表</span>
                  <b class="data-title-right">]</b>
                </div>
                <div id="box9" class="box9-1">
                  <table class="panel-table">
                    <thead>
                    <tr>
                      <th class="ng-binding ng-scope">月份</th>
                      <th class="ng-binding ng-scope">当月收入</th>
                      <th class="ng-binding ng-scope">当月支出</th>
                      <th class="ng-binding ng-scope">支出收入率</th>
                      <th class="ng-binding ng-scope">本月余额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="ng-scope" v-for="income in reverseSum">
                      <td class="ng-binding" >{{income.name}}</td>
                      <td class="ng-binding">{{income.value}}</td>
                      <td class="ng-binding" v-for="pay in thisYearPayMonthData" v-if="pay.name === income.name">{{pay.value}}</td>
                      <td class="ng-binding" v-for="payIncome in thisYearPayIncomeRatio" v-if="payIncome.name === income.name">{{payIncome.value}}</td>
                      <td class="ng-binding">{{thisMonthRemain(income.name) | numFilter}}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            <div id="title03" class="col-xs-12 col-sm-12 col-md-3">
              <div class="data-box1" id="box03" style="height: 379.525px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title" style="width: 238px">
                  <b class="data-title-left">[</b>
                  <span>本月支出类别统计</span>
                  <b class="data-title-right">]</b>
                </div>

                <div id="box3" class="box-echart tabcontent" _echarts_instance_="ec_1562842953156" style="width: 450px; height: 304px; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent; display: block;">

                </div>
              </div>
              <div class="data-box1" id="box04" style="margin-top: 30px; height: 379.525px;">
                <i class="topL"></i>
                <i class="topR"></i>
                <i class="bottomL"></i>
                <i class="bottomR"></i>
                <div class="data-title" style="width: 238px">
                  <b class="data-title-left">[</b>
                  <span>本月收支前五项</span>
                  <b class="data-title-right">]</b>
                </div>
                <div id="box5" class="box-echart" _echarts_instance_="ec_1562842953158" style="width: 432px; height: 363.84px; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script type="text/ecmascript-6">
  // 主模块
  import echarts from 'echarts/lib/echarts'
  require('echarts/lib/chart/bar')
  require('echarts/lib/chart/pie')
  require('echarts/lib/chart/line')
  require('echarts/lib/chart/funnel')
  require('echarts/lib/chart/candlestick')
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')
  require('echarts/lib/component/legend')
  require('echarts/lib/component/visualMap')
  export default {
    name: 'fund-home',
    data () {
      return {
        DateTime: '',
        dataListLoading: false,
        thisMonthDayName: [],
        thisYearMonthName: [],
        thisMonthPayDayName: [],
        thisMonthPayDayData: [],
        thisMonthIncomeDayName: [],
        thisMonthIncomeDayData: [],
        thisMonthPayTypeName: [],
        thisMonthPayTypeData: [],
        thisMonthIncomeTypeName: [],
        thisMonthIncomeTypeData: [],
        thisYearPayMonthName: [],
        thisYearPayMonthData: [],
        thisYearIncomeMonthName: [],
        thisYearIncomeMonthData: [],
        thisMonthPayBudgetTypeName: [],
        thisMonthPayBudgetTypeData: [],
        thisYearTotalBudgetIncomeMoney: [],
        thisYearTotalBudgetPayMoney: [],
        thisYearTotalIncomeMoney: [],
        thisYearTotalPayMoney: [],
        thisYearTotalIncomeBudgetRatio: [],
        thisYearTotalPayBudgetRatio: [],
        thisYearPayIncomeRatio: [],
        thisMonthPayHead5Data: [],
        thisMonthIncomeHead5Data: [],
        thisYearPayIncomeData: []
      }
    },
    mounted () {
      this.initData()
      setInterval(this.tick(), 1000)
      this.initBar()
      this.initPayLine()
      this.initCenter()
      this.initPie()
      this.initIncomeLine()
      this.initHead5()
    },
    created () {
      clearInterval(this.timer)
      this.timer = null
      this.setTimer()
    },
    filters: {
      numFilter (value) {
        // 截取当前数据到小数点后三位
        let tempVal = parseFloat(value).toFixed(3)
        let realVal = tempVal.substring(0, tempVal.length - 1)
        return realVal
      }
    },
    computed: {
      reverseSum () {
        return this.thisYearIncomeMonthData.reverse()
      },
      thisMonthRemain: function () {
        return function (month) {
          let result = 0
          let me = this
          me.thisYearIncomeMonthData.forEach((income) => {
            me.thisYearPayMonthData.forEach((pay) => {
              if (income.name === month && pay.name === month) {
                result = income.value - pay.value
              }
            })
          })
          return result
        }
      }
    },
    methods: {
      showLocale (objD) {
        var str, colorhead, colorfoot
        var yy = objD.getYear()
        if (yy < 1900) yy = yy + 1900
        var MM = objD.getMonth() + 1
        if (MM < 10) MM = '0' + MM
        var dd = objD.getDate()
        if (dd < 10) dd = '0' + dd
        var hh = objD.getHours()
        if (hh < 10) hh = '0' + hh
        var mm = objD.getMinutes()
        if (mm < 10) mm = '0' + mm
        var ss = objD.getSeconds()
        if (ss < 10) ss = '0' + ss
        var ww = objD.getDay()
        if (ww === 0) colorhead = '<font color="#ffffff">'
        if (ww > 0 && ww < 6) colorhead = '<font color="#ffffff">'
        if (ww === 6) colorhead = '<font color="#ffffff">'
        if (ww === 0) ww = '星期日'
        if (ww === 1) ww = '星期一'
        if (ww === 2) ww = '星期二'
        if (ww === 3) ww = '星期三'
        if (ww === 4) ww = '星期四'
        if (ww === 5) ww = '星期五'
        if (ww === 6) ww = '星期六'
        colorfoot = '</font>'
        str = colorhead + yy + '-' + MM + '-' + dd + ' ' + hh + ':' + mm + ':' + ss + '  ' + ww + colorfoot
        return (str)
      },
      tick () {
        var today
        today = new Date()
        this.DateTime = this.showLocale(today)
      },
      setTimer () {
        if (this.timer == null) {
          this.timer = setInterval(() => {
            this.tick()
          }, 1000)
        }
      },
      initData () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/fund/statis/fundHomeStatis'),
          method: 'post',
          params: this.$http.adornParams({})
        }).then(({data}) => {
          if (data && data.code === 0) {
            console.log(data.homeData)
            this.thisMonthDayName = data.homeData.thisMonthDayName
            this.thisYearMonthName = data.homeData.thisYearMonthName
            this.thisMonthPayDayName = data.homeData.thisMonthPayDayName
            this.thisMonthPayDayData = data.homeData.thisMonthPayDayData
            this.thisMonthIncomeDayName = data.homeData.thisMonthIncomeDayName
            this.thisMonthIncomeDayData = data.homeData.thisMonthIncomeDayData
            this.thisMonthPayTypeName = data.homeData.thisMonthPayTypeName
            this.thisMonthPayTypeData = data.homeData.thisMonthPayTypeData
            this.thisMonthIncomeTypeName = data.homeData.thisMonthIncomeTypeName
            this.thisMonthIncomeTypeData = data.homeData.thisMonthIncomeTypeData
            this.thisYearPayMonthName = data.homeData.thisYearPayMonthName
            this.thisYearPayMonthData = data.homeData.thisYearPayMonthData
            this.thisYearIncomeMonthName = data.homeData.thisYearIncomeMonthName
            this.thisYearIncomeMonthData = data.homeData.thisYearIncomeMonthData
            this.thisMonthPayBudgetTypeName = data.homeData.thisMonthPayBudgetTypeName
            this.thisMonthPayBudgetTypeData = data.homeData.thisMonthPayBudgetTypeData
            this.thisYearTotalBudgetIncomeMoney = data.homeData.thisYearTotalBudgetIncomeMoney
            this.thisYearTotalBudgetPayMoney = data.homeData.thisYearTotalBudgetPayMoney
            this.thisYearTotalIncomeMoney = data.homeData.thisYearTotalIncomeMoney
            this.thisYearTotalPayMoney = data.homeData.thisYearTotalPayMoney
            this.thisYearTotalIncomeBudgetRatio = data.homeData.thisYearTotalIncomeBudgetRatio
            this.thisYearTotalPayBudgetRatio = data.homeData.thisYearTotalPayBudgetRatio
            this.thisYearPayIncomeRatio = data.homeData.thisYearPayIncomeRatio
            this.thisMonthPayHead5Data = data.homeData.thisMonthPayHead5Data
            this.thisMonthIncomeHead5Data = data.homeData.thisMonthIncomeHead5Data

            this.initBar()
            this.initPayLine()
            this.initCenter()
            this.initPie()
            this.initIncomeLine()
            this.initHead5()
          } else {
            this.ispData = []
          }
          this.dataListLoading = false
        })
      },
      initBar () {
        var option = {
          color: ['#80FF80', '#FF8096', '#800080', '#4472C5', '#ED7C30'],
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            textStyle: {
              color: '#fff'
            },
            data: ['支出', '收入']
          },
          width: '75%',
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
            show: false
          },
          xAxis: [
            {
              type: 'category',
              data: this.thisMonthDayName,
              axisLine: {
                lineStyle: {
                  color: '#fff'
                }
              },
              splitLine: {
                show: false
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              name: '支出',
              position: 'left',
              axisLine: {
                lineStyle: {
                  color: '#fff'
                }
              },
              splitLine: {
                show: false
              }
            },
            {
              type: 'value',
              name: '收入',
              position: 'right',
              axisLine: {
                lineStyle: {
                  color: '#fff'
                }
              },
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              name: '支出',
              type: 'bar',
              yAxisIndex: 0,
              data: this.thisMonthPayDayData
            },
            {
              yAxisIndex: 1,
              name: '收入',
              type: 'bar',
              data: this.thisMonthIncomeDayData
            }
          ]
        }
        var charBar = echarts.init(document.getElementById('barOne'))
        charBar.setOption(option)
      },
      initPayLine () {
        var option = {
          color: ['#4472C5', '#ED7C30', '#80FF80', '#FF8096', '#800080'],
          width: '75%',
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            textStyle: {
              color: '#fff'
            },
            data: ['月预算', '月支出']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
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
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            data: this.thisMonthPayTypeName
          },
          yAxis: [{
            type: 'value',
            name: '支出',
            position: 'left',
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              show: false
            }
          }, {
            type: 'value',
            name: '预算',
            position: 'right',
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            }
          }
          ],
          series: [
            {
              name: '月预算',
              type: 'line',
              yAxisIndex: 1,
              data: this.thisMonthPayBudgetTypeData
            },
            {
              name: '月支出',
              type: 'line',
              data: this.thisMonthPayTypeData
            }
          ]
        }
        var charLine = echarts.init(document.getElementById('box4'))
        charLine.setOption(option)
      },
      initCenter () {
        var option = {
          tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(255,255,255,0.1)',
            axisPointer: {
              type: 'shadow',
              label: {
                show: true,
                backgroundColor: '#7B7DDC'
              }
            }
          },
          legend: {
            data: ['收入', '支出', '消费率'],
            textStyle: {
              color: '#B4B4B4'
            },
            top: '17%'
          },
          grid: {
            x: '12%',
            width: '70%',
            y: '22%'
          },
          xAxis: {
            type: 'category',
            data: this.thisYearMonthName,
            axisLine: {
              lineStyle: {
                color: '#B4B4B4'
              }
            },
            axisLabel: {
              fontSize: 15,
              color: '#d0d0d0',
              margin: 8,
              interval: 0,
              formatter: function (val) {
                return val.split('').join('\n') + '月'
              }
            },
            axisTick: {
              show: false
            }
          },
          yAxis: [{
            type: 'value',
            position: 'left',
            splitLine: {show: false},
            axisLine: {
              lineStyle: {
                color: '#B4B4B4'
              }
            },
            axisLabel: {
              formatter: '{value} '
            }
          },
          {
            type: 'value',
            position: 'right',
            splitLine: {show: false},
            axisLine: {
              lineStyle: {
                color: '#B4B4B4'
              }
            },
            axisLabel: {
              formatter: '{value} '
            },
            max: 1,
            min: 0
          }],
          series: [
            {
              name: '收入',
              yAxisIndex: 0,
              type: 'bar',
              barGap: '30%',
              barWidth: 10,
              // stack: '1',
              itemStyle: {
                normal: {
                  color: '#956FD4'
                }
              },
              data: this.thisYearIncomeMonthData
            },
            {
              name: '支出',
              type: 'bar',
              yAxisIndex: 0,
              barGap: '100%',
              barWidth: 10,
              // stack: '1',
              itemStyle: {
                normal: {
                  color: '#E66B1A'
                }
              },
              z: -12,
              data: this.thisYearPayMonthData
            },
            {
              name: '消费率',
              type: 'line',
              smooth: true,
              showAllSymbol: true,
              symbol: 'emptyCircle',
              symbolSize: 8,
              yAxisIndex: 1,
              itemStyle: {
                normal: {
                  color: '#F02FC2'
                }
              },
              data: this.thisYearPayIncomeRatio
            }
          ]
        }
        var center = echarts.init(document.getElementById('box1'))
        center.setOption(option)
      },
      initPie () {
        var option = {
          color: ['#4472C5', '#ED7C30', '#80FF80', '#FF8096', '#800080'],
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            bottom: 'bottom',
            textStyle: {
              color: '#fff'
            },
            data: this.thisMonthPayTypeName
          },
          series: [
            {
              name: '本月支出类型',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              label: {
                show: true,
                formatter: '{b}: {d}%'
              },
              data: this.thisMonthPayTypeData,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        }
        var pie = echarts.init(document.getElementById('box3'))
        pie.setOption(option)
      },
      initIncomeLine () {
        var option = {
          color: ['#80FF80', '#FF8096', '#800080', '#4472C5', '#ED7C30'],
          width: '75%',
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            textStyle: {
              color: '#fff'
            },
            data: ['月收入']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
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
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            data: this.thisMonthIncomeTypeName
          },
          yAxis: [{
            type: 'value',
            name: '收入',
            position: 'left',
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              show: false
            }
          }
          ],
          series: [
            {
              name: '月收入',
              type: 'line',
              data: this.thisMonthIncomeTypeData
            }
          ]
        }
        var charLine = echarts.init(document.getElementById('box8'))
        charLine.setOption(option)
      },
      initHead5 () {
        var option = {
          color: ['#00BFFF', '#006400', '#FFD700', '#FF8C00', '#F08080'],
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c}%'
          },
          toolbox: {
            orient: 'vertical',
            top: 'center',
            feature: {
              dataView: {readOnly: false},
              restore: {},
              saveAsImage: {}
            }
          },
          calculable: true,
          series: [
            {
              name: '漏斗图',
              type: 'funnel',
              width: '40%',
              height: '45%',
              left: '25%',
              top: '5%',
              label: {
                normal: {
                  position: 'left'
                }
              },
              data: this.thisMonthPayHead5Data
            },
            {
              name: '金字塔',
              type: 'funnel',
              width: '40%',
              height: '45%',
              left: '25%',
              top: '50%',
              sort: 'ascending',
              label: {
                normal: {
                  position: 'left'
                }
              },
              data: this.thisMonthIncomeHead5Data
            }
          ]
        }
        var head5 = echarts.init(document.getElementById('box5'))
        head5.setOption(option)
      }
    }
  }
</script>

<style scoped>
  @import "./css/content1.0.css";

</style>
