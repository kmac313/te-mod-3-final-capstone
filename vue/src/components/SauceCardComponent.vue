<template>
    <div class="topping-card-container">
      <div class="topping-card-text-container">
        <p class="topping-card-description">
          {{ sauce.name }} &nbsp;
        </p>
        <input type="checkbox" class="sauce-checkbox" :id="'sauce-' + sauce.id" :value="1" v-on:click="$emit('add-sauce')"/>
      </div>
  </div>
</template>

<script>
export default {
  name: 'SauceCardComponent',
  props: { 
    sauce: {
      type: Object,
      required: true
 
  }
}
}
</script>

<style scoped>

.topping-card-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
  border: 1px solid rgb(225, 225, 225);
  box-shadow: rgba(0, 0, 0, 0.14) 0px 3px 6px;
  border-radius: 10px;
  padding: 10px;
  text-align: center;
  cursor: pointer;
  width: 400px;
  margin-top: 20px;
}

.topping-card-text-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

input[type="checkbox"] {
  width: 25px;
  height: 25px;
}

input[type="checkbox"]:checked {
  accent-color: #e61d25;
}

</style>