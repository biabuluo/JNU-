<template>
  <n-card class="card" hoverable>
    <n-space vertical>
      <n-h1>发布文章</n-h1>
      <n-form ref="formRef" :model="formValue" :rules="formRules">
        <n-form-item path="title" label="标题">
          <n-input
            v-model:value="formValue.title"
            placeholder="输入标题"
            @keydown.enter.prevent
          />
        </n-form-item>
        <n-form-item path="tags" label="标签">
          <n-dynamic-tags v-model:value="formValue.tags" />
        </n-form-item>
        <n-form-item path="content" label="内容">
          <mavon-editor class="editor" v-model="formValue.content" />
        </n-form-item>
      </n-form>
      <n-space justify="end">
        <n-button type="primary" @click="handleSubmit">提交</n-button>
      </n-space>
    </n-space>
  </n-card>
</template>

<script setup>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import api from "../api"
import { useMessage } from "naive-ui";
const message = useMessage();
const store = useStore();





const formRef = ref(null);
const formValue = reactive({
  title: "",
  tags: [],
  content: "",
});
const formRules = {
  title: [{ required: true, message: "请输入标题", trigger: ["blur"] }],
  tags: [
    {
      validator(rule, value) {
        if (value.length > 3) {
          return new Error("不得超过3个标签");
        }
        return true;
      },
      trigger: ["change"],
    },
  ],
  content: [{ required: true, message: "请输入内容", trigger: ["blur"] }],
};

const handleSubmit = (e) => {
  e.preventDefault();
  formRef.value?.validate((errors) => {
    if (!errors) {
      console.log(formValue);
      var userInfo = JSON.parse(JSON.stringify(store.getters["login/getUser"]));
  var userId = userInfo.id;
  var params = new URLSearchParams();
  params.append("title", formValue.title);
  params.append("tagList", formValue.tags);
  params.append("content", formValue.content);
  params.append("authorId", userId);
  api.addPost(params).then((res)=>{
    if(res.data.code == 200){
      //提交成功！
      message.success("提交成功");
    }else{
      //提交失败！
      message.warning("提交失败");
    }
  })
    } else {
      console.log(errors);
    }
  });
};
</script>

<style lang="scss" scoped>
.card {
  margin: 64px;
  width: auto;
}

.editor {
  width: 100%;
  min-height: 512px;
}
</style>
