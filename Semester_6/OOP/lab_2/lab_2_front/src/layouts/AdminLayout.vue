<template>
    <main-layout
            :links="links"
    ></main-layout>
</template>

<script>
    import MainLayout from "../components/app/MainLayout";

    export default {
        components: {MainLayout},
        data: () => ({
            links: [
                {title: "Add client", url: "/add-client"},
                {title: "Tours", url: "/admin-tours"},
                {title: "Reserved tours", url: "/reserved-tour"}
            ]
        })
    }
</script>
