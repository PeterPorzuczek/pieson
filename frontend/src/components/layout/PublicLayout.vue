<template>
  <div class="public-layout">
    <div class="main row">
        <router-view></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'PublicLayout'
  }
</script>

<style lang="scss">
  @import '../../sass/variables';
  @import '../../../node_modules/bootstrap/scss/mixins/breakpoints';
  @import '../../../node_modules/bootstrap/scss/variables';
  .public-layout {
    margin: 0;
    .main {
      margin: 0;
      height: 100%;
      .content {
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: white;
      }
    }

    @include media-breakpoint-down(md) {
      .main {
        .content {
          align-items: flex-start;
        }
      }
    }
  }
</style>
