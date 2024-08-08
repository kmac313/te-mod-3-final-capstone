<template>
  <div class="toast-container">
    <p>{{message}}</p>
  </div>
</template>

<script>
export default {
  props: {
    message: String
  }
}
</script>

<style>
.toast-container {
    position: fixed;
    top: 20px;
    left: 500px;
    background: #000000df;
    color: white;
    border-radius: 15px;
    padding: 6px 20px;
    z-index: 999999999999999999999999999;
}
</style>