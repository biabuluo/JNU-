<template>
  <div class="all">
    <n-card style="width: 50%" embedded :bordered="false">
      <n-h1>后续开发计划</n-h1>
      <n-h3>一、改用更好的技术栈</n-h3>
      <n-ul>
        <n-li>加Redis做数据库缓冲</n-li>
        <n-li>改用Spring Security做认证</n-li>
      </n-ul>
      <n-h3> 二、实现增加或完善Blog的功能 </n-h3>
      <n-ul>
        <n-li>增加对Blog的内容管理功能</n-li>
        <n-li>能关注其他Bloger，以便日后交流开发技术</n-li>
        <n-li>能查看自己的Blog被多少人查看，以便获取成就感</n-li>
      </n-ul>
      <n-h3>三、现使用的技术栈</n-h3>
      <n-h4>前端</n-h4>
      <n-ul>
        <n-li>Vite </n-li>
        <n-li>Vue3</n-li>
        <n-li>Vue-Router</n-li>
        <n-li>Vuex</n-li>
        <n-li>Swiper</n-li>
        <n-li>Naive-UI</n-li>
      </n-ul>
    </n-card>
    <n-card style="width: 50%; margin-top: 76px" embedded :bordered="false">
      <n-h4>后端</n-h4>
      <n-ul>
        <n-li>SpringBoot</n-li>
        <n-li>MyBatis</n-li>
        <n-li>Gradle</n-li>
      </n-ul>
      <n-h4>数据库</n-h4>
      <n-ul>
        <n-li>H2 Database (for test)</n-li>
        <n-li>MySQL</n-li>
      </n-ul>
      <div style="margin-top: 80px">
        <n-button type="error" @click="cancelConfirm">
          为开发项目的人员点赞
        </n-button>
      </div>
    </n-card>
  </div>
</template>

<script setup>
import { useMessage, useDialog } from "naive-ui";
const message = useMessage();
const dialog = useDialog();
const cancelConfirm = () => {
  dialog.success({
    title: "赞！",
    content: "我们终于完成了项目！",
    positiveText: "点赞",
    onPositiveClick: () => {
      message.success("感谢各位");
    },
  });
};
</script>

<style lang="scss" scoped>
.all {
  width: 100%;
  display: flex;
}
</style>
