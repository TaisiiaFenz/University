<template>
    <div>
        <ul>
<!--            <li v-for="list of listOfTours" v-bind:name="list.name">-->
<!--                <p class="">Name of tour</p>-->
<!--                <label for="startDate">Start date of tour</label>-->
<!--                <input id="startDate" type="date">-->
<!--                <label for="lastDate">Last date of tour</label>-->
<!--                <input id="lastDate" type="date">-->
<!--                <button onclick="reserveTour">Reserve tour</button>-->
<!--            </li>-->
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'ClientTours',
        computed: {
            listOfTours() {
                //return list of tours
                return null;
            }
        },
        methods: {
            reserveTour() {
                //save tour to bd as reserved
            }
        }
    };
</script>
