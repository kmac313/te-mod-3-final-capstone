<template>
    <div class="pizza-card">
    </div>
    <div class="featured-pizza-card-details">
        <h2 class="featured-pizza-card-text">Featured Pizza
            <p class="featured-pizza-card-description">Our featured pizza is the best pizza you will ever eat!</p>
        </h2>
        <img class="arrow-image" src="../assets/arrow.png" alt="Pizza" style="width: 3%" />
    </div>
</template>

<script>
export default {
    name: 'FeaturedPizzaCard',
    // Your component's JavaScript logic goes here
}
</script>

<style scoped>

.pizza-card {
    position: relative;
    background-image: url('../assets/pizzaimage.jpg');
    height : 200px;
    background-size: cover;
    background-position: center;
    width: 25%;
    }

.featured-pizza-card-details {
    display: flex;
    align-items: center;
    }

    .featured-pizza-card-details .featured-pizza-card-text {
        margin-left: 20px;
        font-size: 1.5em;
        color: #333;
        width: 20%;
        font-size: large;
    }

    .featured-pizza-card-details .featured-pizza-card-description {
        font-size: 1em;
        color: #333;
        font-size: medium;
    }
    
</style>