<template>
    <div class="header">
        <img src="src\assets\Super_Pizza_Mascot_Logo-removebg-preview.png" alt="Pizza" style="width:15%;">
    <nav>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'login' }">About</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'login' }">Cart</router-link>&nbsp;|&nbsp;

        <div class="drop">
        <select class="dropdown">   
            <option value="1" >  <router-link v-bind:to="{ name: 'home' }">Menu</router-link>&nbsp;|&nbsp; </option>
            <option value="2"><router-link v-bind:to="{ name: 'home' }">Custom Pizza</router-link>&nbsp;|&nbsp;</option>
            <option value="3"><router-link v-bind:to="{ name: 'home' }">Specialty Pizza</router-link>&nbsp;|&nbsp;</option>
            <option value="4"><router-link v-bind:to="{ name: 'home' }">Drink</router-link>&nbsp;|&nbsp;</option>
        </select>
        </div>

    </nav>
<h1>Pizza Header</h1>
</div>
</template>


<script>

</script>


<style>

.header {
    display: grid;
    grid-template-columns: 1fr 1fr;
    align-items: center;
    justify-items: start;
}

.drop {
    appearance: none;
    position: relative;
    display: inline-block;
}

.dropdown {
    appearance: none;
  width: 100%;
  font-size: 1.15rem;
  padding: 0.300em 2em 0.300em 0.5em;
  background-color: #fff;
  border: 1px solid #caced1;
  border-radius: 0.25rem;
  color: #000;
  cursor: pointer;
}



</style>