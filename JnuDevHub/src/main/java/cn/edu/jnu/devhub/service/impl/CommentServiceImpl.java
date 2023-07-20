package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.CommentMapper;
import cn.edu.jnu.devhub.model.dto.CommentDTO;
import cn.edu.jnu.devhub.model.vo.CommentVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据评论的信息查找符合条件的评论
     * @param commentDTO 里面可以有评论id、postId、authorId
     * @return 符合条件的评论列表
     */
    @Override
    public Result<CommentVO> listOfComments(CommentDTO commentDTO) {
        Result<CommentVO> result = new Result<>();

        List<CommentVO> resultList = commentMapper.findComments(commentDTO);
        if (resultList.size() != 0) {                       //查询有结果
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search comments success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search comments failure", null);
        }

        return result;
    }

    /**
     * 添加评论
     * @param commentDTO 要添加的信息
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addComment(CommentDTO commentDTO) {
        Result<Object> result = new Result<>();
        int flag = commentMapper.addComment(commentDTO);
        if (flag != 0) {     //添加成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "add Comment success", null);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "add Comment failure", null);
        }
        return result;
    }

    /**
     * 根据id删除评论
     * @param commentDTO 里面含有要删除的评论的id
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteComment(CommentDTO commentDTO) {
        Result<Object> result = new Result<>();
        int flag = commentMapper.deleteComment(commentDTO);

        if (flag == 1) {//删除了一个评论,成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete comment success", null);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete comment failure", null);
        }
        return result;
    }

    /**
     * 根据评论的id修改评论
     * @param commentDTO 里面有要修改的评论的id和其修改的内容
     * @return 修改成功的评论信息
     */
    @Override
    public Result<CommentVO> updateComment(CommentDTO commentDTO) {
        Result<CommentVO> result = new Result<>();
        int flag = commentMapper.updateComment(commentDTO);

        CommentVO commentVO = new CommentVO();
        commentVO.setId(commentDTO.getId());
        commentVO.setContent(commentDTO.getContent());
        commentVO.setAuthorId(commentDTO.getAuthorId());
        commentVO.setPostId(commentDTO.getPostId());
        commentVO.setCreatedTime(commentDTO.getCreatedTime());
        commentVO.setUpdatedTime(commentDTO.getUpdatedTime());

        List<CommentVO> resultList = new ArrayList<>();
        resultList.add(commentVO);
        if (flag == 1) {
            //更新成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "update comment success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "update comment failure", resultList);
        }
        return result;
    }
}
