<template>
  <div class="mod-home">
    <jingjiahome v-if="show1"></jingjiahome>
    <fund-home v-if="show2"></fund-home>
    <div v-if="show0">
      <h3>官方QQ群</h3>
      <ul>
        <li>高级群：704802627(大牛云集，跟大牛学习新技能)</li>
      </ul>
    </div>
  </div>

</template>

<script>
  import jingjiahome from '../modules/jingjia/jingjia-home'
  import FundHome from '../modules/fund/fund-home'
  export default {
    data () {
      return {
        homeType: 0,
        show0: true,
        show1: false,
        show2: false
      }
    },
    components: {
      FundHome,
      jingjiahome
    },
    created () {
      this.getUserInfo()
    },
    methods: {
      // 获取当前管理员信息
      getUserInfo () {
        this.$http({
          url: this.$http.adornUrl('/sys/user/info'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.userId = data.user.userId
            this.userName = data.user.username
            this.homeType = data.user.homeType
            if (this.homeType === 1) {
              this.show0 = false
              this.show1 = true
              this.show2 = false
            } else if (this.homeType === 2) {
              this.show0 = false
              this.show1 = false
              this.show2 = true
            }
            console.log(this.homeType + ',' + this.show0)
          }
        })
      }
    }
  }
</script>

<style>

  .mod-home {
    line-height: 1.5;
  }
</style>

