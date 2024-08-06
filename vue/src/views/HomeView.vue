<template>
  <div class="home">
    <HeroComponent ></HeroComponent>
    <FeaturedComponent></FeaturedComponent>
  </div>
</template>

<script>
import HeroComponent from '../components/HeroComponent.vue';
import FeaturedComponent from '../components/FeaturedComponent.vue';

export default {

  components: {
    HeroComponent,
    FeaturedComponent
  }
};
</script>
