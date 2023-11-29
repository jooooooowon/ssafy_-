<template>
  <div class="title">
    <span> {{ userInfoStore.user.nickname }} </span>의 <span>{{ props.userData.date }}</span> 운동시간
    <span> {{ props.userData.count }} </span> 분
  </div>
  <div class="wrapper">
    <div class="section">
      <DetailExercise
        type="Bench Press"
        :data="{ count: props.userData.benchCount, set: props.userData.benchSet, weight: props.userData.benchWeight }"
      />
    </div>
    <div class="section">
      <DetailExercise
        type="Dead Lift"
        :data="{ count: props.userData.deadCount, set: props.userData.deadSet, weight: props.userData.deadWeight }"
      />
    </div>
    <div class="section">
      <DetailExercise
        type="Squat"
        :data="{ count: props.userData.squatCount, set: props.userData.squatSet, weight: props.userData.squatWeight }"
      />
    </div>
  </div>
</template>

<script setup>
import { defineProps, onMounted } from 'vue'
import DetailExercise from './DetailExercise.vue'
import { useUserInfoStore, useLoginStore } from '@/stores/user'
const userInfoStore = useUserInfoStore()
const loginStore = useLoginStore()
const props = defineProps({
  userData: {
    type: Object,
  },
})

onMounted(() => {
  loginStore.localLogin()
  userInfoStore.getById(loginStore.loginId)
})
</script>

<style scoped>
.wrapper {
  display: flex;
  justify-content: space-around;
  width: 80%;
  margin: 50px auto;
}

.title {
  font-family: 'SBAggroB';
  font-size: 1.6rem;
  text-align: center;
}

.title span {
  color: rgb(111, 88, 194);
}
</style>
