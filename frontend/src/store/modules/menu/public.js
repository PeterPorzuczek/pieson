import lazyLoading from './lazyLoading'

export default {
  path: '/',
  component: lazyLoading('public/Overview'),
  meta: {
    default: true,
    permission: 'public'
  }
}
