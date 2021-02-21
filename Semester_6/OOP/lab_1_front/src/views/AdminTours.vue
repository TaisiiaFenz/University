<template>
    <form @submit.prevent="submitTours">
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
                            <div class="tour-checkbox">
                                <p>
                                    <input
                                            type="checkbox"
                                            id="checkbox`${tour.id}`"
                                            class="custom-checkbox"
                                            v-model="tour.isLastMinuteTour"
                                    ></input>
                                    <label for="checkbox`${tour.id}`"></label>
                                </p>
                            </div>
                        </li>
        </ul>
        <div class="card-action">
            <div>
                <button
                        class="btn waves-effect waves-light auth-submit"
                        type="submit"
                >
                    Save changes
                </button>
            </div>
        </div>
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
            async submitTours() {
                const formData = [];
                for (let tour of this.tours) {
                    formData[tour.id] = `${tour.isLastMinuteTour}`;
                }
                try {
                    await this.$store.dispatch('updateTours', formData);
                } catch (e) {}


                //обновляем туры после изменений
                await this.$store.dispatch('fetchTours');
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
