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
      <div class="login-div">
        <p
          v-on:click.prevent="logOut"
          v-if="$store.state.token != ''"
          class="logout-btn"
          >Logout</p
        >
        <router-link
          class="router-link-active"
          v-bind:to="{ name: 'login' }"
          v-if="$store.state.token == ''"
          >Login</router-link
        >

        <img class="cart" src="../assets/cart.png" alt="cart" />
      </div>
    </div>
    <ul class="header-nav-links hidden" id="header-nav-links">
      
        <li @click.prevent="changePath('pizza')">PIZZAS</li>      
      
        <!-- Change path to /drink -->
        <li @click.prevent="changePath('drink')">DRINKS</li>
      
    </ul>
    <toast v-if="showToast" />
  </div>
</template>

<script>
import Toast from './Toast.vue';

export default {
  components: { Toast },

  data() {
    return {
      showToast: false
    }
  },
  
  methods: {
    toggleMenu() {
      const menuItems = document.getElementById("header-nav-links");

      menuItems.classList.contains("show")
        ? menuItems.classList.remove("show")
        : menuItems.classList.add("show");
      return;
    },
    logOut() {
      this.showToast = true;
      setTimeout(() => {
          this.showToast = false;
          this.$router.replace("/logout");
            }, 1500);

            
    },

    changePath(path) {
      const menuItems = document.getElementById("header-nav-links");
      menuItems.classList.remove('show')
      this.$router.replace(path)
    }
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

.header-nav-links li {
  color: black;
  text-decoration: none;
}

.header-nav-links li {
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
  transition: opacity 0.8s ease, transform 0.4s ease; 
  transform: translateY(-100px);
}
.show {
  visibility: visible;
}

.header-nav-links.show { 
  opacity: 1; 
  transform: translateY(0); 
}

.header-nav-links li {
  border-bottom: #a9a9a9 solid 1px;
  padding: 10px 8px 10px 8px;
  width: 100%;
  cursor: pointer;
}

.header-nav-links li:nth-child(2) {
  border-bottom: none;
}


.header-nav-links li:hover {
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

.logout-btn {
  cursor: pointer;
}

.cart {
  width: 30px;
  cursor: pointer;
}

.login-div {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 20px;
}


</style>
