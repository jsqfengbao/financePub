<template >
  <div class="jingjiahome">
  <header>
    竞价大数据监控云平台
    <span id="localtime" style="font-size:14px; position: absolute; right:60px; top:10px; "></span>
  </header>
  <div id="content">
    <div class="content_left">
      <div class="dtuplc">
        <div class="dtu">
          <div id="container" style="height: 100%;"></div>
        </div>
      </div>
      <!--DTU和PLC完-->
      <div class="online">
        <div class="title"><img src="~@/assets/img/icon01.png" /> 操作系统</div>
        <div v-loading="dataListLoading" class="online_con" id="container2" ></div>
      </div>
      <div class="industry">
        <div class="title"><img src="~@/assets/img/icon02.png" /> 页面访问情况</div>
        <div class="industry_con" id="container3"></div>
      </div>
    </div>
    <div class="content_center">
      <div class="center_top">
        <div class="title"><img src="~@/assets/img/icon03.png" /> 平台数据分布</div>
        <div class="center_top_con" id="distribution_map" >
        </div>
      </div>
      <div class="center_bot">
        <table class="panel-table"bordercolor="#0d48e0" border="1">
          <thead bgcolor="#0e4ae0" align="center" >
          <tr height="40">
            <th colspan="6"><img src="~@/assets/img/icon04.png" /> 关键词监控列表</th>
          </tr>
          </thead>
          <tbody>
          <tr class="aaa" align="center">
            <td v-for="item in factoryHeader" style="color: #00fcff; font-size: 18px; padding: 5px 0;">{{ item.categories }}</td>
          </tr>
          <tr v-for="y in keywordData" class="aaa" style="font-size: 16px;" align="center" >
            <td>{{ y.keyword }}</td>
            <td>{{ y.visitNum }}</td>
            <td>{{ y.copyNum }}</td>
            <td>{{ y.copyRatio }}</td>
            <td>0</td>
            <td width="250">
              <button class="b1 click_pop" @click="changeShow()">报警记录</button>
            </td>
          </tr>
          <tr class="aaa"  style="font-size: 18px;" align="center">
            <td colspan="6" ><router-link to="jingjia-keywordStatis">访问更多</router-link></td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="content_right">
      <div class="report">
        <div class="report1">
          <p>告警信息</p>
          <small>0条</small>
        </div>
      </div>
      <div class="news_report">
        <div class="title"><img src="~@/assets/img/icon05.png" /> 报警记录</div>
        <div class="news_list">
          <ul>
            <li  class="li02">
            </li>
          </ul>
        </div>
      </div>
      <div class="data_box">
        <div class="title"><img src="~@/assets/img/icon06.png" /> 词频统计</div>
        <div class="data_con" id="container4">
          <img src="~@/assets/img/twitter.png">
        </div>
      </div>
    </div>
  </div>
  <!--遮罩层-->
  <div class="bgPop"></div>
  <!--弹出框-->
  <div class="pop" v-show='show'>
    <div class="pop-top">
      报警记录
      <span class="pop-close" @click="hiddenPop">Ｘ</span>
    </div>
    <div class="pop-content" >
      <table class="panel-table" bordercolor="#deefff" border="1">
        <thead bgcolor="#971212" align="center" >
        <tr height="38">
          <th>页面URL</th>
          <th>访客IP</th>
          <th>访客地址</th>
          <th>报警时间</th>
        </tr>
        </thead>
        <tbody>
        <tr class="aaa" style="font-size: 16px;" align="center" >
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>

        </tbody>
      </table>
    </div>
  </div>
</div>
</template>

