import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0',
    port: 5173,
    allowedHosts: true, 
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
        // 📍 REWRITE LOGIC: 
        // This changes "/api/status" to just "/status" before sending to Spring Boot
        rewrite: (path) => path.replace(/^\/api/, ''),
      }
    }
  }
})