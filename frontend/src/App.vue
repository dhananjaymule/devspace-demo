<script setup>
import { ref, onMounted, computed } from 'vue'

const idInput = ref('')
const foodItems = ref([])
const error = ref(null)
const loading = ref(false)

// 1. COMPUTED PROPERTY: Calculates total cost whenever foodItems changes
const totalCost = computed(() => {
  return foodItems.value.reduce((sum, item) => sum + item.price, 0)
})

// 2. FETCH ALL: Function to get everything
const fetchAllItems = async () => {
  loading.value = true
  try {
    const response = await fetch('/api/items')
    if (!response.ok) throw new Error("Could not fetch items")
    foodItems.value = await response.json()
  } catch (err) {
    error.value = err.message
  } finally {
    loading.value = false
  }
}

// 3. LIFECYCLE HOOK: Runs when the page first loads
onMounted(() => {
  fetchAllItems()
})

const performSearch = async () => {
  if (!idInput.value.trim()) {
    fetchAllItems() // If input is empty, just show all again
    return
  }
  
  loading.value = true
  try {
    const idArray = idInput.value.split(',').map(id => parseInt(id.trim())).filter(id => !isNaN(id))
    const response = await fetch('/api/search', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ids: idArray })
    })
    foodItems.value = await response.json()
  } catch (err) {
    error.value = "Search failed"
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="container">
    <h1>Food Menu</h1>

    <div class="search-box">
      <input v-model="idInput" placeholder="Enter IDs (e.g. 1, 2)" />
      <button @click="performSearch">Search IDs</button>
      <button @click="fetchAllItems" style="margin-left: 10px;">Reset/Show All</button>
    </div>

    <table v-if="foodItems.length > 0" border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in foodItems" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>${{ item.price.toFixed(2) }}</td>
        </tr>
      </tbody>
      <tfoot>
        <tr style="font-weight: bold; background: #eee;">
          <td colspan="2" style="text-align: right;">Total Cost:</td>
          <td>${{ totalCost.toFixed(2) }}</td>
        </tr>
      </tfoot>
    </table>

    <p v-if="loading">Loading...</p>
    <p v-if="error" style="color: red;">{{ error }}</p>
  </div>
</template>

<style scoped>
.container { font-family: sans-serif; max-width: 600px; margin: 40px auto; text-align: center; }
.search-box { margin-bottom: 20px; }
table { width: 100%; border-collapse: collapse; margin-top: 20px; }
th, td { padding: 12px; border: 1px solid #ddd; }
</style>