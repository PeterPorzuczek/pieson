import * as api from './const.js'
import { http } from '../../../services/http-commons'

const state = {
  roles: []
}

const mutations = {
  setRoles (state, roles) {
    state.roles = roles
  }
}

const actions = {
  async load ({ commit }) {
    const { data } = await http.get(api.ROLES_GET)
    commit('setRoles', data)
  },
  delete ({ commit }, payload) {
    return http.delete(api.ROLE_DELETE + '/' + payload)
  },
  update ({ commit }, payload) {
    return http.put(api.ROLE_UPDATE, payload)
  },
  add ({ commit }, payload) {
    return http.post(api.ROLE_ADD, payload)
  }
}

const getters = {
  listSortFunctions (state) {
    return api.sortFunctions
  },
  listItemsPerPage (state) {
    return api.itemsPerPage
  },
  listTableFields (state) {
    return api.tableFields
  },
  list (state) {
    return api.ROLE_LIST_GET
  },
  listAll (state) {
    return state.roles
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
