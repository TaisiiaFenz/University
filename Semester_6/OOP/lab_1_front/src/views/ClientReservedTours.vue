<template>
    <div>
<!--        <ul class="list" v-if="listOfClientTours">-->
<!--            <li v-for="list of listOfClientTours"></li>-->
<!--        </ul>-->
<!--        <div v-else>-->
<!--            Sorry, no tour chosen-->
<!--        </div>-->
    </div>
</template>

<script>
    export default {
        name: 'ClientReservedTours',
        computed: {
            listOfClientTours() {
                //return list of tours
                return null;
            }
        }
    };
</script>
