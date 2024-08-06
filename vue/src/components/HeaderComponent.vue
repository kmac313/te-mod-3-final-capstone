<template>
  <div>
    <div class="header">
      <div class="header-logo">
        <img
          src="src\assets\Super_Pizza_Mascot_Logo-removebg-preview.png"
          alt="Pizza"
          style="width: 55%"
        />
      </div>

      <nav>
        <div class="home-nav-btn">
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        </div>
        
        <div class="menu-btn" v-on:click.prevent="toggleMenu">
          MENU <img src="../assets/down-arrow.png" />
        </div>
      </nav>
      <div>
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
        <router-link
          class="router-link-active"
          v-bind:to="{ name: 'login' }"
          v-if="$store.state.token == ''"
          >Login</router-link
        >
      </div>
    </div>
    <ul class="header-nav-links hidden" id="header-nav-links">
      <router-link v-bind:to="{ name: 'home' }">
        <li>CUSTOM PIZZA</li>
      </router-link>
      <router-link v-bind:to="{ name: 'home' }">
        <li>SPECIALTY PIZZA</li>
      </router-link>
      <router-link v-bind:to="{ name: 'home' }">
        <li>DRINKS</li>
      </router-link>
    </ul>
  </div>
</template>

<script>

export default {
  
  methods: {
    toggleMenu() {
      const menuItems = document.getElementById("header-nav-links");

      menuItems.classList.contains("show")
        ? menuItems.classList.remove("show")
        : menuItems.classList.add("show");
      return;
    },
  },
};
</script>

<style scoped>
.header {
  position: relative;
  background-color: #fff;
  display: grid;
  grid-template-columns: 1fr 6fr 1fr;
  align-items: center;
  justify-items: start;
  font-size: 1.2em;
  border-bottom: #e0e0e0 solid 1px;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2);
  padding: 0px;
  margin: 0px;
  width: 100vw;
  z-index: 99999999;
}

.header-logo {
  text-align: center;
}

nav {
  text-transform: uppercase;
  display: flex;
  gap: 20px;
  align-items: center;
}

.router-link-active {
  color: black;
  text-decoration: none;
}

.router-link-active:visited {
  color: black;
}

.menu-btn {
  cursor: pointer;
  padding: 0.3em 0.6em;
  text-transform: uppercase;
  border: none;
  background-color: white;
  font-size: 0.95em;
  display: flex;
  gap: 5px;
}

.menu-btn img {
    width: 20px;
}

.header-nav-links {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 5em;
  left: 14em;
  list-style: none;
  background-color: white;
  padding: 0px 10px;
  border: #e6e6e6 solid 1px;
  z-index: 5;
  visibility: hidden;
  transition: opacity 0.8s ease, transform 0.3s ease; 
  transform: translateY(-100px);
}
.show {
  visibility: visible;
}

.header-nav-links.show { 
  opacity: 1; 
  transform: translateY(0); 
}

.header-nav-links .router-link-active {
  border-bottom: #a9a9a9 solid 1px;
  padding: 10px 8px 10px 8px;
  width: 100%
}

.header-nav-links .router-link-active:nth-child(3) {
  border-bottom: none;
}


.header-nav-links li:hover,
.header-nav-links .router-link-active:hover {
  background-color: #e61d25;
  color: #fff;
}

.drop {
  appearance: none;
  position: relative;
  display: inline-block;
}

.dropdown {
  appearance: none;
  width: 50%;
  font-size: 1.15rem;
  padding: 0.3em 0.1em;
  background-color: #fff;
  border: none;
  border-radius: 0.25rem;
  color: #000;
  cursor: pointer;
}

.home-nav-btn, .menu-btn {
    border-bottom: 3px solid transparent; 
    transition: border-color 0.8s ease-in-out; 
}

.home-nav-btn:hover, .menu-btn:hover {
    border-bottom: 3px solid #e61d25; 
}


</style>
