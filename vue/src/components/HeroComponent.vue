<template>
    <div class="hero-container">
        <div class="hero-header">
            <h1 class="hero-header">Tasty Pizza!</h1>
            <h2 class="hero-subheader">Get 2 pizzas for the price of 1.</h2>
        </div>
        <div class="hero-buttons">
            <button class="custom-pizza-btn">
                Custom Pizza
            </button>
            <button class="specialty-pizza-btn">
                Specialty Pizza
            </button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'HeroComponent',
    // Your component's JavaScript logic goes here
}
</script>

<style scoped>
.hero-container {
    position: relative;
    background-image: url('../assets/pizzadeal.jpg');
    background-size: cover;
    background-position: center;
    height: 50vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 2rem;
    text-align: center;
    width : 100vw;
    font-family: "PT Serif", serif;
}

.hero-container::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100vw;       
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Adjust the opacity as needed */
}

.hero-container .hero-header {
    position: relative;
    color: white;
    z-index: 1;
    opacity: 1;
}

.hero-buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 1rem;
    z-index: 1;
}

.custom-pizza-btn {
    appearance: none;
    background-color: #f8d568;
    color: #000;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.specialty-pizza-btn {
    appearance: none;
    background-color: #f8d568;
    color: #000;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.hero-container .hero-subheader {
    margin : 10px;
    ;
}

</style>