<template>
    <div class="row">
        <div class="col-md-12">
            <vuestic-widget class="col-sm-11 primary-secondary-buttons larger-padding">
                
                <div class="typo-headers">
                    <h1>{{'task.text' | translate}}</h1>
                </div>

                <vuestic-alert type="danger" v-show="showAlert" >
                  <span class="badge badge-pill badge-danger">{{'auth.alert' | translate}}</span>
                    {{'task.error' | translate}}
                </vuestic-alert>

                <vuestic-alert type="success" v-show="showSuccess">
                  <span class="badge badge-pill badge-success">{{'auth.success' | translate}}</span>
                    {{'task.saved' | translate}}
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
                        v-model="task.title" 
                        required/>
                      <label class="control-label" for="simple-input">{{'task.inputs.title' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                      <input 
                        id="simple-input"
                        v-model="task.description" 
                        required/>
                      <label class="control-label" for="simple-input">{{'task.inputs.description' | translate}}</label><i class="bar"></i>
                    </div>
                  </div>

                </fieldset>
              </div>


            </div>

            <button class="btn btn-primary btn-sm" type="submit">{{'task.save' | translate}}</button>

          </form>

            </vuestic-widget>
        </div>
    </div>
</template>

<script>
  export default {
    name: 'TaskAdd',
    data () {
      return {
        showAlert: false,
        showSuccess: false,
        task: {}
      }
    },
    methods: {
      add () {
        this.resetAlerts()
        console.log(this.user)
        this.$store.dispatch('task/add', { data: JSON.stringify(this.task) }).then(response => {
          response.data ? this.showSuccessNotification() : this.showAlertNotification()
          this.$nextTick(function () {
            this.$router.push('/intranet/task/List')
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