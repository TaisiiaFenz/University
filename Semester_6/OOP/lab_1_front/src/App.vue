<template>
  <div id="app">
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
