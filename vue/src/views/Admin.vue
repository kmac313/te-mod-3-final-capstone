<template>
  <div class="admin-container">
    <SideBar 
    @change-table-inventory="changeTableInventory" 
    @change-table-order="changeTableOrder"
    @change-table-specialty="changeTableSpecialty"
    
    />
    <div class="admin-content-container">
        <h1 class="admin-table-heading">{{currentMenuOption}}</h1>

        <!-- v-if currentMenuOption == inventory -->
        <!-- Inventory Table -->
        <InventoryTable v-if="currentMenuOption == 'Inventory'" />
        <!-- v-if currentMenuOption == orders -->
        <!-- orders Table -->
        <OrdersTable v-if="currentMenuOption == 'Orders'"/>
        <!-- v-if currentMenuOption == specialty -->
        <!-- SpecialtyPizza Table -->
        <SpecialtyPizzaTable v-if="currentMenuOption == 'Specialty'" />
    </div>
    
  </div>
</template>

<script>
import InventoryTable from '../components/InventoryTable.vue';
import OrdersTable from '../components/OrdersTable.vue';
import SideBar from "../components/SideBar.vue";
import SpecialtyPizzaTable from '../components/SpecialtyPizzaTable.vue';
export default {
  data() {
    return {
      currentMenuOption: 'Inventory'
    }
  },
  methods: {
    changeTableInventory() {
      this.currentMenuOption = 'Inventory'
    },

    changeTableOrder() {
      this.currentMenuOption = 'Orders'
    },

    changeTableSpecialty() {
      this.currentMenuOption = 'Specialty'
    }
  },
  components: {
    SideBar,
    InventoryTable,
    SpecialtyPizzaTable,
    OrdersTable
  },
  mounted() {
    let user = localStorage.getItem('user')
    user = JSON.parse(user)
    let role = user?.authorities[0]?.name

    if(role !== 'ROLE_ADMIN') {
      this.$router.replace('/myorders')
    }
  }
};
</script>

<style>
.admin-container {
    display: flex;
    height: 90vh;
    width: 95vw;
    margin-bottom: 300px;
}

.admin-content-container {
    margin-top: 50px;
    margin-left: 320px;
    width: 100%;
}

.admin-table-heading {
  text-align: center;
}
</style>
