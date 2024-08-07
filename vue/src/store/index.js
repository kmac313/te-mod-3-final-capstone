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

        sauce : [
          {name: "Marinara",
            id: 1
          },

          {name: "Cheese",
            id: 2
          }
        ],

        drinks: [
          {name: "Coca-Cola",
            price: "2.00",
            id: 1
          },

          {name: "Sprite",
            price: "2.00",
            id: 2
          },

          {name: "Pepsi",
            price: "2.00",
            id: 3
          },

          {name: "Minute Maid",
            price: "2.00",
            id: 4
          },

        ],

        
      },
      cart: [
          
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
      ADD_TO_CART(state, item) {
        let newId = state.cart.length
        item.id = newId
        state.cart.push(item)
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
      }
    },
  });
  return store;
}
