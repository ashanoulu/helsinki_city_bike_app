// // const { defineConfig } = require('@vue/cli-service')
// // module.exports = defineConfig({
// //   server: {
// //     proxy: {
// //       "/api": {
// //         target: "https://apiservice.com",
// //         changeOrigin: true,
// //         secure: false,
// //         rewrite: (path) => path.replace(/^\/api/, ""),
// //       },
// //     },
// //   },
// //   // plugins: [vue()],
// //   transpileDependencies: true
// // })
//
//
// import vue from "@vitejs/plugin-vue"
// import { defineConfig } from "vite"
// export default defineConfig({
//   server: {
//     proxy: {
//       "/api": {
//         target: "https://apiservice.com",
//         changeOrigin: true,
//         secure: false,
//         rewrite: (path) => path.replace(/^\/api/, ""),
//       },
//     },
//   },
//   plugins: [vue()],
// })

module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/api'
        }
      }
    }
  }
}
