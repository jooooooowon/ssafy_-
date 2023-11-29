import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_USER_API = 'http://localhost:8080/user'

// 백스쿼트, 벤치프레스, 데드리프트

export const useRankStore = defineStore('rank', () => {
  const squatList = ref([])
  const benchList = ref([])
  const deadList = ref([])
  const userSquatRank = ref(0)
  const userBenchRank = ref(0)
  const userDeadRank = ref(0)

  const squatRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/squat`, {
        params: {
          key: key,
          userId: userId,
        },
      })
      .then((res) => {
        squatList.value = res.data
      })
  }

  const benchRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/bench`, {
        params: {
          key: key,
          userId: userId,
        },
      })
      .then((res) => {
        benchList.value = res.data
      })
  }

  const deadRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/dead`, {
        params: {
          key: key,
          userId: userId,
        },
      })
      .then((res) => {
        deadList.value = res.data
      })
  }

  const getSquatUserRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/squat/${userId}`, {
        params: {
          key: key,
        },
      })
      .then((res) => {
        userSquatRank.value = res.data
      })
  }

  const getBenchUserRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/bench/${userId}`, {
        params: {
          key: key,
        },
      })
      .then((res) => {
        userBenchRank.value = res.data
      })
  }

  const getDeadUserRank = function (key, userId) {
    axios
      .get(`${REST_USER_API}/rank/dead/${userId}`, {
        params: {
          key: key,
        },
      })
      .then((res) => {
        userDeadRank.value = res.data
      })
  }

  return {
    squatList,
    squatRank,
    benchList,
    benchRank,
    deadList,
    deadRank,
    userSquatRank,
    getSquatUserRank,
    userBenchRank,
    getBenchUserRank,
    userDeadRank,
    getDeadUserRank,
  }
})
