import lazyLoading from '../lazyLoading'

export default {
  name: 'Profile',
  path: '/intranet/Profile',
  component: lazyLoading('intranet/profile/Profile'),
  meta: {
    default: false,
    sidebar: false,
    title: 'menu.profile',
    iconClass: 'vuestic-icon vuestic-icon-dashboard',
    permission: 'STANDARD_USER|ADMINISTRATOR_USER'
  }
}
