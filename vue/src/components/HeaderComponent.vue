<template>
  <div>
    <div :class="['header', { shrink: isShrunk }]">
      <div class="header-logo">
        <img
          src="../../public/Super_Pizza_Mascot_Logo-removebg-preview.png"
          alt="Pizza"
        />
        <img
          @click="toggleMobileMenu"
          class="hamburger-icon"
          src="../../public/hamburger-menu.jpg"
          alt="menu"
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
        <router-link
          class="login-div-btn"
          v-bind:to="{ name: 'login' }"
          v-if="$store.state.token == ''"
          >Login</router-link
        >
        <router-link
          class="login-div-btn"
          v-bind:to="{ name: 'myOrders' }"
          v-if="role == 'ROLE_USER'"
          >My Orders</router-link
        >
        <router-link
          class="login-div-btn"
          v-bind:to="{ name: 'admin' }"
          v-if="role == 'ROLE_ADMIN'"
          >Admin Dashboard</router-link
        >

        <p
          v-on:click.prevent="logOut"
          v-if="$store.state.token != ''"
          class="login-div-btn"
        >
          Logout
        </p>

        <img
          class="cart"
          src="../assets/cart.png"
          alt="cart"
          @click="openCart"
        />
      </div>
    </div>
    <ul
      :class="['header-nav-links', 'hidden', { shrink: isShrunk }]"
      id="header-nav-links"
    >
      <li @click.prevent="changePath('/pizza')">PIZZAS</li>

      <!-- Change path to /drink -->
      <li @click.prevent="changePath('/drink')">DRINKS</li>

      <li @click.prevent="changePath('/sides')">APPETIZERS</li>

      <li @click.prevent="changePath('/salad')">SALADS</li>

      <li @click.prevent="changePath('/dessert')">DESSERTS</li>
    </ul>
    <!-- Mobile Nav -->
    <ul :class="['mobile-nav', 'none', { shrink: isShrunk }]" id="mobile-nav">
      <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
      <li @click.prevent="changePath('/pizza')">PIZZAS</li>

      <!-- Change path to /drink -->
      <li @click.prevent="changePath('/drink')">DRINKS</li>

      <li @click.prevent="changePath('/sides')">APPETIZERS</li>

      <li @click.prevent="changePath('/salad')">SALADS</li>

      <li @click.prevent="changePath('/dessert')">DESSERTS</li>
      <li
        v-if="
          $store.state.token !== '' &&
          $store.state.user?.authorities[0].name !== 'ROLE_ADMIN'
        "
        @click="toggleMobileMenu"
      >
        <router-link v-if="role == 'ROLE_USER'" class="login-div-btn" v-bind:to="{ name: 'myOrders' }"
          >My Orders</router-link
        >
      </li>
      <li v-if="role == 'ROLE_ADMIN'" @click="toggleMobileMenu">
        <router-link class="login-div-btn" v-bind:to="{ name: 'admin' }"
          >Admin Dashboard</router-link
        >
      </li>
      <li v-if="$store.state.token == ''">
        <router-link class="login-div-btn" v-bind:to="{ name: 'login' }"
          >Login</router-link
        >
      </li>
      <li v-if="$store.state.token != ''">
        <p v-on:click.prevent="logOut" class="login-div-btn">Logout</p>
      </li>
    </ul>
    <toast v-if="showToast" :message="'You have logged out'" />
    <CartComponent
      v-if="storeShowCart"
      @close-cart="closeCart()"
      @open-cart="openCart()"
    />
  </div>
</template>

