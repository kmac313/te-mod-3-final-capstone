<template>
  <div class="custom-pizza-view-container">
    <h1>Custom Pizza</h1>
    <div class="pizza-size-cards">
      
      <PizzaSizeCardComponent
        v-for="(size, index) in size"
        v-bind:key="index"
        :size="size"
        @add-size="addSize(size)"
        >PizzaSizeCard</PizzaSizeCardComponent
      >
    </div>

    <!-- Toppings list -->
    <h2>Toppings</h2>
    <ToppingCardComponent
      v-for="(topping, index) in topping"
      v-bind:key="index"
      v-bind:topping="topping"
      @add-topping="addTopping(topping)"
    ></ToppingCardComponent>

    <!-- Crust list -->
    <h2>Crust</h2>
    <CrustCardComponent
      v-for="(crust, index) in crust"
      v-bind:key="index"
      v-bind:crust="crust"
      @add-crust="addCrust(crust)"
    ></CrustCardComponent>

    <!-- Sauce list -->
    <h2>Sauce</h2>
    <SauceCardComponent
      v-for="(sauce, index) in sauce"
      v-bind:key="index"
      v-bind:sauce="sauce"
      @add-sauce="addSauce(sauce)"
    ></SauceCardComponent>

    <!-- Add to order btn -->
    <div class="add-to-cart-section" v-if="totalPrice > 0">
        <button :disabled="totalPrice == 0" @click="addToCart()">Add to Order</button>
        <p><span>Total Price:</span> ${{ totalPrice > 0 ? totalPrice : "" }}</p>
    </div>
    
  </div>
</template>

<script>
import CrustCardComponent from "../components/CrustCardComponent.vue";
import PizzaSizeCardComponent from "../components/PizzaSizeCardComponent.vue";
import ToppingCardComponent from "../components/ToppingCardComponent.vue";
import SauceCardComponent from "../components/SauceCardComponent.vue";

export default {
  data() {
    return {
      customPizza: {
        name: "",
        price: 0.00,
        crust: {},
        sauce: {},
        size: {},
        topping: [],
      },
      allPizzas: this.$store.state.inventory.specialtyPizza,
      size: {
        small: {
            id: 1,
          price: 10.0,
          size: "Small",
        },
        medium: {
            id: 2,
          price: 12.0,
          size: "Medium",
        },
        large: {
            id: 3,
          price: 14.0,
          size: "Large",
        },
      },

      topping: [...this.$store.state.inventory.toppings, ...this.$store.state.inventory.premiumToppings],
      crust: this.$store.state.inventory.crust,
      sauce: this.$store.state.inventory.sauce,
    };
  },
  components: {
    PizzaSizeCardComponent,
    ToppingCardComponent,
    CrustCardComponent,
    SauceCardComponent,
  },

  methods: {
    addTopping(topping) {
      const toppingElem = document.getElementById(`topping-${topping.productId}`);

      if (toppingElem.checked) {
        this.customPizza.topping.push(topping);
      } else if (!toppingElem.checked) {
        this.customPizza.topping = this.customPizza.topping.filter(
          (t) => t.productId !== topping.productId
        );
      }
    },

    addCrust(crust) {
      const crustElem = document.getElementById(`crust-${crust.productId}`);

      if (crustElem.checked) {
        this.customPizza.crust = crust;
        const allOtherCrustOptions =
          document.getElementsByClassName("crust-checkbox");
        for (let currCrust of allOtherCrustOptions) {
          if (currCrust.id == "crust-" + crust.productId) {
            continue;
          }

          currCrust.checked = false;
        }
      } else if (!crustElem.checked) {
        this.customPizza.crust = {};
      }
    },

    addSauce(sauce) {
      const sauceElem = document.getElementById(`sauce-${sauce.id}`);

      if (sauceElem.checked) {
        this.customPizza.sauce = sauce;
        const allOtherSauceOptions =
          document.getElementsByClassName("sauce-checkbox");
        for (let currSauce of allOtherSauceOptions) {
          if (currSauce.id == "sauce-" + sauce.id) {
            continue;
          }

          currSauce.checked = false;
        }
      } else if (!sauceElem.checked) {
        this.customPizza.crust = "";
      }
    },

    addSize(size) {
      const sizeElem = document.getElementById(`size-${size.id}`);
       
      if (!sizeElem.classList.contains("card-highlighted")) {
        this.customPizza.size = size;
        const allOtherSizeOptions =
          document.getElementsByClassName("pizza-size-card-container");
        for (let currSize of allOtherSizeOptions) {
          if (currSize.id == "size-" + size.id) {
            currSize.classList.add("card-highlighted");
            continue;
          }

          currSize.classList.remove("card-highlighted");
        }

        console.log(this.customPizza)
      } else if (sizeElem.classList.contains("card-highlighted")) {
        sizeElem.classList.remove("card-highlighted");
        this.customPizza.size = {};
      }
    },

    addToCart() {
        // Type is string
        this.customPizza.price = this.totalPrice

        // Add to cart in store
        this.$store.commit("ADD_TO_CART", this.customPizza);
        console.log(this.$store.state.cart)

        // Push to pizzas
        this.$router.replace("/pizza")
    }

  },
  computed: {
    totalPrice(){
        if(this.customPizza.crust.productId && this.customPizza.size.id && this.customPizza.sauce.id) {
            let totalComputedPrice = this.customPizza.size.price;
            if(this.customPizza.topping.length > 0) {
                for(let topping of this.customPizza.topping) {
                    totalComputedPrice += topping.price
                }
            }
            return totalComputedPrice.toFixed(2);
        }
        else {
            return 0;
        }
    }
  }
};
</script>

<style>
.custom-pizza-view-container {
  padding: 20px 50px;
}
.pizza-size-cards {
  display: flex;
  justify-content: space-between;
  gap: 20px;
  padding: 10px;
  width: 50%;
}
.add-to-cart-section {
    display: flex;
    position: fixed;
    bottom: 0;
    width: 80vw;
    justify-content: space-between;
    align-items: center;
    padding: 0px 30px;
    background-color: #fff;
    border: 1px solid #000;
    border-radius: 5px;
    height: 100px;
}

.add-to-cart-section button {
    background-color: #e61d25;
    color: #fff;
    border: none;
    border-radius: 15px;
    padding: 15px 35px;
    cursor: pointer;
    font-size: 1.3em;
}

.add-to-cart-section button:hover {
    background-color: #000;
}

.add-to-cart-section p {
    font-size: 1.3em;
}

.add-to-cart-section p span {
    font-weight: bold;
}
</style>
