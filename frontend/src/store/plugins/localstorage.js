import * as types from '../mutation-types'

const verification = 'auth'
const sidebar = 'sidebar'

const tokenStorage = localStorage[verification]
const sidebarStorage = localStorage[sidebar]

const plugin = store => {
  store.subscribe((mutation, state) => {
    switch (mutation.type) {
      case 'auth/setToken':
        localStorage[verification] = mutation.payload
        break
      case 'auth/resetToken':
        localStorage.removeItem(verification)
        localStorage.clear()
        break
      case types.TOGGLE_SIDEBAR:
        localStorage[sidebar] = mutation.payload
        break
    }
  })
}

export default plugin
export { tokenStorage, sidebarStorage }
