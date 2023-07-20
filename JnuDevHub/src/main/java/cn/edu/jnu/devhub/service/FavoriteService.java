package cn.edu.jnu.devhub.service;

import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.vo.FavoriteVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;

public interface FavoriteService {

    @Tag(name = "findFavorite", description = "return List of Favorites and code 200 when search success,else return code 400")
    Result<FavoriteVO> findFavorite(FavoriteDTO favoriteDTO);

    @Tag(name = "addFavorite", description = "return code 200 when search success,else return code 400")
    Result<Object> addFavorite(FavoriteDTO favoriteDTO);

    @Tag(name = "deleteFavorite", description = "return code 200 when search success,else return code 400")
    Result<Object> deleteFavorite(FavoriteDTO favoriteDTO);

    @Tag(name = "countFavorites", description = "return list of counts of Favorites in each blog or return favorites of one chosen blog")
    Result<HotVO> countFavorites(FavoriteDTO favoriteDTO);
}
