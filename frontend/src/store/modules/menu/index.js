import * as types from '../../mutation-types'
import overview from './public'
import dashboard from './navigation/dashboard'
import task from './navigation/task'
import profile from './navigation/profile'
import user from './navigation/user'
import role from './navigation/role'
import auth from './auth'
import error from './error'

const state = {
  items: [
    overview,
    dashboard,
    task,
    profile,
    user,
    role,
    auth,
    error
  ]
}

const mutations = {
  [types.TOGGLE_EXPAND_MENU_ITEM] (state, payload) {
    let menuItem = payload.menuItem
    let expand = payload.expand
    if (menuItem.children && menuItem.meta) {
      menuItem.meta.expanded = expand
    }
  }
}

const actions = {
  toggleExpandMenuItem ({commit}, payload) {
    commit(types.TOGGLE_EXPAND_MENU_ITEM, payload)
  }
}

export default {
  state,
  mutations,
  actions
}
