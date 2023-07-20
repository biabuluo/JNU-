<template>
  <n-modal :show="props.isShow">
    <n-card
      style="width: 866px"
      title="账号注销"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
      closable
      @close="handleChangeCancelAccountClose"
    >
      <n-space justify="center">
        <n-card
          title="账号注销则视为您主动放弃以下资产和权益，且同意以下规则："
          :bordered="false"
        >
          1.账号注销后，您将无法登录、使用该账号，且不支持找回任何个人资料。<br />
          2.该账号将解除与其他产品的绑定或授权关系，且不支持找回。<br />
          3.将不再支持使用已注销账号的用户ID注册新的账号。<br />
          4.注销账号并不代表您注销前的账号行为和相关责任得到豁免或减轻。<br />
        </n-card>
      </n-space>
      <template #action>
        <n-space justify="center">
          <n-popconfirm>
            <template #icon>
              <n-icon color="red">
                <ErrorIcon />
              </n-icon>
            </template>
            <template #trigger>
              <n-button
                type="error"
                style="margin: 0 8px 0 8px"
                :focusable="false"
              >
                我已知晓，确认注销
              </n-button>
            </template>
            <template #action>
              <n-button
                type="error"
                :focusable="false"
                @click="handleChangeCancelAccountConfirm"
              >
                确认
              </n-button>
              <n-button
                :focusable="false"
                @click="handleChangeCancelAccountClose"
              >
                取消
              </n-button>
            </template>
            请注意，注销账号不可恢复，是否确认注销账号？
          </n-popconfirm>
        </n-space>
      </template>
    </n-card>
  </n-modal>
</template>

<script setup>
import { ErrorOutlineOutlined as ErrorIcon } from "@vicons/material";

let props = defineProps({
  isShow: {
    type: Boolean,
  },
});

const emits = defineEmits(["update:isShow"]);

const handleChangeCancelAccountConfirm = () => {
  emits("update:isShow", false);
};

const handleChangeCancelAccountClose = () => {
  emits("update:isShow", false);
};
</script>

<style lang="scss" scoped></style>
