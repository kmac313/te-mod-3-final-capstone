<template>
    <section class="featured-section">
        <h1>Featured!</h1>
        <div class="featured-pizzas-list-container">
            <div v-for="(pizza, index) in allPizzas" v-bind:key={index} >
                <FeaturedPizzaCard v-bind:pizza="pizza" v-if="index < 3" />
            </div>
        </div>
        
        
    </section>
</template>

<script>
import FeaturedPizzaCard from './FeaturedPizzaCard.vue';

export default {
    name: 'FeaturedComponent',
    data() {
        return {
            allPizzas: this.$store.state.inventory.specialtyPizza,
        }
    },    
    components: {
        FeaturedPizzaCard
    },
   
}
</script>

<style scoped>
.featured-pizzas-list-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    justify-items: center;
    gap: 1rem;
    padding: 0px 20px;
    transform: translateX(-100%);
    animation: slideIn 2s ease-in-out forwards;
}

.featured-section {
    padding: 0px 20px;
}

.featured-section h1 {
    font-size: 2em;
}

@keyframes slideIn {
    from {
        transform: translateX(-100%);
    }
    to {
        transform: translateX(0);
    }
}
</style>