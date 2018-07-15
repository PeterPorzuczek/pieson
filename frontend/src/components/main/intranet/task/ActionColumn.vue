<template>
  <!-- <span class="circle" :class="classObject"></span> -->
  <div>
    <!-- <span @click="editRow(rowData)" class="p-2 glyphicon glyphicon-pencil"></span> -->
    <span @click="deleteRow(rowData)" class="p-2 glyphicon glyphicon-trash"></span>
  </div>

</template>

<script>
  export default {
    name: 'ActionColumn',
    props: {
      rowData: {
        type: Object,
        required: true
      },
      rowIndex: {
        type: Number
      }
    },
    data () {
      return {
        classObject: {
          'badge-warning': this.rowIndex % 6 === 0,
          'badge-primary': this.rowIndex % 6 === 1,
          'badge-danger': this.rowIndex % 6 === 2,
          'badge-info': this.rowIndex % 6 === 3,
          'badge-violet': this.rowIndex % 6 === 4,
          'badge-dark-blue': this.rowIndex % 6 === 5
        }
      }
    },
    methods: {
      editRow (rowData) {
        // alert('You clicked edit on' + JSON.stringify(this.rowData))
        // Vue.nextTick(() => this.$refs.vuetable.refresh())
        this.$parent.refresh()
      },
      deleteRow (rowData) {
        // alert('You clicked delete on' + JSON.stringify(this.rowData))
        this.$store.dispatch('task/delete', { time: rowData.time, username: rowData.username }).then(response => {
          this.$parent.refresh()
        }).catch(e => { })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .circle {
    width: .75rem;
    height: .75rem;
    border-radius: 50%;
    display: inline-block;
  }
</style>
