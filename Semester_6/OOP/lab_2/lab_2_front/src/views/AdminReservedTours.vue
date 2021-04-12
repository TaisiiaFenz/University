<template>
    <ul class="tour-list">
        <li v-for="tour of reservedTours"
            :key="tour"
        >
            <div class="tour-client">
                <p class="tour-client__client">Client</p>
                <p class="tour-client__name">{{tour.client.firstName}} {{tour.client.lastName}}</p>
            </div>
            <div class="tour-info">
                <p class="tour-info__name">{{tour.tour.name}}</p>
                <p>{{tour.tour.country}}</p>
                <p>{{tour.tour.tourType}}</p>
            </div>
            <div class="tour-country">
                <p class="tour-client__client">Price</p>
                <p>{{tour.tour.price}}$</p>
            </div>
            <div class="tour-date">
                <p class="tour-client__client">Start/end date</p>
                <p class="tour-date__txt">{{tour.startDate.year}}.{{tour.startDate.month}}.{{tour.startDate.day}}</p>
                <p class="tour-date__txt">{{tour.endDate.year}}.{{tour.endDate.month}}.{{tour.endDate.day}}</p>
            </div>

            <div>
                <div>
                    <p class="tour-client__client">
                        Status: <span class="tour-status">{{ tour.status }}</span>
                    </p>
                </div>
                <p>
                    <select class="tour-discount tour-block"
                    >
                        <option v-for="option in discountOptions"
                                :key="option"
                                :value = "option"
                                class="tour-discount__item"
                        >
                           {{option}}
                        </option>
                    </select>
                </p>
            </div>
            <div class="tour-buttons tour-block">
                <button class="tour-button" v-on:click="approveTour()" :id="tour.reservationId">Approve</button>
                <button class="tour-button" v-on:click="notApproveTour()" :id="tour.reservationId">Not approve</button>
            </div>
        </li>
    </ul>
</template>

<script>
    export default {
        name: 'AdminReservedTours',
        async mounted() {
            if (!Object.keys(this.$store.getters.discountOptions).length) {
                await this.$store.dispatch('fetchDiscountOptions');
            }
            if (!Object.keys(this.$store.getters.reservedTours).length) {
                await this.$store.dispatch('fetchReservedTours');
            }
        },
        computed: {
            tours() {
                return this.$store.getters.tours;
            },
            discountOptions() {
                return this.$store.getters.discountOptions;
            },
            reservedTours() {
                return this.$store.getters.reservedTours;
            }
        },
        methods: {
            async approveTour() {
                let statusElement = event.target.parentNode.previousElementSibling.children[0].firstElementChild.firstElementChild;
                statusElement.innerText = "ACCEPTED";
                let discountIndex = event.target.parentNode.previousElementSibling.children[1].firstElementChild.selectedIndex;
                const formData = {
                    "reservationId": event.target.id,
                    "discountId": discountIndex + 1
                };
                try {
                    await this.$store.dispatch('approveTour', formData);
                } catch (e) {
                    alert("Sorry, smth go wrong :(");
                }
            },
            async notApproveTour() {
                let statusElement = event.target.parentNode.previousElementSibling.children[0].firstElementChild.firstElementChild;
                statusElement.innerText = "NOT_ACCEPTED";
                const formData = {
                    "reservationId": event.target.id
                };
                try {
                    await this.$store.dispatch('notApproveTour', formData);
                } catch (e) {
                    alert("Sorry, smth go wrong :(");
                }
            }
        }
    };
</script>

<style scoped>
    .tour-info {
        width: 200px;
    }

    .tour-client__client {
        font-size: 12px;
        font-weight: bold;
        color: #dedad9;

    }
    .tour-client__name {
        font-weight: bold;
        font-size: 17px;
    }
    .tour-discount__item {
        font-size: 12px;
    }

    .tour-list li {
        padding-left: 20px;
        margin-bottom: 20px;
    }

    .tour-date__txt {
        margin: 0 auto;
        font-size: 12px;
        padding: 0;
        margin: 0;
    }
</style>
