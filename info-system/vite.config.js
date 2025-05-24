import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/Lab_2_DP': { // *** ЗМІНЕНО: тепер проксі слухає на '/Lab_2_DP' ***
        target: 'http://localhost:8080', // *** ЗМІНЕНО: target тепер базовий URL Tomcat ***
        changeOrigin: true,
        secure: false
      }
      // '/assets' більше не потрібен окремо, оскільки він входить в '/Lab_2_DP'
    }
  }
})