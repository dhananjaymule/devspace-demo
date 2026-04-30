<template>
  <div class="app-container">
    <h1>Vue 3 + Spring Boot + OpenShift Dev Spaces</h1>
    <div class="card">
      <button @click="fetchData">Test Backend Connection</button>
      <p v-if="backendResponse"><strong>Response:</strong> {{ backendResponse }}</p>
      <p v-if="error" style="color: red;"><strong>Error:</strong> {{ error }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const backendResponse = ref(null)
const error = ref(null)

const fetchData = async () => {
  error.value = null
  backendResponse.value = null
  try {
    // This will be proxied to http://localhost:8080/api/data by Vite
    const response = await fetch('/api/data') 
    if (!response.ok) throw new Error('Network response was not ok')
    const data = await response.text() // Or .json() if your backend returns JSON
    backendResponse.value = data
  } catch (err) {
    error.value = err.message
  }
}
</script>

<style scoped>
.app-container {
  font-family: Arial, sans-serif;
  text-align: center;
  margin-top: 50px;
}
.card {
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  display: inline-block;
  margin-top: 20px;
}
button {
  padding: 10px 20px;
  cursor: pointer;
}
</style>