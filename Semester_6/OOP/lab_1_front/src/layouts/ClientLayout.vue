<template>
    <div class="app-client-layout">
        <nav-bar></nav-bar>
        <ul class="sidenav app-sidenav open">
            <router-link
            v-for="link in links"
            :key="link.url"
            tag="li"
            active-class="active"
            :to="link.url"
            >
                <a href="#" class="waves-effect waves-teal pointer">{{ link.title }}</a>
            </router-link>
        </ul>
        <main class="app-content">
            <div class="app-page">
                <router-view></router-view>
            </div>
        </main>
    </div>
</template>

<script>
    import NavBar from "../components/app/NavBar";
    export default {
        components: {NavBar},
        data: () => ({
            links: [
                {title: "Tours", url: "/client-tours"},
                {title: "Reserved tours", url: "/client-reserved-tours"},
                {title: "Accepted tours", url: "/client-accepted-tours"}
            ]
        })
    }
</script>
