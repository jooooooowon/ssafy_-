<template>
  <div class="wrapper">
    <div class="button-wrapper" @click="btn = !btn">
      <div class="button">
        <span>운동 기록하기</span>
        <img src="https://cdn-icons-png.flaticon.com/256/6848/6848910.png" />
      </div>
    </div>
    <HeatMap style="margin-top: 50px" />
    <div v-if="btn">
      <AddRecord />
    </div>
  </div>
</template>

<script setup>
import HeatMap from './HeatMap.vue'
import AddRecord from './AddRecord.vue'
import { ref, onMounted } from 'vue'
import { useLoginStore } from '@/stores/user.js'
import { useRouter } from 'vue-router'
const loginStore = useLoginStore()
const router = useRouter()

onMounted(() => {
  if (!localStorage.getItem('loginId')) {
    alert('로그인 후 이용해 주세요.')
    router.push('/')
  }
})

const btn = ref(false)
</script>

<style scoped>
span {
  font-family: 'SBAggroB';
  font-size: 1.5rem;
  margin-right: 10px;
}
.button-wrapper {
  position: relative;
  display: flex;
}
.button {
  display: flex;
  position: absolute;
  right: 0;
  margin: 20px 100px 0 0;
  transition: 0.5s;
  width: auto;
  height: 70px;
  border-radius: 30%;
  align-items: center;
}
.button img {
  width: 70px;
  height: 70px;
}
.button:hover {
  cursor: pointer;
  transform: translateX(15px);
}
</style>
