<template>
  <n-tabs
    type="line"
    animated
    trigger="hover"
    justify-content="space-around"
    ref="tabsInstRef"
    v-model:value="tabsValueRef"
  >
    <n-tab-pane
      v-for="list in recommendBlogLists"
      :key="list.tagId"
      :name="list.tagName"
      :tab="list.tagName"
    >
      <BlogCard
        v-for="blog in list.blogList"
        :key="blog.blogId"
        :blogId="blog.blogId"
        :title="blog.title"
        :content="blog.content"
        :liked="blog.liked"
        :favorite="blog.favorite"
        :authorId="blog.authorId"
        :authorName="blog.authorName"
      />
    </n-tab-pane>
  </n-tabs>
</template>

<script setup>
import { useMessage } from "naive-ui";
import api from "@/api/index";
import BlogCard from "@/components/BlogCard.vue";
import { nextTick } from "vue";

const message = useMessage();
const recommendBlogLists = ref([]);
const tabsInstRef = ref(null);
const tabsValueRef = ref("");

onMounted(() => {
  api.findAllInfo().then((res) => {
    // console.log(res);
    if (res.data.code == 200) {
      let data = res.data.data;
      //获取成功
      for (let eachTag in data) {
        let tmp = {
          tagId: 0,
          tagName: "",
          blogList: [],
        };
        // console.log(data[eachTag]);
        tmp.tagId = data[eachTag].id;
        tmp.tagName = data[eachTag].name;
        for (let eachPost in data[eachTag].blogPostVOList) {
          let tmpblog = {
            blogId: 0,
            title: "",
            content: "",
            liked: 0,
            favorite: 0,
            authorId: 0,
            authorName: "",
          };
          tmpblog.blogId = data[eachTag].blogPostVOList[eachPost].id;
          tmpblog.title = data[eachTag].blogPostVOList[eachPost].title;
          tmpblog.content = data[eachTag].blogPostVOList[eachPost].content;
          tmpblog.liked = data[eachTag].blogPostVOList[eachPost].liked;
          tmpblog.favorite = data[eachTag].blogPostVOList[eachPost].favorite;
          tmpblog.authorId = data[eachTag].blogPostVOList[eachPost].authorId;
          tmpblog.authorName =
            data[eachTag].blogPostVOList[eachPost].authorName;
          // console.log(tmpblog);
          tmp.blogList.push(tmpblog);
        }
        console.log(tmp);
        recommendBlogLists.value.push(tmp);
      }
      if (recommendBlogLists.value.length > 0) {
        tabsValueRef.value = recommendBlogLists.value[0].tagName;
        nextTick(() => {
          tabsInstRef.value?.syncBarPosition();
        });
      }
    } else {
      //获取失败
      message.warning("load error!");
    }
  });
});
</script>

<style lang="scss" scoped></style>
