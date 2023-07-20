<template>
  <n-modal :show="isShow">
    <n-card
      style="width: 512px; height: fit-content; padding-bottom: 64px"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
      closable
      @close="closeLoginDialog"
    >
      <n-tabs
        class="card-tabs"
        default-value="sign-in"
        size="large"
        animated
        pane-wrapper-style="margin: 0 -4px"
        pane-style="padding-left: 4px; padding-right: 4px; box-sizing: border-box;"
      >
        <n-tab-pane name="sign-in" tab="登录">
          <n-form
            ref="signInFormRef"
            :model="signInFormValue"
            :rules="signInFormRules"
            :show-require-mark="false"
          >
            <n-form-item path="account" label="帐号">
              <n-input
                v-model:value="signInFormValue.account"
                placeholder="手机号/邮箱/用户名"
              />
            </n-form-item>
            <n-form-item path="password" label="密码">
              <n-input
                type="password"
                show-password-on="click"
                v-model:value="signInFormValue.password"
                placeholder="密码"
              />
            </n-form-item>
            <n-button type="primary" block strong @click="handleSignIn">
              登录
            </n-button>
          </n-form>
        </n-tab-pane>
        <n-tab-pane name="sign-up" tab="注册">
          <n-form
            ref="signUpFormRef"
            :model="signUpFormValue"
            :rules="signUpFormRules"
            :show-require-mark="false"
          >
            <n-form-item path="username" label="用户名">
              <n-input
                v-model:value="signUpFormValue.username"
                placeholder="用户名"
              />
            </n-form-item>
            <n-form-item path="phone" label="手机">
              <n-input
                v-model:value="signUpFormValue.phone"
                placeholder="手机"
                :maxlength="11"
                :allow-input="allowNumberInput"
              />
            </n-form-item>
            <n-form-item path="verificationCode" label="验证码">
              <n-input-group>
                <n-input
                  type="text"
                  :maxlength="6"
                  v-model:value="signUpFormValue.verificationCode"
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
            <n-form-item path="email" label="邮箱">
              <n-input
                v-model:value="signUpFormValue.email"
                placeholder="邮箱"
              />
            </n-form-item>
            <n-form-item path="password" label="密码">
              <n-input
                type="password"
                show-password-on="click"
                v-model:value="signUpFormValue.password"
                placeholder="密码"
              />
            </n-form-item>
            <n-form-item path="confirmPassword" label="确认密码">
              <n-input
                type="password"
                show-password-on="click"
                v-model:value="signUpFormValue.confirmPassword"
                placeholder="确认密码"
                :disabled="!signUpFormValue.password"
              />
            </n-form-item>
            <n-button type="primary" block strong @click="handleSignUp">
              注册
            </n-button>
          </n-form>
        </n-tab-pane>
      </n-tabs>
    </n-card>
  </n-modal>
</template>

<script setup>
import api from "../api";
import { useMessage } from "naive-ui";
const message = useMessage();

const store = useStore();
const isShow = computed(() => store.state.login.loginDialogVisible);

const signInFormRef = ref(null);
const signInFormValue = reactive({
  account: "",
  password: "",
});
const signInFormRules = {
  account: [
    {
      required: true,
      message: "请输入帐号",
      trigger: ["blur"],
    },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: ["blur"],
    },
  ],
};

const signUpFormRef = ref(null);
const signUpFormValue = reactive({
  username: "",
  phone: "",
  verificationCode: "",
  email: "",
  password: "",
  confirmPassword: "",
});
const signUpFormRules = {
  username: [
    {
      required: true,
      message: "请输入用户名",
      trigger: ["blur"],
    },
  ],
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
  password: [
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
      validator(rule, value) {
        if (value !== signUpFormValue.password) {
          return new Error("两次输入密码不一致");
        }
        return true;
      },
      trigger: ["blur"],
    },
  ],
};

const handleSignIn = () => {
  signUpFormRef.value?.validate((errors) => {
    if (errors) {
      return;
    }
  });
  console.log("handleSignIn");
  let userInfo = `?account=${signInFormValue.account}&password=${signInFormValue.password}`;
  console.log(userInfo);
  //这里出大问题！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  //大聪明yjh解决了！
  api.login(userInfo).then((res) => {
    if (res.data.code == 200) {
      //获取token
      // console.log(res.data.data[0]);
      // console.log(res.data.data[0].token);
      store.commit("login/setToken", res.data.data[0].token);
      store.commit("login/setUserInfo", res.data.data[0]);
      //登录成功！
      console.log(res);
      message.success("登录成功");
      clearForm();
      store.commit("login/hideLoginDialog");
      //窗口关闭
      //todo
    } else {
      //登录失败！
      console.log(res);
      message.warning("登录失败");
    }
  });
};

const handleSignUp = () => {
  signUpFormRef.value?.validate((errors) => {
    if (errors) {
      return;
    }
  });
  console.log("handleSignUp");
  var params = new URLSearchParams();
  params.append("username", signUpFormValue.username);
  params.append("phone", signUpFormValue.phone);
  params.append("email", signUpFormValue.email);
  params.append("password", signUpFormValue.password);

  // console.log(userInfo);
  api.register(params).then((res) => {
    if (res.data.code == 200) {
      //注册成功！
      console.log(res);
      message.success("注册成功");
      clearForm();
      store.commit("login/hideLoginDialog");
    } else {
      //注册失败！
      console.log(res);
      message.warning("注册失败");
    }
  });
};

const clearForm = () => {
  signInFormValue.account = "";
  signInFormValue.password = "";
  signUpFormValue.username = "";
  signUpFormValue.phone = "";
  signUpFormValue.verificationCode = "";
  signUpFormValue.email = "";
  signUpFormValue.password = "";
  signUpFormValue.confirmPassword = "";
};

const closeLoginDialog = () => {
  store.commit("login/hideLoginDialog");
  clearForm();
};

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

const allowNumberInput = (value) => !value || /^\d+$/.test(value);
</script>

<style lang="scss" scoped></style>
