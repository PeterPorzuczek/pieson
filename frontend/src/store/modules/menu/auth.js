import lazyLoading from './lazyLoading'

export default {
  name: 'auth',
  meta: {
    expanded: false,
    title: 'menu.auth',
    iconClass: 'vuestic-icon vuestic-icon-auth'
  },
  children: [
    {
      name: 'Login',
      path: '/auth/login',
      component: lazyLoading('auth/login/Login'),
      meta: {
        permission: 'public',
        default: false,
        title: 'menu.login'
      }
    },
    {
      name: 'Signup',
      path: '/auth/signup',
      component: lazyLoading('auth/signup/Signup'),
      meta: {
        default: false,
        title: 'menu.signUp'
      }
    }
  ]
}
