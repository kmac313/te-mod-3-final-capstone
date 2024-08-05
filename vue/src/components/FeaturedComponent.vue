<template>
    <div>
        <h1>Featured!</h1>
        <FeaturedPizzaCard />
    </div>
</template>

<script>
import FeaturedPizzaCard from './FeaturedPizzaCard.vue';

export default {
    name: 'FeaturedComponent',
    components: {
        FeaturedPizzaCard
    },
    // Your component's logic goes here
}
</script>

<style scoped>
/* Your component's styles go here */
</style>