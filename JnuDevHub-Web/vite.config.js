import { defineConfig } from "vite";
import { join } from "path";
import fs from "fs";
import { NaiveUiResolver } from "unplugin-vue-components/resolvers";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      imports: ["vue", "vue-router", "vuex"],
      dts: "auto-imports.d.ts",
    }),
    Components({
      dts: true,
      dirs: ["src/components", "src/views"],
      resolvers: [NaiveUiResolver()],
    }),
  ],
  server: {
    https: {
      key: fs.readFileSync("keys/agent2-key.pem"),
      cert: fs.readFileSync("keys/agent2-cert.pem"),
    },
    proxy: {
      "/api": {
        //'/api'
        target: "https://127.0.0.1:8888",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
  preprocessorOptions: {
    scss: {
      additionalData: '@import "./src/assets/style/main.scss";',
    },
  },
  base: "./",
  resolve: {
    alias: {
      "@": join(__dirname, "src"),
    },
  },
});
