import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      inventory: {
        specialtyPizza: [
          
        ],
        size: [],

        toppings: [],

        premiumToppings: [],

        crust: [],

        sauce : [],

        drinks: [],

        desserts: [],

        sides: [],

        salads: []

        
      },
      showCart: false,
      currentOrder: [
        
      ],

      isDelivery: {
        orderType: '',
        address: "123 Main Street",
        eta: "15-20 minutes",
        zipcode: null
      },
      
      cart: {
          pizza: [ 

          ],
          other: [

          ],

        },
        

        allInvoices: [

        ]
    },
    
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      ADD_TO_CURR_ORDER(state, item) {
        state.currentOrder.push(item)
      },
      ADD_TO_PIZZA_CART(state, pizza) {
        state.cart.pizza.push(pizza)
        console.log(state.cart.pizza)
      },
      ADD_TO_OTHER_CART(state, item) {
        state.cart.other.push(item)
      },
      ADD_REGULAR_TOPPING(state, topping) {
        state.inventory.toppings.push(topping)
      },
      ADD_SPECIALTY_PIZZA(state, pizza) {
        state.inventory.specialtyPizza.push(pizza)
      },
      ADD_PREMIUM_TOPPING(state, topping) {
        state.inventory.premiumToppings.push(topping)
      },
      ADD_CRUST(state, crust) {
        state.inventory.crust.push(crust)
      },
      ADD_SAUCE(state, sauce) {
        state.inventory.sauce.push(sauce)
      },
      ADD_SIZE(state, size) {
        state.inventory.size.push(size)
      },
      ADD_SALAD(state, salad) {
        state.inventory.salads.push(salad)
      },
      ADD_DESSERT(state, dessert) {
        state.inventory.desserts.push(dessert)
      },
      ADD_APPETIZER(state, side) {
        state.inventory.sides.push(side)
      },
      ADD_DRINK(state, drink) {
        state.inventory.drinks.push(drink)
      },
      UPDATE_CREDIT_CARD(state, number) {
        state.invoice.credit_card = number
      },
      UPDATE_INVOICE_ADDRESS(state, address) {
        state.invoice.address = address
      },
      UPDATE_ORDER_TYPE(state, orderType) {
        state.isDelivery.orderType = orderType
        
       
      },

      UPDATE_ORDER(state, newInvoice, isComplete) {
        for(let invoice of state.allInvoices) {
          if(invoice?.invoice?.invoiceId == newInvoice?.invoice?.invoiceId) {
            invoice.invoice.complete = isComplete;
          }
        }
      },

      ADD_ZIPCODE(state, zip) {
        state.isDelivery.zipcode = zip
        localStorage.setItem('zipcode', zip)
      },

      ADD_INVOICE(state, invoice) {
        state.allInvoices.push(invoice)
      },
      EMPTY_CART(state) {
        state.cart = {
          pizza: [],
          other: []
        },
        state.currentOrder = [];
        localStorage.removeItem('pizza')
        localStorage.removeItem('drink')
        localStorage.removeItem('size')
        localStorage.removeItem('salads')
        localStorage.removeItem('desserts')
        localStorage.removeItem('specialtypizza')

      },
      TOGGLE_CART(state, value) {
        state.showCart = value
      },
      REMOVE_FROM_CURRENT_ORDER(state, productId) {
       const updatedCart = state.currentOrder.filter((item) => item.productId !== productId)
       state.currentOrder = updatedCart
      },
      REMOVE_FROM_CART(state, item) {
        const isPizza = state.cart.pizza.find((pizza) => pizza[0].id == item.id);
        const isOther = state.cart.other.find((otherItem) => otherItem == item.productId);
        console.log(isOther)
        if(isPizza !== undefined) {
          state.cart.pizza = state.cart.pizza.filter((pizza) => pizza[0].id !== item.id)
        } else if (isOther !== undefined) {
          const updatedCart = state.cart.other.filter((otherItem) => item.productId !== otherItem)
          state.cart.other = updatedCart
          console.log(state.cart)
        }
        
        

       }
    },
  });
  return store;
}
