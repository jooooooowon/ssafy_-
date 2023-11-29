<template>
  <div class="login">
    <div class="login__section">
      <form v-on:submit.prevent="login" class="login__form">
        <h1
          style="font-family: 'SBAggroB'; font-weight: bold; font-style: italic; font-size: 1.4rem"
          class="login__title"
        >
          LOGIN
        </h1>

        <div class="login__inputs">
          <div :class="{ fail_box: store.loginResult == 'fail', login__box: store.loginResult != 'fail' }">
            <input type="id" placeholder="ID" required class="login__input" v-model="id" />
            <i class="ri-mail-fill"></i>
          </div>

          <div :class="{ fail_box: store.loginResult == 'fail', login__box: store.loginResult != 'fail' }">
            <input type="password" placeholder="Password" required class="login__input" v-model="pwd" />
            <i class="ri-lock-2-fill"></i>
          </div>
        </div>
        <button type="submit" class="login__button">GO</button>
        <div v-show="store.loginResult == 'fail'" class="fail-msg">로그인에 실패했습니다.</div>

        <div class="login__register">
          계정이 없으신가요?&nbsp;&nbsp;<router-link to="register" v-bind:style="{ 'font-weight': 'bold' }"
            >회원가입</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { ref } from 'vue'
import { useLoginStore } from '../../stores/user'
const id = ref('')
const pwd = ref('')
const store = useLoginStore()
const login = function () {
  store.loginMethod(id.value, pwd.value)
  if (store.loginResult == 'fail') {
    let vibrations = document.querySelectorAll('.login__inputs div')
    vibrations.forEach((elements) => {
      elements.classList.add('vibration')
    })
    setTimeout(function () {
      vibrations.forEach((elements) => {
        elements.classList.remove('vibration')
      })
    }, 400)
  }
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

/*=============== GOOGLE FONTS ===============*/
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

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

/*=============== LOGIN ===============*/
.login {
  display: flex;
  margin: auto;
  height: 100%;
  padding-top: 150px;
}

.login__section {
  width: 420px;
  height: 400px;
  border-radius: 1rem;
  background-image: url('https://github.com/bedimcode/login-form/blob/main/assets/img/login-bg.png?raw=true');
  background-size: cover;
  background-position: center;
  opacity: 90%;
}

.login__form {
  position: relative;
  height: 400px;
  border: 3px solid hsla(0, 0%, 100%, 0.7);
  padding: 2.5rem;
  color: var(--white-color);
  border-radius: 1rem;
  backdrop-filter: blur(4px);
}

.login__title {
  text-align: center;
  font-size: var(--h1-font-size);
  margin-bottom: 1.25rem;
}

.login__inputs,
.login__box {
  display: grid;
}

.login__inputs {
  row-gap: 1.25rem;
  margin-bottom: 1rem;
}

.fail_box {
  grid-template-columns: 1fr max-content;
  column-gap: 0.75rem;
  align-items: center;
  border: 3px solid rgba(256, 0, 0, 0.7);
  padding-inline: 1.25rem;
  border-radius: 4rem;
  animation: vibration 0.1s;
  animation-iteration-count: 4;
}

.vibration {
  animation: vibration 0.1s infinite;
  /* animation-iteration-count: 4; */
}

@keyframes vibration {
  from {
    transform: rotate(1deg);
  }
  to {
    transform: rotate(-1deg);
  }
}

.login__box {
  grid-template-columns: 1fr max-content;
  column-gap: 0.75rem;
  align-items: center;
  border: 3px solid hsla(0, 0%, 100%, 0.7);
  padding-inline: 1.25rem;
  border-radius: 4rem;
}

.login__input,
.login__button {
  border: none;
  outline: none;
}

.login__input {
  width: 100%;
  background: none;
  color: var(--white-color);
  padding-block: 1rem;
}

.login__input::placeholder {
  color: var(--white-color);
}

.login__box i {
  font-size: 1.25rem;
}

.login__button {
  width: 100%;
  padding: 1rem;
  font-family: 'SBAggroB';
  font-style: italic;
  background-color: var(--white-color);
  border-radius: 4rem;
  font-weight: bold;
  margin-bottom: 15px;
  color: var(--black-color);
  cursor: pointer;
}

.login__button:hover {
  background-color: rgba(255, 255, 255, 0.4);
  transition: 0.9s;
  color: rgb(44, 26, 112);
  font-weight: bold;
}

.fail-msg {
  text-align: center;
  color: rgb(256, 0, 0);
  margin-bottom: 1em;
}

.login__register {
  font-size: var(--small-font-size);
  text-align: center;
}

.login__register a {
  color: var(--white-color);
  font-weight: 500;
}

.login__register a:hover {
  text-decoration: underline;
}

/*=============== BREAKPOINTS ===============*/
/* For medium devices */
@media screen and (min-width: 576px) {
  .login {
    justify-content: center;
  }
  .login__form {
    width: 420px;
    padding-inline: 2.5rem;
  }
  .login__title {
    margin-bottom: 2rem;
  }
}

/* ---------------------------------------------------------- */
</style>
