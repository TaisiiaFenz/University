<template>
    <ul class="tour-list">
        <li v-for="tour of reservedTours"
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
            <p>
                <select class="tour-discount tour-block"
                >
                    <option v-for="option in discountOptions"
                            :key="option"
                            :value = "option"
                    >
                       {{option}}
                    </option>
                </select>
            </p>
            <div class="tour-buttons tour-block">
                <button class="tour-button" v-on:click="approveTour()" :id="tour.tour.id">Approve</button>
                <button class="tour-button" v-on:click="notApproveTour()" :id="tour.tour.id">Not approve</button>
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
                let discountIndex = event.target.parentNode.previousElementSibling.firstElementChild.selectedIndex;
                console.log(event.target.id);
                console.log(discountIndex);
                const formData = {
                    "reservationId": event.target.id,
                    "discountId": discountIndex
                };
                    await this.$store.dispatch('approveTour', formData);
                //} catch (e) {}
            },
            async notApproveTour() {
                const formData = {
                    "reservationId": event.target.id
                };
                    await this.$store.dispatch('notApproveTour', formData);
                //} catch (e) {}
            }
        }
    };
</script>
