import lazyLoading from '../lazyLoading'

export default {
  name: 'role',
  meta: {
    expanded: false,
    sidebar: true,
    title: 'menu.role',
    iconClass: 'vuestic-icon vuestic-icon-auth',
    permission: 'ADMINISTRATOR_USER'
  },
  children: [
    {
      name: 'RoleList',
      path: '/intranet/role/List',
      component: lazyLoading('intranet/role/List'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.rolelist',
        permission: 'ADMINISTRATOR_USER'
      }
    }
  ]
}
