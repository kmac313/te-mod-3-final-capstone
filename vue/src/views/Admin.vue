<template>
  <div class="admin-container">
    <SideBar />
    <div class="admin-content-container">
        <h1>Admin Dashboard</h1>
    </div>
    
  </div>
</template>

<script>
import SideBar from "../components/SideBar.vue";
export default {
  components: {
    SideBar,
  },
};
</script>

<style>
.admin-container {
    display: flex;
    height: 90vh;
}

.admin-content-container {
    margin-top: 80px;
    margin-left: 320px;
}
</style>
