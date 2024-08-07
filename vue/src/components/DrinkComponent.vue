<template>
  <div class="drink-card-container">
    <div class="drink-card" :style="drinkStyle"></div>
    <div class="drink-card-details">
      <div class="drink-card-text-container">
        <h2 class="drink-card-text">{{ drink.name }} Drink</h2>
        <p class="drink-card-description">
          ${{ drink.price }} 
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
  name: "DrinkCard",
  props: {
    drink: Object,
  },
  methods: {},
  computed: {
    drinkStyle() {
        return {
            // Images must be placed inside of public folder
            backgroundImage: `url('./${this.drink.name}.jpg')`
        };
    }
}
  // Your component's JavaScript logic goes here
};
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Calistoga&family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap');


.drink-card-container {
  display: grid;
  grid-template-columns: 1fr 4fr;
  border-radius: 9px;
  border: 1px solid #e1e1e1;
  margin-top: 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  width: 100%;
}



.drink-card-container .add-to-cart-btn {
  justify-self: start;
  background-color: #e61d25;
  color: #fff;
  border-radius: 15px;
  border: none;
  padding: 15px 30px;
  cursor: pointer;
  margin-right: 30px;
  height: 50px;
  width: 150px;
  
}
.drink-card-container .add-to-cart-btn:hover {
  background-color: #000;
  color: #fff;
}
.drink-card {
  position: relative;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 9px 0px 0px 9px;
  background-image: url('../assets/Hawian.jpg');
  padding: 0px 30px;
  width: 200px
}

.drink-card-container .drink-card-details {
  display: flex;
  align-items: center;
}

.drink-card-details img {
    cursor: pointer;
}

.drink-card-details .drink-card-text {
  font-size: 1.8em;
  color: #333;
  font-size: large;
}

.drink-card-details .drink-card-description {
  font-size: 1em;
  color: #333;
  font-size: medium;
}

.drink-card-container {
  border: #d8d8d8 solid 1px;
  border-radius: 10px;
}

.drink-card-text-container {
  width: 70%;
  padding: 0px 10px;
}

</style>
