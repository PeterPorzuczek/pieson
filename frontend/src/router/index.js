import Vue from 'vue'
import Router from 'vue-router'
import Store from '@/store'

import menuModule from 'vuex-store/modules/menu'

Vue.use(Router)

const router = new Router({
  routes: [
    ...generateRoutesFromMenu(menuModule.state.items),
    {path: '*', redirect: { name: getDefaultRoute(menuModule.state.items).name }}
  ]
})

function generateRoutesFromMenu (menu = [], routes = []) {
  for (let i = 0, l = menu.length; i < l; i++) {
    let item = menu[i]
    if (item.path) {
      routes.push(item)
    }
    if (item.children) {
      generateRoutesFromMenu(item.children, routes)
    }
  }
  return routes
}

function getDefaultRoute (menu = []) {
  let defaultRoute

  menu.forEach((item) => {
    if (item.meta.default) {
      defaultRoute = item
    } else if (item.children) {
      let defaultChild = item.children.find((i) => i.meta.default)
      defaultRoute = defaultChild || defaultRoute
    }
  })

  return defaultRoute
}

// Navigation guard
router.beforeEach((to, from, next) => {
  const isLoggedIn = Store.getters['auth/isLoggedIn']
  switch (to.path) {
    case '/intranet/dashboard':
      if (isLoggedIn) {
        next(true)
        break
      } else {
        next('/auth/login')
        break
      }
    case '/auth/login':
      if (!isLoggedIn) {
        next(true)
        break
      } else {
        next('/intranet/dashboard')
        break
      }
    case '/auth/register':
      next(!isLoggedIn)
      break
    case '/':
      next(true)
      break
    default:
      if (!isLoggedIn) {
        next('/')
      } else {
        next(isLoggedIn)
      }
  }
})

router.afterEach((to, from) => {
  const isLoggedIn = Store.getters['auth/isLoggedIn']
  if (to.path === '/intranet/dashboard' && from.path === '/auth/login') {
    router.push({name: 'Dashboard'})
  }
  if (to.path === '/intranet/dashboard' && from.path === '/') {
    router.push({name: 'Dashboard'})
  }
  if (isLoggedIn) {
    this.access = Store.getters['auth/currentAuthorities']
  }
})

export default router
