<template>
  
  <header>
    <HeaderComponent/>
  </header>

  <main>
    <router-view></router-view>
  </main>

</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';

  export default {
    components: { HeaderComponent }
}
</script>

<style lang="scss" scoped>

</style>