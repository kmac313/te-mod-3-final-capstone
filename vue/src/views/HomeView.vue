<template>
  <div class="home">
    <HeroComponent></HeroComponent>
    <FeaturedComponent></FeaturedComponent>
  </div>
</template>

<script>
import HeroComponent from "../components/HeroComponent.vue";
import FeaturedComponent from "../components/FeaturedComponent.vue";
import productService from "../services/ProductService";
export default {
  components: {
    HeroComponent,
    FeaturedComponent,
  },
  methods: {
    // Move to App.vue
    addProducts() {
      productService.getProducts().then((data) => {
        const regularToppings = data.data["Regular Topping"];
        const premiumToppings = data.data["Premium Topping"];
        const sizes = data.data["Size"];
        const crusts = data.data["Crust"];
        const sauces = data.data["Sauce"];
        const drinks = data.data["Drink"];

        for(let regTopping of regularToppings ) {
          this.$store.commit("ADD_TOPPING", regTopping);
        }

        for(let premTopping of premiumToppings ) {
          this.$store.commit("ADD_PREMIUM_TOPPING", premTopping);
        }

        for(let size of sizes ) {
          this.$store.commit("ADD_SIZE", size);
        }

        for(let crust of crusts ) {
          this.$store.commit("ADD_CRUST", crust);
        }


        for(let sauce of sauces ) {
          this.$store.commit("ADD_SAUCE", sauce);
        }

        for(let drink of drinks ) {
          this.$store.commit("ADD_DRINK", drink);
        }
        
      })
      
    },
  },
  beforeMount() {
    if(this.$store.state.inventory.crust.length === 0) {
      this.addProducts();
    }
    
  },
};
</script>
