<template>
    <ul class="tour-list">
        <li v-for="tour of reservedToursByClient"
            :key="tour"
        >
            <div class="tour-info">
                <p class="tour-info__name">{{tour.tour.name}}</p>
                <p>{{tour.tour.tourType}}</p>
                <p>{{tour.tour.transportType}}</p>
            </div>
            <div class="tour-country">
                <p>{{tour.tour.country}}</p>
            </div>
            <div class="tour-price">
                <p>{{tour.tour.price}}</p>
            </div>
            <div class="tour-status">
                <p>{{tour.status}}</p>
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: 'ClientReservedTours',
        async mounted() {
            await this.$store.dispatch('fetchReservedToursByClient');
        },
        computed: {
            reservedToursByClient() {
                return this.$store.getters.reservedToursByClient;
            }
        }
    };
</script>
