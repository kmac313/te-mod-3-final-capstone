<template>
      <div class="topping-card-container">
        <div class="topping-card-text-container">
          <p class="topping-card-description">
            {{ topping.name }} &nbsp;
            ${{ topping.price }}
          </p>
          <input type="checkbox" class="topping-checkbox" :id="'topping-' + topping.id" :value="1" v-on:click="$emit('add-topping')"/>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ToppingCardComponent',
    props: {
        topping: {
            type: Object,
            required: true
        },
        handleClick: Function
        
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
    border: 1px solid rgb(255, 167, 167);
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

</style>