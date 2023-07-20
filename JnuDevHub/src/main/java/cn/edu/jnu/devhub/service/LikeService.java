package cn.edu.jnu.devhub.service;


import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.model.vo.LikeVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface LikeService {

    @Tag(name = "findLikes", description = "return List of Likes and code 200 when search success,else return code 400")
    Result<LikeVO> findLikes(LikeDTO likeDTO);

    @Tag(name = "addLike", description = "return code 200 when search success,else return code 400")
    Result<Object> addLike(LikeDTO likeDTO);

    @Tag(name = "deleteLike", description = "return code 200 when search success,else return code 400")
    Result<Object> deleteLike(LikeDTO likeDTO);

    @Tag(name = "countLikes", description = "return list of counts of likes in each blog or return likes of one chosen blog")
    Result<HotVO> countLikes(LikeDTO likeDTO);
}
