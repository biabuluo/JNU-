package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.CommentDTO;
import cn.edu.jnu.devhub.model.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{15:26}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface CommentMapper {
    //查询特定评论
    List<CommentVO> findComments(CommentDTO commentDTO);
    //删除特定评论
    int deleteComment(CommentDTO commentDTO);
    //修改特定评论
    int updateComment(CommentDTO commentDTO);
    //添加特定评论
    int addComment(CommentDTO commentDTO);
}
