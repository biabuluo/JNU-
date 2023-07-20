<template>
  <n-space justify="center">
    <n-card hoverable style="margin: 64px; width: 80vw">
      <n-space vertical>
        <n-h1>{{ blog.title }}</n-h1>

        <n-card embedded hoverable>
          <n-space vertical align="center">
            <n-space justify="space-between">
              <n-button text :focusable="false">
                <template #icon>
                  <n-icon>
                    <UserOutlined />
                  </n-icon>
                </template>
                {{ blog.authorName }}
              </n-button>

              <n-button text :focusable="false">
                <template #icon>
                  <n-icon>
                    <TimeOutline />
                  </n-icon>
                </template>
                {{ blog.updateTime }}
              </n-button>

              <n-button
                text
                :focusable="false"
                :type="favoriteButtonType"
                @click="handleFavorite"
              >
                <template #icon>
                  <n-icon>
                    <StarOutline />
                  </n-icon>
                </template>
                {{ blog.favorited }}
              </n-button>

              <n-button
                text
                :focusable="false"
                :type="likeButtonType"
                @click="handleLike"
              >
                <template #icon>
                  <n-icon>
                    <LikeOutlined />
                  </n-icon>
                </template>
                {{ blog.liked }}
              </n-button>
            </n-space>

            <n-space align="center">
              <n-tag
                v-for="tag in blog.tags"
                :key="tag"
                type="success"
                :bordered="false"
              >
                {{ tag.name }}
              </n-tag>
            </n-space>
          </n-space>
        </n-card>

        <div
          v-html="marked.parse(blog.content)"
          style="margin-top: 32px; margin-bottom: 32px"
        />

        <n-card class="comments-section" embedded hoverable>
          <template #header>
            <span>评论区</span>
          </template>
          <n-form
            v-if="isLogin"
            ref="commentFormRef"
            :model="commentFormValue"
            :rules="commentFormRules"
          >
            <n-form-item path="content">
              <n-input
                type="textarea"
                size="large"
                maxlength="1000"
                show-count
                placeholder="发表你的评论"
                v-model:value="commentFormValue.content"
              />
            </n-form-item>
            <n-space justify="end">
              <n-button type="primary" @click="handleComment">
                发布评论
              </n-button>
            </n-space>
          </n-form>

          <n-space
            v-if="comments.length !== 0"
            vertical
            style="padding-left: 16px; padding-right: 16px"
          >
            <n-space v-for="comment in comments" :key="comment.id" vertical>
              <n-divider />
              <n-space>
                <n-p>{{ comment.content }}</n-p>
              </n-space>
              <n-space justify="end">
                <n-button text :focusable="false">
                  <template #icon>
                    <n-icon>
                      <UserOutlined />
                    </n-icon>
                  </template>
                  {{ comment.authorName }}
                </n-button>

                <n-button text :focusable="false">
                  <template #icon>
                    <n-icon>
                      <TimeOutline />
                    </n-icon>
                  </template>
                  {{ comment.updateTime }}
                </n-button>
              </n-space>
            </n-space>
          </n-space>
          <n-empty v-else> 暂时还没有评论 </n-empty>
        </n-card>
      </n-space>
    </n-card>
  </n-space>
</template>

<script setup>
import { UserOutlined } from "@vicons/antd";
import { useRoute } from "vue-router";
import { TimeOutline, StarOutline } from "@vicons/ionicons5";
import { LikeOutlined } from "@vicons/antd";
import api from "@/api";
import { useMessage } from "naive-ui";
import axios from "axios";

import { marked } from "marked";
const message = useMessage();

const route = useRoute();
const store = useStore();

const isFavorited = ref(false);
const isLiked = ref(false);

onMounted(() => {
  refreshBlog();
  refreshComments();
});

const refreshBlog = async () => {
  const id = Number(route.params.id);
  // TODO: 从服务器获取博客
  let tmp = `?id=${id}`;
  api.findInfoByBlogId(tmp).then((res) => {
    // console.log(res.data.data);
    let data = res.data.data[0];
    blog.value.title = data.title;
    blog.value.content = data.content;
    blog.value.tags = data.tags;
    blog.value.authorName = data.authorName;
    blog.value.authorId = data.authorId;
    blog.value.updateTime = data.updatedTime;
    console.log("在这里！");
    console.log(data.updateTime);
    blog.value.liked = data.liked;
    blog.value.favorited = data.favorite;
  });
  // console.log(blog.value);
  // blog.value = ...;
  await refreshLikeAndFavorite();
};

