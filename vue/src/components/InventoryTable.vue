<template>
    <div class="toppings-table-container">
      <div class="table-wrapper">
        <table v-if="toppings.length > 0">
          <thead>
            <tr class="table-header">
              <th class="category-header">Category</th>
              <th class="description-header">Description</th>
              <th class="price-header">Price</th>
              <th class="quantity-header">Quantity</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(topping, index) in toppings" :key="index">
              <td>{{ topping?.product_category_description }}</td>
              <td>{{ topping?.description }}</td>
              <td>${{ topping?.price.toFixed(2) }}</td>
              <td>{{ topping?.quantity }}</td>
            </tr>
            <tr v-for="(drink, index) in drinks" :key="index">
              <td>{{ drink?.product_category_description }}</td>
              <td>{{ drink?.description }}</td>
              <td>${{ drink?.price.toFixed(2) }}</td>
              <td>{{ drink?.quantity }}</td>
            </tr>
            <tr v-for="(dessert, index) in desserts" :key="index">
              <td>{{ dessert?.product_category_description }}</td>
              <td>{{ dessert?.description }}</td>
              <td>${{ dessert?.price.toFixed(2) }}</td>
              <td>{{ dessert?.quantity }}</td>
            </tr>
            <tr v-for="(appetizer, index) in appetizers" :key="index">
              <td>{{ appetizer?.product_category_description }}</td>
              <td>{{ appetizer?.description }}</td>
              <td>${{ appetizer?.price.toFixed(2) }}</td>
              <td>{{ appetizer?.quantity }}</td>
            </tr>
            <tr v-for="(salad, index) in salads" :key="index">
              <td>{{ salad?.product_category_description }}</td>
              <td>{{ salad?.description }}</td>
              <td>${{ salad?.price.toFixed(2) }}</td>
              <td>{{ salad?.quantity }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import productService from '../services/ProductService';
  export default {
    data() {
      return {
        toppings: [],
        drinks: [],
        desserts: [],
        appetizers: [],
        salads: []
      };
    },
    methods: {
      allToppings() {
        productService.getAllToppings().then((data) => {
          const getToppings = [
            ...data.data['Regular Topping'],
            ...data.data['Premium Topping']
          ];
          this.toppings = getToppings;
        });
      },
      allDrinks() {
        productService.getAllDrinks().then((data) => {
          const getDrinks = [
            ...data.data['Drink'],
          ];
          this.drinks = getDrinks;
        });
      },
      allDesserts() {
        productService.getAllDesserts().then((data) => {
          const getDesserts = [
            ...data.data['Dessert'],
          ];
          this.desserts = getDesserts;
        });
      },
      allAppetizers() {
        productService.getAllAppetizers().then((data) => {
          const getAppetizers = [
            ...data.data['Appetizer'],
          ];
          this.appetizers = getAppetizers;
        });
      },
      allSalads() {
        productService.getAllSalads().then((data) => {
          const getSalads = [
            ...data.data['Salad'],
          ];
          this.salads = getSalads;
        });
      }
    },
    mounted() {
      this.allToppings();
      this.allDrinks();
      this.allDesserts();
      this.allAppetizers();
      this.allSalads();
    }
  };
  </script>
  
  <style>
  .toppings-table-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  
  .toppings-table-container h2 {
    text-transform: uppercase;
    font-size: 2em;
    font-weight: bold;
  }
  
  .table-wrapper {
    max-height: 300px; 
    overflow-y: auto;
    width: 100%;
    text-align: center;
  }
  
  .toppings-table-container table {
    width: 100%;
    border-collapse: collapse;
    border: 2px solid #ddd; 
  }
  
  .toppings-table-container thead th {
    position: sticky;
    top: 0;
    background-color: #f8f8f8; 
    z-index: 1; 
    padding: 12px;
    border-bottom: 2px solid #e61d25; 
    text-transform: uppercase;
  }
  
  .toppings-table-container tbody tr {
    cursor: pointer;
    border-bottom: 1px solid #ddd; 
  }
  
  .toppings-table-container tbody tr:hover {
    background-color: #f0f0f0; 
  }
  
  .toppings-table-container td {
    padding: 12px;
  }
  
  .category-header, .description-header, .price-header, .quantity-header {
    width: 25%;
  }
  </style>
  