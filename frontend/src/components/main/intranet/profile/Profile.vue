<template>
    <div class="row">
        <div class="col-md-12">
            <vuestic-widget class="col-sm-11 primary-secondary-buttons larger-padding">
                
                <div class="typo-headers">
                    <h1>{{'profile.text' | translate}} {{ profileEdit.firstName }}</h1>
                </div>

                <vuestic-alert type="danger" v-show="showAlert" >
                  <span class="badge badge-pill badge-danger">{{'auth.alert' | translate}}</span>
                    {{'profile.error' | translate}}
                </vuestic-alert>

                <vuestic-alert type="success" v-show="showSuccess">
                  <span class="badge badge-pill badge-success">{{'auth.success' | translate}}</span>
                    {{'profile.saved' | translate}}
                  <i class="fa fa-close alert-close"></i>
                </vuestic-alert>
                
            <form sv-el:custom class="formPlain" @submit.prevent="update">

            <div class="row">
              <div class="col-md-12">
                <fieldset>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="profileEdit.firstName" 
                        required/>
                      <label class="control-label" for="simple-input">{{'profile.inputs.firstName' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="profileEdit.lastName" 
                        required/>
                      <label class="control-label" for="simple-input">{{'profile.inputs.lastName' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="profileEdit.password" 
                        type="password"
                        required/>
                      <label class="control-label" for="simple-input">{{'profile.inputs.password' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>


                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="profileEdit.oldPassword" 
                        type="password"
                        required/>
                      <label class="control-label" for="simple-input">{{'profile.inputs.oldPassword' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group with-icon-right"
                       :class="{'has-error': errors.has('successfulEmail'), 'valid': isSuccessfulEmailValid}">
                    <div class="input-group">
                      <input
                        id="successfulEmail"
                        name="successfulEmail"
                        v-model="profileEdit.email"
                        v-validate="'required|email'"
                        required/>
                      <i class="fa fa-exclamation-triangle error-icon icon-right input-icon"></i>
                      <i class="fa fa-check valid-icon icon-right input-icon"></i>
                      <label class="control-label" for="successfulEmail">{{'profile.inputs.emailValidatedSuccess' | translate}} </label><i
                      class="bar"></i>
                      <small v-show="errors.has('successfulEmail')" class="help text-danger">
                        {{ errors.first('successfulEmail') }}
                      </small>
                    </div>
                  </div>

                </fieldset>
              </div>


            </div>

            <button class="btn btn-primary btn-sm" type="submit">{{'profile.save' | translate}}</button>

          </form>

            </vuestic-widget>
        </div>
    </div>
</template>

<script>
  export default {
    name: 'Profile',
    data () {
      return {
        showAlert: false,
        showSuccess: false
      }
    },
    computed: {
      profileEdit: function () {
        return Object.assign({}, this.$store.getters['profile/current'])
      }
    },
    methods: {
      update () {
        this.resetAlerts()
        this.$store.dispatch('profile/update', { data: JSON.stringify(this.profileEdit) }).then(response => {
          this.$store.dispatch('profile/load')
          response.data ? this.showSuccessNotification() : this.showAlertNotification()
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