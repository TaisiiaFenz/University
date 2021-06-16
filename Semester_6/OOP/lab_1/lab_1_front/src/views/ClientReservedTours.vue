<template>
    <ul class="tour-list">
        <li v-for="tour of reservedToursByClient"
            :key="tour"
        >
            <div class="tour-info">
                <p class="tour-info__name">{{tour.tour.name}}</p>
                <p>{{tour.tour.country}}</p>
                <p>{{tour.tour.tourType}}</p>
            </div>
            <div class="tour-price">
                <p class="tour-price__txt">{{tour.tour.price}}$</p>
                <p class="tour-txt" v-if="tour.tour.isLastMinuteTour">Last minute tour</p>
            </div>
            <div class="tour-date">
                <p class="tour-txt">Start/end date</p>
                <p class="tour-date__txt">{{tour.startDate.year}}.{{tour.startDate.month}}.{{tour.startDate.day}}</p>
                <p class="tour-date__txt">{{tour.endDate.year}}.{{tour.endDate.month}}.{{tour.endDate.day}}</p>
            </div>
            <div>
                <p class="tour-txt tour-status__txt">
                    Status: <span class="tour-status">{{ tour.status }}</span>
                </p>
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

<style scoped>
    .tour-list li {
        padding-right: 20px;
        margin-bottom: 20px;
    }

    .tour-info {
        width: 200px;
    }

    .tour-price {
        width: 150px;
    }

    .tour-txt {
        font-size: 12px;
        font-weight: bold;
        color: #dedad9;
    }

    .tour-date__txt {
        margin: 0 auto;
        font-size: 12px;
        padding: 0;
        margin: 0;
    }

    .tour-status__txt {
        width: 130px;
    }
</style>
