<template>
  <div class="row dashboard-info-widgets">

    <div class="col-mx-6 col-xl-6" v-show='$can("ADMINISTRATOR_USER")'>
      <vuestic-widget class="info-widget brand-info">
        <div class="info-widget-inner">
          <div class="stats">
            <div class="stats-number">
              <i class="ion ion-android-people stats-icon icon-wide"></i>
              {{allTasksCount}}
            </div>
            <div class="stats-title">{{'dashboard.allTasks' | translate}}</div>
          </div>
        </div>
      </vuestic-widget>
    </div>

    <div class="col-mx-6 col-xl-6" >
      <vuestic-widget class="info-widget brand-info">
        <div class="info-widget-inner">
          <div class="stats">
            <div class="stats-number">
              <i class="ion ion-android-people stats-icon icon-wide"></i>
              {{tasksCount}}
            </div>
            <div class="stats-title">{{'dashboard.tasks' | translate}}</div>
          </div>
        </div>
      </vuestic-widget>
    </div>

  </div>
</template>

<script>
  import ProgressBar from '../../../../components/vuestic-components/vuestic-progress-bar/VuesticProgressBar'
  export default {
    name: 'dashboard-info-widgets',
    components: {
      ProgressBar
    },
    created: function () {
      this.$store.dispatch('task/loadCount')
      this.$store.dispatch('task/loadAllCount')
    },
    computed: {
      allTasksCount: function () {
        return this.$store.getters['task/getAllCount']
      },
      tasksCount: function () {
        return this.$store.getters['task/getCount']
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "../../../../sass/_variables.scss";

  .stats-number, .stats-title {
    line-height: 1;
  }

  .info-widget-inner {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    position: relative;
    width: 100%;

    &.has-chart {
      justify-content: space-between;
    }

    .stats {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100%;
    }
  }

  .stats-number {
    position: relative;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    font-size: 2.625rem;
    margin-bottom: 0.5rem;

    .stats-icon {
      font-size: 1.5625rem;
      position: absolute;
      top: 0.625rem;
      left: -1.25rem;

      &.icon-wide {
        left: -1.875rem;
      }
    }
  }

</style>
