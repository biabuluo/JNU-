<template>
  <n-config-provider :theme="theme">
    <n-dialog-provider>
      <n-notification-provider>
        <n-message-provider>
          <slot />
        </n-message-provider>
      </n-notification-provider>
    </n-dialog-provider>
  </n-config-provider>
</template>

<script setup>
const store = useStore();
const theme = computed(() => store.getters["theme/getTheme"]);
</script>

<style lang="scss" scoped></style>
