package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.FavoriteMapper;
import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.vo.FavoriteVO;
import cn.edu.jnu.devhub.model.vo.FavoriteVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;

    /**
     * 根据favoriteDTO来查找相应的结果
     *
     * @param favoriteDTO 里面可以有id或文章id或用户id
     * @return 查找到的收藏列表
     */
    @Override
    public Result<FavoriteVO> findFavorite(FavoriteDTO favoriteDTO) {
        Result<FavoriteVO> result = new Result<>();
        List<FavoriteVO> resultList = favoriteMapper.findFavorites(favoriteDTO);
        if (resultList.size() != 0) {
            //有结果
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search favorites success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search favorites failure", null);
        }
        return result;
    }

    /**
     * 添加收藏
     *
     * @param favoriteDTO 里面需要有文章的id和用户的id
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addFavorite(FavoriteDTO favoriteDTO) {
        Result<Object> result = new Result<>();
        int flag = favoriteMapper.addFavorite(favoriteDTO); //插入收藏记录
        if (flag == 1) {
            //添加成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "add favorite success", null);
        } else {
            //添加失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "add favorite failure", null);
        }
        return result;
    }

    /**
     * 删除收藏
     *
     * @param favoriteDTO 里面需要有文章的id和用户的id
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteFavorite(FavoriteDTO favoriteDTO) {
        Result<Object> result = new Result<>();
        int flag = favoriteMapper.deleteFavorite(favoriteDTO);  //删除收藏记录
        if (flag == 1) {
            //删除成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete favorite success", null);
        } else {
            //删除失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete favorite failure", null);
        }
        return result;
    }

    /**
     * 查找一个blog的收藏数或者查询所有blog的收藏数按降序排列
     *
     * @param favoriteDTO 设置里面的postId为0来获取所有收藏列表，或者设置为某一个blog的postId来获取那一个blog的收藏数
     * @return 以postId为key和count为value的HashMap列表
     */
    @Override
    public Result<HotVO> countFavorites(FavoriteDTO favoriteDTO) {
        Result<HotVO> result = new Result<>();

        List<HotVO> resultList;
        if (favoriteDTO.getPostId() == 0) {
            //返回降序的收藏列表
            resultList = favoriteMapper.countFavorites(null);
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "get favorites list success", resultList);
        } else {
            //返回特定postId的收藏数
            FavoriteDTO postIdGetter = new FavoriteDTO();
            postIdGetter.setPostId(favoriteDTO.getPostId());
            List<FavoriteVO> temp = favoriteMapper.findFavorites(postIdGetter); //查询此id是否存在
            if (temp.size() != 0) {
                //有结果
                resultList = favoriteMapper.countFavorites(favoriteDTO.getPostId());
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "get favorites of one blog success", resultList);
            } else {
                //不存在此postId
                result = ResultFactory.buildResult(ResultCode.FAILURE, "blog not exists", null);
            }

        }
        return result;
    }
}
