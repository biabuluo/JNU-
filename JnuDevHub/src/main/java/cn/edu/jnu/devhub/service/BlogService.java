package cn.edu.jnu.devhub.service;


import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

public interface BlogService {
    @Tag(name = "findBlogPosts" ,description = "return List of BlogPostVO and code 200 when search success,else return code 400")
    Result<BlogPostVO> findBlogPosts(BlogPostDTO blogPostDTO);
    @Tag(name = "addBlog" ,description = "return code 200 when add success,else return code 400")
    Result<Object> addBlog(BlogPostDTO blogPostDTO);
    @Tag(name = "updateBlog" ,description = "return code 200 and updateData when add success,else return code 400")
    Result<BlogPostVO> updateBlog(BlogPostDTO blogPostDTO);
    @Tag(name = "updateBlog" ,description = "return code 200 and updateData when add success,else return code 400")
    Result<Object> deleteBlog(BlogPostDTO blogPostDTO);
    @Tag(name = "tagsOfBlog" ,description = "find all tags of one blog")
    Result<TagVO> tagsOfBlog(BlogPostDTO blogPostDTO);
    @Tag(name = "blogsOfTag" ,description = "find all blogs of one tag")
    Result<BlogPostVO> blogsOfTag(BlogPostDTO blogPostDTO);
    @Tag(name = "addPostTag" ,description = "return code 200 and updateData when add success,else return code 400")
    Result<Object> addPostTag(BlogPostDTO blogPostDTO);
    @Tag(name = "deletePostTag" ,description = "return code 200 and updateData when add success,else return code 400")
    Result<Object> deletePostTag(BlogPostDTO blogPostDTO);

    Result<BlogPostVO> findBlogPostByBlurSearch (BlogPostDTO blogPostDTO);

    Result<Object> insertTagListForOneBlog (BlogPostDTO blogPostDTO);
}
