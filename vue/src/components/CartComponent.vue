<template>
  <div class="cart-section-container">
    <h1>Your Cart</h1>
    <div v-for="(item, index) in allItems"  :key="index">
        <h2>Item: {{ item?.description }}</h2>
        <p>Price: {{ item?.price }}</p>
    </div>
    <div>
        <button @click="submitOrder">Submit Order</button>
    </div>
  </div>
</template>

<script>
import invoiceService from '../services/InvoiceService.js';
export default {
    
    data() {
        return {
            allItems: this.$store.state.currentOrder,
            invoice: {
                "items": this.$store.state.cart,
                "creditcard": this.$store.state.invoice.creditcard,
                "isDelivery": this.$store.state.invoice.isDelivery,
                "address": this.$store.state.invoice.address
            }
        }
    },
    methods: {
        submitOrder() {
            const invoice = invoiceService.sendOrder(this.invoice).then((data) => {return data.data})
            console.log(invoice)
        }
        
    }
}
</script>

<style>
.cart-section-container {
    position: fixed;
    background-color: #fff;
    height: 100vh;
    width: 50vw;
    top: 100px;
    right: 0;
    z-index: 99999999999999;
    
}
</style>