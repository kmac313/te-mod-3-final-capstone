<template>
    <div class="salad-card-container" :id="'salad-' + salad.productId">
    <div class="salad-card" :style="saladStyle"></div>
    <div class="salad-card-details">
      <div class="salad-card-text-container">
        <h2 class="salad-card-text">{{ saladName }} salad</h2>
        <p class="salad-card-description">
          ${{ salad.price.toFixed(2) }} 
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
        salad: Object,
        saladName: String
    },

    computed: {
        saladStyle() {
          return  {
            backgroundImage: `url('./${this.saladName}.jpg')`
        }
        }
    }
}
    
</script>
<style scoped>
.salad-card-container {
  display: grid;
  grid-template-columns: 1fr 4fr;
  border-radius: 9px;
  border: 1px solid #e1e1e1;
  margin-top: 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  width: 100%;
}



.salad-card-container .add-to-cart-btn {
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
.salad-card-container .add-to-cart-btn:hover {
  background-color: #000;
  color: #fff;
}
.salad-card {
  position: relative;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 9px 0px 0px 9px;
  background-image: url('../assets/Hawian.jpg');
  padding: 0px 30px;
  width: 200px
}

.salad-card-container .salad-card-details {
  display: flex;
  align-items: center;
}

.salad-card-details img {
    cursor: pointer;
}

.salad-card-details .salad-card-text {
  font-size: 1.8em;
  color: #333;
  font-size: large;
}

.salad-card-details .salad-card-description {
  font-size: 1em;
  color: #333;
  font-size: medium;
}

.salad-card-container {
  border: #d8d8d8 solid 1px;
  border-radius: 10px;
}

.salad-card-text-container {
  width: 70%;
  padding: 0px 10px;
}
</style>