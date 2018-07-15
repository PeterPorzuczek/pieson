<template>
  <div id="app" class="app">
    <auth-layout v-if="isAuth"></auth-layout>
    <intranet-layout v-if="isIntranet"></intranet-layout>
    <error-layout v-if="isError"></error-layout>
    <public-layout v-if="!isError && !isIntranet && !isAuth"></public-layout>
  </div>
</template>

<script>
  import IntranetLayout from 'components/layout/IntranetLayout'
  import AuthLayout from './components/layout/AuthLayout'
  import ErrorLayout from './components/layout/ErrorLayout'
  import PublicLayout from './components/layout/PublicLayout'
  import VuesticPreLoader from './components/vuestic-components/vuestic-preloader/VuesticPreLoader.vue'

  export default {
    name: 'app',
    components: {
      VuesticPreLoader,
      PublicLayout,
      ErrorLayout,
      AuthLayout,
      IntranetLayout
    },
    computed: {
      isAuth () {
        return this.$route.path.match('auth')
      },
      isError () {
        return this.$route.path.match('error')
      },
      isIntranet () {
        return this.$route.path.match('intranet')
      }
    }
  }
</script>

<style lang="scss">
  @import "sass/main";
  body {
    height: 100%;
    .app {
      height: 100%;
    }
  }
</style>
