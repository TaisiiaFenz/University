<template>
    <ul class="tour-list">
        <li v-for="tour of reservedToursByClient"
            :key="tour"
        >
            <div class="tour-info">
                <p class="tour-info__name">{{tour.name}}</p>
                <p>{{tour.tourType}}</p>
                <p>{{tour.transportType}}</p>
            </div>
            <div class="tour-country">
                <p>{{tour.country}}</p>
            </div>
            <div class="tour-price">
                <p>{{tour.price}}</p>
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: 'ClientReservedTours',
        async mounted() {
            let clientId = 0;
            await this.$store.dispatch('fetchReservedToursByClient', clientId);
        },
        computed: {
            reservedToursByClient() {
                return this.$store.getters.reservedToursByClient;
            }
        }
    };
</script>
