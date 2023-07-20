import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/store";

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("@/views/Home.vue"),
      meta: {},
    },
    {
      path: "/publish",
      name: "publish",
      component: () => import("@/views/Publish.vue"),
      meta: { requiresAuth: true },
    },
    {
      path: "/user-center",
      name: "user-center",
      component: () => import("@/views/UserCenter.vue"),
      meta: { requiresAuth: true },
      children: [
        {
          path: "home",
          name: "user-center-space",
          component: () => import("@/views/UserCenter/Space.vue"),
        },
        {
          path: "account",
          name: "user-center-account",
          component: () => import("@/views/UserCenter/Account.vue"),
        },
        {
          path: "favorite",
          name: "user-center-favorite",
          component: () => import("@/views/UserCenter/Favorite.vue"),
        },
      ],
    },
    {
      path: "/blog/:id",
      name: "blog",
      component: () => import("@/views/Blog.vue"),
      meta: {},
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    const token = localStorage.getItem("token");
    if (!token) {
      store.commit("login/showLoginDialog");
      console.log("请先登录");
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
