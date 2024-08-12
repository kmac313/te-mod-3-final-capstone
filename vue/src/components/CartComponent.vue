<template>
  <div class="cart-new-overlay">
    <div class="cart-section-container">
      <div class="cart-btns">
        <button class="cart-close-btn" @click="$emit('close-cart')">
          Close
        </button>
      </div>
      <h1 class="cart-header">Your Cart</h1>
      <!-- User zipcode -->
      <div class="cart-zipcode">
        <div v-if="zipcode == null">
          <form class="request-cart-zipcode">
            <label for="zipcode">Please enter your zip code</label>
            <input
              id="zipcode"
              type="text"
              pattern="[0-9]{5}"
              placeholder="zipcode"
              required
            />
            <button class="submit-zipcode" @click="setZipCode">Save</button>
          </form>
        </div>
        <div class="show-cart-zipcode" v-if="zipcode != null">
          <p>Zip code: {{ zipcode }}</p>
          <button class="submit-zipcode" @click="setZipCodeToNull">
            Change
          </button>
        </div>
      </div>

      <!-- Carryout or delivery -->
      <div class="carryout-delivery" v-if="zipcode !== null">
        <h2>Carryout or Delivery</h2>

        <div class="order-type-container" v-if="orderType.length > 0">
          <div class="order-type-change-header">
            <p class="order-type-text">
              <strong>{{ orderType }}</strong>
            </p>
            <p class="change-order" @click="setOrderToNull">Change</p>
          </div>

          <p class="eta">{{ eta }}</p>
          <p class="address">{{ address }}</p>
        </div>

        <select
          class="cart-select"
          v-model="selectedOption"
          @change="updateOrder"
          v-if="orderType.length === 0"
        >
          <option value="carryout">Carryout</option>
          <option value="delivery">Delivery</option>
        </select>
      </div>

      <!-- Items -->
      <div v-if="orderType !== ''" class="order-container">
        <h2>Your order:</h2>
        <h4>Total: ${{ finalPrice }}</h4>
        <div v-if="allItems.length == 0">
          <p>Your cart is empty</p>
        </div>

        <!-- list all items -->
        <div
          class="order-header"
          v-for="(item, index) in allItems"
          :key="index"
        >
          <div class="item-heading">
            <h3>{{ item?.description }}</h3>
            <p>Price: ${{ parseInt(item?.price).toFixed(2) }}</p>
          </div>
          <h4 v-if="item?.sauce">
            Sauce: {{ item?.sauce.description.split("-")[0] }}
          </h4>
          <h4 v-if="item?.crust">Crust: {{ item?.crust.description }}</h4>
          <div v-if="item?.topping && item?.topping.length > 0">
            <h4>Toppings:</h4>
            <p v-for="(topping, index) in item.topping" :key="index">
              {{ topping?.description }}
            </p>
          </div>
          <div class="remove-from-cart-btn">
            <button @click="removeFromCart(item)">Remove from cart</button>
          </div>
        </div>
      </div>

      <!-- Payment info -->
      <div
        class="payment-container"
        v-if="orderType !== '' && allItems.length > 0"
      >
        <h2>Payment</h2>

        <form>
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
            v-model="paymentmethod"
            @change="console.log(paymentmethod)"
          />
          <div class="deliveryInformation" v-if="orderType == 'delivery'">
            <input
              required
              type="text"
              placeholder="Street name"
              name="street"
              v-model="street"
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
              v-model="state"
            />
            <input
              required
              type="text"
              placeholder="Zip code"
              name="zipcode"
            />
          </div>
          <button
            type="submit"
            @click.prevent="submitOrder"
            
          >
            Checkout
          </button>
        </form>
      </div>
    </div>
    <Toast :message="toastMessage" v-if="showToast" />
  </div>
</template>

<script>
import Toast from "../components/Toast.vue";
import invoiceService from "../services/InvoiceService";

