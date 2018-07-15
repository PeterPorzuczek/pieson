<template>
  <nav class="navbar app-navbar navbar-toggleable-md">
    <div class="navbar-brand-container d-flex align-items-center justify-content-start">
      <i class="i-vuestic"></i>
    </div>

    <div class="row navbar-container">

      <div class="menu-icon-container d-flex align-items-center justify-content-center justify-content-lg-start col">
        <a class="menu-icon i-menu-expanded" href="#" @click.prevent="toggleSidebar(false)" v-if="sidebarOpened"></a>
        <a class="menu-icon i-menu-collapsed" href="#" @click.prevent="toggleSidebar(true)" v-else></a>
      </div>

      <div class="offset-lg-10"></div>

      <div class="col nav-item dropdown navbar-dropdown d-flex align-items-center justify-content-center" v-dropdown>
        <a class="nav-link dropdown-toggle d-flex align-items-center justify-content" href="#" @click.prevent="closeMenu">
          <span class="avatar-container">
            <p>{{ profile.firstName[0] }}</p>
          </span>
        </a>
        <div class="dropdown-menu last">
          <div class="dropdown-menu-content">
            <div class="dropdown-item plain-link-item">
              <router-link :to="{name: 'Profile'}" >{{'menu.profile' | translate}}</router-link>
            </div>
            <div class="dropdown-item plain-link-item">
              <a class="plain-link" href="/" @click.prevent="logout">{{'profile.logout' | translate}}</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
  import Vue from 'vue'
  import { mapGetters, mapActions } from 'vuex'
  import Dropdown from 'directives/Dropdown'
  import Modal from '../../vuestic-components/vuestic-modal/VuesticModal'
  export default {
    name: 'navbar',
    components: {
      Modal
    },
    directives: {
      dropdown: Dropdown
    },

    computed: {
      ...mapGetters([
        'sidebarOpened',
        'toggleWithoutAnimation'
      ]),
      currentLanguage () {
        return Vue.i18n.locale() === 'en' ? 'English' : 'Polish'
      },
      profile: function () {
        return this.$store.getters['profile/current']
      }
    },
    created: function () {
      this.loadProfile()
    },
    methods: {
      ...mapActions([
        'closeMenu',
        'toggleSidebar',
        'isToggleWithoutAnimation'
      ]),
      showLanguageModal () {
        this.$refs.languageModal.open()
      },
      setLanguage (locale) {
        Vue.i18n.set(locale)
        this.$refs.languageModal.cancel()
      },
      loadProfile () {
        try {
          this.$store.dispatch('profile/load')
        } catch (e) {
          console.log(e)
        }
      },
      logout () {
        this.$store.dispatch('auth/logout')
        this.access = ['public']
        setTimeout(() => { this.$router.push('/') }, 100)
      }
    }
  }
</script>

<style lang="scss">
  @import "../../../sass/_variables.scss";
  @import "../../../../node_modules/bootstrap/scss/mixins/breakpoints";
  @import "../../../../node_modules/bootstrap/scss/variables";

  .navbar.app-navbar {
    .navbar-container {
      width: 100%;
      height: 100%;
      margin: 0;
    }
    //To stay on top
    position: fixed;
    z-index: 9999;
    //width: 100%;
    width: $nav-width;
    margin-left: $nav-margin-left;

    height: $top-nav-height;
    padding-left: $nav-padding-left;
    padding-right: $nav-padding-right;
    background-color: $top-nav-bg;

    .avatar-container {
      display: inline-block;
      width: 40px;
      height: 40px;
      background-color: $theme-main-color;
      border-radius: 50%;
      border: 2px solid $theme-main-color;
      overflow: hidden;

      img {
        height: 100%;
        width: 100%;
      }

      p {
        color: $theme-additional-color;
        font-weight: 400;
        height: 100%;
        width: 100%;
        line-height: 100%;
        font-size: 1.4rem;
        text-align:center; 
        vertical-align: middle;  
        padding-top:20%;
      }
    }

    .menu-icon-container {
      padding: 0;
      font-size: $font-size-base;
      fill: black;
    }

    .navbar-brand-container {
      position: absolute;
      z-index: 3;
      height: 100%;
      left: $navbar-brand-container-left;
      top: 0;
    }

    .nav-item {
      padding: 0;
      height: 100%;
    }

    .dropdown.navbar-dropdown {
      .dropdown-toggle {
        padding: 0;
        &:after {
           display: none;
         }
      }

      &.show {
        @include media-breakpoint-up(lg) {
          .dropdown-menu {
            left: auto;
            right: 0;
          }
        }
        &:after {
           position: absolute;
           bottom: -$dropdown-show-b;
           right: calc(50% - 10px);
           width: 0;
           height: 0;
           display: block;
           content: '';
           border-left: 10px solid transparent;
           border-right: 10px solid transparent;
           border-bottom: 10px solid $darkest-gray;
         }
      }

      .dropdown-menu {
        margin-top: $dropdown-show-b;
        padding-top: 0;
        width: 100%;
      }


      .dropdown-item {
        height: $navbar-dd-item-height;
        cursor: pointer;
        font-size: $font-size-base;

        &:hover, &:active, &:focus, &.active {
          outline: none;
        }

        &.plain-link-item {
          color: $brand-primary;

          &:hover, &:active, &:focus {
            background: $dropdown-background;
          }

          .plain-link {
            text-decoration: none;
          }
        }
      }
    }

    .notify {
      position: relative;

      &::after {
         content: '';
         position: absolute;
         right: -6px;
         top: -6px;
         background-color: $brand-primary;
         height: 12px;
         width: 12px;
         border-radius: 50%;
       }
    }

    .i-nav-notification.notify::after {
      right: -4px;
      top: 0;
    }

    @include media-breakpoint-down(md) {
      width: $nav-mobile-width;
      margin-left: 0;
      height: $top-mobile-nav-height;
      padding: $nav-mobile-pt $nav-mobile-padding-h $nav-mobile-pb $nav-mobile-padding-h;

      .navbar-brand-container {
        opacity: 0;
        width: $nav-mobile-brand-width;
        top: $nav-mobile-brand-top;
        left: $nav-mobile-brand-left;
        height: auto;
        .navbar-brand {
          height: $font-size-smaller;
          padding: 0;
          font-size: $font-size-smaller;
        }
      }

      .dropdown.navbar-dropdown {
        &.show {
          display: flex;
          &:after {
            bottom: -$dropdown-mobile-show-b;
            z-index: 2;
          }
          .dropdown-menu {
            margin-top: $dropdown-mobile-show-b;
            left: auto;
            &.last {
              right: 0;
            }
          }
        }
      }
    }
  }
</style>
