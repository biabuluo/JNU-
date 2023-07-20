package cn.edu.jnu.devhub.controller.translate;

import cn.edu.jnu.devhub.model.dto.*;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.CommentVO;
import cn.edu.jnu.devhub.model.vo.ProfileVO;
import cn.edu.jnu.devhub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.LongStream;

@RestController
public class Translate {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private CommentService commentService;

    public List<BlogPostVO> BlogPost (List<BlogPostVO> blogList) {
        for (BlogPostVO blogPostVO : blogList) {
            int post_id = blogPostVO.getId();
            // 获取 postId
            LikeDTO likeDTO = new LikeDTO();
            FavoriteDTO favoriteDTO = new FavoriteDTO();
            UserDTO userDTO = new UserDTO();
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            likeDTO.setPostId(post_id);
            favoriteDTO.setPostId(post_id);
            userDTO.setId(blogPostVO.getAuthorId());
            blogPostDTO.setId(post_id);
            int likeCount = 0;
            int favoriteCount = 0;
            if (likeService.countLikes(likeDTO).getData() != null) {
                likeCount = likeService.countLikes(likeDTO).getData().get(0).getCount();
            }
            if (favoriteService.countFavorites(favoriteDTO).getData() != null) {
                favoriteCount = favoriteService.countFavorites(favoriteDTO).getData().get(0).getCount();
            }
            String authorName = userService.listOfUser(userDTO).getData().get(0).getUsername();
            blogPostVO.setLiked(likeCount);
            blogPostVO.setFavorite(favoriteCount);
            blogPostVO.setAuthorName(authorName);
            blogPostVO.setTags(blogService.tagsOfBlog(blogPostDTO).getData());
        }
        return blogList;
    }

    public List<ProfileVO> ProFile (List<ProfileVO> profileList) {
        return profileList;
    }

    public List<CommentVO> Comment (List<CommentVO> commentList) {
        for (CommentVO commentVO : commentList) {
            UserDTO userDTO = new UserDTO();
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            userDTO.setId(commentVO.getAuthorId());
            String authorName = userService.listOfUser(userDTO).getData().get(0).getUsername();
            commentVO.setAuthorName(authorName);
        }
        return commentList;
    }

}
