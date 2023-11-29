import { createApp } from 'vue'
import { createPinia } from 'pinia'
import './assets/style.css'
import App from './App.vue'
import router from './router'
import VueCalendarHeatmap from 'vue3-calendar-heatmap'

const app = createApp(App)

app.use(VueCalendarHeatmap)
app.use(createPinia())
app.use(router)

app.mount('#app')
