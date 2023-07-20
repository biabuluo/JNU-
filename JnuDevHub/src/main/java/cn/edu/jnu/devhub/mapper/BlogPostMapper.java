package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{11:17}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface BlogPostMapper {
    //查询
    List<BlogPostVO> findBlogPosts(BlogPostDTO blogPostDTO);
    //修改
    int updateBlogPost(BlogPostDTO blogPostDTO);
    //新增
    int addBlogPost(BlogPostDTO blogPostDTO);
    //删除
    int deleteBlogPost(BlogPostDTO blogPostDTO);

    List<BlogPostVO> findBlogPostByBlurSearch(BlogPostDTO blogPostDTO);
}
