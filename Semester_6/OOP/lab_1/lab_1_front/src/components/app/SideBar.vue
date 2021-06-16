<template>
    <ul class="sidenav app-sidenav" :class="{open: value}">
        <router-link
                v-for="link in links"
                :key="link.url"
                tag="li"
                active-class="active"
                :to="link.url"
                :exact="link.exact"
        >
            <a href="#" class="waves-effect waves-teal pointer">{{ link.title }}</a>
        </router-link>
    </ul>
</template>

<script>
    export default {
        props: {
            links: Array,
            value: Boolean
        }
    }
</script>
