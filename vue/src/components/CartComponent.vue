<template>
    <div class="cart-new-overlay" >
  <div class="cart-section-container">
    <h1 class="cart-header">Your Cart</h1>
    <div class="cart-btns">
        <button class @click="$emit('close-cart')">Close</button>
    </div>
    <div class="carryout-delivery">
        <h2>Carryout or Delivery</h2>


        <div v-if="orderType.length>0">
           <p class="order-type-text">{{ orderType }}</p>
           <p class="change-order" @click="updateOrder('')">Change</p>
           <p class="eta">{{ eta }}</p>
           <p class="address">{{ address }}</p>
        </div>

    

        <select class="cart-select" v-model="orderType" v-on:change="updateOrder(orderType)" v-if="orderType.length===0">
            <option value="carryout">Carryout</option>
            <option value="delivery">Delivery</option>
        </select>
    </div>

    <h2>Your order:</h2>
    <div class="order-header" v-for="(item, index) in allItems"  :key="index">
        <h3> {{ item?.description }}</h3>
        <p>Price: ${{ item?.price.toFixed(2) }}</p>
    </div>



    <h2>Payment method:</h2>
    <div class="payment-container">
        <div class="payment-radio-buttons">
        <input type="radio" id="creditcard" name="payment" value="creditcard" v-model="paymentmethod">
        <label for="creditcard">Credit Card</label>
        <input type="radio" id="cash" name="payment" value="cash" v-model="paymentmethod">
        <label for="cash">Cash</label>
         </div>
        <form v-if="paymentmethod=='creditcard'">
            <p>Please enter your credit card information:</p>
            <input required type="text" placeholder="First name" name="firstname">
            <input required type="text" placeholder="Last name" name="lastname">
            <input required type="email" placeholder="Email" name="email">
            <input required type="tel" placeholder="Phone number" name="phone" v-model="phoneNumber">
            <input required type="text" placeholder="Credit card number" name="cardnumber" v-model="invoice.creditcard">
            <div class="deliveryInformation" v-if="orderType == 'delivery'">
            <input required type="text" placeholder="Street name" name="street" v-model="invoice.street">
            <input required type="text" placeholder="City name" name="city" v-model="city">
            <input required type="text" placeholder="State" name="state" v-model="invoice.state">
            <input required type="text" placeholder="Zip code" name="zipcode" v-model="invoice.zipcode">
            </div>
            <button type="submit" @click="submitOrder" v-if="orderType.length > 0 && paymentmethod.length > 0">Checkout</button>

        </form>
        <form v-if="paymentmethod=='cash'">
            <p>Please enter your credit card information:</p>
            <input required type="text" placeholder="First name" name="firstname">
            <input required type="text" placeholder="Last name" name="lastname">
            <input required type="email" placeholder="Email" name="email">
            <input required type="tel" placeholder="Phone number" name="phone" v-model="phoneNumber">
            <div class="deliveryInformation" v-if="orderType == 'delivery'">
            <input required type="text" placeholder="Street name" name="street" v-model="invoice.street">
            <input required type="text" placeholder="City name" name="city" v-model="city">
            <input required type="text" placeholder="State" name="state" v-model="invoice.state">
            <input required type="text" placeholder="Zip code" name="zipcode" v-model="invoice.zipcode">
            </div>


            <div class="cart-btns">
        <button type="submit" @click="submitOrder" v-if="orderType.length > 0 && paymentmethod.length > 0">Checkout</button>

    </div>
        </form>
        </div> 
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
            },
            orderType: this.$store.state.isDelivery.orderType,
            eta: this.$store.state.isDelivery.eta,
            address: this.$store.state.isDelivery.address,
            paymentmethod: ''
        }
    },
    methods: {
        submitOrder() {
            const invoice = invoiceService.sendOrder(this.invoice).then((data) => {return data.data})
            console.log(invoice)
        },

        updateOrder(type) {
            this.$store.commit('UPDATE_ORDER_TYPE', type)
            this.orderType = type
                console.log(this.$store.state.isDelivery.orderType)
        }
        
    
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Calistoga&family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap');

.cart-header {
    font-family: 'Calistoga', cursive;
    font-size: 2rem;
    margin: 10px;
    width: 100%;
    border-bottom: 1px solid #000;
    text-align: center;
    
}
.cart-section-container {
    display: flex;
    position: fixed;
    background-color: #fff;
    height: 100vh;
    width: 30vw;
    top: 0;
    right: 0;
    z-index: 99999999999999;
    align-items: center;
    flex-direction: column;
    overflow-y: scroll;
    overflow-x: hidden;
    
}

.cart-btns button  {
margin: 10px;
}

.cart-new-overlay {
    position: fixed;
    background-color: rgba(0, 0, 0, 0.5);
    height: 100vh;
    width: 100vw;
    top: 0;
    left: 0;
    z-index: 9999999999999;
    display: flex;
    justify-content: center;
    align-items: center;
}

.carryout-delivery .cart-select {
    width: 100%;
    text-transform: uppercase;

}

.carryout-delivery .order-type-text {
    text-transform: uppercase;
}

.carryout-delivery .change-order {
    color: blue;
    cursor: pointer;
    text-decoration: underline;
}

.carryout-delivery {
    align-items: center;
    width: 100%;
    border-bottom: 1px solid #000;
    text-align: center;
}

.order-header {
    width: 100%;
    border-bottom: 1px solid #000;
    text-align: center;
    
}

.payment-container {
    display: flex;
    margin-bottom: 20px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-direction: column;
}

.payment-container input {
    display: flex;
    width: 80%;
    justify-content: center;
    margin-top: 10px;
}

.payment-container .payment-radio-buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
</style>