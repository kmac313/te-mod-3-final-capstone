<template>
  <div class="cart-new-overlay">
    <div class="cart-section-container">
      <div class="cart-btns">
        <button class="cart-close-btn" @click="$emit('close-cart')">
          Close
        </button>
      </div>
      <h1 class="cart-header">Your Cart</h1>

      <div class="carryout-delivery">
        <h2>Carryout or Delivery</h2>

        <div class="order-type-container" v-if="orderType.length > 0">
          <div class="order-type-change-header">
            <p class="order-type-text">
              <strong>{{ orderType }}</strong>
            </p>
            <p class="change-order" @click="updateOrder('')">Change</p>
          </div>

          <p class="eta">{{ eta }}</p>
          <p class="address">{{ address }}</p>
        </div>

        <select
          class="cart-select"
          v-model="orderType"
          v-on:change="updateOrder(orderType)"
          v-if="orderType.length === 0"
        >
          <option value="carryout">Carryout</option>
          <option value="delivery">Delivery</option>
        </select>
      </div>

      <h2>Your order:</h2>
      <div class="order-header" v-for="(item, index) in allItems" :key="index">
        <div class="item-heading">
          <h3>{{ item?.description }}</h3>
          <p>Price: ${{ parseInt(item?.price).toFixed(2) }}</p>
        </div>
        <h4 v-if="item?.sauce">
          Sauce: {{ item?.sauce.description.split("-")[0] }}
        </h4>
        <h4 v-if="item?.crust">Crust: {{ item?.crust.description }}</h4>
        <div v-if="item.topping && item?.topping.length > 0">
          <h4>Toppings:</h4>
          <p v-for="(topping, index) in item.topping" :key="index">
            {{ topping?.description }}
          </p>
        </div>
      </div>

      <h2>Payment method:</h2>
      <div class="payment-container">
        <div class="payment-radio-buttons">
          <input
            type="radio"
            id="creditcard"
            name="payment"
            value="creditcard"
            v-model="paymentmethod"
          />
          <label for="creditcard">Credit Card</label>
          <input
            type="radio"
            id="cash"
            name="payment"
            value="cash"
            v-model="paymentmethod"
          />
          <label for="cash">Cash</label>
        </div>
        <form v-if="paymentmethod == 'creditcard'">
          <p>Please enter your credit card information:</p>
          <input
            required
            type="text"
            placeholder="First name"
            name="firstname"
          />
          <input required type="text" placeholder="Last name" name="lastname" />
          <input required type="email" placeholder="Email" name="email" />
          <input
            required
            type="tel"
            placeholder="Phone number"
            name="phone"
            v-model="phoneNumber"
          />
          <input
            required
            type="text"
            placeholder="Credit card number"
            name="cardnumber"
            v-model="invoice.creditcard"
          />
          <div class="deliveryInformation" v-if="orderType == 'delivery'">
            <input
              required
              type="text"
              placeholder="Street name"
              name="street"
              v-model="invoice.street"
            />
            <input
              required
              type="text"
              placeholder="City name"
              name="city"
              v-model="city"
            />
            <input
              required
              type="text"
              placeholder="State"
              name="state"
              v-model="invoice.state"
            />
            <input
              required
              type="text"
              placeholder="Zip code"
              name="zipcode"
              v-model="invoice.zipcode"
            />
          </div>
          <button
            type="submit"
            @click="submitOrder"
            v-if="orderType.length > 0 && paymentmethod.length > 0"
          >
            Checkout
          </button>
        </form>
        <form v-if="paymentmethod == 'cash'">
          <p>Please enter your credit card information:</p>
          <input
            required
            type="text"
            placeholder="First name"
            name="firstname"
          />
          <input required type="text" placeholder="Last name" name="lastname" />
          <input required type="email" placeholder="Email" name="email" />
          <input
            required
            type="tel"
            placeholder="Phone number"
            name="phone"
            v-model="phoneNumber"
          />
          <div class="deliveryInformation" v-if="orderType == 'delivery'">
            <input
              required
              type="text"
              placeholder="Street name"
              name="street"
              v-model="invoice.street"
            />
            <input
              required
              type="text"
              placeholder="City name"
              name="city"
              v-model="city"
            />
            <input
              required
              type="text"
              placeholder="State"
              name="state"
              v-model="invoice.state"
            />
            <input
              required
              type="text"
              placeholder="Zip code"
              name="zipcode"
              v-model="invoice.zipcode"
            />
          </div>

          <div>
            <button
              type="submit"
              @click.prevent="submitOrder"
              v-if="orderType.length > 0 && paymentmethod.length > 0"
            >
              Checkout
            </button>
          </div>
        </form>
      </div>
    </div>
    <Toast :message="toastMessage" v-if="showToast" />
  </div>
