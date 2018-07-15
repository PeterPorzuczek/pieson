export const ROLE_LIST_GET = 'resources/intranet/role/list'
export const ROLES_GET = 'resources/intranet/role/list/all'
export const ROLE_DELETE = 'resources/intranet/role/delete'
export const ROLE_UPDATE = 'resources/intranet/role/update'
export const ROLE_ADD = 'resources/intranet/role/add'
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
    name: 'roleName',
    sortField: 'roleName'
  },
  {
    name: 'description',
    sortField: 'description'
  }
]
export const sortFunctions = {
  'roleName': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  },
  'description': function (item1, item2) {
    return item1 >= item2 ? 1 : -1
  }
}
