<template>
    <div class="app-main-layout">
        <nav-bar @click="isOpen = !isOpen"></nav-bar>
        <side-bar
                :links="links"
                :value="isOpen"
        >
        </side-bar>
        <main class="app-content" :class="{full: !isOpen}">
            <div class="app-page">
                <router-view></router-view>
            </div>
        </main>
    </div>
</template>

<script>
    import SideBar from "./SideBar";
    import NavBar from "./NavBar";

    export default {
        components: {NavBar, SideBar},
        props: {
            links: Array
        },
        data: () => ({
            isOpen: true
        }),
        async mounted() {
            if (!Object.keys(this.$store.getters.tours).length) {
                await this.$store.dispatch('fetchTours');
            }
        }
    }
</script>
