export const USER_LIST_GET = 'resources/intranet/user/list'
export const USER_DELETE = 'resources/intranet/user/delete'
export const USER_UPDATE = 'resources/intranet/user/update'
export const USER_ADD = 'resources/intranet/user/add'
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
    name: 'username',
    sortField: 'username'
  },
  {
    name: 'email',
    sortField: 'email'
  },
  {
    name: 'firstName',
    sortField: 'firstName'
  },
  {
    name: 'lastName',
    sortField: 'lastName'
  },
  {
    name: 'lastLogin',
    sortField: 'lastLogin'
  },
  {
    name: '__component:action-column',
    title: '',
    dataClass: 'text-center'
  }
]
export const sortFunctions = {
  'username': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'email': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'firstName': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'lastName': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'lastLogin': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  }
}