<script>
import Toast from "./Toast.vue";
import CartComponent from "./CartComponent.vue";
export default {
  components: { Toast, CartComponent },

  data() {
    return {
      showToast: false,
      isShrunk: false,
      role: null
    };
  },

  methods: {
    toggleMenu() {
      const menuItems = document.getElementById("header-nav-links");

      menuItems.classList.contains("show")
        ? menuItems.classList.remove("show")
        : menuItems.classList.add("show");
    },
    toggleMobileMenu() {
      const navMenuItems = document.getElementById("mobile-nav");
      console.log(navMenuItems);
      if (navMenuItems.classList.contains("show")) {
        navMenuItems.classList.remove("show");
        navMenuItems.classList.add("none");
      } else {
        navMenuItems.classList.add("show");
        navMenuItems.classList.remove("none");
      }

      return;
    },
    logOut() {
      this.showToast = true;
      setTimeout(() => {
        this.showToast = false;
        this.$router.replace("/logout");
      }, 1500);
    },

    closeCart() {
      this.$store.commit("TOGGLE_CART", false);
    },

    openCart() {
      this.$store.commit("TOGGLE_CART", true);
    },

    changePath(path) {
      const menuItems = document.getElementById("header-nav-links");
      const navMenuItems = document.getElementById("mobile-nav");
      menuItems.classList.remove("show");
      navMenuItems.classList.remove("show");
      navMenuItems.classList.add("none");
      this.$router.replace(path);
    },
    handleScroll() {
      this.isShrunk = window.scrollY > 50;
    },
  },
  computed: {

    adminUser() {
      return localStorage.getItem("user")
        ? this.$store.state.user?.authorities[0]?.name == "ROLE_ADMIN"
        : null;
    },

    storeShowCart() {
      return this.$store.state.showCart;
    },
    user() {
      return this.$store.state.user?.authorities?.name;
    },
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },

  beforeUnmount() {
    window.removeEventListener("scroll", this.handleScroll);
  },

  updated() {
    if(localStorage.getItem("user")) {
      this.role = JSON.parse(localStorage.getItem("user")).authorities[0].name
    } else {
      this.role = null
    }
  },
};
</script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  background-color: #fff;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: center;
  font-size: 1.2em;
  border-bottom: #e0e0e0 solid 1px;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2);
  padding: 10px 0px;
  margin: 0px;
  width: 100%;
  z-index: 99999999;
  transition: padding 0.3s, font-size 0.3s;
}

.header-logo {
  display: flex;
  text-align: center;
  justify-content: flex-start;
  padding-left: 55px;
}

.header-logo img {
  width: 20%;
}

.header.shrink {
  padding: 0px;
  font-size: 0.9em;
}

.header-logo img {
  width: 20%;
  transition: width 0.3s;
}

.header.shrink .header-logo img {
  width: 15%;
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
  position: fixed;
  top: 4.75em;
  left: 30em;
  list-style: none;
  background-color: white;
  padding: 0px 10px;
  border: #e6e6e6 solid 1px;
  z-index: 5;
  visibility: hidden;
  transition: opacity 0.8s ease, transform 0.4s ease;
  transform: translateY(-100px);
}

.header-nav-links.shrink {
  top: 2em;
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

.header-nav-links li:nth-child(5) {
  border-bottom: none;
}

.header-nav-links li:hover {
  background-color: #e61d25;
  color: #fff;
}

.home-nav-btn,
.menu-btn {
  border-bottom: 3px solid transparent;
  transition: border-color 0.8s ease-in-out;
}

.home-nav-btn:hover,
.menu-btn:hover {
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
  padding-right: 55px;
}

.login-div-btn {
  border-bottom: 3px solid transparent;
  transition: border-color 0.8s ease-in-out;
  cursor: pointer;
}

.mobile-nav,
.header-logo img.hamburger-icon {
  display: none;
}

.login-div-btn:hover {
  border-bottom: 3px solid #e61d25;
}

@media screen and (max-width: 680px) {
  .header-nav-links,
  .login-div,
  nav {
    display: none;
  }
  .mobile-nav {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 6.6em;
    text-align: center;
    left: 0px;
    width: 100%;
    list-style: none;
    background-color: #e61d25;
    padding: 0px 10px;
    border: #e6e6e6 solid 1px;
    z-index: 99999;
    transition: opacity 0.8s ease, transform 0.4s ease;
    transform: translateY(-100px);
  }

  .mobile-nav.none {
    visibility: hidden;
  }
  .mobile-nav.show {
    opacity: 1;
    transform: translateY(0);
  }

  .header-logo img {
    width: 100px;
  }

  .header-logo img.hamburger-icon {
    width: 50px;
    height: 40px;
    display: inline;
  }

  .header-logo {
    width: 80vw;
    align-items: center;
    justify-content: space-between;
  }

  .mobile-nav li,
  .mobile-nav li a {
    color: #fff;
    font-weight: bold;
    border-bottom: #a9a9a9 solid 1px;
    padding: 10px 8px 10px 8px;
    width: 100%;
    cursor: pointer;
    font-size: 1.2em;
  }

  .mobile-nav.shrink {
    top: 2em;
  }
}
</style>
