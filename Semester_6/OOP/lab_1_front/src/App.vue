<template>
  <div id="app">
<!--    <div id="nav">-->
<!--      <router-link to="/">Home</router-link> |-->
<!--      <router-link to="/about">About</router-link>-->
<!--    </div>-->
      <component :is="layout">
            <router-view />
      </component>
  </div>
</template>

<script>
    import AdminLayout from '@/layouts/AdminLayout'
    import EmptyLayout from '@/layouts/EmptyLayout'
    import ClientLayout from '@/layouts/ClientLayout'
    export default {
        computed: {
            layout() {
                console.log(this.$route.meta);
                return (this.$route.meta.layout || 'empty') + '-layout';
            }
        },
        components: {
            AdminLayout,
            EmptyLayout,
            ClientLayout
        }
    }
</script>

<style>
@import '~materialize-css/dist/css/materialize.min.css';
  @import 'assets/index.css';
</style>
