package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.model.vo.LikeVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "LikeController", description = "Manage all requests about the like interface")
@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Operation(
        summary = "Get list of like",
        description = "Get like based on the search condition",
        parameters = {
            @Parameter(
                name = "likeDTO",
                description = "Search condition { PostId | UserId }",
                schema = @Schema(
                    implementation = LikeVO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null array of like info { id & postId & userId }"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and get a null array of like info"
            )
        }
    )
    @GetMapping("")
    public Result<LikeVO> get (LikeDTO likeDTO) {
        return likeService.findLikes(likeDTO);
    }

    @Operation(
        summary = "Add like by user",
        description = "Add a like by userId and postId",
        parameters = {
            @Parameter(
                name = "likeDTO",
                description = "Like info { userId & postId }",
                schema = @Schema(
                    implementation = LikeDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new like by user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new like by user "
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (LikeDTO likeDTO) {
        return likeService.addLike(likeDTO);
    }

    @Operation(
        summary = "Delete like by user",
        description = "Delete a like with userId and postId",
        parameters = {
            @Parameter(
                name = "likeDTO",
                description = "Like info { userId & postId }",
                schema = @Schema(
                    implementation = LikeDTO.class
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
    public Result<Object> delete (LikeDTO likeDTO) {
        return likeService.deleteLike(likeDTO);
    }

    @Operation(
        summary = "Count like",
        description = "Count like by a postId or all",
        parameters = {
            @Parameter(
                name = "likeDTO",
                description = "Post info { postId }",
                schema = @Schema(
                    implementation = LikeDTO.class
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
    public Result<HotVO> countLikes (LikeDTO likeDTO) {
        return likeService.countLikes(likeDTO);
    }
}
