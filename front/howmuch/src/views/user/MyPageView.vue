<template>
  <aside>
    <div class="asdieWarpper">
      <div class="SMN_effect-16"><a @click="changeTag(0)">home</a></div>
      <div class="SMN_effect-16"><a @click="changeTag(1)">update Or Delete</a></div>
    </div>
  </aside>
  <section>
    <span v-if="tagList[0]">
      <MyPageHome />
    </span>
    <span v-if="tagList[1]">
      <UpdateOrDelete />
    </span>
  </section>
</template>

<script setup>
import { ref } from 'vue'
import UpdateOrDelete from '@/components/user/myPage/UpdateOrDelete.vue'
import MyPageHome from '@/components/user/myPage/MyPageHome.vue'
const tagList = ref([true, false, false])
const changeTag = function (idx) {
  for (let i = 0; i < tagList.value.length; i++) {
    if (i == idx) {
      tagList.value[i] = true
    } else {
      tagList.value[i] = false
    }
  }
  console.log(tagList.value)
}
</script>

<style scoped>
.asdieWarpper {
  display: flex;
  justify-content: space-evenly;
}
.asdieWarpper div {
  width: 40%;
  text-align: center;
  transition: 0.9s;
  height: 100px;
  background: rgb(44, 26, 112);
  line-height: 30px;
  color: rgb(186, 174, 228);
  line-height: 100px;
  border-radius: 10px;
}

*,
*:after,
*:before {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.menu a {
  font-family: Lato;
  font-size: 17pt;
  font-weight: 400;
  padding: 15px 25px;
  /**/
  position: relative;
  display: block;
  text-decoration: none;
  text-transform: uppercase;
}
a:hover,
a:focus {
  outline: none;
}
.SMN_effect-16 a:before,
.SMN_effect-16 a:after {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 100px;
  height: 100px;
  border: 2px solid rgba(255, 255, 255, 0.1);
  border-radius: 50%;
  content: '';
  opacity: 0;
  -webkit-transition: -webkit-transform 0.3s, opacity 0.3s;
  -moz-transition: -moz-transform 0.3s, opacity 0.3s;
  transition: transform 0.3s, opacity 0.3s;
  -webkit-transform: translateX(-50%) translateY(-50%) scale(0.2);
  -moz-transform: translateX(-50%) translateY(-50%) scale(0.2);
  transform: translateX(-50%) translateY(-50%) scale(0.2);
}
.SMN_effect-16 a {
  font-size: 17pt;
  font-weight: 400;
  /**/
  position: relative;
  display: block;
  text-decoration: none;
  text-transform: uppercase;
}

.SMN_effect-16 a:after {
  width: 90px;
  height: 90px;
  border-width: 6px;
  -webkit-transform: translateX(-50%) translateY(-50%) scale(0.8);
  -moz-transform: translateX(-50%) translateY(-50%) scale(0.8);
  transform: translateX(-50%) translateY(-50%) scale(0.8);
}

.SMN_effect-16 a:hover:before,
.SMN_effect-16 a:hover:after,
.SMN_effect-16 a:focus:before,
.SMN_effect-16 a:focus:after {
  opacity: 1;
  -webkit-transform: translateX(-50%) translateY(-50%) scale(1);
  -moz-transform: translateX(-50%) translateY(-50%) scale(1);
  transform: translateX(-50%) translateY(-50%) scale(1);
}
</style>
