package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.vo.FavoriteVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{19:10}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface FavoriteMapper {
    //收藏信息查找
    List<FavoriteVO> findFavorites(FavoriteDTO favoriteDTO);
    //收藏信息删除
    int deleteFavorite(FavoriteDTO favoriteDTO);
    //收藏信息添加
    int addFavorite(FavoriteDTO favoriteDTO);

    //统计收藏
    List<HotVO> countFavorites(Integer id);
}