</template>

<script>
import Toast from '../components/Toast.vue'
import invoiceService from '../services/InvoiceService'

export default {
  data() {
    return {
      allItems: this.$store.state.currentOrder,
      invoice: {
        items: this.$store.state.cart,
        creditcard: this.$store.state.invoice.creditcard,
        isDelivery: this.$store.state.invoice.isDelivery,
        address: this.$store.state.invoice.address,
      },
      orderType: this.$store.state.isDelivery.orderType,
      eta: this.$store.state.isDelivery.eta,
      address: this.$store.state.isDelivery.address,
      paymentmethod: "",
      showToast: false,
      toastMessage: 'This has not been changed'
    };
  },
  components: {
    Toast
  },
  methods: {
    submitOrder() {
      const invoice = invoiceService.sendOrder(this.invoice).then((data) => {
        try{
            this.$store.commit("ADD_INVOICE", data.data);
            this.$store.commit("EMPTY_CART")
        if (this.$store.state.token.length > 0) {
            this.toastMessage = 'We have received your order. Check you email for order information'
            
          this.showToast = true;
          setTimeout(() => {
            this.showToast = false;
            this.$router.replace("/myOrders");
            this.$emit('close-cart');
          }, 2500);
          
        } else {
            
            this.toastMessage = 'We have received your order. Check you email for order information'
          this.showToast = true;
          setTimeout(() => {
            this.showToast = false;
            this.$router.replace("/");
            this.$emit('close-cart');
          }, 2500);
          
        }
        }
        catch(err) {
        console.log('error')
        this.toastMessage = 'Unable to process, your order. Please try again'
        this.showToast = true;
        setTimeout(() => {
            this.showToast = false;
          }, 3500);
      }
        
      })
      
    },

    updateOrder(type) {
      this.$store.commit("UPDATE_ORDER_TYPE", type);
      this.orderType = type;
      console.log(this.$store.state.isDelivery.orderType);
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Calistoga&family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap");

.cart-header {
  font-family: "Calistoga", cursive;
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

.item-heading,
.order-type-change-header {
  display: flex;
  justify-content: start;
}

.order-type-change-header {
  justify-content: space-between;
}

.order-type-container {
  text-align: left;
  padding: 0px 15px;
}

button[type="submit"] {
  margin: 10px;
  background-color: #e61d25;
  color: #fff;
  border: none;
  border-radius: 15px;
  padding: 9px 25px;
  cursor: pointer;
  font-size: 1.3em;
}

button[type="submit"]:hover {
  background-color: #000;
}

.cart-close-btn {
  margin: 10px;
  background-color: #000;
  color: #fff;
  border: none;
  border-radius: 15px;
  padding: 9px 15px;
  cursor: pointer;
  font-size: 0.9em;
  border: 1px solid #000;
}

.cart-close-btn:hover {
  background-color: #fff;
  color: #000;
}

.cart-btns {
  display: flex;
  justify-content: end;
  width: 100%;
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
  width: 80%;
  text-transform: uppercase;
  margin: 10px 10px;
  cursor: pointer;
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
  text-align: center;
  align-items: center;
  padding-left: 30px;
  border: 1px solid #d1d1d1;
  margin: 10px 0px;
  box-shadow: 0 2px 4px 3px rgba(0, 0, 0, 0.085);
}

.order-header p,
.order-header h4,
.order-header h3,
.order-header h2,
.order-type-change-header p {
  padding: 5px;
  margin: 0px;
  text-align: left;
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

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
