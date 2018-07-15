import lazyLoading from '../lazyLoading'

export default {
  name: 'Dashboard',
  path: '/intranet/dashboard',
  component: lazyLoading('intranet/dashboard/Dashboard'),
  meta: {
    default: false,
    sidebar: true,
    title: 'menu.dashboard',
    iconClass: 'vuestic-icon vuestic-icon-dashboard',
    permission: 'STANDARD_USER|ADMINISTRATOR_USER'
  }
}
