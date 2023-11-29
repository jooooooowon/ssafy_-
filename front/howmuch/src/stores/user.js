import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import axios from 'axios'

// USER 기능
// 1. POST - 한 명 선택 (로그인) user/
// 2. POST - 회원가입 user/login
// 3. DELETE - 회원 탈퇴 user/delete
// 4. PUT - 수정 user/update
// 5. GET - 개인 랭킹 user/rank

const REST_USER_API = 'http://localhost:8080/user'

export const useJoinStore = defineStore('join', () => {
  const router = useRouter()
  const joinResult = ref('')
  const joinMethod = function (data) {
    console.log(data.value)
    let temp = {
      userId: data.userId,
      password: data.password,
      email: data.email,
      birthday: data.birthday,
      nickname: data.nickname,
      weight: data.weight,
      height: data.height,
    }
    console.log(temp)
    axios.post('http://localhost:8080/user', temp, { header: { 'Content-type': 'application/json' } }).then((res) => {
      console.log(res.data)
      if (res.data == 'success') {
        alert('회원 가입 성공')
        router.push('/login')
      }
      joinResult.value = res.data
    })
  }
  return {
    joinResult,
    joinMethod,
  }
})

// 유저 정보 수정

export const userUpdateStore = defineStore('update', () => {
  const router = useRouter()
  const updateResult = ref('')
  const updateMethod = function (data) {
    console.log(data.value)
    axios.put(`${REST_USER_API}/update`, data, { header: { 'Content-type': 'application/json' } }).then((res) => {
      if (res.data == 'success') {
        alert('수정 완료')
        router.push('/')
      }
      updateResult.value = res.data
    })
  }
  return {
    updateResult,
    updateMethod,
  }
})

export const useUserInfoStore = defineStore('mypage', () => {
  const user = ref({})
  const getById = function (loginId) {
    axios.get(`${REST_USER_API}/${loginId}`).then((res) => {
      console.log(res)
      user.value = { ...res.data }
    })
  }

  return {
    user,
    getById,
  }
})
export const useLoginStore = defineStore('login', () => {
  const loginId = ref('')
  const router = useRouter()
  const loginResult = ref('')
  const loginUser = ref('')

  // loginId가 필요한 곳에 onMounted해서 localLogin부터 하기
  const localLogin = function () {
    const itemStr = localStorage.getItem('loginId')
    console.log(itemStr)
    if (!itemStr) {
      loginId.value = ''
      return
    }
    const item = JSON.parse(itemStr)
    const now = new Date()
    if (now.getTime() > item.expiry) {
      localStorage.removeItem('loginId')
      return
    }
    loginId.value = item.loginId
    console.log('loginId : ' + loginId.value)
  }

  const loginMethod = function (id, pwd) {
    let data = {
      userId: id,
      password: pwd,
    }
    axios
      .post('http://localhost:8080/user/login', data, { header: { 'Content-Type': 'application/json' } })
      .then((res) => {
        if (res.data == 'success') {
          loginId.value = id
          router.push('/')
          loginResult.value = 'success'
          const now = new Date()
          const item = {
            loginId: loginId.value,
            expiry: now.getTime() + 20 * 60 * 1000,
          }
          console.log(JSON.stringify(item))
          localStorage.setItem('loginId', JSON.stringify(item))
        } else {
          loginResult.value = 'fail'
        }
      })
  }

  const logoutMethod = function () {
    let ask = confirm('로그아웃 하시겠습니까?')
    if (ask) {
      loginId.value = ''
      localStorage.removeItem('loginId')
      router.push('/')
    }
  }

  // 유저 객체 가져오기
  const getById = function (loginId) {
    axios.get(`${REST_USER_API}/${loginId}`).then((res) => {
      loginUser.value = res.data
    })
  }

  return {
    loginMethod,
    loginId,
    logoutMethod,
    loginResult,
    loginUser,
    getById,
    localLogin,
  }
})

// 유저 삭제

export const userDeleteStore = defineStore('delete', () => {
  const deleteResult = ref('')
  const deleteMethod = function (loginId) {
    axios.delete(`${REST_USER_API}/${loginId}`).then((res) => {
      console.log('탈퇴', res)
      if (res.data == 'success') {
        alert('탈퇴 완료 ㅠㅠ')
        localStorage.removeItem('loginId')
        location.reload()
      }
      deleteResult.value = res.data
    })
  }
  return {
    deleteMethod,
    deleteResult,
  }
})
