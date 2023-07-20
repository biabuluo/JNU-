package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.controller.translate.Translate;
import cn.edu.jnu.devhub.model.dto.*;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.model.vo.UserVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.TrackedWebResource;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TagController", description = "Manage all requests about the tag interface")
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private Translate translate;

    @Operation(
        summary = "Get all blog of tag",
        description = "Get all blog of tag by a unique identifier of tag",
        parameters = {
            @Parameter(
                name = "tagDTO",
                description = "Unique identifier of tag { tagId }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null array of blog of tag [ { id & title & content & authorId & createdTime & updateTime } ]"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and return a null array of blog "
            )
        }
    )
    @GetMapping("/blog")
    public Result<BlogPostVO> blogsOfTag (BlogPostDTO blogPostDTO) {
        // 返回所有使用该标签的博客
        List<BlogPostVO> blogList = blogService.blogsOfTag(blogPostDTO).getData();

        blogList = translate.BlogPost(blogList);

        return ResultFactory.buildResult(ResultCode.SUCCESS, "search success", blogList);
    }

    @Operation(
        summary = "Get list of tag",
        description = "Get tags based on the search condition",
        parameters = {
            @Parameter(
                name = "tagDTO",
                description = "Search condition { tagId | name }",
                schema = @Schema(
                    implementation = TagDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null array of tag info { id & name  }"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and get a null array of tag info"
            )
        }
    )
    @GetMapping("/list")
    public Result<TagVO> listOfTag (TagDTO tagDTO) {
        // 返回所有符合条件的标签
        return tagService.findTags(tagDTO);
    }

    @Operation(
        summary = "Add tag for blog",
        description = "Add a new tag for blog",
        parameters = {
            @Parameter(
                name = "tagDTO",
                description = "Info of new tag { name }",
                schema = @Schema(
                    implementation = TagDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new tag for user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new tag by user"
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (TagDTO tagDTO) {
        // 添加标签
        return tagService.addTag(tagDTO);
    }

    @Operation(
        summary = "Delete tag",
        description = "Delete a tag with a unique identifier",
        parameters = {
            @Parameter(
                name = "tagDTO",
                description = "Unique identifier of tag { id }",
                schema = @Schema(
                    implementation = TagDTO.class
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
    public Result<Object> delete (TagDTO tagDTO) {
        // 删除标签
        return tagService.deleteTag(tagDTO);
    }

    @GetMapping("/findAllInfo")
    public Result<TagVO> findAllInfo (TagDTO tagDTO) {
        List<TagVO> tagVOList = tagService.findTags(tagDTO).getData();
        for (TagVO tagVO : tagVOList) {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setTagId(tagVO.getId());
            List<BlogPostVO> blogList = blogService.blogsOfTag(blogPostDTO).getData();

            blogList = translate.BlogPost(blogList);

            tagVO.setBlogPostVOList(blogList);

        }

        return ResultFactory.buildResult(ResultCode.SUCCESS, "find all info success", tagVOList);
    }
}
