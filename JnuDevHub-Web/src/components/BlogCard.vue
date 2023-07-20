<template>
  <n-card embedded hoverable style="margin: 16px 0 16px 0">
    <n-space vertical>
      <n-button
        bordered
        text
        :focusable="false"
        @click="handleBlogClick(props.blogId)"
      >
        <div style="font-size: 22px; margin-bottom: 8px">
          {{ props.title }}
        </div>
      </n-button>
      <n-ellipsis :line-clamp="2" :tooltip="false">
        {{ props.content }}
      </n-ellipsis>
    </n-space>

    <template #footer>
      <n-space justify="space-between">
        <n-space justify="start">
          <n-button text :focusable="false">
            <template #icon>
              <n-icon>
                <LikeOutlined />
              </n-icon>
            </template>
            {{ props.liked }}
          </n-button>
          <n-button text :focusable="false" style="margin-left: 16px">
            <template #icon>
              <n-icon>
                <StarOutlined />
              </n-icon>
            </template>
            {{ props.favorite }}
          </n-button>
        </n-space>
        <n-button text :focusable="false">
          <template #icon>
            <n-icon>
              <PersonOutline />
            </n-icon>
          </template>
          {{ props.authorName }}
        </n-button>
      </n-space>
    </template>
  </n-card>
</template>

<script setup>
import { LikeOutlined, StarOutlined } from "@vicons/antd";
import { PersonOutline } from "@vicons/ionicons5";
const props = defineProps({
  blogId: {
    type: Number,
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  content: {
    type: String,
    required: true,
  },
  liked: {
    type: Number,
    required: true,
  },
  favorite: {
    type: Number,
    required: true,
  },
  authorId: {
    type: Number,
    required: true,
  },
  authorName: {
    type: String,
    required: true,
  },
});

const router = useRouter();
const handleBlogClick = (blogId) => {
  router.push(`/blog/${blogId}`);
};
</script>

<style lang="scss" scoped></style>
