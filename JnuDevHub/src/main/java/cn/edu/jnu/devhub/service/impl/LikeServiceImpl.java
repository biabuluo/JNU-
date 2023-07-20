package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.LikeMapper;
import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.model.vo.LikeVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeMapper likeMapper;

    /**
     * 根据likeDTO来查找相应的结果
     *
     * @param likeDTO 里面可以有id或文章id或用户id
     * @return 查找到的点赞列表
     */
    @Override
    public Result<LikeVO> findLikes(LikeDTO likeDTO) {
        Result<LikeVO> result = new Result<>();
        List<LikeVO> resultList = likeMapper.findLikes(likeDTO);
        if (resultList.size() != 0) {
            //有结果
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search likes success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search likes failure", null);
        }
        return result;
    }

    /**
     * 添加点赞
     *
     * @param likeDTO 里面需要有文章的id和用户的id
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addLike(LikeDTO likeDTO) {
        Result<Object> result = new Result<>();
        int flag = likeMapper.addLike(likeDTO); //插入点赞记录
        if (flag == 1) {
            //添加成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "add like success", null);
        } else {
            //添加失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "add like failure", null);
        }
        return result;
    }

    /**
     * 删除点赞
     *
     * @param likeDTO 里面需要有文章的id和用户的id
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteLike(LikeDTO likeDTO) {
        Result<Object> result = new Result<>();
        int flag = likeMapper.deleteLike(likeDTO);  //删除点赞记录
        if (flag == 1) {
            //删除成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete like success", null);
        } else {
            //删除失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete like failure", null);
        }
        return result;
    }

    /**
     * 查找一个blog的点赞数或者查询所有blog的点赞数按降序排列
     *
     * @param likeDTO 设置里面的postId为0来获取所有点赞列表，或者设置为某一个blog的postId来获取那一个blog的点赞数
     * @return 以postId为key和count为value的HashMap列表
     */
    @Override
    public Result<HotVO> countLikes(LikeDTO likeDTO) {
        Result<HotVO> result = new Result<>();

        List<HotVO> resultList;
        if (likeDTO.getPostId() == 0) {
            //返回降序的点赞列表
            resultList = likeMapper.countLiks(null);
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "get likes list success", resultList);
        } else {
            //返回特定postId的点赞数
            LikeDTO postIdGetter = new LikeDTO();
            postIdGetter.setPostId(likeDTO.getPostId());
            List<LikeVO> temp = likeMapper.findLikes(postIdGetter); //查询此id是否存在
            if (temp.size() != 0) {
                //有结果
                resultList = likeMapper.countLiks(likeDTO.getPostId());

                result = ResultFactory.buildResult(ResultCode.SUCCESS, "get likes of one blog success", resultList);
            } else {
                //不存在此postId
                result = ResultFactory.buildResult(ResultCode.FAILURE, "blog not exists", null);
            }

        }
        return result;
    }
}
