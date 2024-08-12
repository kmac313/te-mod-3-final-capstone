<template>
    <div class="toppings-table-container">
      <div class="table-wrapper">
        <table v-if="pizzas?.length > 0">
          <thead>
            <tr class="table-header">
              <th class="description-header">Description</th>
              <th class="price-header">Price</th>
              <th class="quantity-header">Quantity</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(pizza, index) in pizzas" :key="index">
              <td>{{ pizza?.description }}</td>
              <td>${{ pizza?.price.toFixed(2) }}</td>
              <td>{{ pizza?.quantity }}</td>
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
        pizzas: [],
      };
    },
    methods: {
      allSpecialtyPizzas() {
        productService.getAllSpecialtyPizzas().then((data) => {
          const getPizzas = [
            ...data.data['Specialty Pizza'],
          ];
          this.pizzas = getPizzas;
          console.log(this.pizzas)
        });
      },
      
    },
    mounted() {
      this.allSpecialtyPizzas();
      
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
  