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
                <button class="tour-button" v-on:click="approveTour()" :id="tour.id">Approve</button>
                <button class="tour-button" v-on:click="notApproveTour()" :id="tour.id">Not approve</button>
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
        },
        computed: {
            tours() {
                return this.$store.getters.tours;
            },
            discountOptions() {
                console.log(this.$store.getters.discountOptions);
                return this.$store.getters.discountOptions;
            }
        },
        methods: {
            async approveTour() {
                console.log(event.target.id);
                let tour = this.tours.find(item => item.id == event.target.id);
                let discountIndex = event.target.parentNode.previousElementSibling.firstElementChild.selectedIndex;
                const formData = {
                    "reservationId": event.target.id,
                    "discountId": discountIndex
                };

                try {
                    //тут чтото вернется и мы сможем проверить на какую страницу его отправить
                    await this.$store.dispatch('approveTour', formData);
                } catch (e) {}
            },
            async notApproveTour() {
                const formData = {
                    "reservationId": event.target.id
                };
                try {
                    //тут чтото вернется и мы сможем проверить на какую страницу его отправить
                    await this.$store.dispatch('notApproveTour', formData);
                } catch (e) {}
            }
        }
    };
</script>
