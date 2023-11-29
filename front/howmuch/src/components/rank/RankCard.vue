<template>
  <div class="card">
    <div class="rank">{{ props.rankData.index + 1 }}</div>
    <div class="content">
      <div class="nickname">{{ props.rankData.nickname }}</div>
      <div>
        <span>{{ record }}</span> kg
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
const props = defineProps({
  rankData: {
    type: Object,
  },
})
const record = ref(0)

if (props.rankData.squatRecord > 0) record.value = props.rankData.squatRecord
else if (props.rankData.benchRecord > 0) record.value = props.rankData.benchRecord
else record.value = props.rankData.deadRecord
</script>

<style scoped>
.card {
  width: 300px;
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: rgb(199, 199, 199) 1.5px solid;
  border-radius: 10px;
  margin: 10px 0;
}

.rank {
  font-family: 'SBAggroB';
  width: 100px;
  text-align: center;
  font-weight: bold;
  font-size: 2.2rem;
  color: white;
  -webkit-text-stroke-width: 1.5px;
  -webkit-text-stroke-color: rgb(44, 26, 112);
  /* text-shadow: 0 0 4px rgb(44, 26, 112); */
}

.content {
  font-size: 1.2rem;
  text-align: start;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  padding: 0 0 0 20px;
  height: 80%;
}

.content span {
  font-weight: bold;
  color: rgb(44, 26, 112);
}

.nickname {
  font-family: 'yg-jalnan';
  font-weight: bold;
  font-size: 1.3rem;
}
</style>
