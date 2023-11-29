import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useVideoStore = defineStore('video', () => {
  // onMounted(() => {
  //   console.log(123);
  //   youtubeSearch();
  // });
  const videos = ref([])
  const youtubeSearch = function (type) {
    const URL = 'https://www.googleapis.com/youtube/v3/search'
    const API_KEY = 'AIzaSyDG5CSqaUne50q2rN7ZKM6iAmd5ENTK8Yc'
    if (type === undefined) {
      type = '운동'
    }
    axios({
      url: URL,
      method: 'GET',
      params: {
        key: API_KEY,
        part: 'snippet',
        q: type,
        type: 'video',
        maxResults: 96,
      },
    })
      .then((response) => {
        console.log(response)
        videos.value = response.data.items
      })
      .catch(() => {
        console.log('fail')
      })
  }
  return {
    videos,
    youtubeSearch,
  }
})
