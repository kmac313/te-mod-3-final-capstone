<template>
  <div class="drink-view-container">
    <div class="drink-view-components">
        <h2 class="drink-header">Drinks</h2>
        <div v-for="(drink, index) in allDrinks" v-bind:key="{ index }" class="drink-view-components">
      <DrinkComponent v-bind:drink="drink" />
    </div>
    </div>
  </div>
</template>

<script>
import DrinkComponent from '../components/DrinkComponent.vue';


export default {
    data() {
        return {
            allDrinks: this.$store.state.inventory.drinks,
        }
    },
    components: {
        DrinkComponent
    }
};

</script>

<style>

 .drink-view-container {
        display: flex;
        flex-direction: column;
        column-gap: 30px;
        align-items: start;
        padding: 40px 40px;
    }

    .drink-view-components {
        width: 75%;
    }

    .drink-header {
        font-size: 2rem;
        font-weight: 700;
        margin-bottom: 20px;
    }
</style>