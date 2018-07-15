<template>
  <div class="login">
    <vuestic-alert type="danger" v-show="showAlert" >
      <span class="badge badge-pill badge-danger">{{'auth.alert' | translate}}</span>
        {{'auth.loginAlertMessage' | translate}}
    </vuestic-alert>
    <vuestic-alert type="success" v-show="showSuccess">
            <span class="badge badge-pill badge-success">{{'auth.success' | translate}}</span>
          {{'auth.loginSuccessMessage' | translate}}
            <i class="fa fa-close alert-close"></i>
    </vuestic-alert>
    <h2>{{'auth.welcome' | translate}}</h2>
    <form sv-el:custom  @submit.prevent="login" name="login" class="login-form">
      <div class="form-group">
        <div class="input-group">
          <input type="text" id="username" v-model="username" required="required"/>
          <label class="control-label" for="username">{{'auth.username' | translate}}</label><i class="bar"></i>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <input type="password" id="password" v-model="password" required="required"/>
          <label class="control-label" for="password">{{'auth.password' | translate}}</label><i class="bar"></i>
        </div>
      </div>
      <div class="d-flex flex-column flex-lg-row align-items-center justify-content-between down-container">
        <button class="btn btn-primary" type="submit">
          {{'auth.login' | translate}}
        </button>
        <!-- <router-link class='link' :to="{name: 'Signup'}">{{'auth.createAccount' | translate}}</router-link> -->
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      showAlert: false,
      showSuccess: false,
      password: '',
      username: ''
    }
  },
  methods: {
    async login () {
      try {
        this.resetAlerts()
        await this.$store.dispatch('auth/login', { 'username': this.username, 'password': this.password })
        this.access = this.$store.getters['auth/currentAuthorities']
        this.$nextTick(function () {
          this.$router.push({name: 'Dashboard'})
        })
      } catch (e) {
        this.showAlertNotification()
        console.log(e)
      }
    },
    resetAlerts () {
      this.showAlert = false
      this.showSuccess = false
    },
    showSuccessNotification () {
      this.showSuccess = true
      setTimeout(() => { this.showSuccess = false }, 1500)
    },
    showAlertNotification () {
      this.showAlert = true
      setTimeout(() => { this.showAlert = false }, 1500)
    }
  }
}
</script>

<style lang="scss">
  @import '../../../../sass/variables';
  @import '../../../../../node_modules/bootstrap/scss/mixins/breakpoints';
  @import '../../../../../node_modules/bootstrap/scss/variables';
  .login {
    @include media-breakpoint-down(md) {
      width: 100%;
      padding-right: 2rem;
      padding-left: 2rem;
      .down-container {
        .link {
          margin-top: 2rem;
        }
      }
    }

    h2 {
      text-align: center;
    }
    width: 21.375rem;

    .down-container {
      margin-top: 3.125rem;
    }

    .btn-primary {
      width: 100%
    }
  }
</style>
