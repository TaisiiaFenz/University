<template>
    <form>
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
                            <div class="tour-checkbox" v-on:click="updateTour()">
                                <p>
                                    <input
                                            type="checkbox"
                                            :id="tour.id"
                                            class="custom-checkbox"
                                            v-model="tour.isLastMinuteTour"
                                    ></input>
                                    <label :for="tour.id"></label>
                                </p>
                            </div>
                        </li>
        </ul>
    </form>
</template>

<script>
    export default {
        name: "login",
        computed: {
            tours() {
                return this.$store.getters.tours;
            }
        },
        methods: {
            async updateTour() {
                // console.log(event.target.id);
                let checkBoxId;
                if (event.target.id) {
                    checkBoxId = event.target.id;
                    await this.$store.dispatch('updateTour', checkBoxId);
                }
            }
        }
    }
</script>

<style scoped>
    .card-action {
        width: 200px;
        float: right;
    }
</style>
