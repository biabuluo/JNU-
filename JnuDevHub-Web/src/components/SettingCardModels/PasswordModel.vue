<template>
  <n-modal :show="props.isShow">
    <n-card
      style="width: 648px"
      title="更改密码"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
      closable
      @close="handleChangePasswordClose"
    >
      <n-form
        ref="formRef"
        :label-width="80"
        :model="formValue"
        :rules="formRules"
        size="large"
      >
        <n-form-item label="输入旧密码" path="oldPassword">
          <n-input
            clearable
            type="password"
            show-password-on="click"
            v-model:value="formValue.oldPassword"
            placeholder="请输入旧密码"
          />
        </n-form-item>
        <n-form-item label="输入新密码" path="newPassword">
          <n-input
            clearable
            type="password"
            show-password-on="click"
            v-model:value="formValue.newPassword"
            placeholder="请输入新密码"
          />
        </n-form-item>
        <n-form-item label="确认新密码" path="confirmPassword">
          <n-input
            clearable
            type="password"
            show-password-on="click"
            :disabled="!formValue.newPassword"
            v-model:value="formValue.confirmPassword"
            placeholder="确认新密码"
          />
        </n-form-item>
      </n-form>

      <template #action>
        <n-space justify="center">
          <n-button
            @click="handleChangePasswordConfirm"
            type="primary"
            style="margin: 0 8px 0 8px"
            :focusable="false"
          >
            确认
          </n-button>
          <n-button
            @click="handleChangePasswordClose"
            style="margin: 0 8px 0 8px"
            :focusable="false"
          >
            取消
          </n-button>
        </n-space>
      </template>
    </n-card>
  </n-modal>
</template>

<script setup>
import { useMessage } from "naive-ui";

const props = defineProps({
  isShow: {
    type: Boolean,
  },
});

const message = useMessage();

const emits = defineEmits(["update:isShow"]);
const formRef = ref(null);

const formValue = reactive({
  oldPassword: "",
  newPassword: "",
  confirmPassword: "",
});

const validatePasswordSame = (rule, value) => {
  return value === formValue.newPassword;
};

const formRules = {
  oldPassword: [
    {
      required: true,
      message: "请输入旧密码",
      trigger: ["blur"],
    },
  ],
  newPassword: [
    {
      validator(rule, value) {
        if (value.length < 6) {
          return new Error("密码长度不能小于6位");
        }
        return true;
      },
      trigger: ["blur"],
    },
  ],
  confirmPassword: [
    {
      required: true,
      message: "请确认新密码",
      trigger: ["blur"],
    },
    {
      validator: validatePasswordSame,
      message: "两次输入密码不一致",
      trigger: ["blur"],
    },
  ],
};

const clearForm = () => {
  formValue.oldPassword = "";
  formValue.newPassword = "";
  formValue.confirmPassword = "";
};

const handleChangePasswordConfirm = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      message.success("修改成功");
      emits("update:isShow", false);
      clearForm();
    } else {
      message.error("修改失败");
    }
  });
};

const handleChangePasswordClose = () => {
  emits("update:isShow", false);
  clearForm();
};
</script>

<style lang="scss" scoped></style>
