<template>
    <div>
        <ul class="list" v-if="listOfClientTours">
<!--            <li v-for="list of listOfClientTours">-->
<!--                <select v-model="selected">-->
<!--                    <option v-for="option in options" v-bind:value="option.value">-->
<!--                        {{ option.text }}-->
<!--                    </option>-->
<!--                </select>-->
<!--                <button onclick="reserveTour">Save tour</button>-->
<!--            </li>-->
        </ul>
        <div v-else>
            Sorry, no reserved tours
        </div>
    </div>
</template>

<script>
    export default {
        name: 'AdminReservedTours',
        // data: {
        //     selected: 'А',
        //     options: [
        //         { text: 'New', value: 'А' },
        //         { text: 'Accepted', value: 'Б' },
        //         { text: 'Not accepted', value: 'В' }
        //     ]
        // },
        computed: {
            listOfTours() {
                //return list of tours
                return null;
            }
        },
        methods: {
            acceptTour() {
                //save tour to bd as reserved
            }
        }
    };
</script>
