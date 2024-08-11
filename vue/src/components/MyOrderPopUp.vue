<template>
  <div
    class="drink-size-pop-up-container"
    v-bind:class="{ 'popup-hidden': showPopUp === false }"
  >
    <div class="drink-size-option-container">
      <h2>Order Information</h2>

      <div class="order-info-container">
        <div class="order-info">
          <p class="order-info-subheader">Total:</p>
          <p>${{ order.invoice?.total }}</p>
        </div>
        <div class="order-info">
          <p class="order-info-subheader">Status:</p>
          <p>{{ isComplete ? "Complete" : "In Progress" }}</p>
        </div>
        <div class="order-info">
          <p class="order-info-subheader">Order Type:</p>
          <p>{{ order.invoice?.delivery ? "Delivery" : "Carryout" }}</p>
        </div>
        <div class="order-info">
          <p class="order-info-subheader">Order Date:</p>
          <p>{{ order.invoice?.timestamp.slice(0, 10) }}</p>
        </div>
      </div>
      <div v-if="isComplete == false">
        <button @click="cancelOrder">Cancel Order</button>
      </div>
      <!-- Order Items -->
      <div class="order-items-container">
        <div class="order-items">
          <h3>Items</h3>
          <!-- Pizzas Section -->
          <div v-if="order?.pizzas?.length > 0">
            <div
              class="order-item"
              v-for="(item, index) in order?.pizzas"
              :key="index"
            >
              <p>{{ item.pizzaName }}</p>
              <p>${{ item.total }}</p>
            </div>
          </div>

          <!-- Additional Items Section -->
          <div v-if="order?.other?.length > 0">
            <div
              class="order-item"
              v-for="(item, index) in order?.other"
              :key="index"
            >
              <p>
                <strong>{{ item.description }}</strong>
              </p>
              <p>${{ item.price }}</p>
              <p>{{ item.productCategoryDescription }}</p>
            </div>
          </div>
        </div>
      </div>

      <div class="drink-size-btns">
        <button v-on:click="$emit('hide-popup')" class="cancel-drink-size">
          Close
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import invoiceService from "../services/InvoiceService";

export default {
  data() {
    return {
      isComplete: this.order?.invoice?.complete
    };
  },
  props: {
    order: Object,
    showPopUp: Boolean,
  },
  methods: {
    cancelOrder() {
      const newInvoice = {
        ...this.order.invoice
      };
      newInvoice.complete = true;
      this.isComplete = true;
      console.log(newInvoice);
      invoiceService
        .updateOrder(newInvoice.invoiceId, newInvoice)
        .then((data) => {
          this.$router.replace('/myorders')
        });
    },
  },
  
};
</script>

<style>
.drink-size-pop-up-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  background-color: #00000058;
  height: 100vh;
  width: 100vw;
  z-index: 9999999999999999999999999;
}

.drink-size-option-container {
  background-color: #fff;
  width: 60%;
  max-height: 90vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 15px;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 20px 0px;
}

.order-items-container {
  width: 100%;
  overflow-y: auto;
}

.popup-hidden {
  visibility: hidden;
}

.drink-size-btns {
  width: 60%;
  display: flex;
  justify-content: center;
  gap: 30px;
}

.drink-size-btns button {
  padding: 15px 30px;
  font-size: 1em;
  cursor: pointer;
}

.cancel-drink-size {
  color: #e61d25;
  border: 1px solid #000;
  background-color: #fff;
}

.cancel-drink-size:hover {
  background-color: #000;
  color: #fff;
  border-color: #000;
}

.order-info {
  display: flex;
  justify-content: center;
  width: 30%;
  text-align: left;
}

.order-info-subheader {
  font-weight: bold;
  padding: 0px 10px;
}

.order-info-container {
  display: flex;
  width: 100%;
  padding: 10px 15px;
  justify-content: space-around;
  border-bottom: 1px solid #dcdcdc;
}

.order-item {
  padding: 5px 15px;
  margin-left: 45px;
  border: 1px solid #dcdcdc;
  width: 60%;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  margin: 5px 20px;
}

.order-items h3 {
  text-align: center;
  font-size: 2em;
  margin: 5px;
}
</style>
