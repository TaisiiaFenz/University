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
                <p>{{tour.price}}$</p>
            </div>
            <p class="tour-duration">
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
                let date = new Date(startData);
                date.setDate(date.getDate() + +duration);
                let endMonth = date.getMonth() + 1;
                let endDay = date.getDate();
                endMonth = (`${endMonth}`.length == 1) ? `0${endMonth}` : endMonth;
                endDay = (`${endDay}`.length == 1) ? `0${endDay}` : endDay;
                let endData = date.getFullYear() + "-" + endMonth + "-" + endDay;

                const formData = {
                    "tourId": event.target.id,
                    "startDate": startData,
                    "endDate": endData
                };

                try {
                    await this.$store.dispatch('reserveTourByClient', formData);
                } catch (e) {
                    alert("Sorry, sth go wrong:(");
                }
            }
        }
    };
</script>

<style scoped>
    .tour-list li {
        margin-bottom: 20px;
    }
    .tour-info {
        width: 250px;
        height: 120px;
    }
    .tour-duration {
        width: 90px;
    }
</style>
