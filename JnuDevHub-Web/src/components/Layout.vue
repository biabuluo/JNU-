<template>
  <div style="height: 100vh">
    <n-layout position="absolute" :native-scrollbar="false">
      <Header />
      <div style="top: 64px; height: calc(100vh - 64px)">
        <slot />
        <Login />
      </div>
    </n-layout>
  </div>
</template>

<script lang="scss" setup></script>

<style scoped></style>
