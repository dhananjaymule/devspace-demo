import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0', // Critical for Dev Spaces port forwarding
    port: 5173,
    proxy: {
      // Assuming your Spring Boot APIs start with /api
      '/api': {
        target: 'http://localhost:8080', // The default Spring Boot port
        changeOrigin: true,
        secure: false,
      }
    }
  }
})
