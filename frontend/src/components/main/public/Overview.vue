<template>
  <div class="overview">
  <nav class="navbar navbar-expand-md navbar-dark sticky-top background">
    <div class="col-xs-12">
      <div class="row">
        <div class="col-xs-6 mr-auto pl-5">
          <i class="i-vuestic"></i>
        </div>
        <div class="col-xs-6 ml-auto pr-5 pt-1">
          <router-link class='btn btn-primary btn-micro' :to="{name: 'Login'}"><i class="fa d-inline fa-lg fa-user-circle-o"></i>{{'overview.signInNav' | translate}}</router-link>
        </div>
      </div>
    </div>
  </nav>
  <div class="img-back py-5">
    <div class="container py-5">
      <div class="row">
        <div class="col-md-9 text-white align-self-center">
          <h1 class="mb-4 display-2">{{'overview.headingMain' | translate}}</h1>
          <p class="lead mb-5">{{'overview.headingAdd' | translate}}</p>
          <router-link class='btn btn-primary' :to="{name: 'Login'}">{{'overview.signIn' | translate}}</router-link>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 background">
    <div class="container">
      <div class="row">
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4"><i class="d-block mx-auto fa fa-5x fa-angle-double-up text-light"></i></div>
            <div class="col-8">
              <h5 class="mb-3 text-light"><b>{{'overview.infos.1.title' | translate}}</b></h5>
              <p class="my-1 text-light">{{'overview.infos.1.message' | translate}}</p>
            </div>
          </div>
        </div>
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4"><i class="d-block mx-auto fa fa-5x fa-map-o text-light"></i></div>
            <div class="col-8">
              <h5 class="mb-3 text-light"><b>{{'overview.infos.2.title' | translate}}</b></h5>
              <p class="my-1 text-light">{{'overview.infos.2.message' | translate}}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4"><i class="d-block mx-auto fa fa-5x fa-file-o text-light"></i></div>
            <div class="col-8">
              <h5 class="mb-3 text-light"><b>{{'overview.infos.3.title' | translate}}</b></h5>
              <p class="my-1 text-light">{{'overview.infos.3.message' | translate}}</p>
            </div>
          </div>
        </div>
        <div class="py-5 col-md-6">
          <div class="row">
            <div class="text-center col-4"><i class="d-block mx-auto fa fa-5x fa-code text-light"></i></div>
            <div class="col-8">
              <h5 class="mb-3 text-light"><b>{{'overview.infos.4.title' | translate}}</b></h5>
              <p class="my-1 text-light">{{'overview.infos.4.message' | translate}}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="text-center text-white py-5 transparent">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="display-4">{{'overview.footer' | translate}}</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <a href="https://www.facebook.com/" class="text-white" target="_blank"><i class="fa fa-fw fa-facebook fa-3x mx-3"></i></a>
          <a href="https://twitter.com/" class="text-white" target="_blank"><i class="fa fa-fw fa-twitter fa-3x mx-3"></i></a>
          <a href="https://plus.google.com/" class="text-white" target="_blank"><i class="fa fa-fw fa-3x fa-google-plus mx-3"></i></a>
          <a href="https://www.instagram.com/" class="text-white" target="_blank"><i class="fa fa-fw fa-instagram fa-3x mx-3"></i></a>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
  export default {
    name: 'overview',
    created: function () {
      this.access = this.$store.getters['auth/currentAuthorities']
    }
  }
</script>

<style lang="scss">
@import '../../../sass/variables';
@import '../../../../node_modules/bootstrap/scss/mixins/breakpoints';
@import '../../../../node_modules/bootstrap/scss/variables';
.overview {
  background: $theme-main-gradient;
  width: 100%;
  padding: 0px;
  margin: 0px;
}
.transparent {
  background-color: transparent;
  background: transparent;
}

.background {
  background-color: $theme-main-color;
  background: $theme-main-color;
  color: white;
}
.img-back {
    background: url('../../../assets/images/back.jpg') no-repeat center center fixed;
    -webkit-background-size: cover; /* For WebKit*/
    -moz-background-size: cover;    /* Mozilla*/
    -o-background-size: cover;      /* Opera*/
    background-size: cover;         /* Generic*/
    overflow: hidden;
    position: relative; 
      > *:first-child:before {
        content: '';
        width: 200%;
        height: 116%;
        display: block;
        position: absolute;
        left: -50%;
        top: -1%; 
        pointer-events:none;
        background: $theme-straight-gradient;
      }
}
</style>
