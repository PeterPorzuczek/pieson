<template>
  <div class="error-layout">
    <div class="main row">
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ErrorLayout'
  }
</script>

<style lang="scss">
  @import '../../sass/variables';
  @import '../../../node_modules/bootstrap/scss/mixins/breakpoints';
  @import '../../../node_modules/bootstrap/scss/variables';
  .error-layout {
    width: 100%;
    height: 100%;
    background: $theme-main-gradient;
    margin: 0;
    .main {
      margin: 0;
      height: 100%;
      width: 100%;
      .content {
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
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
