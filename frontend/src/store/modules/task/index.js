import * as api from './const.js'
import { http } from '../../../services/http-commons'

const state = {
  allCount: 0,
  count: 0
}

const actions = {
  async loadAllCount ({ commit }) {
    const { data } = await http.get(api.TASK_LIST_ALL_COUNT_GET)
    commit('setAllCount', data)
  },
  async loadCount ({ commit }) {
    const { data } = await http.get(api.TASK_LIST_COUNT_GET)
    commit('setCount', data)
  },
  delete ({ commit }, payload) {
    return http.delete(api.TASK_DELETE + '/' + payload.time + '/' + payload.username)
  },
  update ({ commit }, payload) {
    return http.put(api.TASK_UPDATE, payload)
  },
  add ({ commit }, payload) {
    return http.post(api.TASK_ADD, payload)
  }
}

const mutations = {
  setAllCount (state, allCount) {
    state.allCount = allCount
  },
  setCount (state, count) {
    state.count = count
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
  listTableFieldsAll (state) {
    return api.tableFieldsAll
  },
  list (state) {
    return api.TASK_LIST_GET
  },
  listAll (state) {
    return api.TASK_LIST_ALL_GET
  },
  getAllCount (state) {
    return state.allCount
  },
  getCount (state) {
    return state.count
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