<script>
  import moment from 'moment'
  // 主模块
  import echarts from 'echarts/lib/echarts'
  // Vue.prototype.$echarts = echarts
  import china from 'echarts/map/json/china.json'
  echarts.registerMap('china', china)

  // import echarts from 'echarts'

  require('echarts/lib/chart/map')
  require('echarts/lib/chart/bar')
  require('echarts/lib/chart/pie')
  require('echarts/lib/chart/line')
  require('echarts/lib/chart/candlestick')
  require('echarts/lib/component/tooltip')
  require('echarts/lib/component/title')
  require('echarts/lib/component/legend')
  require('echarts/lib/component/visualMap')

  export default {
    name: 'jingjia-home',
    data () {
      return {
        show: false,
        dataListLoading: false,
        color: ['#37a2da', '#ffd85c', '#cda', '#cc85da', '#32f26c', '#adc', '#476', '#FF7F00', '#CD6839', '#C71585', '#9B30FF', '#8B4C39', '#3CB371'],
        ispName: ['移动', '联通', '电信'],
        ispData: [
          {value: 150, name: '移动'},
          {value: 150, name: '联通'},
          {value: 200, name: '电信'}
        ],
        regionMax: '',
        regionMin: '',
        operateName: [],
        operateData: [],
        regionName: [],
        regionData: [],
        todayName: [],
        todayData: [],
        thisMonthName: [],
        thisMonthData: [],
        keywordName: [],
        keywordValue: [],
        timer: null,
        chartPie: null,
        onlinePie: null,
        chinaMap: null,
        timeLine: null,
        wordCloud: null,
        factoryHeader: [
          {'categories': '关键词'},
          {'categories': '访问次数'},
          {'categories': '复制次数'},
          {'categories': '复制率'},
          {'categories': '报警次数'},
          {'categories': '操作'}
        ],
        keywordData: []
      }
    },
    created () {
      clearInterval(this.timer)
      this.timer = null
      this.setTimer()
    },
    mounted () {
      this.initData()
      setInterval(this.tick(), 1000)
      this.initChartPie()
      this.initOnlinePie()
      this.drawChinaMap()
      this.initTimeLine()
      // this.initWordCloud()
    },
    activated () {
      // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
      if (this.chartPie) {
        this.chartPie.resize()
      }
      if (this.onlinePie) {
        this.onlinePie.resize()
      }
      if (this.chinaMap) {
        this.chinaMap.resize()
      }
      if (this.timeLine) {
        this.timeLine.resize()
      }
      // if (this.wordCloud) {
      //   this.wordCloud.resize()
      // }
    },
    methods: {
      hiddenPop () {
        this.show = false
      },
      changeShow () {
        this.show = !this.show
      },
      initData () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/jingjia/homeStatis/homeStatis'),
          method: 'post',
          params: this.$http.adornParams({})
        }).then(({data}) => {
          if (data && data.code === 0) {
            console.log(data.homeData)
            this.ispData = data.homeData.ispData
            this.ispName = data.homeData.ispName
            this.operateName = data.homeData.operateName
            this.operateData = data.homeData.operateData
            this.regionName = data.homeData.regionName
            this.regionData = data.homeData.regionData
            this.todayName = data.homeData.todayName
            this.todayData = data.homeData.todayData
            this.thisMonthName = data.homeData.thisMonthName
            this.thisMonthData = data.homeData.thisMonthData
            this.regionMax = data.homeData.regionMax
            this.regionMin = data.homeData.regionMin
            // this.keywordName = data.homeData.keywordName
            // this.keywordValue = data.homeData.keywordValue
            this.initChartPie()
            this.initOnlinePie()
            this.drawChinaMap()
            this.initTimeLine()
            // this.initWordCloud()
          } else {
            this.ispData = []
          }
          this.dataListLoading = false
        })
        // 页面访问数据
        this.$http({
          url: this.$http.adornUrl('/jingjia/jjgetinfo/keywordList'),
          method: 'get',
          params: this.$http.adornParams({
            'page': 1,
            'limit': 7,
            'startTime': moment().subtract('month', 0).format('YYYY-MM') + '-01',
            'endTime': moment(new Date()).format('YYYY-MM-DD'),
            'sortType': 'visitNum'
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.keywordData = data.page.list
          } else {
            this.keywordData = []
          }
          this.dataListLoading = false
        })
      },
      randomData (arr) {
        return arr[Math.round(Math.random() * (arr.length - 1))]
      },
      colorArr (arrLength) {
        var arr = []
        for (var i = 0; i < arrLength; i++) {
          arr.push(this.randomData(this.color))
        }
        return arr
      },
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
        document.getElementById('localtime').innerHTML = this.showLocale(today)
      },
      setTimer () {
        if (this.timer == null) {
          this.timer = setInterval(() => {
            this.tick()
          }, 1000)
        }
      },
      // 环形图
      initChartPie () {
        var option = {
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            orient: 'horizontal',
            x: 'right',
            data: this.ispName,
            itemWidth: 8,
            itemHeight: 8,
            textStyle: {// 图例文字的样式
              color: '#fff',
              fontSize: 12
            }
          },
          color: this.colorArr(5),
          series: [
            {
              name: '运营商数据',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '24',
                    color: '#00fcff',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.ispData
            }
          ]
        }
        this.chartPie = echarts.init(document.getElementById('container'))
        this.chartPie.setOption(option)
        window.addEventListener('resize', () => {
          this.chartPie.resize()
        })
      },
      initOnlinePie () {
        var option = {
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            x: 'center',
            y: 'bottom',
            itemWidth: 8,
            itemHeight: 8,
            textStyle: { // 图例文字的样式
              color: '#fff',
              fontSize: 12
            },
            data: this.operateName
          },
          calculable: true,
          series: [
            {
              name: '操作系统',
              type: 'pie',
              radius: [30, 100],
              hoverAnimation: true,
              center: ['50%', '45%'],
              roseType: 'radius',
              data: this.operateData
            }
          ]
        }
        this.onlinePie = echarts.init(document.getElementById('container2'))
        this.onlinePie.setOption(option)
        // window.addEventListener('resize', () => {
        //   this.onlinePie.resize()
        // })
      },
      drawChinaMap () {
        var option = {
          title: {
            text: '加粉量',
            left: 'center',
            top: 50,
            textStyle: {
              color: '#fff'
            }
          },
          tooltip: {  // 提示框组件。
            trigger: 'item'
          },
          legend: {
            orient: 'horizontal',
            x: 'right',
            y: 'bottom',
            data: ['访问量', '加粉量'],
            selected: {
              '访问量': false
            },
            textStyle: {
              color: '#fff'
            }
          },
          dataRange: {
            padding: [0, 20, 20, 0],
            x: 'right',
            y: 'bottom',
            min: this.regionMin,
            max: this.regionMax,
            text: ['高', '低'],
            calculable: true,
            color: ['#104E8B', '#1874CD', '#1C86EE', '#20B2AA', '#fff'],
            textStyle: {
              color: '#fff'
            }
          },
          toolbox: { // 工具栏
            show: true,
            orient: 'vertical',
            x: 'right',
            y: 'center',
            feature: { // 各工具配置项。
              mark: {show: true},
              dataView: {show: true, readOnly: false},
              restore: {show: true}, // 配置项还原。
              saveAsImage: {show: true} // 保存为图片。
            }
          },
          roamController: { // 控制地图的上下左右放大缩小
            show: true,
            x: 'right',
            mapTypeControl: {
              'china': true
            }
          },
          series: [
            {
              aspectScale: 0.75,
              zoom: 1.2,
              showLegendSymbol: false,
              name: '加粉量',
              type: 'map',
              mapType: 'china',
              mapLocation: {
                x: 'left'
              },
              roam: true,
              showEffectOn: 'render',
              rippleEffect: {
                brushType: 'stroke'
              },
              hoverAnimation: true,
              label: {
                normal: {
                  show: true, // 显示省份标签
                  textStyle: {color: '#c71585'} // 省份标签字体颜色
                },
                emphasis: { // 对应的鼠标悬浮效果
                  show: true,
                  textStyle: {color: '#800080'}
                }
              },
              itemStyle: {
                normal: {
                  borderColor: '#112b3b', // 省市边界线
                  shadowColor: 'rgba(166, 230, 236, 0.6)',
                  shadowOffsetX: 0,
                  shadowOffsetY: 0,
                  shadowBlur: 120,
                  textStyle: {color: '#c71585'}, // 省份标签字体颜色
                  areaColor: '#ffffff' // 区域颜色
                },
                emphasis: {
                  borderWidth: 0.5,
                  borderColor: '#4b0082',
                  areaColor: '#ffdead'
                }

              },
              data: this.regionData
            }
          ]
        }
        var chinaMap = echarts.init(document.getElementById('distribution_map'))
        chinaMap.setOption(option)
        // window.addEventListener('resize', () => {
        //   this.chinaMap.resize()
        // })
      },
      // 时间统计图
      initTimeLine () {
        var option = {
          color: '#fff',
          title: [{
            left: 'center',
            text: '当日访问情况',
            textStyle: {
              color: '#fff',
              fontStyle: 'lighter'
            }
          }, {
            top: '50%',
            left: 'center',
            text: '当月访问情况',
            textStyle: {
              color: '#fff',
              fontStyle: 'lighter'
            }
          }],
          tooltip: {
            trigger: 'axis',
            formatter: function (datas) {
              var res = '时间：' + datas[0].name + '<br/>'
              for (var i = 0, length = datas.length; i < length; i++) {
                res += '访问量：' + datas[i].value + '<br/>'
              }
              return res
            }
          },
          xAxis: [{
            data: this.todayName,
            axisLabel: {
              textStyle: {
                color: '#fff' // 坐标值得具体的颜色
              }
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            }
          }, {
            data: this.thisMonthName,
            axisLabel: {
              textStyle: {
                color: '#fff' // 坐标值得具体的颜色
              }
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            gridIndex: 1
          }],
          yAxis: [{
            splitLine: {show: false},
            nameTextStyle: {color: '#fff'},
            axisLabel: {
              textStyle: {
                color: '#fff' // 坐标值得具体的颜色
              },
              showMinLabel: true,
              showMaxLabel: true
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            }
          }, {
            splitLine: {show: false},
            gridIndex: 1,
            nameTextStyle: {color: '#fff', backgroundColor: '#000'},
            axisLabel: {
              textStyle: {
                color: '#fff' // 坐标值得具体的颜色
              },
              showMinLabel: true,
              showMaxLabel: true
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            }
          }],
          grid: [{
            bottom: '60%',
            left: '15%'
          }, {
            top: '60%',
            left: '15%'
          }],
          series: [{
            type: 'line',
            showSymbol: false,
            lineStyle: {  // 线条样式
              normal: {
                color: '#ff00ff',
                lineStyle: {
                  color: '#ff00ff'
                }
              }
            },
            data: this.todayData
          }, {
            type: 'line',
            showSymbol: false,
            itemStyle: {
              normal: {
                color: '#00FF00',
                lineStyle: {
                  color: '#00FF00'
                }
              }
            },
            data: this.thisMonthData,
            xAxisIndex: 1,
            yAxisIndex: 1
          }]
        }
        var timeLine = echarts.init(document.getElementById('container3'))
        timeLine.setOption(option)
      }
      // 词云图wordCloud
      // initWordCloud () {
      //   var words = []
      //   for (var i = 0, j = 0; i < this.keywordName.length; i++, j++) {
      //     words.push([this.keywordName[i], this.keywordValue[j]])
      //   }
      //   console.log(words)
      //   var option = {
      //     tooltip: {
      //       show: true,
      //       formatter: function (item) {
      //         return '关键词:' + item[0] + ',出现个数:' + item[1] + '<br>' + '词云图'
      //       }
      //     },
      //     gridSize: 6, // 密集程度 数字越小越密集
      //     maxFontSize: 16,
      //     minFontSize: 8,
      //     fontSizeFactor: 20,
      //     list: words,
      //     color: '#15a4fa',
      //     backgroundColor: '#000',
      //     // imageShape: './static/img/twitter.png',
      //     ellipticity: 1,
      //     cardioid: 'square'
      //
      //   }
      //   var wc = new Js2WordCloud(document.getElementById('container4'))
      //   wc.showLoading({
      //     backgroundColor: '#000',
      //     text: '正在加载报道...',
      //     effect: 'spin'
      //   })
      //   setTimeout(function () {
      //     wc.hideLoading()
      //     wc.setOption(option)
      //   }, 2000)
      // }
    },
    destroyed () {
      // 每次离开当前界面时，清除定时器
      clearInterval(this.timer)
      this.timer = null
    }

  }
</script>

<style scoped>
  @import "../../../assets/css/style.css";
</style>
