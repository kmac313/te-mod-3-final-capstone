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
    height: 300px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 2rem;
    text-align: center;
    width : 100vw;
    font-family: "PT Serif", serif;
    opacity: 0; /* Start fully transparent */
    animation: fadeIn 2s ease-in-out forwards;
}

.hero-container::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100vw;       
    height: 100%;
    background-color: rgba(0, 0, 0, 0.59); /* Adjust the opacity as needed */
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
    background-color: #e61d25;
    color: #fff;
    text-transform: uppercase;
    border: none;
    padding: 1rem 1rem;
    font-size: 0.4em;
    letter-spacing: 0.15em;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.custom-pizza-btn:hover {
    color: #fff;
    background-color: #000;
}

.specialty-pizza-btn {
    appearance: none;
    border: none;
    color: #000;
    text-transform: uppercase;
    padding: 1rem 1rem;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.specialty-pizza-btn:hover {
    color: #fff;
    background: #000000;
}

.hero-container .hero-subheader {
    margin : 10px;
    font-size: 1.8em;
    font-weight: lighter;
}

.hero-container h1 {
    margin: 5px 0px;
}

h1.hero-header {
    text-transform: uppercase;
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
</style>