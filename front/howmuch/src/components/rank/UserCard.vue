<template>
  <div class="card">
    <div class="content">
      <div>
        <div class="record">
          <span style="font-size: 1.3rem"> {{ props.userData.nickname }} </span>
        </div>
        <div class="record">
          현재
          <span style="font-weight: bold">{{ props.userData.rank }}</span
          >위
        </div>
        <div class="record">
          <span>최고 기록: {{ record }} kg</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps } from 'vue'
const props = defineProps({
  userData: {
    type: Object,
  },
})

const record = ref(0)

if (props.userData.part == 'squat') record.value = props.userData.squatRecord
else if (props.userData.part == 'bench') record.value = props.userData.benchRecord
else if (props.userData.part == 'dead') record.value = props.userData.deadRecord

onMounted(() => {
  console.log('props.userData', props.userData)
})
</script>

<style scoped>
.card {
  width: 300px;
  height: 120px;
  display: flex;
  font-family: 'yg-jalnan';
  align-items: center;
  justify-content: space-around;
  border-radius: 10px;
  margin: 20px 0;
  box-shadow: 0 0 10px rgb(44, 26, 112);
}
.card span {
  font-weight: bold;
  color: rgb(44, 26, 112);
}
.content div {
  font-size: 1.2rem;
  width: 100%;
  align-items: center;
  text-align: center;
  height: 70%;
}

.record {
  margin: 10px 0;
}
</style>
