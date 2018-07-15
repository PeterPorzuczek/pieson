import lazyLoading from '../lazyLoading'

export default {
  name: 'user',
  meta: {
    expanded: false,
    sidebar: true,
    title: 'menu.user',
    iconClass: 'vuestic-icon vuestic-icon-auth',
    permission: 'ADMINISTRATOR_USER'
  },
  children: [
    {
      name: 'UsersList',
      path: '/intranet/user/List',
      component: lazyLoading('intranet/user/List'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.userlist',
        permission: 'ADMINISTRATOR_USER'
      }
    },
    {
      name: 'UserAdd',
      path: '/intranet/user/Add',
      component: lazyLoading('intranet/user/Add'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.userAdd',
        permission: 'ADMINISTRATOR_USER'
      }
    }
  ]
}
