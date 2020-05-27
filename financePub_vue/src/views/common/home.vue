<template>
  <div class="mod-home">
    <fund-home></fund-home>
  </div>

</template>

<script>
  import FundHome from '../modules/fund/fund-home'
  export default {
    data () {
      return {
      }
    },
    components: {
      FundHome
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