export default {
  data() {
    return {
      selectedOption: this.$store.state.isDelivery.orderType ? this.$store.state.isDelivery.orderType : ( localStorage.getItem('orderType') ? localStorage.getItem('orderType') : ""),
      street: '',
      city: '',
      state: '',
      zipcode: localStorage.getItem('zipcode') ? localStorage.getItem('zipcode') : this.$store.state.isDelivery.zipcode,
      paymentmethod: '',
      eta: '',
      showToast: false,
      address: '123 Main St, Cleveland, Ohio 44108'
    };
  },
  components: {
    Toast,
  },
  methods: {
    submitOrder() {
      let pizzaItems = this.$store.state.cart.pizza
      if(pizzaItems.length > 0) {
        for (let pizza of pizzaItems) {
          pizza.shift();
          
        }
      }
      
      const delivery = this.$store.state.isDelivery.orderType == 'delivery' ? true : false
      const newInvoice = {
        "items": this.$store.state.cart,
        "credit_card": this.paymentmethod,
        "is_delivery": delivery,
        "address": this.street + " " + this.city + " " + this.state + " " + this.zipcode
      }
      console.log(newInvoice)
      const invoice = invoiceService.sendOrder(newInvoice).then((data) => {
        try {
          this.$store.commit("ADD_INVOICE", data.data);
          this.$store.commit("EMPTY_CART");
          if (this.$store.state.token.length > 0) {
            this.toastMessage =
              "We have received your order. Check you email for order information";

            this.showToast = true;
            setTimeout(() => {
              this.showToast = false;
              this.$router.replace("/myOrders");
              this.$emit("close-cart");
            }, 2500);
           
          } else {
            this.toastMessage =
              "We have received your order. Check your email for order information";
            this.showToast = true;
            setTimeout(() => {
              this.showToast = false;
              this.$router.replace("/");
              this.$emit("close-cart");
            }, 2500);
          }
        } catch (err) {
          console.log("error");
          this.toastMessage = "Unable to process, your order. Please try again";
          this.showToast = true;
          setTimeout(() => {
            this.showToast = false;
          }, 3500);
        }
      });

      
    },
    updateOrder() {
      localStorage.setItem('orderType', this.selectedOption)
      this.$store.commit('UPDATE_ORDER_TYPE', this.selectedOption);
      console.log(this.$store.state.isDelivery.orderType)
    },

    setOrderToNull() {
      localStorage.setItem('orderType', '')
      this.$store.commit('UPDATE_ORDER_TYPE', '');
    },

    updateAddress() {
      this.$store.commit('UPDATE_ADDRESS', this.address)
      console.log(this.$store.state.invoice.address)
    },

    setZipCode() {
      const inputElem = document.getElementById("zipcode");

      const zip = inputElem.value;
      const pattern = /^\d{5}(?:-?\d{4})?$/;
      if (pattern.test(zip)) {
        this.$store.commit("ADD_ZIPCODE", zip);
        localStorage.setItem('zipcode', zip)
        this.zipcode = zip;
      } else {
        this.toastMessage = "Invalid zipcode format";
        this.showToast = true;
        setTimeout(() => {
          this.showToast = false;
        }, 3500);
      }
    },
    setZipCodeToNull() {
      this.$store.commit("ADD_ZIPCODE", "");
      this.zipcode = null;
    },

    // remove item from cart
    removeFromCart(item) {
      // remove from currentOrder
      this.$store.commit("REMOVE_FROM_CURRENT_ORDER", item.productId);

      // remove from cart
      const isPizza = this.$store.state.cart.pizza.find(
        (pizza) => pizza[0].id == item.id
      );
      console.log(isPizza);
      const isOther = this.$store.state.cart.other.find(
        (otherItem) => otherItem === item.productId
      );
     
        this.$store.commit("REMOVE_FROM_CART", item);
        console.log(this.$store.state.cart);
      

      // remove from localStorage
      // Check if appetizer
      if (item.productCategoryId == 7) {
        let storedSide = localStorage.getItem('sides')
        let objectSide = JSON.parse(storedSide)
        let newObjectSide = objectSide.filter((side) => side.productId !== item.productId)
        if(newObjectSide.length > 0) {
          localStorage.setItem('sides', newObjectSide)
        } else {
          localStorage.removeItem('sides')
        }
      }
      // Check if specialty pizza
      else if (item.productCategoryId == 10) {
        let storedSpecialtyPizza = localStorage.getItem('specialtypizza')
        let objectSpecialtyPizza = JSON.parse(storedSpecialtyPizza)
        let newObjectSpecialtyPizza = objectSpecialtyPizza.filter((pizza) => pizza.productId !== item.productId)
        if(newObjectSpecialtyPizza.length > 0) {
          localStorage.setItem('specialtypizza', newObjectSpecialtyPizza)
        } else {
          localStorage.removeItem('specialtypizza')
        }
      }
      // Check if drink
      else if (item.productCategoryId == 8) {
        let storedDrink = localStorage.getItem('drink')
        let objectDrink = JSON.parse(storedDrink)
        let newObjectDrink = objectDrink.filter((drink) => drink.productId !== item.productId)
        if(newObjectDrink.length > 0) {
          localStorage.setItem('drink', newObjectDrink)
        } else {
          localStorage.removeItem('drink')
        }
      }

      //  Check if dessert
      else if (item.productCategoryId == 9) {
        let storedDessert = localStorage.getItem('dessert')
        let objectDessert = JSON.parse(storedDessert)
        let newObjectDessert = objectDessert.filter((dessert) => dessert.productId !== item.productId)
        if(newObjectDessert.length > 0) {
          localStorage.setItem('dessert', newObjectDessert)
        } else {
          localStorage.removeItem('dessert')
        }
      }
      // Check if salad
      else if (item.productCategoryId == 6) {
        let storedSalad = localStorage.getItem('salads')
        let objectSalad = JSON.parse(storedSalad)
        let newObjectSalad = objectSalad.filter((salad) => salad.productId !== item.productId)
        if(newObjectSalad.length > 0) {
          localStorage.setItem('salads', newObjectSalad)
        } else {
          localStorage.removeItem('salads')
        }
      } else {
        let storedPizza = localStorage.getItem("pizza");
        storedPizza = JSON.parse(storedPizza);
        if (storedPizza) {
          let newStoredPizza = storedPizza.filter(
            (pizza) => pizza.id !== item.id
          );
          if (newStoredPizza.length > 0) {
            localStorage.setItem("pizza", newStoredPizza);
          } else {
            localStorage.removeItem("pizza");
          }
        }
      }
    },
  },

  computed: {
    allItems() {
      return this.$store.state.currentOrder;
    },
    orderType() {
      return this.$store.state.isDelivery.orderType;
    },
    finalPrice() {
      let allItems = this.$store.state.currentOrder;
      let total = 0.0;
      if (allItems.length > 0) {
        for (let item of allItems) {
          total += parseFloat(item.price);
        }
      }

      return total.toFixed(2);
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

.cart-zipcode {
  padding-bottom: 20px;
  width: 100%;
  border-bottom: 1px solid #1e1e1e;
}

.request-cart-zipcode {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.request-cart-zipcode label {
  text-align: center;
  margin-bottom: 10px;
}

.request-cart-zipcode input,
.request-cart-zipcode button {
  width: 70%;
  margin-bottom: 10px;
}

.submit-zipcode {
  background-color: #000;
  color: #fff;
  border: 1px solid #000;
  cursor: pointer;
  border-radius: 5px;
}

.submit-zipcode:hover {
  background-color: #fff;
  color: #000;
}

.show-cart-zipcode {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.show-cart-zipcode .submit-zipcode {
  width: 40%;
  height: 70%;
  padding: 5px 10px;
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
  width: 70%;
  text-align: center;
  align-items: center;
  padding-left: 30px;
  border-bottom: 1px solid #e6e6e6;
  margin: 10px 0px;
}

.order-container {
  display: flex;
  flex-direction: column;
  align-items: center;
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

.payment-container input,
.deliveryInformation input {
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

.remove-from-cart-btn button {
  background-color: #e61d25;
  border: none;
  border-radius: 5px;
  color: #fff;
  padding: 5px 5px;
  cursor: pointer;
}

.remove-from-cart-btn button:hover {
  background-color: #740004;
}
</style>
