<template>
    <div class="row">
        <div class="col-md-12">
            <vuestic-widget class="col-sm-12 primary-secondary-buttons larger-padding welcome" v-show="welcomeShow">
                <div class="dash">
                   <i class="fa fa-close close" @click="hide()"></i>
                    <div class="typo-headers">
                        <h1>{{'dashboard.welcome' | translate}}{{ profile.firstName }}</h1>
                        <h5>{{'dashboard.welcomeMore' | translate}}</h5>
                    </div>
                </div>
            </vuestic-widget>
            <dashboard-info-widgets></dashboard-info-widgets>
        </div>
    </div>
</template>

<script>
  import DashboardInfoWidgets from './DashboardInfoWidgets'
  export default {
    name: 'Dashboard',
    components: {
      DashboardInfoWidgets
    },
    data () {
      return {
        welcomeShow: true
      }
    },
    computed: {
      profile: function () {
        return this.$store.getters['profile/current']
      }
    },
    methods: {
      hide () {
        this.welcomeShow = false
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import '../../../../sass/variables';
@import '../../../../sass/optional';
.welcome {
  background: $theme-additional-light-gradient;
  color: white;
}
</style>
