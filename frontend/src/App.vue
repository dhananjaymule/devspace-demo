<script setup>
import { ref } from 'vue'

const backendResponse = ref(null)
const error = ref(null)

const fetchData = async () => {
  error.value = null
  backendResponse.value = null
  try {
    // Calling our proxied route
    const response = await fetch('/api/status') 
    
    if (!response.ok) {
      throw new Error(`Backend returned ${response.status}`)
    }
    
    // Check if your status page returns JSON or String
    const data = await response.text() 
    backendResponse.value = data
  } catch (err) {
    error.value = `Connection Failed: ${err.message}`
  }
}
</script>

<template>
  <div style="text-align: center; margin-top: 50px;">
    <h1>Vue 3 Frontend</h1>
    <button @click="fetchData" style="padding: 10px 20px; cursor: pointer;">
      Check Backend Status
    </button>
    
    <div v-if="backendResponse" style="margin-top: 20px; color: green;">
      <h3>Success!</h3>
      <pre>{{ backendResponse }}</pre>
    </div>
    
    <div v-if="error" style="margin-top: 20px; color: red;">
      <h3>Error</h3>
      <p>{{ error }}</p>
    </div>
  </div>
</template>