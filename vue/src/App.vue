<template>
  <div id="capstone-app">
    <div id="nav">
      <HeaderComponent />
    </div>
    <router-view />
    <div id="footer">
    <FooterComponent />
  </div>
  </div>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue'
import FooterComponent from './components/FooterComponent.vue'

export default {
  components: {
    HeaderComponent,
    FooterComponent
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap');
@import url('https://fonts.googleapis.com/css2?family=PT+Serif:ital,wght@0,400;0,700;1,400;1,700&display=swap');
* {
  font-family: "Nunito", sans-serif;
}

body {
  padding: 0px;
  margin: 0px;
  overflow-x: hidden;

}
</style>