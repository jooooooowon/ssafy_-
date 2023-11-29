<template>
  <header>
    <nav>
      <div class="content-link">
        <div style="display: flex; justify-content: space-between">
          <div>
            <RouterLink to="videolist">VIDEOS</RouterLink>
          </div>
          <div>
            <div @click="ranklist"><a>RANKING</a></div>
          </div>
        </div>
      </div>
      <div class="home">
        <RouterLink to="/"><img class="logo" src="../assets/logo.png" alt="로고임" /></RouterLink>
      </div>
      <div class="user-link">
        <div>
          <div
            v-if="loginStore.loginId === null || loginStore.loginId == ''"
            style="display: flex; justify-content: space-between"
          >
            <RouterLink to="login">LOGIN</RouterLink>
            <RouterLink to="register">JOIN US</RouterLink>
          </div>
          <div v-else style="display: flex; justify-content: space-between">
            <RouterLink to="myPage">MY PAGE</RouterLink>
            <div class="logout" @click="logout">LOGOUT</div>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { useLoginStore } from '../stores/user'
import { onMounted } from 'vue'
const router = useRouter()
const loginStore = useLoginStore()
onMounted(() => {
  loginStore.localLogin()
  console.log(loginStore.loginId)
})
let logout = function () {
  loginStore.logoutMethod()
  console.log(loginStore.loginId)
}

let ranklist = function () {
  if (loginStore.loginId === null || loginStore.loginId == '') {
    alert('로그인 후 이용해주세요.')
  } else {
    router.replace('/ranklist')
  }
}
</script>

<style scoped>
* {
  font-family: 'SBAggroB';
  font-style: italic;
  font-size: 2rem;
}

header {
  height: auto;
  margin-top: 20px;
}

header nav {
  display: flex;
  position: relative;
  padding-top: 15px;
  justify-content: space-around;
  align-items: center;
}

.logo {
  object-fit: cover;
  width: 180px;
  height: auto;
}

.home {
  margin: auto 0;
}
/* .user-link {
  position: absolute;
  right: 10px;
} */

.user-link {
  text-align: right;
  width: 30%;
  margin-right: 20px;
}

.user-link a {
  text-decoration: none;
  font-weight: bold;
  font-size: 1em;
  color: #000;
  margin-right: 5px;
}

.user-link a:hover {
  transition: 0.8s;
  font-size: 1.05em;
  color: rgb(91, 70, 183);
}
/* 
.content-link {
  position: absolute;
  right: 0;
  bottom: 0;
  display: flex;
} */

.content-link {
  width: 30%;
  margin-left: 20px;
}

.content-link a {
  cursor: pointer;
  text-decoration: none;
  font-size: 1em;
  font-weight: bold;
  color: #000;
  margin-left: 5px;
}

.content-link a:hover {
  font-size: 1.05em;
  transition-duration: 0.8s;
  color: rgb(91, 70, 183);
}
.logout {
  border: 0;
  background-color: transparent;
  cursor: pointer;
  font-weight: bold;
  font-size: 1em;
  color: #000;
  margin-right: 5px;
}
.logout:hover {
  font-size: 1.05em;
  transition-duration: 0.8s;
  color: rgb(91, 70, 183);
}
</style>
