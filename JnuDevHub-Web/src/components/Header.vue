<template>
  <n-layout-header class="header" bordered>
    <n-space align="center" justify="space-between" style="padding-top: 6px">
      <n-space align="center">
        <n-image
          class="logo"
          width="48"
          height="48"
          :src="JnuDev1"
          preview-disabled
        />

        <n-h2 class="title"> JNU DevHub </n-h2>
        <n-menu class="menu" mode="horizontal" :options="menuOptions" />
        <n-auto-complete
          class="search-box"
          placeholder="搜索"
          clear-after-select
          blur-after-select
          :options="searchOptions"
          v-model:value="searchText"
          @select="handleSearchSelect"
        >
          <template #prefix>
            <n-icon :component="Search" />
          </template>
        </n-auto-complete>
      </n-space>
      <n-space align="center" justify="end">
        <n-button
          class="button"
          quaternary
          :focusable="false"
          @click="handleToggleTheme"
        >
          {{ themeButtonText }}
        </n-button>
        <n-button
          class="button"
          round
          type="primary"
          :focusable="false"
          @click="handlePublish"
        >
          发布
        </n-button>
        <n-dropdown
          :options="dropDownOptions"
          @select="handleUserDropDownSelect"
        >
          <n-avatar class="avatar" round :size="42">
            {{ username }}
          </n-avatar>
        </n-dropdown>
      </n-space>
    </n-space>
  </n-layout-header>
</template>

<script setup>
import JnuDev1 from "@/assets/JnuDev1.jpg";
import api from "@/api";
import { RouterLink, useRouter } from "vue-router";
import { Search } from "@vicons/ionicons5";
import { NIcon, useMessage } from "naive-ui";
import { User, Exit } from "@vicons/carbon";

const renderIcon = (icon) => {
  return () => {
    return h(NIcon, null, {
      default: () => h(icon),
    });
  };
};

const menuOptions = [
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "home",
          },
        },
        { default: () => "首页" }
      ),
    key: "home",
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "user-center-space",
          },
        },
        { default: () => "用户" }
      ),
    key: "user-center-space",
  },
];

const dropDownOptions = computed(() => {
  if (isLogin.value) {
    return [
      {
        label: "退出",
        key: "logout",
        icon: renderIcon(Exit),
      },
    ];
  } else {
    return [
      {
        label: "登录",
        key: "login",
        icon: renderIcon(User),
      },
    ];
  }
});

const message = useMessage();
const store = useStore();
const router = useRouter();

const themeButtonText = computed(() => {
  return store.state.theme.isDarkTheme ? "浅色" : "深色";
});

const handleUserDropDownSelect = (key) => {
  if (key === "logout") {
    store.commit("login/removeUserInfo");
    message.success("登出成功");
  } else if (key === "login") {
    store.commit("login/showLoginDialog");
  } else if (key === "username") {
    router.push("/user-center/home");
  }
};

const blogList = ref([]);

onMounted(() => {
  api
    .getAllBlogs()
    .then((res) => {
      if (res.data.code == 200) {
        blogList.value = res.data.data;
        console.log(blogList.value);
      }
    })
    .catch((err) => {
      console.log(err);
    });
});

const searchText = ref("");
const searchResult = ref([]);

const searchOptions = computed(() => {
  if (searchText.value) {
    searchResult.value = blogList.value.filter((item) => {
      return item.title.toLowerCase().includes(searchText.value.toLowerCase());
    });
    return searchResult.value.map((item) => {
      return {
        label: item.title,
        value: item.title,
      };
    });
  } else {
    return [];
  }
});

const handleSearchSelect = (value) => {
  if (searchResult.value.length) {
    router.push(`/blog/${searchResult.value[0].id}`);
  }
};

const isLogin = computed(() => {
  return store.getters["login/isLogin"];
});

const userInfo = computed(() => {
  return store.state.login.userInfo;
});

const username = computed(() => {
  return isLogin.value ? userInfo.value.username.slice(0, 2) : "登录";
});

const handleToggleTheme = () => {
  store.commit("theme/toggleTheme");
};

const handlePublish = () => {
  router.push("/publish");
};

const handleSearch = () => {
  console.log("search");
};
</script>

<style lang="scss" scoped>
.header {
  height: 64px;
  width: 100%;
  top: 0;
  padding: 0 24px 0 24px;
}

.logo {
  margin: 0;
  padding: 0;
}

.title {
  margin: 0;
  padding: 0;
}

.menu {
  margin-left: 128px;
}

.search-box {
  text-align: left;
  margin-left: 32px;
  width: 100%;
}

.button {
  margin-left: 16px;
}

.avatar {
  font-size: 16px;
  margin: 4px 0 0 36px;
  transition: all 0.2s;
}

.avatar:hover {
  cursor: pointer;
  transform: scale(1.1);
  box-shadow: 0 0 16px gray;
}
</style>
