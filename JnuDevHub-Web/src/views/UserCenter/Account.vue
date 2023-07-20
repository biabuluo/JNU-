<template>
  <n-space justify="center">
    <n-card hoverable class="card">
      <n-space vertical>
        <n-h1>帐号设置</n-h1>
        <n-divider class="divider" />

        <n-space justify="space-between" align="center">
          <n-space :inline="true" style="margin-right: 256px">
            <n-p>密码</n-p>
          </n-space>
          <n-space :inline="true" justify="end" align="center">
            <n-p>********</n-p>
            <n-button
              quaternary
              type="info"
              :focusable="false"
              @click="isPasswordModelShow = true"
            >
              修改密码
            </n-button>
          </n-space>
        </n-space>
        <n-divider class="divider" />

        <n-space justify="space-between" align="center">
          <n-space style="margin-right: 256px" :inline="true">
            <n-p>手机</n-p>
          </n-space>
          <n-space :inline="true" justify="end" align="center">
            <n-p>{{ accountSettings.phone }}</n-p>
            <n-button
              quaternary
              type="info"
              :focusable="false"
              @click="isPhoneModelShow = true"
            >
              修改手机
            </n-button>
          </n-space>
        </n-space>
        <n-divider class="divider" />

        <n-space justify="space-between" align="center">
          <n-space :inline="true" style="margin-right: 256px">
            <n-p>邮箱</n-p>
          </n-space>
          <n-space :inline="true" justify="end" align="center">
            <n-p>{{ accountSettings.email }}</n-p>
            <n-button
              quaternary
              type="info"
              :focusable="false"
              @click="isEmailModelShow = true"
            >
              修改邮箱
            </n-button>
          </n-space>
        </n-space>
        <n-divider class="divider" />

        <n-space justify="space-between" align="center">
          <n-space :inline="true" style="margin-right: 256px">
            <n-p>账号注销</n-p>
          </n-space>
          <n-space :inline="true" justify="end" align="center">
            <n-button
              quaternary
              type="error"
              :focusable="false"
              @click="isCancelAccountModelShow = true"
            >
              注销账号
            </n-button>
          </n-space>
        </n-space>
        <n-divider class="divider" />
      </n-space>
    </n-card>
  </n-space>
  <PasswordModal v-model:isShow="isPasswordModelShow" />
  <PhoneModal v-model:isShow="isPhoneModelShow" />
  <EmailModel v-model:isShow="isEmailModelShow" />
  <CancelAccountModel v-model:isShow="isCancelAccountModelShow" />
</template>

<script setup>
import { NButton } from "naive-ui";
import PasswordModal from "@/components/SettingCardModels/PasswordModel.vue";
import PhoneModal from "@/components/SettingCardModels/PhoneModel.vue";
import EmailModel from "@/components/SettingCardModels/EmailModel.vue";
import CancelAccountModel from "@/components/SettingCardModels/CancelAccountModel.vue";

// 通过变量控制弹窗显示隐藏
const isPasswordModelShow = ref(false);
const isPhoneModelShow = ref(false);
const isEmailModelShow = ref(false);
const isCancelAccountModelShow = ref(false);

const accountSettings = reactive({
  password: "****************",
  phone: "122****1444",
  email: "e@@@e@example.com",
});

const store = useStore();

onMounted(() => {
  const userInfo = store.getters["login/getUser"];
  accountSettings.phone =
    userInfo.phone.slice(0, 3) + "****" + userInfo.phone.slice(7);
  accountSettings.email =
    userInfo.email.slice(0, 1) + "****" + userInfo.email.slice(10);
});
</script>

<style lang="scss" scoped>
.divider {
  margin-top: 8px;
  margin-bottom: 8px;
}

.card {
  padding: 32px 128px 32px 128px;
  margin-top: 64px;
}
</style>
