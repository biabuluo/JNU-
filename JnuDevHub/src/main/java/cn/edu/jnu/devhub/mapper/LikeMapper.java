package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.model.vo.LikeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{18:56}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface LikeMapper {
    //查询点赞信息
    List<LikeVO> findLikes(LikeDTO likeDTO);
    //添加点赞
    int addLike(LikeDTO likeDTO);
    //删除点赞
    int deleteLike(LikeDTO likeDTO);
    //统计点赞排行
    List<HotVO> countLiks(Integer id);
}
