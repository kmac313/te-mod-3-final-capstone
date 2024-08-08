<template>
    <div class="all-pizza-card-container">
      <div class="pizza-card" :style="pizzaStyle"></div>
      <div class="featured-pizza-card-details">
        <div class="featured-pizza-card-text-container">
          <h2 class="featured-pizza-card-text">{{ pizza.name }} Pizza</h2>
          <p class="featured-pizza-card-description">
            Our {{ pizza.name }} pizza is the best pizza you will ever eat!
          </p>
        </div>
  
        <button class="add-to-cart-btn" v-on:click="this.$router.replace('pizza/custom')" >
            Add to Cart
        </button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "FeaturedPizzaCard",
    props: {
      pizza: Object,
    },
    
    methods: {},
    computed: {
      pizzaStyle() {
          return {
              // Images must be placed inside of public folder
              backgroundImage: `url('./${this.pizza.name}.jpg')`
          };
      }
  }
    // Your component's JavaScript logic goes here
  };
  </script>
  
  <style scoped>
  .all-pizza-card-container {
    display: grid;
    grid-template-columns: 1fr 4fr;
    border-radius: 9px;
    border: 1px solid #e1e1e1;
    margin-top: 20px;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
    width: 100%;
  }

  

  .all-pizza-card-container .add-to-cart-btn {
    justify-self: start;
    background-color: #e61d25;
    color: #fff;
    border-radius: 15px;
    border: none;
    padding: 15px 30px;
    cursor: pointer;
  }
  .all-pizza-card-container .add-to-cart-btn:hover {
    background-color: #000;
    color: #fff;
  }
  .pizza-card {
    position: relative;
    height: 200px;
    background-size: cover;
    background-position: center;
    border-radius: 9px 0px 0px 9px;
    background-image: url('../assets/Hawian.jpg');
    padding: 0px 30px;
  }
  
  .all-pizza-card-container .featured-pizza-card-details {
    display: flex;
    align-items: center;
  }
  
  .featured-pizza-card-details img {
      cursor: pointer;
  }
  
  .featured-pizza-card-details .featured-pizza-card-text {
    font-size: 1.8em;
    color: #333;
    font-size: large;
  }
  
  .featured-pizza-card-details .featured-pizza-card-description {
    font-size: 1em;
    color: #333;
    font-size: medium;
  }
  
  .pizza-card-container {
    border: #d8d8d8 solid 1px;
    border-radius: 10px;
  }
  
  .featured-pizza-card-text-container {
      width: 70%;
      padding: 0px 10px;
  }
  </style>
  