<template>
  <n-card title="个人收藏列表" class="container">
    <n-card
      v-for="blog in blogList"
      class="oneCollection"
      style="margin-top: -6px"
    >
      <n-tag :bordered="false" type="error"> 博客 </n-tag>
      <n-button text>
        <div style="margin-left: 16px">{{ blog.title }}</div>
      </n-button>

      <div class="cancel">
        <n-button tertiary type="error" ghost="true" @click="cancelConfirm">
          删除
        </n-button>
      </div>
    </n-card>
    <n-button type="error" class="btn" style="margin-top: 16px"
      >删除全部</n-button
    >
  </n-card>
</template>

<script setup>
import { useMessage, useDialog } from "naive-ui";
const blogList = [
  { title: "博客标题111111" },
  { title: "博客标题222222222" },
  { title: "博客标题333" },
  { title: "博客标题5555555555 " },
  { title: "博客标题6666666" },
  { title: "博客标题" },
];

const message = useMessage();
const dialog = useDialog();
const cancelConfirm = () => {
  dialog.error({
    title: "警告",
    content: "确定删除吗？",
    positiveText: "确定",
    negativeText: "不确定",
    onPositiveClick: () => {
      message.success("确定");
    },
    onNegativeClick: () => {
      message.error("不确定");
    },
  });
};
</script>
<style scoped>
.container {
  position: relative;
  width: 90%;
  left: 10%;
}
.btn {
  position: relative;
  display: inline;
  left: 85%;
  margin-bottom: 2%;
}
.oneCollection {
  position: relative;
  margin-top: 1%;
}

.cancel {
  display: inline;
  position: absolute;
  left: 90%;
}
</style>
