package cn.edu.jnu.devhub;

import cn.edu.jnu.devhub.mapper.FavoriteMapper;
import cn.edu.jnu.devhub.mapper.LikeMapper;
import cn.edu.jnu.devhub.mapper.TagMapper;
import cn.edu.jnu.devhub.model.dto.*;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.service.*;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JnuDevHubApplicationTests {
	@Autowired
	FavoriteMapper likeMapper;

	@Autowired
	TagService tagService;

	@Autowired
	BlogService blogService;

	@Autowired
	LikeService likeService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		List res = likeMapper.countFavorites(null);
		System.out.println(res);
	}

	@Test
	void testEncodeing() {
		String password = "password123";

		String encodePassword = BCrypt.hashpw(password,BCrypt.gensalt());
		System.out.println("encodePassword:"+encodePassword);

		boolean flag = BCrypt.checkpw(password,encodePassword);
		System.out.println("correct password flag:"+flag);
		flag = BCrypt.checkpw("12347",encodePassword);
		System.out.println("wrong password flag:"+flag);


	}

	@Test
	void testNewTagVO() {
		TagDTO tagDTO = new TagDTO();
		List<TagVO> tagVOList = tagService.findTags(tagDTO).getData();
		for (TagVO tagVO : tagVOList) {
			BlogPostDTO blogPostDTO = new BlogPostDTO();
			blogPostDTO.setTagId(tagVO.getId());
			List<BlogPostVO> blogList = blogService.blogsOfTag(blogPostDTO).getData();

			for (BlogPostVO blogPostVO : blogList) {
				int post_id = blogPostDTO.getId();
				LikeDTO likeDTO = new LikeDTO();
				FavoriteDTO favoriteDTO = new FavoriteDTO();
				UserDTO userDTO = new UserDTO();
				likeDTO.setPostId(post_id);
				favoriteDTO.setPostId(post_id);
				userDTO.setId(blogPostDTO.getAuthorId());
				int likeCount = likeService.countLikes(likeDTO).getData().get(0).getCount();
				int favoriteCount = favoriteService.countFavorites(favoriteDTO).getData().get(0).getCount();
				String authorName = userService.listOfUser(userDTO).getData().get(0).getUsername();
				blogPostVO.setLiked(likeCount);
				blogPostVO.setFavorite(favoriteCount);
				blogPostVO.setAuthorName(authorName);
			}

			tagVO.setBlogPostVOList(blogList);

		}
		
		System.out.println(tagVOList);
	}
}
