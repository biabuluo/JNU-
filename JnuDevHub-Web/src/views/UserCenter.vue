<template>
  <n-layout has-sider position="absolute" style="top: 64px">
    <n-layout-sider
      bordered
      :native-scrollbar="false"
      :width="240"
      show-trigger
      collapse-mode="width"
      :collapsed="menuCollapsed"
      :collapsed-width="64"
      @collapse="menuCollapsed = true"
      @expand="menuCollapsed = false"
    >
      <n-menu
        :options="menuOptions"
        style="min-height: calc(100vh - 64px)"
        :collapsed="menuCollapsed"
        :collapsed-width="64"
      />
    </n-layout-sider>
    <n-layout :native-scrollbar="false">
      <router-view />
    </n-layout>
  </n-layout>
</template>

<script setup>
import { RouterLink } from "vue-router";
import { NIcon } from "naive-ui";
import { UserOutlined, SettingOutlined, StarOutlined } from "@vicons/antd";

const renderIcon = (icon) => {
  return () => h(NIcon, null, { default: () => h(icon) });
};

const menuCollapsed = ref(false);

const menuOptions = [
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "user-center-space",
          },
        },
        { default: () => "我的空间" }
      ),
    key: "user-center-space",
    icon: renderIcon(UserOutlined),
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "user-center-account",
          },
        },
        { default: () => "帐号设置" }
      ),
    key: "user-center-account",
    icon: renderIcon(SettingOutlined),
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "user-center-favorite",
          },
        },
        { default: () => "我的收藏" }
      ),
    key: "user-center-favorite",
    icon: renderIcon(StarOutlined),
  },
];
</script>

<style lang="scss" scoped></style>
