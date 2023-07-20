<template>
  <n-card class="card" embedded>
    <n-space vertical>
      <n-h1 style="margin-bottom: 0">热门</n-h1>
      <n-divider class="divider" />
      <n-space v-for="blog in hotBlogList" :key="blog.id" vertical>
        <n-button text :focusable="false" style="font-size: 20px">
          {{ blog.title }}
        </n-button>
        <n-ellipsis :line="3" style="margin-top: 16px">
          {{ blog.content }}
        </n-ellipsis>
        <n-divider class="divider" />
      </n-space>
    </n-space>
  </n-card>
</template>

<script setup>
const hotBlogList = [
  {
    id: 1,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 2,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 3,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 4,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 5,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 6,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 7,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 8,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 9,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 10,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 11,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 12,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 13,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 14,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 15,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 16,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 17,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 18,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 19,
    title: "Hello World",
    content: "Hello World",
  },
  {
    id: 20,
    title: "Hello World",
    content: "Hello World",
  },
];
</script>

<style lang="scss" scoped>
.card {
  width: 512px;
  height: 100%;
}

.divider {
  margin: 8px 0 8px 0;
}
</style>
