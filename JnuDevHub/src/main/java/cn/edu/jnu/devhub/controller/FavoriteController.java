package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.FavoriteVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "FavoriteController", description = "Manage all requests about the favorite interface")
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Operation(
        summary = "Get list of favorite",
        description = "Get favorite based on the search condition",
        parameters = {
            @Parameter(
                name = "favoriteDTO",
                description = "Search condition { postId | userId }",
                schema = @Schema(
                    implementation = FavoriteDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null array of favorite info [ { id & postId & userId } ]"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and return a null array of favorite info"
            )
        }
    )
    @GetMapping("")
    public Result<FavoriteVO> get (FavoriteDTO favoriteDTO) {
        return favoriteService.findFavorite(favoriteDTO);
    }

    @Operation(
        summary = "Add favorite by user",
        description = "Add a favorite by userId and postId",
        parameters = {
            @Parameter(
                name = "favoriteDTO",
                description = "Favorite info { userId & postId }",
                schema = @Schema(
                    implementation = FavoriteDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new favorite by user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new favorite by user "
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (FavoriteDTO favoriteDTO) {
        return favoriteService.addFavorite(favoriteDTO);
    }

    @Operation(
        summary = "Delete favorite by user",
        description = "Delete a favorite with userId and postId",
        parameters = {
            @Parameter(
                name = "favoriteDTO",
                description = "Favorite info { postId & userId }",
                schema = @Schema(
                    implementation = FavoriteDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Delete success"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Delete failure"
            )
        }
    )
    @DeleteMapping("")
    private Result<Object> delete (FavoriteDTO favoriteDTO) {
        return favoriteService.deleteFavorite(favoriteDTO);
    }

    @Operation(
        summary = "Count favorite",
        description = "Count favorite by a postId or all",
        parameters = {
            @Parameter(
                name = "favoriteDTO",
                description = "Post info { postId }",
                schema = @Schema(
                    implementation = FavoriteDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null json array [ { count & postId } ]"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and return a null json array"
            )
        }
    )
    @GetMapping("/count")
    public Result<HotVO> countFavorites (FavoriteDTO favoriteDTO) {
        return favoriteService.countFavorites(favoriteDTO);
    }
}
