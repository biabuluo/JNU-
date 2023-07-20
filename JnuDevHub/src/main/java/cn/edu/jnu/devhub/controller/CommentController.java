package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.controller.translate.Translate;
import cn.edu.jnu.devhub.model.dto.CommentDTO;
import cn.edu.jnu.devhub.model.vo.CommentVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CommentController", description = "Manage all requests about the comment interface")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private Translate translate;


    @Operation(
        summary = "Get list of comment",
        description = "Get a list of comment by searching for the criteria",
        parameters = {
            @Parameter(
                name = "commentDTO",
                description = "Search criteria { id | postId | authorId }",
                schema = @Schema(
                        implementation = CommentDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null array of comment info [ { id & postId & authorId & content & createdTime & updatedTime } ]"
            ),
            @ApiResponse(responseCode = "400",
                description = "Failure and get a null array of comment info"
            )
        }
    )
    @GetMapping("/list")
    public Result<CommentVO> listOfComment (CommentDTO commentDTO) {
        // 获取指定条件的评论列表
        Result<CommentVO> result = commentService.listOfComments(commentDTO);
        result.setData(translate.Comment(result.getData()));
        return result;
    }

    @Operation(
        summary = "Add comment by user and blog",
        description = "Add a comment by user and blog with comment info",
        parameters = {
            @Parameter(
                name = "commentDTO",
                description = "comment info and a unique identifier of user and blog { postId & authorId & content & createdTime & updatedTime }",
                schema = @Schema(
                    implementation = CommentDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new comment by user and blog"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new comment by user and blog "
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (CommentDTO commentDTO) {
        // 添加评论
        System.out.println(commentDTO);
        return commentService.addComment(commentDTO);
    }

    @Operation(
        summary = "Update comment info",
        description = "Update a comment by new info",
        parameters = {
            @Parameter(
                name = "commentDTO",
                description = "a unique identifier of comment and new info { id & { postId | authorId | content | updatedTime } }",
                schema = @Schema(
                    implementation = CommentDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Update success and return a not null json of new comment info after update"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Update failure and return a null json"
            )
        }
    )
    @PutMapping("")
    public Result<CommentVO> update (CommentDTO commentDTO) {
        // 更新评论
        return commentService.updateComment(commentDTO);
    }

    @Operation(
        summary = "Delete comment",
        description = "Delete a comment with a unique identifier",
        parameters = {
            @Parameter(
                name = "commentDTO",
                description = "Unique identifier of comment { id }",
                schema = @Schema(
                    implementation = CommentDTO.class
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
    public Result<Object> delete (CommentDTO commentDTO) {
        // 删除评论
        return commentService.deleteComment(commentDTO);
    }
}
