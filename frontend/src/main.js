// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VeeValidate from 'vee-validate'
import App from './App'
import store from './store'
import router from './router'
import { sync } from 'vuex-router-sync'
import VuesticPlugin from 'src/components/vuestic-components/vuestic-components-plugin'
import './i18n'
import Acl from 'vue-acl'
import pace from 'pace-js/pace.min.js'
import vueKanban from 'vue-kanban'

Vue.use(BootstrapVue)
Vue.use(VuesticPlugin)
Vue.use(vueKanban)
Vue.use(Acl, { router: router, init: 'public', save: true, fail: '/error/404' })

// NOTE: workaround for VeeValidate + vuetable-2
Vue.use(VeeValidate, {fieldsBagName: 'formFields'})

sync(store, router)

let mediaHandler = () => {
  if (window.matchMedia(store.getters.config.windowMatchSizeLg).matches) {
    var toggleBar = false
    try { toggleBar = !!JSON.parse(String(store.getters.sidebar).toLowerCase()) } catch (e) { console.log(e) }
    store.dispatch('toggleSidebar', toggleBar)
  } else {
    store.dispatch('toggleSidebar', false)
  }
}

router.beforeEach((to, from, next) => {
  store.commit('setLoading', true)
  pace.start()
  next()
})

router.afterEach((to, from) => {
  mediaHandler()
  pace.stop()
  store.commit('setLoading', false)
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
