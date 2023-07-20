<template>
  <n-modal :show="props.isShow">
    <n-card
      style="width: 648px"
      title="手机设置"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
      closable
      @close="handleChangePhoneClose"
    >
      <n-form
        ref="formRef"
        :label-width="80"
        :model="formValue"
        :rules="formRules"
      >
        <n-form-item label="输入新手机" path="phone">
          <n-input
            :maxlength="11"
            v-model:value="formValue.phone"
            placeholder="请输入新号码"
            :allow-input="allowNumberInput"
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
            @click="handleChangePhoneConfirm"
            type="primary"
            style="margin: 0 8px 0 8px"
            :focusable="false"
          >
            确认
          </n-button>
          <n-button
            @click="handleChangePhoneClose"
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
  phone: "",
  verificationCode: "",
});
const formRules = {
  phone: [
    {
      validator(rule, value) {
        if (value.length !== 11) {
          return new Error("请输入11位手机号");
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
};

const allowNumberInput = (value) => !value || /^\d+$/.test(value);

const clearForm = () => {
  formValue.phone = "";
};

const handleChangePhoneConfirm = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      emits("update:isShow", false);
      clearForm();
    } else {
      console.log(errors);
    }
  });
};

const handleChangePhoneClose = (e) => {
  emits("update:isShow", false);
  clearForm();
};

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
</script>

<style lang="scss" scoped></style>
