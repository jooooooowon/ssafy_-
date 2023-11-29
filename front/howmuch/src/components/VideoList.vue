<template>
  <div style="display: flex; justify-content: center">
    <div class="search-box">
      <div class="input-box">
        <input v-on:keyup.enter="search(type)" class="search" type="text" v-model="type" />
      </div>
      <button @click="search(type)">검색</button>
    </div>
  </div>

  <div class="body">
    <ul>
      <li v-for="(video, index) in store.videos">
        <div class="contentwrapper">
          <a :href="'https://www.youtube.com/watch?v=' + `${video.id.videoId}`">
            <img :src="video.snippet.thumbnails.default.url" style="margin-right: 1rem" />
          </a>
          <div>
            <div class="title">
              <a :href="'https://www.youtube.com/watch?v=' + `${video.id.videoId}`">{{ video.snippet.title }}</a>
              <br />
            </div>
            <div style="color: #606060; margin-bottom: 5px">{{ video.snippet.channelTitle }}</div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useVideoStore } from '../stores/videolist'
const store = useVideoStore()
const type = ref('')
const search = function (keyword) {
  store.youtubeSearch(keyword)
}
onMounted(() => {
  store.youtubeSearch()
})
</script>

<style scoped>
.search-box {
  width: 800px;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.input-box {
  position: relative;
  align-items: center;
  background-color: white;
  border: 1px solid rgb(210, 210, 210);
  border-right: none;
  border-radius: 40px 0 0 40px;
  box-shadow: inset 0 1px 2px #eee;
  caret-color: #0f0f0f;
  color: #111111;
  padding: 0 4px 0 16px;
  margin-left: 32px;
  flex: 1;
}

.search-box input {
  width: 100%;
  border: none;
  height: 98%;
  background-color: transparent;
}

.search-box input:focus {
  outline: none;
}

.search-box button {
  border: 1px solid RGB(217, 217, 217);
  background-color: rgb(248, 248, 248);
  border-radius: 0 40px 40px 0;
  cursor: pointer;
  height: 40px;
  width: 64px;
  margin: 0;
}

a {
  text-decoration: none;
  color: black;
}

ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

li {
  width: 24%;
}

li img {
  width: 100%;
  border-radius: 10px;
}

li img:hover {
  transition: 0.5s;
  border-radius: 0px;
}

.title {
  font-size: 1rem;
  margin: 0.5rem 0;
  line-height: 1.2rem;
}

.content {
  font-size: 1.1em;
}

.contentwrapper {
  margin: 10px 8px;
}

.body {
  height: 100%;
}
</style>
