<template>
  <n-modal :show="props.isShow">
    <n-card
      style="width: 648px"
      title="邮箱设置"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
      closable
      @close="handleChangeEmailClose"
    >
      <n-form
        ref="formRef"
        :label-width="80"
        :model="formValue"
        :rules="formRules"
      >
        <n-form-item label="输入邮箱进行验证" path="email">
          <n-input
            :maxlength="50"
            v-model:value="formValue.email"
            placeholder="输入邮箱"
          />
        </n-form-item>
        <n-form-item label="填写验证码" path="verificationCode">
          <n-input-group>
            <n-input
              type="text"
              :maxlength="6"
              v-model:value="formValue.verificationCode"
              placeholder="输入六位验证码"
              :allow-input="allowNumberInput"
            />
            <n-button
              :focusable="false"
              type="primary"
              :disabled="verificationCodeButtonDisabled"
              @click="handleVerificationCodeButtonClick"
            >
              {{ verificationCodeButtonText }}
            </n-button>
          </n-input-group>
        </n-form-item>
      </n-form>

      <template #action>
        <n-space justify="center">
          <n-button
            @click="handleChangeEmailConfirm"
            type="primary"
            style="margin: 0 8px 0 8px"
            :focusable="false"
          >
            确认
          </n-button>
          <n-button
            @click="handleChangeEmailClose"
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
import { ref } from "vue";
let props = defineProps({
  isShow: {
    type: Boolean,
  },
});

const emits = defineEmits(["update:isShow"]);

const formRef = ref(null);
const formValue = reactive({
  email: "",
  verificationCode: "",
});
const formRules = reactive({
  email: [
    {
      validator(rule, value) {
        if (
          !value ||
          !/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)
        ) {
          return new Error("请输入正确的邮箱");
        }
        return true;
      },
      trigger: ["blur"],
    },
  ],
  verificationCode: [
    {
      validator(rule, value) {
        if (value.length !== 6) {
          return new Error("请输入6位验证码");
        }
        return true;
      },
      trigger: ["blur"],
    },
  ],
});

const allowNumberInput = (value) => !value || /^\d+$/.test(value);

// Verification Code Button
const verificationCodeButtonText = computed(() => {
  if (verificationCodeTimer.value === 0) {
    return "获取验证码";
  } else {
    return `${verificationCodeTimer.value}秒后重发`;
  }
});
const verificationCodeButtonDisabled = ref(false);
const verificationCodeInterval = ref(null);
const verificationCodeTimer = ref(0);
const handleVerificationCodeButtonClick = () => {
  verificationCodeTimer.value = 60;
  verificationCodeButtonDisabled.value = true;
  verificationCodeInterval.value = setInterval(() => {
    verificationCodeTimer.value--;
    if (verificationCodeTimer.value === 0) {
      clearInterval(verificationCodeInterval.value);
      verificationCodeButtonDisabled.value = false;
    }
  }, 1000);
};

const clearForm = () => {
  formValue.email = "";
};

const handleChangeEmailConfirm = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      emits("update:isShow", false);
      clearForm();
    } else {
      console.log(errors);
    }
  });
};

const handleChangeEmailClose = () => {
  emits("update:isShow", false);
  clearForm();
};
</script>

<style lang="scss" scoped></style>
