package cn.edu.jnu.devhub.controller;


import cn.edu.jnu.devhub.controller.translate.Translate;
import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.dto.LikeDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.HotVO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.service.BlogService;
import cn.edu.jnu.devhub.service.FavoriteService;
import cn.edu.jnu.devhub.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


@Tag(name = "BlogController", description = "Manage all requests about the blog interface")
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private Translate translate;

    @Operation(
        summary = "Get list of blog",
        description = "Get blogs based on the search condition",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Search condition { id | title | authorId | updatedTime }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null array of blog info [ { id & title & content & authorId & createdTime & updateTime } ]"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and return a null array of blog info"
            )
        }
    )
    @GetMapping("/list")
    public Result<BlogPostVO> listOfBlog (BlogPostDTO blogPostDTO) {
        // 返回所有符合条件的博客信息
        Result<BlogPostVO> blogPostVOResult = blogService.findBlogPosts(blogPostDTO);


        List<HotVO> likeCountList = likeService.countLikes(new LikeDTO()).getData();
        List<HotVO> favoCountList = favoriteService.countFavorites(new FavoriteDTO()).getData();

        for (HotVO like : likeCountList) {
            if (favoCountList.contains(like)) {
                HotVO hotVO = favoCountList.get(favoCountList.indexOf(like));
                hotVO.setCount(hotVO.getCount() + like.getCount());
            } else {
                favoCountList.add(like);
            }
        }

        favoCountList.sort(
                (a, b) -> b.getCount() - a.getCount()
        );

        List<BlogPostVO> data = new ArrayList<>();
        List<BlogPostVO> temp = blogPostVOResult.getData();

        for (int i=0; i<favoCountList.size(); i++) {
            for (int j=0; j<temp.size(); j++) {
                if (temp.get(j).getId() == favoCountList.get(i).getPostId()) {
                    data.add(temp.get(j));
                    break;
                }
            }
        }

        //blogPostVOResult.setData(translate.BlogPost(data));

        return blogPostVOResult;
    }

    @Operation(
        summary = "Add blog by user",
        description = "Add a blog by user with a unique identifier of user",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Blog info and a unique identifier of user { title & content & authorId & createdTime & updatedTime }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new blog by user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new blog by user "
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (BlogPostDTO blogPostDTO) {
        // 添加新的博客
        return blogService.addBlog(blogPostDTO);
    }

    @Operation(
        summary = "Update blog",
        description = "Update blog with new info of blog",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "New info of blog { id & { title | content | authorId | updatedTime } }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Update success and return a not null json of new blog info after update { id & title & content & authorId & createdTime & updateTime }"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Update failure and return a null json"
            )
        }
    )
    @PutMapping("")
    public Result<BlogPostVO> update (BlogPostDTO blogPostDTO) {
        // 更新博客内容
        return blogService.updateBlog(blogPostDTO);
    }

    @Operation(
        summary = "Delete blog",
        description = "Delete a blog with a unique identifier",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Unique identifier of blog { id }"
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
    public Result<Object> delete (BlogPostDTO blogPostDTO) {
        // 删除博客
        return blogService.deleteBlog(blogPostDTO);
    }

    @Operation(
        summary = "Get all tag of blog",
        description = "Get all tag of blog by a unique identifier of blog",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Unique identifier of blog { id }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null array of tag of blog [ { id & name } ]"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and return a null array of tag "
            )
        }
    )
    @GetMapping("/tag")
    public Result<TagVO> tagsOfBlog (BlogPostDTO blogPostDTO) {
        // 返回博客中的所有标签
        return blogService.tagsOfBlog(blogPostDTO);
    }

    @Operation(
        summary = "Add tag by blog",
        description = "Add a tag by blog with a unique identifier of blog",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Blog info and a unique identifier of user { id & tagId }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new tag by blog"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new tag by blog "
            )
        }
    )
    @PostMapping("/tag")
    public Result<Object> addTagForBlog (BlogPostDTO blogPostDTO) {
        return blogService.addPostTag(blogPostDTO);
    }

    @Operation(
        summary = "Delete tag by blog",
        description = "Delete a tag by blog with a unique identifier of blog",
        parameters = {
            @Parameter(
                name = "blogPostDTO",
                description = "Blog info and a unique identifier of user { id & tagId }",
                schema = @Schema(
                    implementation = BlogPostDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to delete a new tag by blog"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to delete a new tag by blog "
            )
        }
    )
    @DeleteMapping("/tag")
    public Result<Object> deleteTagForBlog (BlogPostDTO blogPostDTO){
        return blogService.deletePostTag(blogPostDTO);
    }

    @GetMapping("/blur")
    public Result<BlogPostVO> findByBlurSearch (BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> result = blogService.findBlogPostByBlurSearch(blogPostDTO);
        if (result.getCode() == 400) return result;
        result.setData(translate.BlogPost(result.getData()));
        return result;
    }

    @GetMapping("/info")
    public Result<BlogPostVO> findInfoByBlogId (BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> result = blogService.findBlogPosts(blogPostDTO);
        if (result.getCode() == ResultCode.FAILURE) {
            return result;
        }

        result.setData(translate.BlogPost(result.getData()));
        return result;
    }

    @PostMapping("/insertBlog")
    public Result<Object> insertTagListForOneBlog(BlogPostDTO blogPostDTO) {
        return blogService.insertTagListForOneBlog(blogPostDTO);
    }

    @GetMapping("/listnothot")
    public Result<BlogPostVO> listOfBlogNotHot (BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> blogPostVOResult = blogService.findBlogPosts(blogPostDTO);


        List<HotVO> likeCountList = likeService.countLikes(new LikeDTO()).getData();
        List<HotVO> favoCountList = favoriteService.countFavorites(new FavoriteDTO()).getData();

        for (HotVO like : likeCountList) {
            if (favoCountList.contains(like)) {
                HotVO hotVO = favoCountList.get(favoCountList.indexOf(like));
                hotVO.setCount(hotVO.getCount() + like.getCount());
            } else {
                favoCountList.add(like);
            }
        }

        favoCountList.sort(
                (a, b) -> b.getCount() - a.getCount()
        );

        List<BlogPostVO> data = new ArrayList<>();
        List<BlogPostVO> temp = blogPostVOResult.getData();

        for (int i=0; i<favoCountList.size(); i++) {
            for (int j=0; j<temp.size(); j++) {
                if (temp.get(j).getId() == favoCountList.get(i).getPostId()) {
                    data.add(temp.get(j));
                    break;
                }
            }
        }

        //blogPostVOResult.setData(translate.BlogPost(data));

        return blogPostVOResult;
    }
}
