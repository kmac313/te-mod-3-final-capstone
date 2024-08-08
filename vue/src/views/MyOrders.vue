<template>
  <div>
    <h2>My Orders</h2>
    <button v-on:click.prevent="getAllOrders"> Click me! </button>
    <!-- <p v-for="(invoice, index) in allInvoices" :key="index">{{ invoice.total }}</p> -->
    <!-- Add order table -->
    <!-- Create order component -->
  </div>
</template>

<script>
import invoiceService from '../services/InvoiceService';
export default {
  data() {
    return {
      
    }
  },

  methods: {
    getAllOrders() {
      invoiceService.getOrders().then((data) => {
        console.log(data);
      })
    }
  },

  computed: {
    allInvoices() {
      return this.$store.state.allInvoices;
    }
  }
}
</script>

<style></style>