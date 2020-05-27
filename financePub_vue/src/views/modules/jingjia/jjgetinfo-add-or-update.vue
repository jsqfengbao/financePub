<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="sysUserId">
      <el-input v-model="dataForm.sysUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="操作类型:1,仅访问 2，复制，3，点击" prop="operateType">
      <el-input v-model="dataForm.operateType" placeholder="操作类型:1,仅访问 2，复制，3，点击"></el-input>
    </el-form-item>
    <el-form-item label="关键字" prop="keyword">
      <el-input v-model="dataForm.keyword" placeholder="关键字"></el-input>
    </el-form-item>
    <el-form-item label="访问链接" prop="visitUrl">
      <el-input v-model="dataForm.visitUrl" placeholder="访问链接"></el-input>
    </el-form-item>
    <el-form-item label="" prop="visitIp">
      <el-input v-model="dataForm.visitIp" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="国家" prop="country">
      <el-input v-model="dataForm.country" placeholder="国家"></el-input>
    </el-form-item>
    <el-form-item label="区域" prop="area">
      <el-input v-model="dataForm.area" placeholder="区域"></el-input>
    </el-form-item>
    <el-form-item label="省" prop="region">
      <el-input v-model="dataForm.region" placeholder="省"></el-input>
    </el-form-item>
    <el-form-item label="市" prop="city">
      <el-input v-model="dataForm.city" placeholder="市"></el-input>
    </el-form-item>
    <el-form-item label="区" prop="county">
      <el-input v-model="dataForm.county" placeholder="区"></el-input>
    </el-form-item>
    <el-form-item label="运营商" prop="isp">
      <el-input v-model="dataForm.isp" placeholder="运营商"></el-input>
    </el-form-item>
    <el-form-item label="国家编码" prop="countryId">
      <el-input v-model="dataForm.countryId" placeholder="国家编码"></el-input>
    </el-form-item>
    <el-form-item label="区域ID" prop="areaId">
      <el-input v-model="dataForm.areaId" placeholder="区域ID"></el-input>
    </el-form-item>
    <el-form-item label="省ID" prop="regionId">
      <el-input v-model="dataForm.regionId" placeholder="省ID"></el-input>
    </el-form-item>
    <el-form-item label="市ID" prop="cityId">
      <el-input v-model="dataForm.cityId" placeholder="市ID"></el-input>
    </el-form-item>
    <el-form-item label="区ID" prop="countyId">
      <el-input v-model="dataForm.countyId" placeholder="区ID"></el-input>
    </el-form-item>
    <el-form-item label="运营商ID" prop="ispId">
      <el-input v-model="dataForm.ispId" placeholder="运营商ID"></el-input>
    </el-form-item>
    <el-form-item label="操作系统名称" prop="systemName">
      <el-input v-model="dataForm.systemName" placeholder="操作系统名称"></el-input>
    </el-form-item>
    <el-form-item label="浏览器名称" prop="appName">
      <el-input v-model="dataForm.appName" placeholder="浏览器名称"></el-input>
    </el-form-item>
    <el-form-item label="浏览器版本号" prop="appVersion">
      <el-input v-model="dataForm.appVersion" placeholder="浏览器版本号"></el-input>
    </el-form-item>
    <el-form-item label="用户浏览器是否启用了cookie" prop="cookieEnabled">
      <el-input v-model="dataForm.cookieEnabled" placeholder="用户浏览器是否启用了cookie"></el-input>
    </el-form-item>
    <el-form-item label="用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）" prop="cpuClass">
      <el-input v-model="dataForm.cpuClass" placeholder="用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）"></el-input>
    </el-form-item>
    <el-form-item label=" 浏览器支持的所有MIME类型的数组" prop="mimeType">
      <el-input v-model="dataForm.mimeType" placeholder=" 浏览器支持的所有MIME类型的数组"></el-input>
    </el-form-item>
    <el-form-item label="浏览器正在运行的操作系统平台，包括Win16(windows3.x) " prop="platform">
      <el-input v-model="dataForm.platform" placeholder="浏览器正在运行的操作系统平台，包括Win16(windows3.x) "></el-input>
    </el-form-item>
    <el-form-item label="安装在浏览器上的所有插件的数组" prop="plugins">
      <el-input v-model="dataForm.plugins" placeholder="安装在浏览器上的所有插件的数组"></el-input>
    </el-form-item>
    <el-form-item label="用户在自己的操作系统上设置的语言（火狐没有）" prop="userLanguage">
      <el-input v-model="dataForm.userLanguage" placeholder="用户在自己的操作系统上设置的语言（火狐没有）"></el-input>
    </el-form-item>
    <el-form-item label="包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform" prop="userAgent">
      <el-input v-model="dataForm.userAgent" placeholder="包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform"></el-input>
    </el-form-item>
    <el-form-item label="用户操作系统支持的默认语言（火狐没有）" prop="systemLanguage">
      <el-input v-model="dataForm.systemLanguage" placeholder="用户操作系统支持的默认语言（火狐没有）"></el-input>
    </el-form-item>
    <el-form-item label="浏览器类型" prop="browserType">
      <el-input v-model="dataForm.browserType" placeholder="浏览器类型"></el-input>
    </el-form-item>
    <el-form-item label="与浏览器相关的内部代码名" prop="appCodeName">
      <el-input v-model="dataForm.appCodeName" placeholder="与浏览器相关的内部代码名"></el-input>
    </el-form-item>
    <el-form-item label="辅版本号（通常应用于浏览器的补丁或服务包)" prop="appMinorVersion">
      <el-input v-model="dataForm.appMinorVersion" placeholder="辅版本号（通常应用于浏览器的补丁或服务包)"></el-input>
    </el-form-item>
    <el-form-item label="浏览器支持的语言 （IE没有）" prop="language">
      <el-input v-model="dataForm.language" placeholder="浏览器支持的语言 （IE没有）"></el-input>
    </el-form-item>
    <el-form-item label="返回浏览器是否处于在线模式（IE4以上版本）" prop="onLine">
      <el-input v-model="dataForm.onLine" placeholder="返回浏览器是否处于在线模式（IE4以上版本）"></el-input>
    </el-form-item>
    <el-form-item label="浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）" prop="oscpu">
      <el-input v-model="dataForm.oscpu" placeholder="浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）"></el-input>
    </el-form-item>
    <el-form-item label="浏览器的产品名（IE没有）" prop="product">
      <el-input v-model="dataForm.product" placeholder="浏览器的产品名（IE没有）"></el-input>
    </el-form-item>
    <el-form-item label="关于浏览器更多信息（IE没有）" prop="productSub">
      <el-input v-model="dataForm.productSub" placeholder="关于浏览器更多信息（IE没有）"></el-input>
    </el-form-item>
    <el-form-item label="浏览器支持的加密类型（IE没有）" prop="securityPolicy">
      <el-input v-model="dataForm.securityPolicy" placeholder="浏览器支持的加密类型（IE没有）"></el-input>
    </el-form-item>
    <el-form-item label="返回一个UserProfile对象，它存储用户的个人信息（火狐没有）" prop="userProfile">
      <el-input v-model="dataForm.userProfile" placeholder="返回一个UserProfile对象，它存储用户的个人信息（火狐没有）"></el-input>
    </el-form-item>
    <el-form-item label="浏览器厂商名称（IE、火狐没有）" prop="vender">
      <el-input v-model="dataForm.vender" placeholder="浏览器厂商名称（IE、火狐没有）"></el-input>
    </el-form-item>
    <el-form-item label="关于浏览器厂商更多的信息 " prop="vendorSub">
      <el-input v-model="dataForm.vendorSub" placeholder="关于浏览器厂商更多的信息 "></el-input>
    </el-form-item>
    <el-form-item label="访客地址" prop="visitAddress">
      <el-input v-model="dataForm.visitAddress" placeholder="访客地址"></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="是否有效" prop="enabled">
      <el-input v-model="dataForm.enabled" placeholder="是否有效"></el-input>
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
          operateType: '',
          keyword: '',
          visitUrl: '',
          visitIp: '',
          country: '',
          area: '',
          region: '',
          city: '',
          county: '',
          isp: '',
          countryId: '',
          areaId: '',
          regionId: '',
          cityId: '',
          countyId: '',
          ispId: '',
          systemName: '',
          appName: '',
          appVersion: '',
          cookieEnabled: '',
          cpuClass: '',
          mimeType: '',
          platform: '',
          plugins: '',
          userLanguage: '',
          userAgent: '',
          systemLanguage: '',
          browserType: '',
          appCodeName: '',
          appMinorVersion: '',
          language: '',
          onLine: '',
          oscpu: '',
          product: '',
          productSub: '',
          securityPolicy: '',
          userProfile: '',
          vender: '',
          vendorSub: '',
          visitAddress: '',
          createTime: '',
          updateTime: '',
          enabled: ''
        },
        dataRule: {
          sysUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          operateType: [
            { required: true, message: '操作类型:1,仅访问 2，复制，3，点击不能为空', trigger: 'blur' }
          ],
          keyword: [
            { required: true, message: '关键字不能为空', trigger: 'blur' }
          ],
          visitUrl: [
            { required: true, message: '访问链接不能为空', trigger: 'blur' }
          ],
          visitIp: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          country: [
            { required: true, message: '国家不能为空', trigger: 'blur' }
          ],
          area: [
            { required: true, message: '区域不能为空', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '省不能为空', trigger: 'blur' }
          ],
          city: [
            { required: true, message: '市不能为空', trigger: 'blur' }
          ],
          county: [
            { required: true, message: '区不能为空', trigger: 'blur' }
          ],
          isp: [
            { required: true, message: '运营商不能为空', trigger: 'blur' }
          ],
          countryId: [
            { required: true, message: '国家编码不能为空', trigger: 'blur' }
          ],
          areaId: [
            { required: true, message: '区域ID不能为空', trigger: 'blur' }
          ],
          regionId: [
            { required: true, message: '省ID不能为空', trigger: 'blur' }
          ],
          cityId: [
            { required: true, message: '市ID不能为空', trigger: 'blur' }
          ],
          countyId: [
            { required: true, message: '区ID不能为空', trigger: 'blur' }
          ],
          ispId: [
            { required: true, message: '运营商ID不能为空', trigger: 'blur' }
          ],
          systemName: [
            { required: true, message: '操作系统名称不能为空', trigger: 'blur' }
          ],
          appName: [
            { required: true, message: '浏览器名称不能为空', trigger: 'blur' }
          ],
          appVersion: [
            { required: true, message: '浏览器版本号不能为空', trigger: 'blur' }
          ],
          cookieEnabled: [
            { required: true, message: '用户浏览器是否启用了cookie不能为空', trigger: 'blur' }
          ],
          cpuClass: [
            { required: true, message: '用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）不能为空', trigger: 'blur' }
          ],
          mimeType: [
            { required: true, message: ' 浏览器支持的所有MIME类型的数组不能为空', trigger: 'blur' }
          ],
          platform: [
            { required: true, message: '浏览器正在运行的操作系统平台，包括Win16(windows3.x) 不能为空', trigger: 'blur' }
          ],
          plugins: [
            { required: true, message: '安装在浏览器上的所有插件的数组不能为空', trigger: 'blur' }
          ],
          userLanguage: [
            { required: true, message: '用户在自己的操作系统上设置的语言（火狐没有）不能为空', trigger: 'blur' }
          ],
          userAgent: [
            { required: true, message: '包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform不能为空', trigger: 'blur' }
          ],
          systemLanguage: [
            { required: true, message: '用户操作系统支持的默认语言（火狐没有）不能为空', trigger: 'blur' }
          ],
          browserType: [
            { required: true, message: '浏览器类型不能为空', trigger: 'blur' }
          ],
          appCodeName: [
            { required: true, message: '与浏览器相关的内部代码名不能为空', trigger: 'blur' }
          ],
          appMinorVersion: [
            { required: true, message: '辅版本号（通常应用于浏览器的补丁或服务包)不能为空', trigger: 'blur' }
          ],
          language: [
            { required: true, message: '浏览器支持的语言 （IE没有）不能为空', trigger: 'blur' }
          ],
          onLine: [
            { required: true, message: '返回浏览器是否处于在线模式（IE4以上版本）不能为空', trigger: 'blur' }
          ],
          oscpu: [
            { required: true, message: '浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）不能为空', trigger: 'blur' }
          ],
          product: [
            { required: true, message: '浏览器的产品名（IE没有）不能为空', trigger: 'blur' }
          ],
          productSub: [
            { required: true, message: '关于浏览器更多信息（IE没有）不能为空', trigger: 'blur' }
          ],
          securityPolicy: [
            { required: true, message: '浏览器支持的加密类型（IE没有）不能为空', trigger: 'blur' }
          ],
          userProfile: [
            { required: true, message: '返回一个UserProfile对象，它存储用户的个人信息（火狐没有）不能为空', trigger: 'blur' }
          ],
          vender: [
            { required: true, message: '浏览器厂商名称（IE、火狐没有）不能为空', trigger: 'blur' }
          ],
          vendorSub: [
            { required: true, message: '关于浏览器厂商更多的信息 不能为空', trigger: 'blur' }
          ],
          visitAddress: [
            { required: true, message: '访客地址不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          enabled: [
            { required: true, message: '是否有效不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/jingjia/jjgetinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sysUserId = data.jjgetinfo.sysUserId
                this.dataForm.operateType = data.jjgetinfo.operateType
                this.dataForm.keyword = data.jjgetinfo.keyword
                this.dataForm.visitUrl = data.jjgetinfo.visitUrl
                this.dataForm.visitIp = data.jjgetinfo.visitIp
                this.dataForm.country = data.jjgetinfo.country
                this.dataForm.area = data.jjgetinfo.area
                this.dataForm.region = data.jjgetinfo.region
                this.dataForm.city = data.jjgetinfo.city
                this.dataForm.county = data.jjgetinfo.county
                this.dataForm.isp = data.jjgetinfo.isp
                this.dataForm.countryId = data.jjgetinfo.countryId
                this.dataForm.areaId = data.jjgetinfo.areaId
                this.dataForm.regionId = data.jjgetinfo.regionId
                this.dataForm.cityId = data.jjgetinfo.cityId
                this.dataForm.countyId = data.jjgetinfo.countyId
                this.dataForm.ispId = data.jjgetinfo.ispId
                this.dataForm.systemName = data.jjgetinfo.systemName
                this.dataForm.appName = data.jjgetinfo.appName
                this.dataForm.appVersion = data.jjgetinfo.appVersion
                this.dataForm.cookieEnabled = data.jjgetinfo.cookieEnabled
                this.dataForm.cpuClass = data.jjgetinfo.cpuClass
                this.dataForm.mimeType = data.jjgetinfo.mimeType
                this.dataForm.platform = data.jjgetinfo.platform
                this.dataForm.plugins = data.jjgetinfo.plugins
                this.dataForm.userLanguage = data.jjgetinfo.userLanguage
                this.dataForm.userAgent = data.jjgetinfo.userAgent
                this.dataForm.systemLanguage = data.jjgetinfo.systemLanguage
                this.dataForm.browserType = data.jjgetinfo.browserType
                this.dataForm.appCodeName = data.jjgetinfo.appCodeName
                this.dataForm.appMinorVersion = data.jjgetinfo.appMinorVersion
                this.dataForm.language = data.jjgetinfo.language
                this.dataForm.onLine = data.jjgetinfo.onLine
                this.dataForm.oscpu = data.jjgetinfo.oscpu
                this.dataForm.product = data.jjgetinfo.product
                this.dataForm.productSub = data.jjgetinfo.productSub
                this.dataForm.securityPolicy = data.jjgetinfo.securityPolicy
                this.dataForm.userProfile = data.jjgetinfo.userProfile
                this.dataForm.vender = data.jjgetinfo.vender
                this.dataForm.vendorSub = data.jjgetinfo.vendorSub
                this.dataForm.visitAddress = data.jjgetinfo.visitAddress
                this.dataForm.createTime = data.jjgetinfo.createTime
                this.dataForm.updateTime = data.jjgetinfo.updateTime
                this.dataForm.enabled = data.jjgetinfo.enabled
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
              url: this.$http.adornUrl(`/jingjia/jjgetinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sysUserId': this.dataForm.sysUserId,
                'operateType': this.dataForm.operateType,
                'keyword': this.dataForm.keyword,
                'visitUrl': this.dataForm.visitUrl,
                'visitIp': this.dataForm.visitIp,
                'country': this.dataForm.country,
                'area': this.dataForm.area,
                'region': this.dataForm.region,
                'city': this.dataForm.city,
                'county': this.dataForm.county,
                'isp': this.dataForm.isp,
                'countryId': this.dataForm.countryId,
                'areaId': this.dataForm.areaId,
                'regionId': this.dataForm.regionId,
                'cityId': this.dataForm.cityId,
                'countyId': this.dataForm.countyId,
                'ispId': this.dataForm.ispId,
                'systemName': this.dataForm.systemName,
                'appName': this.dataForm.appName,
                'appVersion': this.dataForm.appVersion,
                'cookieEnabled': this.dataForm.cookieEnabled,
                'cpuClass': this.dataForm.cpuClass,
                'mimeType': this.dataForm.mimeType,
                'platform': this.dataForm.platform,
                'plugins': this.dataForm.plugins,
                'userLanguage': this.dataForm.userLanguage,
                'userAgent': this.dataForm.userAgent,
                'systemLanguage': this.dataForm.systemLanguage,
                'browserType': this.dataForm.browserType,
                'appCodeName': this.dataForm.appCodeName,
                'appMinorVersion': this.dataForm.appMinorVersion,
                'language': this.dataForm.language,
                'onLine': this.dataForm.onLine,
                'oscpu': this.dataForm.oscpu,
                'product': this.dataForm.product,
                'productSub': this.dataForm.productSub,
                'securityPolicy': this.dataForm.securityPolicy,
                'userProfile': this.dataForm.userProfile,
                'vender': this.dataForm.vender,
                'vendorSub': this.dataForm.vendorSub,
                'visitAddress': this.dataForm.visitAddress,
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
