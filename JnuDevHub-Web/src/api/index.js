import axios from "axios";
import path from "./path";
import { babelParse } from "@vue/compiler-sfc";

const api = {
  //获取首页所有tags & posts
  findAllInfo() {
    return axios.get(path.baseUrl + path.findAllInfo);
  },
  //注册接口
  register(userInfo) {
    return axios.post(path.baseUrl + path.register, userInfo);
  },
  //登录接口
  login(userInfo) {
    return axios.get(path.baseUrl + path.login + userInfo);
  },
  //根据uid查找blog信息
  findInfoByBlogId(authorId) {
    return axios.get(path.baseUrl + path.findInfoByBlogId + authorId);
  },
  //获取对应评论列表
  findCommentByBlogId(postId) {
    return axios.get(path.baseUrl + path.findCommentByBlogId, postId);
  },
  //添加评论
  addComment(commentInfo){
    return axios.post(path.baseUrl+path.addComment+commentInfo);
  },
  //查询点赞
  findIsLike(likeInfo) {
    return axios.get(path.baseUrl + path.findIsLike + likeInfo);
  },
  //查询收藏
  findIsFavorit(favoriteInfo) {
    return axios.get(path.baseUrl + path.findIsLike + favoriteInfo);
  },
  //添加博客！
  addPost(postInfo){
    return axios.post(path.baseUrl+path.addPost, postInfo);
  },
  //---------------------------------
  getPostsByTag(tagId) {
    return axios.get(path.baseUrl + path.getPostsByTag, tagId);
  },
  getAllTags() {
    return axios.get(path.baseUrl + path.getAllTags);
  },
  getAllBlogs() {
    return axios.get(path.baseUrl + path.getAllBlogs);
  },
};
export default api;
