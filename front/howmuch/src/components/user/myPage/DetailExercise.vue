<template>
  <div class="card">
    <div class="content">
      <div class="title">
        {{ props.type }}
      </div>
      <div>
        <ul>
          <li>{{ props.data.weight }}kg</li>
          <li>{{ props.data.count }}회 {{ props.data.set }}세트</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  data: {
    type: Object,
  },
  type: {
    type: String,
  },
})
</script>

<style scoped>
.card {
  width: 300px;
  height: 120px;
  display: flex;

  align-items: center;
  justify-content: space-around;
  border-radius: 10px;
  margin: 20px 0;
  box-shadow: 0 0 10px rgb(44, 26, 112);
}

.title {
  font-family: 'SBAggroB';
}

.content {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  height: 70%;
}

.title {
  font-size: 1.4rem;
  font-weight: bold;
}
ul {
  text-align: center;
}

li {
  padding-top: 10px;
}
</style>
