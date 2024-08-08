<template>
  <div id="capstone-app">
    <!-- Header -->
    <div id="nav">
      <HeaderComponent />
    </div>
    <!-- Body -->
    <div>
      <router-view />
    </div>
    
    <!-- Footer -->
    <div id="footer">
      <FooterComponent />
    </div>
  </div>
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
import FooterComponent from "./components/FooterComponent.vue";

export default {
  components: {
    HeaderComponent,
    FooterComponent,
  },
};


</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap");
@import url("https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap");
* {
  font-family: "Nunito", sans-serif;
}

body {
  padding: 0px;
  margin: 0px;
  overflow-x: hidden;
}

a, .router-link-active, .router-link-active:visited, .router-link-active:active {
        text-decoration: none;
        color: black;
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
</style>
