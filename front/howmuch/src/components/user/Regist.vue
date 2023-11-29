<template>
  <div class="register">
    <div class="register__section">
      <form v-on:submit.prevent="join" class="register__form">
        <h1
          style="font-family: 'SBAggroB'; font-weight: bold; font-style: italic; font-size: 1.4rem"
          class="register__title"
        >
          REGISTER
        </h1>

        <div class="register__inputs">
          <div class="register__box">
            <input type="id" placeholder="아이디" required class="register__input" v-model="id" />
            <button type="button" @click="idChk" class="register__input chk">Id Check</button>
            <i class="ri-mail-fill"></i>
          </div>

          <div class="register__box">
            <input type="email" placeholder="이메일" required class="register__input" v-model="eMail" />
            <i class="ri-mail-fill"></i>
          </div>

          <div class="register__box">
            <input type="password" placeholder="비밀번호" required class="register__input" v-model="pwd" />
            <i class="ri-lock-2-fill"></i>
          </div>
          <div v-show="pwd != '' && !pwdRegex">
            비밀번호는 8~12자 내의 <br />영문자, 특수문자, 숫자가 포함되어야 합니다.
          </div>

          <div class="register__box">
            <input type="password" placeholder="비밀번호 확인" required class="register__input" v-model="pwdChk" />
            <i class="ri-lock-3-fill"></i>
          </div>
          <div v-show="pwdChk != '' && pwd != pwdChk" style="color: #f00; font-weight: bold">
            비밀번호 확인을 다시 해주세요.
          </div>
          <div v-show="pwdChk != '' && pwd == pwdChk">비밀번호가 일치합니다.</div>

          <div class="register__box">
            <input type="name" placeholder="닉네임" required class="register__input" v-model="nickName" />
            <button type="button" @click="nickNameChk" class="register__input chk">Name Check</button>
            <i class="ri-lock-4-fill"></i>
          </div>

          <div class="register__box">
            태어난 날 : <input type="date" required class="register__input" v-model="birthdate" />
            <i class="ri-lock-4-fill"></i>
          </div>

          <div style="display: flex; justify-content: space-between">
            <div style="width: 49%" class="register__box">
              <input type="heigth" placeholder="키 (cm)" required class="register__input" v-model="h" />
              <i class="ri-lock-5-fill"></i>
            </div>
            <div style="width: 49%" class="register__box">
              <input type="name" placeholder="몸무게 (kg)" required class="register__input" v-model="w" />
              <i class="ri-lock-5-fill"></i>
            </div>
          </div>
        </div>

        <button type="submit" class="register__button">JOIN</button>
        <div v-if="joinStore.joinResult == 'fail'">다시 시도해주세요.</div>
        <div class="register__register">
          계정이 있으신가요? <router-link to="login" v-bind:style="{ 'font-weight': 'bold' }">로그인</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { useJoinStore } from '../../stores/user'
import { ref, watch } from 'vue'
import axios from 'axios'
const joinStore = useJoinStore()
const id = ref('')
const eMail = ref('')
const pwd = ref('')
const pwdRegex = ref(false)
const pwdChk = ref('')
const nickName = ref('')
const h = ref('')
const w = ref('')
const data = ref({})
const birthdate = ref('')
let regex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,12}$/
// 가능하면 색 변경.

// idChkTF, nicknameChkTF, pwdChkTF : 아이디, 닉네임, 비밀번호 체크 했는 지 확인.
const idChkTF = ref(false)
const nicknameChkTF = ref(false)
const pwdChkTF = ref(false)

// 아이디 체크 하기.
const idChk = function () {
  axios
    .get(`http://localhost:8080/user/idchk`, {
      params: {
        userId: id.value,
      },
    })
    .then((res) => {
      if (res.data == 'success') {
        idChkTF.value = true
        alert('사용 가능한 아이디입니다.')
      } else {
        idChkTF.value = false
        alert('이미 존재하는 아이디 입니다.')
      }
    })
}

// email 체크 확인
const emailChk = function () {
  console.log('email : ' + eMail.value)
  const encodedEmail = encodeURIComponent(eMail.value)
  console.log(encodedEmail)
  axios
    .get('http://localhost:8080/user/emailchk', {
      params: {
        email: encodedEmail,
      },
    })
    .then((res) => {
      console.log('res : ' + res.data)
    })
}

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

// id, 비밀번호, 닉네임 모두 체크했는 지 확인한 후 회원가입하기.
const join = function () {
  if (!idChkTF.value) {
    alert('id체크는 필수 입니다.')
    return
  }
  if (!pwdChkTF.value) {
    alert('비밀번호 체크는 필수 입니다.')
    return
  }
  if (!nicknameChkTF.value) {
    alert('닉네임 체크는 필수 입니다.')
    return
  }
  data.value = {
    userId: id.value,
    password: pwd.value,
    email: eMail.value,
    birthday: birthdate.value,
    nickname: nickName.value,
    weight: w.value,
    height: h.value,
  }
  console.log(data.value)
  joinStore.joinMethod(data.value)
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
  height: 100%;
  width: 100%;
  display: flex;
  margin: auto;
  justify-content: center;
  padding-top: 50px;
}

.register__section {
  width: 420px;
  display: inline-block; /* 또는 display: flex; */
  height: 850px;
  border-radius: 1rem;
  background-image: url('https://github.com/bedimcode/login-form/blob/main/assets/img/login-bg.png?raw=true');
  background-size: cover;
  background-position: center;
  opacity: 90%;
}

.register__form {
  width: 100%;
  position: relative;
  height: 850px;
  border: 3px solid hsla(0, 0%, 100%, 0.7);
  padding: 2.5rem;
  color: var(--white-color);
  border-radius: 1rem;
  backdrop-filter: blur(4px);
}

.register__title {
  text-align: center;
  font-size: var(--h1-font-size);
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
.register__button {
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
  font-weight: bold;
  background-color: var(--white-color);
  border-radius: 4rem;
  font-family: 'SBAggroB';
  font-style: italic;
  color: var(--black-color);
  cursor: pointer;
}

.register__button:hover {
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
