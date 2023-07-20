<template>
  <n-space justify="center">
    <n-card hoverable style="margin: 64px 0 64px 0; min-width: 75vw">
      <n-space vertical justify="center">
        <n-space justify="center" style="padding: 64px 0 32px 0">
          <n-avatar class="avatar" round :size="256">
            {{ avatarName }}
          </n-avatar>
        </n-space>
        <n-space justify="center">
          <n-h1>{{ username.name }}</n-h1>
        </n-space>
        <n-space
          vertical
          v-if="blogList.length > 0"
          v-for="blog in blogList"
          :key="blog.id"
        >
          <n-divider />
          <n-space justify="center">
            <n-button
              quaternary
              style="height: 256px; width: 75vw"
              :focusable="false"
              @click="handleBlogClick(blog.id)"
            >
              <n-space vertical style="width: 100%">
                <n-h2>{{ blog.title }}</n-h2>
                <n-space justify="center">
                  <n-tag
                    :bordered="false"
                    type="success"
                    v-for="tag in blog.tags"
                    :key="tag"
                  >
                    {{ tag }}
                  </n-tag>
                </n-space>
                <n-space justify="center">
                  <n-button text :focusable="false">
                    <template #icon>
                      <n-icon>
                        <LikeOutlined />
                      </n-icon>
                    </template>
                    {{ blog.liked }}
                  </n-button>
                  <n-button text :focusable="false" style="margin-left: 16px">
                    <template #icon>
                      <n-icon>
                        <StarOutline />
                      </n-icon>
                    </template>
                    {{ blog.favorite }}
                  </n-button>
                </n-space>
                <n-ellipsis :line="3" style="margin-top: 16px">
                  {{ blog.content }}
                </n-ellipsis>
              </n-space>
            </n-button>
          </n-space>
        </n-space>
        <n-empty v-else> 暂无文章 </n-empty>
      </n-space>
    </n-card>
  </n-space>
</template>

<script setup>
import { useRouter } from "vue-router";
import { LikeOutlined } from "@vicons/antd";
import { StarOutline } from "@vicons/ionicons5";
import api from "@/api";

const router = useRouter();
const store = useStore();
onMounted(() => {
  //用户头像根据用户名渲染
  //获取用户名、post信息
  var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]));
  username.name = userInfo.username;

  let input = `?authorId=${userInfo.id}`;
  console.log(userInfo.id);
  api.findInfoByBlogId(input).then((res) => {
    console.log(res.data.data);
    for (let item in res.data.data) {
      var blog = {
        id: 0,
        title: "",
        content: "",
        tags: [],
        liked: 0,
        favorite: 0,
      };
      blog.id = res.data.data[item].id;
      blog.title = res.data.data[item].title;
      // blog.tags = res.data.data[item].tags;
      blog.content = res.data.data[item].content;
      blog.liked = res.data.data[item].liked;
      blog.favorite = res.data.data[item].favorite;
      for (let tag in res.data.data[item].tags) {
        blog.tags.push(res.data.data[item].tags[tag].name);
      }
      blogList.value.push(blog);
    }
  });
});

const username = reactive({
  name: "",
});
const avatarName = computed(() => {
  return username.name.slice(0, 2);
});
const blogList = ref([]);

const handleBlogClick = (id) => {
  router.push(`/blog/${id}`);
};
</script>

<style lang="scss" scoped>
.avatar {
  font-size: 80px;
  transition: all 0.3s ease-in-out;
}

.avatar:hover {
  box-shadow: 0 0 32px gray;
}
</style>
