import * as api from './const.js'
import { http } from '../../../services/http-commons'
import { tokenStorage } from '../../plugins/localStorage'

const state = {
  token: tokenStorage
}

const mutations = {
  setToken (state, token) {
    state.token = token
  },
  resetToken (state) {
    state.token = ''
  }
}

const actions = {
  async login ({ commit }, payload) {
    const authOptions = api.AUTH_OPTIONS(payload.username, payload.password)
    const { data } = await http(authOptions)
    const authHeadContent = api.CLIENT_TOKEN_TYPE + ' ' + data[api.CLIENT_TOKEN_NAME]
    // console.log(api.TOKEN_DECODE(data))
    Object.assign(http.defaults, {headers: {[api.AUTHORIZATION_HEADER]: authHeadContent}})
    commit('setToken', btoa(btoa(data[api.CLIENT_TOKEN_NAME])))
  },
  logout ({ commit }) {
    resetHeader()
    commit('resetToken')
  }
}

const resetHeader = () => {
  Object.assign(http.defaults, {headers: {[api.AUTHORIZATION_HEADER]: ''}})
}

const updateHeader = ({ token }) => {
  const authHeadContent = api.CLIENT_TOKEN_TYPE + ' ' + atob(atob(token))
  Object.assign(http.defaults, {headers: {[api.AUTHORIZATION_HEADER]: authHeadContent}})
}

const getPayload = ({ token }) => {
  const payload = atob(atob(token)).split('.')[1]
  return JSON.parse(atob(payload))
}

const getters = {
  isLoggedIn (state) {
    if (!state.token || state.token === 'undefined') {
      resetHeader()
      return false
    } else {
      updateHeader(state)
      let payload = getPayload(state)
      // console.log(payload)
      return payload.exp > (Date.now() / 1000)
    }
  },
  currentAuthorities (state) {
    var authorities = ['public']
    if (state.token) {
      const payloadAuth = getPayload(state).authorities
      Array.prototype.push.apply(authorities, payloadAuth)
    }
    return authorities
  },
  currentUser (state) {
    if (!state.token) return {}
    const { name, email } = getPayload(state)
    return { name, email }
  },
  token (state) {
    return atob(atob(state.token))
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
