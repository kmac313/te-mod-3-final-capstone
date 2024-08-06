<template>
  <div class="pizza-size-card-container">
    <div class="pizza-size-card" :style="pizzaStyle"></div>
    <div class="pizza-size-card-details">
      <div class="pizza-size-card-text-container">
        <h2 class="pizza-size-card-text">{{ size }} Pizza</h2>
        <p class="pizza-size-card-description">
          ${{ price }}
        </p>
      </div>

    </div>
  </div>
</template>

<script>
export default {
    name: 'PizzaSizeCardComponent',

    props: {
        price: {
            type: Number,
            required: true
        },

        size: {
            type: String,
            required: true
        }
    },

}
</script>

<style scoped>


.pizza-size-card-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px;
    border: 1px solid rgb(255, 167, 167);
    border-radius: 10px;
    padding: 10px;
    text-align: center;
    cursor: pointer;
}
</style>