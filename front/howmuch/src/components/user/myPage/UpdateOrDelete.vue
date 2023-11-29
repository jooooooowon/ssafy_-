<template>
  <div class="register">
    <div class="register__section">
      <!-- v-on:submit.prevent="join" -->
      <form class="register__form" v-on:submit.prevent="updateUser">
        <h1 class="register__title">My Info</h1>

        <div class="register__inputs">
          <div class="register__box">
            <input
              type="id"
              placeholder="아이디"
              required
              class="register__input"
              readonly
              :value="userStore.user.userId"
            />
            <i class="ri-mail-fill"></i>
          </div>

          <div class="register__box">
            <input
              type="email"
              placeholder="이메일"
              required
              class="register__input"
              :value="userStore.user.email"
              readonly
            />
            <i class="ri-mail-fill"></i>
          </div>

          <div class="register__box">
            <input type="password" placeholder="비밀번호" required class="register__input" v-model="temp.password" />
            <i class="ri-lock-2-fill"></i>
          </div>

          <div class="register__box">
            <input type="password" placeholder="비밀번호 확인" required class="register__input" v-model="pwdChk" />
            <i class="ri-lock-3-fill"></i>
          </div>

          <div class="register__box">
            <input type="name" placeholder="닉네임" required class="register__input" v-model="temp.nickname" />
            <button type="button" @click="nickNameChk" class="register__input chk">Name Check</button>
            <i class="ri-lock-4-fill"></i>
          </div>

          <div class="register__box">
            태어난 날 :
            <span v-if="userStore.user.birthday != undefined">
              <input
                type="text"
                required
                class="register__input"
                :value="userStore.user.birthday.substring(0, 10)"
                readonly
              />
            </span>
            <i class="ri-lock-4-fill"></i>
          </div>

          <div style="display: flex; justify-content: space-between">
            <div style="width: 49%" class="register__box">
              <input type="heigth" placeholder="키 (cm)" required class="register__input" v-model="temp.height" />cm
              <i class="ri-lock-5-fill"></i>
            </div>
            <div style="width: 49%" class="register__box">
              <input type="name" placeholder="몸무게 (kg)" required class="register__input" v-model="temp.weight" />kg
              <i class="ri-lock-5-fill"></i>
            </div>
          </div>
        </div>

        <div class="btn-wrapper" style="display: flex">
          <button type="submit" class="register__button">수정</button>
          <button class="cancel__button">취소</button>
        </div>
        <div class="register__register">
          계정을 탈퇴하시겠습니까?
          <span @click="deleteUser" v-bind:style="{ cursor: 'pointer', 'font-weight': 'bold' }">탈퇴</span>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { useUserInfoStore, useLoginStore, userUpdateStore, userDeleteStore } from '@/stores/user'
import { onMounted, ref, watch } from 'vue'
import axios from 'axios'
import _ from 'lodash'

const userStore = useUserInfoStore()
const loginStore = useLoginStore()
const deleteStore = userDeleteStore()
const updateStore = userUpdateStore()

const pwd = ref('')
const pwdRegex = ref(false)
const pwdChk = ref('')
const nickName = ref('')

const temp = ref({})
const nicknameChkTF = ref(false)
const pwdChkTF = ref(false)

onMounted(() => {
  loginStore.localLogin()

  userStore.getById(loginStore.loginId)
  console.log('userStore user', userStore.user)
  temp.value = { ...userStore.user }
  console.log('temp', temp.value)
})
let regex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,12}$/
// // 가능하면 색 변경.

// 자바스크립트 정규식 확인
watch(pwd, (newValue) => {
  console.log(regex.test(newValue))
  console.log(newValue)
  pwdRegex.value = regex.test(newValue)
})

// 비밀번호 체크 확인
watch(pwdChk, (newValue) => {
  if (regex.test(pwd.value) && newValue == pwd.value) {
    pwdChkTF.value = true
  } else {
    pwdChkTF.value = false
  }
})

// 닉네임 체크 확인
const nickNameChk = function () {
  axios
    .get(`http://localhost:8080/user/namechk`, {
      params: {
        nickName: nickName.value,
      },
    })
    .then((res) => {
      if (res.data == 'success') {
        nicknameChkTF.value = true
        alert('사용 가능한 닉네임 입니다.')
      } else {
        nicknameChkTF.value = false
        alert('이미 존재하는 닉네임 입니다.')
      }
    })
}

const updateUser = function () {
  updateStore.updateMethod({
    ...temp.value,
  })
}

const deleteUser = function () {
  if (confirm('정말 탈퇴할 거니? ㅜ ㅜ')) {
    deleteStore.deleteMethod(loginStore.loginId)
  } else return
}
</script>

<style scoped>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}

