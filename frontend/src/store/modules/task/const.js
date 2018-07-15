export const TASK_LIST_GET = 'resources/intranet/task/list'
export const TASK_LIST_ALL_GET = 'resources/intranet/task/list/all'
export const TASK_LIST_ALL_COUNT_GET = '/resources/intranet/task/list/all/count'
export const TASK_LIST_COUNT_GET = '/resources/intranet/task/list/count'
export const TASK_DELETE = 'resources/intranet/task/delete'
export const TASK_UPDATE = 'resources/intranet/task/update'
export const TASK_ADD = 'resources/intranet/task/add'
export const itemsPerPage = [
  {
    value: 1
  },
  {
    value: 10
  },
  {
    value: 20
  }
]
export const tableFields = [
  {
    name: 'title',
    sortField: 'title'
  },
  {
    name: 'description',
    sortField: 'description'
  },
  {
    name: '__component:action-column',
    title: '',
    dataClass: 'text-center'
  }
]
export const tableFieldsAll = [
  {
    name: 'title',
    sortField: 'title'
  },
  {
    name: 'description',
    sortField: 'description'
  },
  {
    name: 'username',
    sortField: 'username'
  }
]
export const sortFunctions = {
  'title': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'description': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  }
}
