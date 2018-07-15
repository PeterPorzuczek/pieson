<template>
    <div class="row">
        <div class="col-md-12">
            <vuestic-widget class="col-sm-11 primary-secondary-buttons larger-padding">
                
                <div class="typo-headers">
                    <h1>{{'user.text' | translate}}</h1>
                </div>

                <vuestic-alert type="danger" v-show="showAlert" >
                  <span class="badge badge-pill badge-danger">{{'auth.alert' | translate}}</span>
                    {{'user.error' | translate}}
                </vuestic-alert>

                <vuestic-alert type="success" v-show="showSuccess">
                  <span class="badge badge-pill badge-success">{{'auth.success' | translate}}</span>
                    {{'user.saved' | translate}}
                  <i class="fa fa-close alert-close"></i>
                </vuestic-alert>
                
            <form sv-el:custom class="formPlain" @submit.prevent="add">

            <div class="row">
              <div class="col-md-12">
                <fieldset>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="user.username" 
                        required/>
                      <label class="control-label" for="simple-input">{{'user.inputs.username' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="user.firstName" 
                        required/>
                      <label class="control-label" for="simple-input">{{'user.inputs.firstName' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="user.lastName" 
                        required/>
                      <label class="control-label" for="simple-input">{{'user.inputs.lastName' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="user.password" 
                        type="password"
                        required/>
                      <label class="control-label" for="simple-input">{{'user.inputs.password' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>


                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="user.confirmPassword" 
                        type="password"
                        required/>
                      <label class="control-label" for="simple-input">{{'user.inputs.confirmPassword' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group with-icon-right"
                       :class="{'has-error': errors.has('successfulEmail'), 'valid': isSuccessfulEmailValid}">
                    <div class="input-group">
                      <input
                        id="successfulEmail"
                        name="successfulEmail"
                        v-model="user.email"
                        v-validate="'required|email'"
                        required/>
                      <i class="fa fa-exclamation-triangle error-icon icon-right input-icon"></i>
                      <i class="fa fa-check valid-icon icon-right input-icon"></i>
                      <label class="control-label" for="successfulEmail">{{'user.inputs.emailValidatedSuccess' | translate}} </label><i
                      class="bar"></i>
                      <small v-show="errors.has('successfulEmail')" class="help text-danger">
                        {{ errors.first('successfulEmail') }}
                      </small>
                    </div>
                  </div>

                  <vuestic-simple-select
                    :label="'user.inputs.role' | translate"
                    v-model="user.role"
                    option-key="description"
                    v-bind:options="roles"
                    required>
                  </vuestic-simple-select>

                </fieldset>
              </div>


            </div>

            <button class="btn btn-primary btn-sm" type="submit">{{'user.save' | translate}}</button>

          </form>

            </vuestic-widget>
        </div>
    </div>
</template>

<script>
  export default {
    name: 'UserAdd',
    data () {
      return {
        showAlert: false,
        showSuccess: false,
        user: {}
      }
    },
    computed: {
      roles: function () {
        return this.$store.getters['role/listAll']
      }
    },
    created: function () {
      this.$store.dispatch('role/load')
    },
    methods: {
      add () {
        this.resetAlerts()
        console.log(this.user)
        this.$store.dispatch('user/add', { data: JSON.stringify(this.user) }).then(response => {
          response.data ? this.showSuccessNotification() : this.showAlertNotification()
          this.$nextTick(function () {
            this.$router.push('/intranet/user/List')
          })
        }).catch(e => { })
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

<style scoped lang='scss'>
@import '../../../../sass/optional';
</style>