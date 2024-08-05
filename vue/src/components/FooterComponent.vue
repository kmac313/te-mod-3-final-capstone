<template>
  <div class="footer">
    <footer>
      <div class="footer-logo">
        <img
          src="src\assets\Super_Pizza_Mascot_Logo-removebg-preview.png"
          alt="Pizza"
          style="width: 55%"
        />
      </div>
      <div class="footer-menu">
        <h3>Pages</h3>
        <ul class="footer-nav-links" id="footer-nav-links">
          <router-link v-bind:to="{ name: 'home' }">
            <li>CUSTOM PIZZA</li>
          </router-link>
          <router-link v-bind:to="{ name: 'home' }">
            <li>SPECIALTY PIZZA</li>
          </router-link>
          <router-link v-bind:to="{ name: 'home' }">
            <li>DRINKS</li>
          </router-link>
        </ul>
      </div>
    </footer>
  </div>
</template>

<script></script>

<style>
    footer {
        display: grid;
        grid-template-columns: 1fr 4fr;
        border-bottom: #e0e0e0 solid 1px;
        box-shadow: 0 -4px 2px 0px rgba(0, 0, 0, 0.1);
    }



    footer ul {
        display: flex;
        flex-direction: column;
        align-items: start;
        list-style: none;
        gap: 10px;
        max-width: 100%;
        padding: 0px;
    }

    .footer-nav-links .router-link-active:hover {
        color: #e61d25;
    }
</style>
