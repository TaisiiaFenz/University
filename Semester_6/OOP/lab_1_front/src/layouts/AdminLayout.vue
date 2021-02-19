<template>
        <div class="app-main-layout">
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

<!--            <div class="fixed-action-btn">-->
<!--                <a class="btn-floating btn-large blue" href="#">-->
<!--                    <i class="large material-icons">add</i>-->
<!--                </a>-->
<!--            </div>-->
        </div>
</template>
<script>
    import NavBar from "../components/app/NavBar";
    export default {
        components: {NavBar},
        data: () => ({
            links: [
                {title: "Add client", url: "/add-client"},
                //{title: "Add tour", url: "/add-tour"},
                {title: "Tours", url: "/admin-tours"},
                {title: "Reserved tours", url: "/reserved-tour"},
                {title: "Accepted tours", url: "/accepted-tour"}
            ]
        })
    }
</script>
