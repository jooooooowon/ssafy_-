<template>
  <div class="select">
    <!--onMounted 할 때 전체 랭킹 띄우는 함수-->
    <!--여기 영역마다 onClink 이벤트로 랭킹 정보 따로 요청하는 함수 넣기-->

    <div class="select-item" @click="getRank('total', $event)">전체</div>
    <div class="select-item" @click="getRank('age', $event)">나이별</div>
    <div class="select-item" @click="getRank('weight', $event)">체급별</div>
  </div>
  <div class="total-list">
    <!--각각 삼대 하나씩 1. 스쿼트, 2. 벤치, 3. 데드-->
    <!--UserCard는 접속한 사용자 정보이고, v-for로 나머지 카드 렌더링 하는데 이때 props로 랭킹 정보 보내기-->
    <div class="section">
      <h3>백 스쿼트</h3>
      <UserCard :userData="{ part: 'squat', ...loginStore.loginUser, rank: rankStore.userSquatRank }" />
      <RankCard v-for="(item, index) in rankStore.squatList" :key="index" :rankData="{ index: index, ...item }" />
    </div>
    <div class="section">
      <h3>벤치 프레스</h3>
      <UserCard :userData="{ part: 'bench', ...loginStore.loginUser, rank: rankStore.userBenchRank }" />
      <RankCard v-for="(item, index) in rankStore.benchList" :key="index" :rankData="{ index: index, ...item }" />
    </div>
    <div class="section">
      <h3>데드 리프트</h3>
      <UserCard :userData="{ part: 'dead', ...loginStore.loginUser, rank: rankStore.userDeadRank }" />
      <RankCard v-for="(item, index) in rankStore.deadList" :key="index" :rankData="{ index: index, ...item }" />
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import UserCard from '../rank/UserCard.vue'
import RankCard from '../rank/RankCard.vue'
import { useRankStore } from '../../stores/rank.js'
import { useLoginStore } from '../../stores/user.js'

// onMount
// user = userStroe. 로그인 유저 정보를 가져와
// 1. 처음에는 전체 순위를 띄울 거니까 상관없긴해
// 개인의 숫자를 알아야돼

const rankStore = useRankStore()
const loginStore = useLoginStore()
const loginId = loginStore.loginId

onMounted(() => {
  console.log('loginId', loginId)
  loginStore.getById(loginId)
  console.log('rank 페이지의 유저', loginStore.loginUser)
  rankStore.squatRank('total', loginId)
  rankStore.benchRank('total', loginId)
  rankStore.deadRank('total', loginId)
  rankStore.getSquatUserRank('total', loginId)
  rankStore.getBenchUserRank('total', loginId)
  rankStore.getDeadUserRank('total', loginId)
})

const getRank = (key, event) => {
  let itemList = document.querySelectorAll('.select-item')
  itemList.forEach((element) => {
    element.classList.remove('checked')
  })
  event.target.classList.add('checked')
  rankStore.squatRank(key, loginId)
  rankStore.benchRank(key, loginId)
  rankStore.deadRank(key, loginId)
  rankStore.getSquatUserRank(key, loginId)
  rankStore.getBenchUserRank(key, loginId)
  rankStore.getDeadUserRank(key, loginId)
}
</script>

<style scoped>
h3 {
  margin-top: 15px 0 5px 0;
  color: rgb(46, 0, 88);
  font-weight: bold;
  font-size: 1.5rem;
  font-family: 'SBAggroB';
}
.section {
  width: 30%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.select {
  display: flex;
  justify-content: center;
}

.select-item {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 8px 20px 8px;
  width: 100px;
  height: 40px;
  font-size: 1rem;
  background-color: rgb(44, 26, 112);
  color: white;
  border-radius: 15px;
  cursor: pointer;
}

.select-item:hover {
  color: rgb(0, 0, 0);
  font-weight: bold;
  transition: 0.6s;
  font-size: 1.1rem;
  background-color: rgb(186, 174, 228);
}
.checked {
  color: rgb(0, 0, 0);
  font-weight: bold;
  transition: 0.6s;
  font-size: 1.1rem;
  background-color: rgb(186, 174, 228);
}
.total-list {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
