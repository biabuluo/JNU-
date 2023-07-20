package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.PostTagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{14:38}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface PostTagMapper {
    //查询文章标签
    List<PostTagVO> findPostTag(BlogPostDTO blogPostDTO);
    //添加文章标签
    int addPostTag(BlogPostDTO blogPostDTO);
    //删除文章标签
    int deletePostTag(BlogPostDTO blogPostDTO);
}
