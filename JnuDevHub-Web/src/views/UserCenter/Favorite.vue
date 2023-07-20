<template>
  <div class="container">
    <n-h1>我的收藏</n-h1>
    <n-card
      v-for="blog in blogList"
      class="oneCollection"
      style="margin-top: -6px"
    >
      <n-space justify="space-between" align="center">
        <n-space align="center">
          <n-button text @click="handleClickBlog(blog.id)">
            <div style="margin-left: 16px">{{ blog.title }}</div>
          </n-button>
          <n-space :inline="true" style="margin-left: 32px">
            <n-tag
              :bordered="false"
              type="success"
              v-for="tag in blog.tags"
              :key="tag.id"
            >
              {{ tag.name }}
            </n-tag>
          </n-space>
        </n-space>

        <n-button tertiary type="error" ghost="true" @click="handleConfirm">
          删除
        </n-button>
      </n-space>
    </n-card>
    <n-space justify="end">
      <n-button type="error" style="margin-top: 32px"> 删除全部 </n-button>
    </n-space>
  </div>
</template>

<script setup>
import { useMessage } from "naive-ui";
import axios from "axios";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();
const message = useMessage();

const blogList = ref([]);

onMounted(async () => {
  await getFavoriteBlogsId();
  await getFacoriteBlogs();
});

const getFavoriteBlogsId = async () => {
  axios
    .get(`api/user/getFavoriteOfUser?id=${store.getters["login/getUser"].id}`)
    .then((res) => {
      blogList.value = res.data.data;
    })
    .catch((err) => {
      message.error(err);
    });
};

const handleClickBlog = (blogId) => {
  router.push(`/blog/${blogId}`);
};
</script>
<style scoped>
.container {
  padding: 32px 64px 32px 64px;
}
.oneCollection {
  position: relative;
  /* margin-top: 1%; */
}

.cancel {
  display: inline;
  position: absolute;
  left: 90%;
}
</style>
