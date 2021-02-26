<template>
    <ul class="tour-list">
        <li v-for="tour of tours"
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
            <p>
                <input
                        type="number"
                        min="3" max="10"
                        :id="tour.id"
                >
                <label :for="tour.id">Number of days</label>
            </p>
            <p>
                <input
                        type="date"
                        :id="tour.id"
                >
                <label :for="tour.id">Start date</label>
            </p>
            <div class="tour-buttons tour-block">
                <button class="tour-button" v-on:click="reserveTour()" :id="tour.id">Reserve</button>
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: 'ClientTours',
        computed: {
            tours() {
                return this.$store.getters.tours;
            }
        },
        methods: {
            async reserveTour() {
                let startData = event.target.parentNode.previousElementSibling.firstElementChild.value;
                let duration = event.target.parentNode.previousElementSibling.previousElementSibling.firstElementChild.value;
                const formData = {
                    "reservationId": event.target.id,
                    "startData": startData,
                    "duration": duration
                };

                try {
                    //тут чтото вернется и мы сможем проверить на какую страницу его отправить
                    await this.$store.dispatch('reserveTourByClient', formData);
                } catch (e) {}
            }
        }
    };
</script>
