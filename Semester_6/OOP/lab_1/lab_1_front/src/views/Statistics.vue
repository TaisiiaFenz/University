<template>
    <div>
        <div class="section">
            <p class="title">In which month were the most booked tours:</p>
            <p>March <span>3</span></p>
            <p>April <span>4</span></p>
            <p>May <span>3</span></p>
        </div>
        <div class="section">
            <p class="title">The most booked month:</p>
            <p>March <span>4</span></p>
            <p>April <span>4</span></p>
            <p>May <span>0</span></p>
            <p>June <span>1</span></p>
            <img src="../assets/pic.png">
        </div>
        <div class="section">
            <p class="title">The most booked tours:</p>
            <p>Spain - proud south flower <span>3</span></p>
            <p>Rest for soul <span>2</span></p>
            <p>Swiss fondue <span>2</span></p>
        </div>
        <div class="section">
            <p class="title">The most active users:</p>
            <p>Alica Fiar book <span>7</span> tours</p>
            <p>Vasia Leleka book <span>3</span> tours</p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Statistics"
    }
</script>

<style scoped>
    .section {
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.2);
    }

    .title {
        font-size: 23px;
    }

    span {
        color: #008B8B;
        font-weight: bold;
        font-size: 20px;
    }

    img {
        margin: 0 auto;
        width: 600px;
    }

</style>
