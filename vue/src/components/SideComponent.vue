<template>
    <div class="side-card-container" :id="'side-' + side.productId">
    <div class="side-card" :style="sideStyle"></div>
    <div class="side-card-details">
      <div class="side-card-text-container">
        <h2 class="side-card-text">{{ sideName }} side</h2>
        <p class="side-card-description">
          ${{ side.price.toFixed(2) }} 
        </p>
      </div>

      <button v-if="side?.quantity > 0" class="add-to-cart-btn" v-on:click="$emit('add-to-cart')" >
          Add to Cart
      </button>
      <button v-if="side?.quantity == 0" disabled class="out-of-stock-btn">
        OUT OF STOCK
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
        side: Object,
        sideName: String
    },

    computed: {
        sideStyle() {
          return  {
            backgroundImage: `url('./${this.sideName}.jpg')`
        }
        }
    }
}
    
</script>
<style scoped>
.side-card-container {
  display: grid;
  grid-template-columns: 1fr 4fr;
  border-radius: 9px;
  border: 1px solid #e1e1e1;
  margin-top: 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  width: 100%;
}



.side-card-container .add-to-cart-btn {
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
.side-card-container .add-to-cart-btn:hover {
  background-color: #000;
  color: #fff;
}
.side-card {
  position: relative;
  height: 150px;
  background-size: cover;
  background-position: center;
  border-radius: 9px 0px 0px 9px;
  background-image: url('../assets/Hawian.jpg');
  padding: 0px 30px;
  width: 200px
}

.side-card-container .side-card-details {
  display: flex;
  align-items: center;
}

.side-card-details img {
    cursor: pointer;
}

.side-card-details .side-card-text {
  font-size: 1.8em;
  color: #333;
  font-size: large;
}

.out-of-stock-btn {
  margin-right: 40px
}

.side-card-details .side-card-description {
  font-size: 1em;
  color: #333;
  font-size: medium;
}

.side-card-container {
  border: #d8d8d8 solid 1px;
  border-radius: 10px;
}

.side-card-text-container {
  width: 70%;
  padding: 0px 10px;
}
</style>