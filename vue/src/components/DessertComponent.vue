<template>
    <div class="dessert-card-container" :id="'dessert-' + dessert.productId">
    <div class="dessert-card" :style="dessertStyle"></div>
    <div class="dessert-card-details">
      <div class="dessert-card-text-container">
        <h2 class="dessert-card-text">{{ dessertName }} dessert</h2>
        <p class="dessert-card-description">
          ${{ dessert.price.toFixed(2) }} 
        </p>
      </div>

      <button class="add-to-cart-btn" v-on:click="$emit('add-to-cart')" >
          Add to Cart
      </button>
    </div>
  </div>
</template>
<script>
export default {
    data() {
        return {
            
        }
    },
    props: {
        dessert: Object,
        dessertName: String
    },

    computed: {
        dessertStyle() {
          return  {
            backgroundImage: `url('./${this.dessertName}.jpg')`
        }
        }
    }
}
    
</script>
<style scoped>
.dessert-card-container {
  display: grid;
  grid-template-columns: 1fr 4fr;
  border-radius: 9px;
  border: 1px solid #e1e1e1;
  margin-top: 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  width: 100%;
}



.dessert-card-container .add-to-cart-btn {
  justify-self: start;
  background-color: #e61d25;
  color: #fff;
  border-radius: 15px;
  border: none;
  padding: 15px 30px;
  cursor: pointer;
  margin-right: 30px;
  height: 50px;
  width: 150px;
  
}
.dessert-card-container .add-to-cart-btn:hover {
  background-color: #000;
  color: #fff;
}
.dessert-card {
  position: relative;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 9px 0px 0px 9px;
  background-image: url('../assets/Hawian.jpg');
  padding: 0px 30px;
  width: 200px
}

.dessert-card-container .dessert-card-details {
  display: flex;
  align-items: center;
}

.dessert-card-details img {
    cursor: pointer;
}

.dessert-card-details .dessert-card-text {
  font-size: 1.8em;
  color: #333;
  font-size: large;
}

.dessert-card-details .dessert-card-description {
  font-size: 1em;
  color: #333;
  font-size: medium;
}

.dessert-card-container {
  border: #d8d8d8 solid 1px;
  border-radius: 10px;
}

.dessert-card-text-container {
  width: 70%;
  padding: 0px 10px;
}
</style>