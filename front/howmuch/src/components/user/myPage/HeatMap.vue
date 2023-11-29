<template>
  <div>
    <!-- { date: '2018-9-22', count: 6 },
    { date: '2018-4-30', count: 1 },
    { date: '2018-4-03', count: 3 },
    { date: '2018-4-04', count: 0 },
    { date: '2017-4-10', count: 4 },
    { date: '2017-11-30', count: 2 },
    { date: '2017-11-17', count: 5 },
    { date: '2023-11-19', count: 6 }, -->
    <div v-if="dates.length > 0">
      <!-- tooltip-unit="분" -->
      <calendar-heatmap
        id="heatmap"
        :values="dates"
        :end-date="today"
        round="2"
        no-data-text="이 새끼 운동 안했네ㅋ"
        :range-color="rangeColor"
        :tooltip-formatter="(v) => tooltip(v)"
        @click="detailByDate"
      />
      <div v-if="!userDataChk">
        <DetailHeatMap :userData="userData" />
      </div>
    </div>
    <div v-else>
      <calendar-heatmap
        id="heatmap"
        :values="[{}]"
        :end-date="today"
        round="2"
        no-data-text="이 새끼 운동 안했네ㅋ"
        :range-color="rangeColor"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useLoginStore } from '@/stores/user'
import axios from 'axios'
import DetailHeatMap from '@/components/user/myPage/DetailHeatMap.vue'
import _ from 'lodash'
// const rangeColor = ref([
//   '#e1dcef',
//   '#d3cbe7',
//   '#c4b9df',
//   '#b5a8d7',
//   '#a696cf',
//   '#9785c7',
//   '#8973bf',
//   '#7a62b7',
//   '#6b50af',
//   '#60489d',
//   '#56408c',
//   '#4b387a',
//   '#403069',
//   '#362857',
//   '#2b2046',
// ])
const rangeColor = ref(['#ebedf0', '#e1dcef', '#d2cbe7', '#a696cf', '#816aba', '#56408c'])
const router = useLoginStore()
const dates = ref([])
const date = new Date()
const today = date.toLocaleDateString()
const userData = ref({})
const showDate = ref('')

onMounted(() => {
  router.localLogin()
  axios.get(`http://localhost:8080/exercise/${router.loginId}`).then((res) => {
    res.data.forEach((element) => {
      let count = ''
      // 글자를 날짜만 잘라서 날짜로 넣기
      let startDate = new Date(element.startTime.substring(0, 19))
      let endDate = new Date(element.endTime.substring(0, 19))

      // 날짜의 시간 차이, 1000*60을 나눠서 분으로 표현
      let timeDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60)
      count = timeDiff
      // 소수점 제거
      count = Math.floor(count)

      // dates에 push
      dates.value.push({
        date: element.startTime.substring(0, 10),
        count: count,
        benchCount: element.benchCount,
        benchSet: element.benchSet,
        benchWeight: element.benchWeight,
        deadCount: element.deadCount,
        deadSet: element.deadSet,
        deadWeight: element.deadWeight,
        squatCount: element.squatCount,
        squatSet: element.squatSet,
        squatWeight: element.squatWeight,
      })
    })
  })
})

// 체크했을 때만 component 활성화를 위한 comptued
const userDataChk = computed(() => {
  return _.isEmpty(userData.value)
})

// tooltip 보여주기
const tooltip = function (element) {
  console.log(element)
  // detailHeatMap.vue 컴포넌트를 보여주기위한 showDate.value 변경
  showDate.value = element.date

  // tooltip 보여주기
  let str = `<span style="font-weight:bold">
              ${element.count} 분 <br>
              ${element.date.toString().substring(0, 16)}
             </span>
            `
  return str
}

const detailByDate = function () {
  // 마지막으로 갖다댔던 박스의 date를 구해서,
  // dates에 담아놨던 것 중 날짜가 같은 것을 component에 전달 하기 위한 메서드
  let showTempDate = new Date(showDate.value)

  // console.log('showtempdate : ' + showTempDate)
  // console.log('year : ' + showTempDate.getFullYear())
  // console.log('month : ' + showTempDate.getMonth())
  // console.log('day : ' + showTempDate.getDate())

  let temp = dates.value.filter((element) => {
    let tempDate = new Date(element.date)
    return (
      showTempDate.getFullYear() == tempDate.getFullYear() &&
      showTempDate.getMonth() == tempDate.getMonth() &&
      showTempDate.getDate() == tempDate.getDate()
    )
  })
  // 다음 component에 전달할 userData
  console.log(temp[0])
  // date: temp[0].date,
  // benchCount: temp[0].benchCount,
  // benchSet: temp[0].benchSet,
  // benchWeight: temp[0].benchWeight,
  // deadCount: temp[0].deadCount,
  // deadSet: temp[0].deadSet,
  // deadWeight: temp[0].deadWeight,
  // squatCount: temp[0].squatCount,
  // squatSet: temp[0].squatSet,
  // squatWeight: temp[0].squatWeight,
  userData.value = {
    ...temp[0],
  }
  console.log(userData.value)
}
</script>
<style>
#heatmap > svg > g > text {
  font-size: 10px;
}

#heatmap {
  margin-top: 30px;
  width: 80%;
  margin: auto;
  padding: 70px 0;
}
</style>
