<template>
<div>
<h1>Custom Pizza</h1>
<div class="pizza-size-cards">
<PizzaSizeCardComponent v-bind:price="size.small.price" v-bind:size="size.small.size">PizzaSizeCard</PizzaSizeCardComponent>
<PizzaSizeCardComponent v-bind:price="size.medium.price" v-bind:size="size.medium.size">PizzaSizeCard</PizzaSizeCardComponent>
<PizzaSizeCardComponent v-bind:price="size.large.price"  v-bind:size="size.large.size">PizzaSizeCard</PizzaSizeCardComponent>
</div>

<h2>Toppings</h2>
<ToppingCardComponent v-on:click.prevent="addTopping(topping)" v-for="(topping, index) in topping" v-bind:key="index" v-bind:topping="topping"></ToppingCardComponent>
<h2>Crust</h2>
<CrustCardComponent v-for="(crust, index) in crust" v-bind:key="index" v-bind:crust="crust"></CrustCardComponent>
<h2>Sauce</h2>
<SauceCardComponent v-for="(sauce, index) in sauce" v-bind:key="index" v-bind:sauce="sauce"></SauceCardComponent>
</div>
</template>


<script>
import CrustCardComponent from '../components/CrustCardComponent.vue';
import PizzaSizeCardComponent from '../components/PizzaSizeCardComponent.vue';
import ToppingCardComponent from '../components/ToppingCardComponent.vue';
import SauceCardComponent from '../components/SauceCardComponent.vue';

export default {
    data() {
        return {
            customPizza: {
            name: "Hawian",
            price: "32.00",
            crust: "regular",
            sauce: "Alfredo",
            topping: []
            },
            allPizzas: this.$store.state.inventory.specialtyPizza,
            size: {
                small: {
                    price: 10.00,
                    size: "Small"
                },
                medium: {
                    price: 12.00,
                    size: "Medium"
                },
                large: {
                    price: 14.00,
                    size: "Large"
                }
            },

            topping: this.$store.state.inventory.toppings,
            crust: this.$store.state.inventory.crust,
            sauce: this.$store.state.inventory.sauce
            
        }
    },
    components: {
        PizzaSizeCardComponent,
        ToppingCardComponent,
        CrustCardComponent,
        SauceCardComponent
    },
    
    method: {
        addTopping(topping) {
            // this.customPizza.topping.push(topping)
            console.log(topping)
        },

        removeTopping(topping) {
            this.customPizza.topping = this.customPizza.topping.filter(t => t !== topping)
        }
    }
};


</script>


<style>

.pizza-size-cards {
    display: flex;
    justify-content: space-between;
    gap: 20px;
    padding: 10px;
    width: 50%;
}
</style>