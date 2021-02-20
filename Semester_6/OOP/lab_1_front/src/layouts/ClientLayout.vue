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
                {title: "Tours", url: "/client-tours"},
                {title: "Reserved tours", url: "/client-reserved-tours"},
                {title: "Accepted tours", url: "/client-accepted-tours"}
            ]
        })
    }
</script>
