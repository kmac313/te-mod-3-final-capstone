<template>
  <div class="pizza-card-container">
    <div class="pizza-card" :style="pizzaStyle"></div>
    <div class="featured-pizza-card-details">
      <div class="featured-pizza-card-text-container">
        <h2 class="featured-pizza-card-text">{{ pizza.name }} Pizza</h2>
        <p class="featured-pizza-card-description">
          Our {{ pizza.name }} pizza is the best pizza you will ever eat!
        </p>
      </div>

      <img
        class="arrow-image"
        src="../assets/arrow.png"
        alt="Pizza"
        style="width: 8%"
        @click="this.$router.replace('/pizza')"
      />
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
.pizza-card {
  position: relative;
  height: 200px;
  background-size: cover;
  background-position: center;
  border-radius: 9px 9px 0px 0px;
  background-image: url('../assets/Hawian.jpg');
  padding: 0px 30px;
}

.featured-pizza-card-details {
  display: flex;
  align-items: center;
  justify-content: space-between;
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
