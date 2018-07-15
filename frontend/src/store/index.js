import Vue from 'vue'
import Vuex from 'vuex'
import VuexI18n from 'vuex-i18n' // load vuex i18n module

import app from './modules/app'
import menu from './modules/menu'
import localStoragePlugin from './plugins/localStorage'
import auth from './modules/auth'
import profile from './modules/profile'
import user from './modules/user'
import task from './modules/task'
import role from './modules/role'

import * as getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  strict: true, // process.env.NODE_ENV !== 'production',
  getters,
  modules: {
    app,
    auth,
    user,
    task,
    role,
    profile,
    menu
  },
  state: {},
  mutations: {},
  plugins: [localStoragePlugin]
})

Vue.use(VuexI18n.plugin, store)

export default store
