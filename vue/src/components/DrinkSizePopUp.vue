<template>
  <div
    class="drink-size-pop-up-container"
    v-bind:class="{ 'popup-hidden': showPopUp === false }"
  >
    <div class="drink-size-option-container">
      <h2>Pick your drink size</h2>
      <select>
        <option>{{ size.small }}</option>
        <option>{{ size.medium }}</option>
        <option>{{ size.large }}</option>
      </select>

      <div class="drink-size-btns">
        <button class="add-drink-size" v-on:click="$emit('add-to-order')">Add to Cart</button>
        <button v-on:click="$emit('hide-popup')" class="cancel-drink-size">Cancel</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: {
        small: "Small",
        medium: "Medium",
        large: "Large",
      },
    };
  },
  props: {
    drink: Object,
    showPopUp: Boolean,
  },
};
</script>

<style>
.drink-size-pop-up-container {
    display:flex;
    justify-content: center;
    align-items: center;
  position: fixed;
  top: 0px;
  background-color: #00000058;
  height: 100vh;
  width: 100vw;
  z-index: 9999999999999999999999999;
}

.popup-hidden {
  visibility: hidden;
}

select {
    width: 40%;
    padding: 5px 0px;
    font-size: 1em;
}

.drink-size-option-container {
    background-color: #fff;
    width: 60%;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 15px;
    padding: 20px 0px;
}

.drink-size-btns {
   width: 60%;
   display: flex;
   justify-content: center; 
   gap: 30px;
   margin-top: 25px;
}

.drink-size-btns button {
  padding: 15px 30px;
  font-size: 1em;
  cursor: pointer;
}

.add-drink-size {
    color: white;
    background-color: #e61d25;
    border: 1px solid #e61d25;
}

.add-drink-size:hover {
    background-color: #000;
    border-color: #000;
}

.cancel-drink-size {
    color: #e61d25;
    border: 1px solid #000;
    background-color: #fff;
}

.cancel-drink-size:hover {
    background-color: #000;
    color: #fff;
    border-color: #000;
}
</style>
