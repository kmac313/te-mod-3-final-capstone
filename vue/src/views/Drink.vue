<template>
  <div class="drink-view-container">
    <div class="drink-view-components">
      <h2 class="drink-header">Drinks</h2>
      <div
        v-for="(drink, index) in allDrinks"
        v-bind:key="{ index }"
        class="drink-view-components"
      >
        <DrinkComponent
          v-bind:drink="drink"
          @show-popup="showSizePopUp(drink.productId)"
        />
      </div>
    </div>
  </div>
  <DrinkSizePopUp
    :showPopUp="popUpVisible"
    @hide-popup="hideSizePopUp()"
    @add-to-order="addToOrder()"
  ></DrinkSizePopUp>
  <Toast :message="'Successfully added to order'" v-if="showToast" />
</template>

<script>
import DrinkComponent from "../components/DrinkComponent.vue";
import DrinkSizePopUp from "../components/DrinkSizePopUp.vue";
import Toast from "../components/Toast.vue";
export default {
  data() {
    return {
      allDrinks: this.$store.state.inventory.drinks,
      popUpVisible: false,
      currDrink: {},
      showToast: false,
    };
  },
  components: {
    DrinkComponent,
    DrinkSizePopUp,
    Toast,
  },
  methods: {
    showSizePopUp(id) {
      this.popUpVisible = true;
      this.currDrink = this.allDrinks.find((drink) => drink.productId === id);
      console.log(this.currDrink)
    },
    hideSizePopUp() {
      this.popUpVisible = false;
      console.log(this.popUpVisible);
    },

    addToOrder() {
      // Add to current order in store
      this.$store.commit("ADD_TO_CURR_ORDER", this.currDrink);
      console.log(this.$store.state.currentOrder);

      // Add to other cart
      this.$store.commit("ADD_TO_OTHER_CART", this.currDrink.productId);
        console.log(this.$store.state.cart);
      this.popUpVisible = false;
      this.showToast = true;
      this.showToast = true;
      setTimeout(() => {
        this.showToast = false;
      }, 1500);
    },
  },
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
