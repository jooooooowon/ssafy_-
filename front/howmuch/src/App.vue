<script setup>
import { RouterView } from 'vue-router'
import Header from '@/components/Header.vue'
</script>

<template>
  <body>
    <div class="wrapper">
      <div>
        <Header />
      </div>
      <section>
        <RouterView />
      </section>
    </div>
  </body>
</template>

<style scoped>
body {
  background-color: rgb(230, 226, 234);
  padding: 20px 0;
  height: full;
}

.wrapper {
  height: 1400px;
  width: 1280px;
  margin: auto;
  border-radius: 80px;
  background-color: white;
  box-shadow: 0px 0px 10px 6px rgb(225, 225, 225);
}
/* -------------------- wrapper -------------------- */

header nav {
  height: 100%;
  display: flex;
  position: relative;
  border: 1px solid black;
}

.home {
  margin: auto 0;
  padding-left: 10px;
}
.user-link {
  position: absolute;
  right: 10px;
}
.user-link a {
  padding: 20px 0 0 10px;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.2em;
  color: #000;
  transition: 0.5s;
}
.user-link a:hover {
  color: blueviolet;
}

.content-link {
  position: absolute;
  right: 0;
  bottom: 0;
  display: flex;
}
.content-link div {
  padding: 0 5px;
}

/* -------------------- header -------------------- */

section {
  margin: 50px 0;
  overflow-y: auto;
  height: 1080px;
}
</style>
