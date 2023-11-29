<template>
  <main>
    <div class="aggro">
      <span>삼</span>
      <span>대</span>
      <span>&nbsp;</span>
      <span>몇</span>
      <span>&nbsp;</span>
      <span>치</span>
      <span>세</span>
      <span>요</span>
      <span>?</span>
      <span>?</span>
      <span>?</span>
    </div>
    <div class="container">
      <div type="text" class="content"></div>

      <div class="author">{{ author }}</div>
      <img :src="imgLink" />
    </div>
  </main>
</template>

<script setup>
import { onMounted, ref } from 'vue'
const imgLink = ref('')
const author = ref('')
const sentences = [
  {
    link: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MjRfMzYg/MDAxNTg3Njg4NDIxNzg3.A-XK8YvH9_12WkUudvz4qk76xJBswX7H_1yntnhVWZwg.TRwJ7Nqm43NoL2xrE5CSRhYThJalqNIHXMDnqERyt0Ug.JPEG.mitao685/2.JPG?type=w800',
    content: "오늘로부터 1년 뒤 당신은 '그때 시작할걸... 할지도 모른다.'",
    author: '카렌 렘',
  },
  {
    link: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MjRfNzkg/MDAxNTg3Njg4NDIzNzAx.MwK3WpPB35kNBDGNjZIoTnpfHuz1DAmk0-lw-z2Gmi4g.T8QsX_RmF_k8Xk2LAGQYAciVxyMVhHD8SXyvpjRicgUg.JPEG.mitao685/12.JPG?type=w800',
    content: '운동만 하고 제대로 안 챙겨 먹으면 그건 노동이다.',
    author: 'GYM종국',
  },
  {
    link: 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9974173B5B2A36540A',
    content: '헬스클럽은 클럽보다 더 즐거운 곳',
    author: 'GYM종국',
  },
  {
    link: 'https://file.sportsseoul.com/news/legacy/wyzmob/images/20150402/20150402_1427959645_53740000_1.jpg',
    content: '운동할 때 힘이 든 것은 몸이 아니라 마음',
    author: 'GYM종국',
  },
  {
    link: 'https://mblogthumb-phinf.pstatic.net/MjAyMjA3MThfMTAg/MDAxNjU4MTQ5MDcxNDU2.Q83bayTwEXYwdh2Y0cAQNN5cJBSCvnfJohwROq0efy4g.GJsjT2_e3guE38Mc1rIn-a2XhfKfhMy8ZOUq7Zi1Amcg.JPEG.dandilife/IMG_3403.JPG?type=w800',
    content: '운동의 고통은 통증일 뿐 힘든 것이 아니다',
    author: 'GYM종국',
  },
  {
    link: 'https://mblogthumb-phinf.pstatic.net/MjAyMjA3MThfMTEg/MDAxNjU4MTQ5MDc0NzI4.Pqbm2kvUGkyZHHP04ifIYgWfJKQ5-hPcMMO7kOu7wFwg.JO6ylGuhGzkaRU-P4bA_Cqc3Ies8a5pyytfAYJAZQe4g.JPEG.dandilife/IMG_3444.JPG?type=w800',
    content: '가슴 운동은 애인이랑 헤어졌을 때보다 더 아프게 해야 한다',
    author: '???',
  },
]

onMounted(() => {
  let sentenceIdx = 0
  let imgIdx = 0
  let sentence = ''
  function getImageIdx() {
    imgIdx = Math.floor(Math.random() * sentences.length)
    imgLink.value = sentences[imgIdx].link
  }
  function getSentenceIdx() {
    sentenceIdx = Math.floor(Math.random() * sentences.length)
    sentence = sentences[sentenceIdx].content
    author.value = sentences[sentenceIdx].author
  }
  function showStr() {
    if (idx == 0) {
      content.textContent = ''
    }
    if (idx < sentence.length) {
      content.textContent += sentence.charAt(idx)
      idx++
      setTimeout(showStr, 180)
    } else {
      idx = 0
      setTimeout(showStr, 1000)
      getImageIdx()
      getSentenceIdx()
    }
  }

  let idx = 0
  const content = document.querySelector('.content')
  getImageIdx()
  getSentenceIdx()
  showStr()
})
</script>

<style scoped>
.aggro {
  margin-top: 40px;
  text-align: center;
  font-family: 'yg-jalnan';
  font-size: 5rem;

  height: 6rem;
}

.aggro span {
  position: relative;
  top: 20px;
  color: rgb(255, 255, 255);
  display: inline-block;
  -webkit-text-stroke-width: 1.5px;
  -webkit-text-stroke-color: rgb(133, 107, 250);
  animation: bounce 0.3s ease infinite alternate;
  font-size: 80px;
  text-shadow: 0 1px 0 #ccc, 0 2px 0 #ccc, 0 3px 0 #ccc, 0 4px 0 #ccc, 0 5px 0 #ccc, 0 6px 0 transparent,
    0 7px 0 transparent, 0 8px 0 transparent, 0 9px 0 transparent, 0 10px 10px rgba(0, 0, 0, 0.4);
}

.aggro span:nth-child(1) {
  animation-delay: 0.1s;
}
.aggro span:nth-child(3) {
  animation-delay: 0.2s;
}
.aggro span:nth-child(4) {
  animation-delay: 0.3s;
}
.aggro span:nth-child(6) {
  animation-delay: 0.4s;
}
.aggro span:nth-child(7) {
  animation-delay: 0.6s;
}
.aggro span:nth-child(8) {
  animation-delay: 0.7s;
}
.aggro span:nth-child(9) {
  animation-delay: 0.5s;
}

.aggro span:nth-child(10) {
  animation-delay: 0.7s;
}

.aggro span:nth-child(11) {
  animation-delay: 0.8s;
}

.aggro span:nth-child(12) {
  animation-delay: 0.9s;
}

@keyframes bounce {
  100% {
    top: -20px;
    text-shadow: 0 1px 0 #ccc, 0 2px 0 #ccc, 0 3px 0 #ccc, 0 4px 0 #ccc, 0 5px 0 #ccc, 0 6px 0 #ccc, 0 7px 0 #ccc,
      0 8px 0 #ccc, 0 9px 0 #ccc, 0 50px 25px rgba(0, 0, 0, 0.2);
  }
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin-top: 55px;
}

.container img {
  width: 50%;
}

.content {
  width: 100%;
  height: auto;
  font-family: 'KOTRAHOPE';
  font-size: 3rem;
  margin-bottom: 20px;
  text-align: center;
  text-shadow: 0 0 6px rgb(184, 175, 222);
}

.author {
  color: gray;
  font-family: 'KOTRAHOPE';
  font-size: 1.8rem;
  margin-bottom: 40px;
}
</style>
