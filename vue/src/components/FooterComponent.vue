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
      <div class="socials">
        <h3>Follow Us</h3>
        <img src="src\assets\facebook.png" alt="Facebook" />
        <img src="src\assets\instagram.png" alt="Instagram" />
        <img src="src\assets\twitter.png" alt="Twitter" />
        <img src="src\assets\tiktok.png" alt="Youtube" />

      </div>
      <div class="newsletter">
        <h3>Subscribe</h3>
        <input type="email" placeholder="Enter your email" />
        <button class="subscribe-btn">Subscribe</button>
        </div>
    </footer>
  </div>
</template>

<script></script>

<style>
    footer {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
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

    footer .socials img {
        width: 30px;
        margin-right: 10px;
        cursor: pointer;
    }

    footer .subscribe-btn {
        background-color: #e61d25;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    footer .subscribe-btn:hover {
        background-color: #e61d25;
        color: white;
    }

    footer input {
        padding: 10px;
        border: 1px solid #e0e0e0;
        border-radius: 5px;
        width: 50%;
        margin-bottom: 10px;
    }
</style>
