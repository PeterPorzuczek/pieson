import lazyLoading from './lazyLoading'

export default {
  name: 'error',
  meta: {
    expanded: false,
    title: 'menu.error',
    iconClass: 'vuestic-icon vuestic-icon-error'
  },
  children: [
    {
      name: 'Error404',
      path: '/error/404',
      component: lazyLoading('error/Error404'),
      meta: {
        permission: 'public',
        default: false,
        title: 'menu.error'
      }
    }
  ]
}
