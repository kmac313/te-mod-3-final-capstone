<template>
  <div id="capstone-app">
    <!-- Header -->
    <div id="nav">
      <HeaderComponent />
    </div>
    <!-- Body -->
    <div>
      <router-view />
    </div>
    
    <!-- Footer -->
    <div id="footer">
      <FooterComponent />
    </div>
  </div>
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
import FooterComponent from "./components/FooterComponent.vue";
import productService from "./services/ProductService";
export default {
  components: {
    HeaderComponent,
    FooterComponent,
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

        let storedPizzas = localStorage.getItem('pizza')
      
        if(storedPizzas) {
          storedPizzas = JSON.parse(storedPizzas)
          console.log(storedPizzas)
          for(let item of storedPizzas) {
            this.$store.commit('ADD_TO_CURR_ORDER', item);
          }
        }

        let storedDrinks = localStorage.getItem('drink')
      
        if(storedDrinks) {
          storedDrinks = JSON.parse(storedDrinks)
          console.log(storedDrinks)
          for(let item of storedDrinks) {
            this.$store.commit('ADD_TO_CURR_ORDER', item);
          }
        }

        let storedZipCode = localStorage.getItem('zipcode');

        if(storedZipCode) {
          this.$store.commit('ADD_ZIPCODE', parseInt(storedZipCode))
        }

        let storedOrderType = localStorage.getItem('orderType');

        if(storedOrderType) {
          this.$store.commit('UPDATE_ORDER_TYPE', storedOrderType)
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

<style>
@import url("https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap");
@import url("https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap");
* {
  font-family: "Nunito", sans-serif;
}

body {
  padding: 0px;
  margin: 0px;
  overflow-x: hidden;
}

a, .router-link-active, .router-link-active:visited, .router-link-active:active {
        text-decoration: none;
        color: black;
}

.header-nav-links {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 5em;
  left: 14em;
  list-style: none;
  background-color: white;
  padding: 0px 10px;
  border: #e6e6e6 solid 1px;
  z-index: 5;
  visibility: hidden;
  transition: opacity 0.8s ease, transform 0.3s ease; 
  transform: translateY(-100px);
}
.show {
  visibility: visible;
}

.header-nav-links.show { 
  opacity: 1; 
  transform: translateY(0); 
}

.header-nav-links .router-link-active {
  border-bottom: #a9a9a9 solid 1px;
  padding: 10px 8px 10px 8px;
  width: 100%
}

.header-nav-links .router-link-active:nth-child(3) {
  border-bottom: none;
}


.header-nav-links li:hover,
.header-nav-links .router-link-active:hover {
  background-color: #e61d25;
  color: #fff;
}
</style>
