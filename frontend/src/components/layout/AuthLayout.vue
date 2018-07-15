<template>
  <div class="auth-layout">
    <div class="nav hidden-lg-up"><router-link class="i-vuestic" :to="{path: '/'}"></router-link></div>
    <div class="main row">
      <div class="auth-wallpaper col-6 hidden-md-down">
        <div class="oblique"></div>
        <router-link class="i-vuestic-auth" :to="{path: '/'}"></router-link>
      </div>
      <div class="auth-content col-lg-6 col-12">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'AuthLayout'
  }
</script>

<style lang="scss">
  @import '../../sass/variables';
  @import '../../../node_modules/bootstrap/scss/mixins/breakpoints';
  @import '../../../node_modules/bootstrap/scss/variables';
  .auth-layout {
    background: url('../../assets/images/back.jpg') no-repeat center center fixed;
    -webkit-background-size: cover; /* For WebKit*/
    -moz-background-size: cover;    /* Mozilla*/
    -o-background-size: cover;      /* Opera*/
    background-size: cover;         /* Generic*/
    height: 100%;
    margin: 0;
    .nav {
      display: flex;
      align-items: center;
      justify-content: center;
      height: $top-mobile-nav-height;
      background-color: $theme-auth-color;
      .i-vuestic-auth {
        height: $auth-mobile-nav-ivuestic-h;
        width: 100%;
      }
    }
    .main {
      margin: 0;
      height: 100%;
      .auth-content {
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: $theme-auth-color;
      }
      .auth-wallpaper {
        background: $theme-main-gradient;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
        .i-vuestic-auth {
          z-index: 2;
        }
        .oblique {
          position: absolute;
          background-color: $auth-wallpaper-oblique-line;
          left: calc(50% - 27%/2);
          transform: rotate(-83deg);
          z-index: 1;
          width: 47%;
          height: 225%;
          opacity: 0.8;
        }
      }
    }

    @include media-breakpoint-down(md) {
      .main {
        height: $auth-mobile-main-h;
        .auth-content {
          align-items: flex-start;
          padding-top: $auth-content-padding-t;
        }
      }
    }
  }
</style>
