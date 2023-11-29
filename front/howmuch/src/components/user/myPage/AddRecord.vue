<template>
  <div>
    <form v-on:submit.prevent="addRecord" class="from-wrapper">
      <div class="squat-wrapper inner-wrapper">
        <div>
          <img src="https://static.strengthlevel.com/images/silhouettes/sumo-squat-256x256.png" alt="스쿼트 이미지" />
        </div>
        <div class="record-wrapper">
          <div>
            <div class="record-title">백스쿼트 무게</div>
            <input type="number" id="squatweight" name="squatweight" v-model="data.squatWeight" />
          </div>
          <div>
            <div class="record-title">백스쿼트 세트 수</div>
            <input type="number" id="squatset" name="squatset" v-model="data.squatSet" />
          </div>
          <div>
            <div class="record-title">백스쿼트 개수</div>
            <input type="number" id="squatcount" name="squatcount" v-model="data.squatCount" />
          </div>
        </div>
      </div>
      <div class="bench-wrapper inner-wrapper">
        <div>
          <img src="https://static.strengthlevel.com/images/silhouettes/bench-press-256x256.png" alt="벤치 이미지" />
        </div>
        <div class="record-wrapper">
          <div>
            <div class="record-title">벤치프레스 무게</div>
            <input type="number" id="benchweight" name="benchweight" v-model="data.benchWeight" />
          </div>
          <div>
            <div class="record-title">벤치프레스 세트 수</div>
            <input type="number" id="benchset" name="benchset" v-model="data.benchSet" />
          </div>
          <div>
            <div class="record-title">벤치프레스 개수</div>
            <input type="number" id="benchcount" name="benchcount" v-model="data.benchCount" />
          </div>
        </div>
      </div>
      <div class="dead-wrapper inner-wrapper">
        <div>
          <img
            src="https://static.strengthlevel.com/images/silhouettes/snatch-deadlift-256x256.png"
            alt="데드 이미지"
          />
        </div>
        <div class="record-wrapper">
          <div>
            <div class="record-title">데드리프트 무게</div>
            <input type="number" id="deadweight" name="deadweight" v-model="data.deadWeight" />
          </div>
          <div>
            <div class="record-title">데드리프트 세트 수</div>
            <input type="number" id="deadset" name="deadset" v-model="data.deadSet" />
          </div>
          <div>
            <div class="record-title">데드리프트 개수</div>
            <input type="number" id="deadcount" name="deadcount" v-model="data.deadCount" />
          </div>
        </div>
      </div>
      <div class="inner-wrapper">
        <div>
          <div class="record-title">start time</div>
          <input type="datetime-local" name="datetime" :max="now" v-model="data.startTime" />
        </div>
        <div>
          <div class="record-title">end time</div>
          <input type="datetime-local" name="datetime" :max="now" v-model="data.endTime" />
        </div>
      </div>
      <div class="inner-wrapper">
        <button type="submit">추가하기</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useLoginStore } from '@/stores/user'
import axios from 'axios'
const loginStore = useLoginStore()
const data = ref({
  exerciseId: -1,
  userId: null,
  startTime: null,
  endTime: null,
  squatWeight: 0,
  squatCount: 0,
  squatSet: 0,
  deadWeight: 0,
  deadCount: 0,
  deadSet: 0,
  benchWeight: 0,
  benchCount: 0,
  benchSet: 0,
})

onMounted(() => {
  loginStore.localLogin()
  const offset = new Date().getTimezoneOffset() * 60000
  const temp = new Date(Date.now() - offset).toISOString().substring(0, 16)
  data.value.userId = loginStore.loginId
  data.value.startTime = temp
  data.value.endTime = temp
})

const addRecord = function () {
  if (data.value.startTime.substring(0, 10) != data.value.endTime.substring(0, 10)) {
    alert('시작 날짜와 끝 날짜가 같아야합니다.')
    return
  }
  if (data.value.startTime > data.value.endTime) {
    alert('끝 시간이 시작 시간보다 더 늦어야 합니다.')
    return
  }
  axios
    .get('http://localhost:8080/exercise/chk', {
      params: {
        year: data.value.startTime.substring(0, 4),
        month: data.value.startTime.substring(5, 7),
        day: data.value.startTime.substring(8, 10),
        userId: data.value.userId,
      },
    })
    .then((res) => {
      if (res.data > 0) {
        if (confirm('오늘 값이 있습니다. 변경하시겠습니까?')) {
          data.value.exerciseId = res.data
          axios
            .put(
              'http://localhost:8080/exercise',
              { ...data.value },
              {
                headers: {
                  'Content-Type': 'application/json',
                },
              },
            )
            .then((postResult) => {
              if (postResult.data == 'success') {
                alert('pull 오운완')
                location.reload()
              }
            })
        }
      } else {
        if (res.data == 0) {
          axios
            .post(
              'http://localhost:8080/exercise',
              { ...data.value },
              {
                headers: {
                  'Content-Type': 'application/json',
                },
              },
            )
            .then((postResult) => {
              if (postResult.data == 'success') {
                alert('post 오운완')
                location.reload()
              }
            })
        }
      }
    })
}
</script>

<style scoped>
.inner-wrapper {
  display: flex;
  justify-content: center;
  padding: 40px 0;
}
.inner-wrapper > div {
  padding: 0 40px;
}
.inner-wrapper button {
  width: 150px;
  height: 50px;
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 4rem;
  font-weight: bold;
  cursor: pointer;
  font-size: var(--normal-font-size);
  border: none;
  outline: none;
}
.inner-wrapper button:hover {
  background-color: rgb(44, 26, 112);
  color: #fff;
  transition: 0.9s;
  font-weight: bold;
}

.record-wrapper div {
  padding: 10px 0;
}

.record-wrapper input {
  border: rgb(187, 187, 187) 1px solid;
  border-radius: 5px;
  font-size: 1.2rem;
}

.record-wrapper input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.record-wrapper input:focus {
  outline: none;
}

.record-title {
  font-size: 1.3rem;
  font-weight: bold;
  font-family: 'SBAggroB';
}

input[name='datetime'] {
  height: 40px;
  margin: 20px 0 0 0;
  font-size: 1.2rem;
}
</style>
