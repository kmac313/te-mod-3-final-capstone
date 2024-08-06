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
            name: "Hawian",
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
            name: "Hawian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Hawian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Hawian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
          {
            name: "Hawian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo"
          },
        ],
        cart: [
          
        ]
      },
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
      }
    },
  });
  return store;
}
