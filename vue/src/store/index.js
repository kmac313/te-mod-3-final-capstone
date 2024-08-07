import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      inventory: {
        specialtyPizza: [
          {
            name: "Hawaiian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo",
            toppings: [],
          },
          {
            name: "Buffalo Chicken",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Double Pepperoni",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Chicken Parmesan",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Stuffed Crust",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Garlic Bread",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
        ],
        size: [],

        toppings: [],

        premiumToppings: [],

        crust: [],

        sauce : [],

        drinks: [],

        
      },
      currentOrder: [
        
      ],
      cart: {
          pizza: [

          ],
          other: [

          ]

        },
        invoice: {
          creditcard: "123456789235", 
          isDelivery: true,
          address: "123 Main Street",
          
        }
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
        const newId = state.currentOrder.length + 1;
        item.currOrderId = newId;
        state.currentOrder.push(item)
      },
      ADD_TO_PIZZA_CART(state, pizza) {
        state.cart.pizza.push(pizza)
      },
      ADD_TO_OTHER_CART(state, item) {
        state.cart.other.push(item)
      },
      ADD_TOPPING(state, topping) {
        state.inventory.toppings.push(topping)
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
      ADD_DRINK(state, drink) {
        state.inventory.drinks.push(drink)
      }
    },
  });
  return store;
}
