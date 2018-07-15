<template>
  <div>

    <div class="row">
      <div class="col-md-12">
        <widget :headerText="$t('user.listTitle')">
          <data-table :httpFetch="customFetch"
                      :apiUrl="apiUrl"
                      :tableFields="tableFields"
                      :itemsPerPage="itemsPerPage"
                      :sortFunctions="sortFunctions"
                      :apiMode="apiMode"
                      :paginationPath="paginationPath"></data-table>
        </widget>
      </div>
    </div>

  </div>
</template>

<script>
  import Widget from '../../../vuestic-components/vuestic-widget/VuesticWidget'
  import DataTable from '../../../vuestic-components/vuestic-datatable/VuesticDataTable'
  import { http } from '../../../../services/http-commons'
  import ActionColumn from './ActionColumn.vue'
  import Vue from 'vue'

  Vue.component('action-column', ActionColumn)

  export default {
    components: {
      DataTable,
      Widget
    },
    name: 'UserList',
    data () {
      return {
        apiUrl: this.$store.getters['user/list'],
        apiMode: true,
        tableFields: this.$store.getters['user/listTableFields'],
        itemsPerPage: this.$store.getters['user/listItemsPerPage'],
        sortFunctions: this.$store.getters['user/listSortFunctions'],
        paginationPath: ''
      }
    },
    methods: {
      customFetch (apiUrl, httpOptions) {
        return http.get(apiUrl, httpOptions)
      }
    }
  }
</script>

<style lang="scss">

  .color-icon-label-table {
    td:first-child {
      width: 1rem;
    }
  }
  .filter {
    opacity: 1;
  }
</style>
