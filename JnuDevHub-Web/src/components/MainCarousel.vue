<template>
  <n-carousel
    direction="vertical"
    dot-placement="right"
    show-arrow
    style="width: 100%; height: 700px"
    autoplay
  >
    <img class="carousel-img" src="@/assets/diao1.jpg" />
    <img class="carousel-img" src="@/assets/diao2.jpg" />
  </n-carousel>
</template>

<script setup></script>

<style lang="scss" scoped>
.carousel-img {
  width: 100%;
  height: 700px;
  object-fit: cover;
}
</style>
