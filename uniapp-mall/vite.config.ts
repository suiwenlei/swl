import { defineConfig } from 'vite'
import { createRequire } from 'node:module'
const require = createRequire(import.meta.url)
// Handle CJS/ESM interop for @dcloudio/vite-plugin-uni
const uniModule = require('@dcloudio/vite-plugin-uni')
const uniPlugin = uniModule.default || uniModule

export default defineConfig({
  plugins: uniPlugin(),
})