/*=============== VARIABLES CSS ===============*/
:root {
  /*========== Colors ==========*/
  /*Color mode HSL(hue, saturation, lightness)*/
  --white-color: hsl(0, 0%, 100%);
  --black-color: hsl(0, 0%, 0%);

  /*========== Font and typography ==========*/
  /*.5rem = 8px | 1rem = 16px ...*/
  --body-font: 'Poppins', sans-serif;
  --h1-font-size: 2rem;
  --normal-font-size: 1rem;
  --small-font-size: 0.813rem;
}

/*=============== BASE ===============*/
* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
  color: white;
}

body,
input,
button {
  font-family: var(--body-font);
  font-size: var(--normal-font-size);
}

a {
  text-decoration: none;
}

img {
  display: block;
  max-width: 100%;
  height: auto;
}

/*=============== register ===============*/
.register {
  position: relative;
  width: 100%;
  display: flex;
  margin: 30px auto;
  justify-content: center;
  align-items: center;
}

.register__section {
  width: 420px;
  display: inline-block; /* 또는 display: flex; */
  height: 80px 770px;
  border-radius: 1rem;
  background-image: url('https://github.com/bedimcode/login-form/blob/main/assets/img/login-bg.png?raw=true');
  background-size: cover;
  background-position: center;
  opacity: 90%;
}

.register__form {
  width: 100%;
  position: relative;
  height: 770px;
  border: 3px solid hsla(0, 0%, 100%, 0.7);
  padding: 2.5rem;
  color: var(--white-color);
  border-radius: 1rem;
  backdrop-filter: blur(4px);
}

.register__title {
  font-family: 'SBAggroB';
  text-align: center;
  font-style: italic;
  font-size: 1.4rem;
  font-weight: bold;
  margin-bottom: 1.25rem;
}

.register__inputs,
.register__box {
  display: grid;
  align-items: center;
}

.register__inputs {
  row-gap: 1.25rem;
  margin-bottom: 1rem;
}

.register__box {
  grid-template-columns: 1fr max-content;
  column-gap: 0.75rem;
  align-items: center;
  border: 2px solid hsla(0, 0%, 100%, 0.7);
  padding-inline: 1.25rem;
  border-radius: 4rem;
}

.register__input,
.register__button,
.cancel__button {
  border: none;
  outline: none;
}

.register__input {
  width: 100%;
  background: none;
  color: var(--white-color);
  padding-block: 1rem;
}

.chk {
  padding-block: 0.5rem;
  padding: 0.9rem 0.8rem;
}
.chk:hover {
  cursor: pointer;
  background-color: rgba(255, 255, 255, 0.4);
  transition: 0.9s;
  color: rgb(44, 26, 112);
  font-weight: bold;
  border-radius: 2em;
}

.register__input::placeholder {
  color: var(--white-color);
}

/* .register__box input:focus {
	color: black;
  } */

/* .register__input:focus+.register__box {
	background-color: rgba(56, 0, 0, 0.4);
	color: black;
  } */

.register__box i {
  font-size: 1.25rem;
}

.register__check,
.register__check-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.register__check {
  margin-bottom: 1rem;
  font-size: var(--small-font-size);
}

.register__check-box {
  column-gap: 0.5rem;
}

.register__check-input {
  width: 1rem;
  height: 1rem;
  accent-color: var(--white-color);
}

.register__forgot {
  color: var(--white-color);
}

.register__forgot:hover {
  text-decoration: underline;
}

.register__button {
  width: 100%;
  padding: 1rem;
  margin-bottom: 1rem;
  background-color: var(--white-color);
  border-radius: 4rem;
  color: var(--black-color);
  font-weight: 500;
  cursor: pointer;
}

.register__button:hover {
  background-color: rgba(255, 255, 255, 0.4);
  transition: 0.9s;
  color: rgb(44, 26, 112);
  font-weight: bold;
}

.cancel__button {
  width: 100%;
  padding: 1rem;
  margin-bottom: 1rem;
  background-color: var(--white-color);
  border-radius: 4rem;
  color: var(--black-color);
  font-weight: 500;
  cursor: pointer;
}

.cancel__button:hover {
  background-color: rgba(255, 255, 255, 0.4);
  transition: 0.9s;
  color: rgb(44, 26, 112);
  font-weight: bold;
}

.register__register {
  font-size: var(--small-font-size);
  text-align: center;
}

.register__register a {
  color: var(--white-color);
  font-weight: 500;
}

.register__register a:hover {
  text-decoration: underline;
}

/*=============== BREAKPOINTS ===============*/
/* For medium devices */
@media screen and (min-width: 576px) {
  .register {
    justify-content: center;
  }
  .register__form {
    width: 420px;
    padding-inline: 2.5rem;
  }
  .register__title {
    margin-bottom: 2rem;
  }
}
</style>
