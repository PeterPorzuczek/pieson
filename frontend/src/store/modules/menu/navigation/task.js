import lazyLoading from '../lazyLoading'

export default {
  name: 'task',
  meta: {
    expanded: false,
    sidebar: true,
    title: 'menu.user',
    iconClass: 'vuestic-icon vuestic-icon-dashboard',
    permission: 'STANDARD_USER|ADMINISTRATOR_USER'
  },
  children: [
    {
      name: 'TaskList',
      path: '/intranet/task/list',
      component: lazyLoading('intranet/task/List'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.tasklist',
        iconClass: 'vuestic-icon vuestic-icon-dashboard',
        permission: 'STANDARD_USER|ADMINISTRATOR_USER'
      }
    },
    {
      name: 'TaskAdd',
      path: '/intranet/task/Add',
      component: lazyLoading('intranet/task/Add'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.taskAdd',
        permission: 'STANDARD_USER|ADMINISTRATOR_USER'
      }
    },
    {
      name: 'TaskListAll',
      path: '/intranet/task/listAll',
      component: lazyLoading('intranet/task/ListAll'),
      meta: {
        default: false,
        sidebar: true,
        title: 'menu.tasklistAll',
        iconClass: 'vuestic-icon vuestic-icon-dashboard',
        permission: 'ADMINISTRATOR_USER'
      }
    }
  ]
}
