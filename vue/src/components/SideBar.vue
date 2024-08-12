<template>
    <div class="sidebar-container">
      <ul>
        <li v-on:click="$emit('change-table-inventory')">Inventory</li>
        <li v-on:click="$emit('change-table-order')">Orders</li>
        <li v-on:click="$emit('change-table-specialty')">Specialty Items</li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
  
  }
  </script>
  
  <style scoped>
  .sidebar-container {
      position: fixed;
      width: 220px;
      border-right: 1px solid #d3d3d3;
      height: 100vh;
      box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
      background-color: #1a1a1a; 
      color: #fff;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      padding-top: 80px;
      overflow-x: hidden; 
  }
  
  .sidebar-container ul {
      list-style: none;
      padding: 0;
      margin: 0;
      width: 100%;
  }
  
  .sidebar-container ul li {
      background-color: #2e2e2e;
      color: #fff;
      padding: 15px 20px;
      box-sizing: border-box; 
      width: 100%; 
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.3s ease;
      border-left: 5px solid transparent;
  }
  
  .sidebar-container ul li:hover {
      background-color: #e61d25; 
      border-left: 5px solid #fff; 
  }
  
  .sidebar-container ul li:not(:last-child) {
      border-bottom: 1px solid #383838; 
  }
  </style>
  