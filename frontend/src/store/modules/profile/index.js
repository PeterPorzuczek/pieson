import * as api from './const.js'
import { http } from '../../../services/http-commons'

const state = {
  profile: ''
}

const mutations = {
  setProfile (state, profile) {
    state.profile = profile
  }
}

const actions = {
  async load ({ commit }) {
    const { data } = await http.get(api.PROFILE_GET)
    commit('setProfile', data)
  },
  update ({ commit }, payload) {
    return http.put(api.PROFILE_UPDATE, payload)
  }
}

const getters = {
  current (state) {
    return state.profile
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
