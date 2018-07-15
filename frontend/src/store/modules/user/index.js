import * as api from './const.js'
import { http } from '../../../services/http-commons'

const state = {}

const actions = {
  delete ({ commit }, payload) {
    return http.delete(api.USER_DELETE + '/' + payload)
  },
  update ({ commit }, payload) {
    return http.put(api.USER_UPDATE, payload)
  },
  add ({ commit }, payload) {
    return http.post(api.USER_ADD, payload)
  }
}

const mutations = {
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
    return api.USER_LIST_GET
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