const refreshComments = async () => {
  const id = Number(route.params.id);
  // TODO: 从服务器获取评论列表
  // comments.value = ...;
  // let tmp = `?postId=${id}`;
  api.findCommentByBlogId(id).then((res) => {
    console.log(res);
    let data = res.data.data;
    for (let index in data) {
      let comment = {
        id: 0,
        content: "",
        authorName: "",
        authorId: 0,
        updateTime: "",
      };
      comment.id = data[index].id;
      comment.content = data[index].content;
      comment.authorName = data[index].authorName;
      comment.authorId = data[index].authorId;
      comment.updateTime = data[index].updatedTime;
      comments.value.push(comment);
    }
  });
};

const refreshLikeAndFavorite = async () => {
  if (isLogin.value) {
    // TODO: 查询当前用户是否点赞
      var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]))
    var userId = userInfo.id;
    var postId = Number(route.params.id);
    console.log(userId)
    console.log(postId)
    axios.get("/api/like?postId=" + postId + "&userId=" + userId).then((res) => {
      console.log(res)
      isLiked.value = res.data.code == 200
    })
    // isLiked.value = ...;
    // TODO: 查询当前用户是否收藏
    axios.get("/api/favorite?postId=" + postId + "&userId=" + userId).then((res) => {
      console.log(res)
      isFavorited.value = res.data.code == 200
    })
    // isFavorited.value = ...;
  }
  await Promise.resolve();
};

const blog = ref({
  title: "Hello World",
  content: "Hello World",
  tags: [
    {
      id: 1,
      name: "Technology",
    },
    {
      id: 2,
      name: "Life",
    },
  ],
  authorName: "07akioni",
  authorId: 1,
  updateTime: "2021-10-01 00:00:00",
  liked: 0,
  favorited: 0,
});

const comments = ref([
  {
    id: 1,
    content: "Hello World",
    authorName: "07akioni",
    authorId: 1,
    updateTime: "2021-10-01 00:00:00",
  },
]);

const isLogin = computed(() => {
  return store.getters["login/isLogin"];
});

const favoriteButtonType = computed(() => {
  return isFavorited.value ? "primary" : "default";
});

const likeButtonType = computed(() => {
  return isLiked.value ? "primary" : "default";
});

const handleFavorite = async () => {
  if (!isLogin.value) {
    store.commit("login/showLoginDialog");
    return;
  }
  var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]))
  var userId = userInfo.id;
  var postId = Number(route.params.id);
  if (isFavorited.value) {
    var url = "/api/favorite" + "?postId=" + postId + "&userId=" + userId
    axios.delete(url).then((res) => {
      if (res.data.data == 200) {
                isFavorited.value = !isFavorited.value
        // TODO 提示成功
      } else {
        // TODO 提示失败
      }
    })
  } else {
    var params = new URLSearchParams();
    params.append("postId", postId)
    params.append("userId", userId)
    axios.post("/api/favorite", params).then((res) => {
    if (res.data.code = 200) {
              isFavorited.value = !isFavorited.value
      } else {
      }
    })
  }
  await refreshLikeAndFavorite();
};

const handleLike = async () => {
  if (!isLogin.value) {
    store.commit("login/showLoginDialog");
    console.log("login");
    return;
  }
    var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]))
  var userId = userInfo.id;
  var postId = Number(route.params.id);
  if (isLiked.value) {
      var url = "/api/like" + "?postId=" + postId + "&userId=" + userId
      axios.delete(url).then((res) => {
        if (res.data.code == 200) {
        // TODO 提示成功
        isLiked.value = !isLiked.value
      } else {
        // TODO 提示失败
      }
    })
  } else {
    var params = new URLSearchParams();
    params.append("postId", postId)
    params.append("userId", userId)
    axios.post("/api/like", params).then((res) => {
      if (res.data.code = 200) {
                isLiked.value = !isLiked.value
      } else {
      }
    })
  }
  await refreshLikeAndFavorite();
};

const commentFormRef = ref(null);
const commentFormValue = reactive({
  content: "",
});
const commentFormRules = {
  content: [
    {
      required: true,
      message: "请输入评论",
    },
  ],
};

const handleComment = async () => {
  commentFormRef.value?.validate(async (errors) => {
    if (!errors) {
      var postId = Number(route.params.id);
      var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]));
      var userId = userInfo.id;
      // console.log(commentFormValue.content)
      // var params = new URLSearchParams()
      // params.append("postId", postId)
      // params.append("authorId", authorId)
      // params.append("content", commentFormValue.content)
      var params = `?postId=${postId}&authorId=${userId}&content=${commentFormValue.content}`;
      console.log(params)
      // TODO: 提交评论
      api.addComment(params).then((res)=>{
          if(res.data.code == 200){
            //提交成功！
            message.success("评论成功");
          }
          else{
            //提交失败！
            message.warning("评论失败");
          }
      })
      await refreshComments();
    } else {
      console.log("error");
    }
  });
};
</script>

<style lang="scss" scoped></style>
