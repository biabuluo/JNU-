package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.BlogPostMapper;
import cn.edu.jnu.devhub.mapper.PostTagMapper;
import cn.edu.jnu.devhub.mapper.TagMapper;
import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.dto.TagDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.model.vo.PostTagVO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogPostMapper blogPostMapper;
    @Autowired
    PostTagMapper postTagMapper;
    @Autowired
    TagMapper tagMapper;

    /**
     * 根据blogPostDTO的信息查询
     *
     * @param blogPostDTO 在此DTO填入筛选的信息
     * @return 结果的blogPostVO列表
     */
    @Override
    public Result<BlogPostVO> findBlogPosts(BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> result = new Result<>();

        List<BlogPostVO> resultList = blogPostMapper.findBlogPosts(blogPostDTO);

        if (resultList.size() != 0) {     //查询有结果
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search success", resultList);

        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search failure", null);
        }
        return result;

    }

    /**
     * 添加blog
     *
     * @param blogPostDTO 新的blog
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addBlog(BlogPostDTO blogPostDTO) {
        Result<Object> result = new Result<>();
        int flag = blogPostMapper.addBlogPost(blogPostDTO);
        if (flag != 0) {     //添加成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "add blog success", null);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "add blog failure", null);
        }
        return result;
    }

    /**
     * 修改blog
     *
     * @param blogPostDTO 要修改的信息
     * @return 修改后的信息
     */
    @Override
    public Result<BlogPostVO> updateBlog(BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> result = new Result<>();
        int flag = blogPostMapper.updateBlogPost(blogPostDTO);

        BlogPostVO blogPostVO = new BlogPostVO();           //为返回的BlogPostVO赋值
        blogPostVO.setId(blogPostDTO.getId());
        blogPostVO.setContent(blogPostDTO.getContent());
        blogPostVO.setAuthorId(blogPostDTO.getAuthorId());
        blogPostVO.setTitle(blogPostDTO.getTitle());
        blogPostVO.setUpdateTime(blogPostDTO.getUpdatedTime());
        blogPostVO.setCreatedTime(blogPostDTO.getCreatedTime());

        List<BlogPostVO> resultList = new ArrayList<>();
        resultList.add(blogPostVO);

        if (flag == 1) {
            //更新成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "update blog success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "update blog failure", resultList);
        }

        return result;
    }

    /**
     * 根据博客id删除博客
     *
     * @param blogPostDTO 里面有要删除的博客的id
     * @return 是否成功
     */
    @Override
    public Result<Object> deleteBlog(BlogPostDTO blogPostDTO) {
        Result<Object> result = new Result<>();
        int flag = blogPostMapper.deleteBlogPost(blogPostDTO);

        if (flag == 1) {//删除了一个博客,成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete blog success", null);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete blog failure", null);
        }
        return result;
    }

    /**
     * 返回输入的博客的所有tag
     *
     * @param blogPostDTO 要查找的blog
     * @return tag列表
     */
    @Override
    public Result<TagVO> tagsOfBlog(BlogPostDTO blogPostDTO) {
        Result<TagVO> tagVOResult = new Result<>();
        blogPostDTO.setTagId(0);            //设置tagId为0让findPostTag通过blog的id来查找
        List<PostTagVO> postTagVOList = postTagMapper.findPostTag(blogPostDTO);     //根据blogPostDTO中的blogId找到所有符合此id的tagId

        if (postTagVOList.size() != 0) {                             //有结果
            List<TagVO> tagVOList = new ArrayList<>();              //存放找到的所有tag
            for (PostTagVO postTagVO : postTagVOList) {             //根据tagId在tag表中找到其对应的tag的完整信息
                int tagId = postTagVO.getTagId();
                TagDTO tagDTO = new TagDTO();
                tagDTO.setId(tagId);

                List<TagVO> singleTagVO = tagMapper.findTag(tagDTO);//根据tagDTO的id从tag表查找对应的完整的tag信息,返回的是列表
                TagVO tagVO = singleTagVO.get(0);
                tagVOList.add(tagVO);                               //把上面查找的结果放到tagVOList里面
            }
            tagVOResult = ResultFactory.buildResult(ResultCode.SUCCESS, "search tags of blog success", tagVOList);
        } else {
            tagVOResult = ResultFactory.buildResult(ResultCode.FAILURE, "search tags of blog failure", null);
        }
        return tagVOResult;
    }

    /**
     * 根据某一个tag找到其对应的所有blog
     *
     * @param blogPostDTO 要查找的tagId在这里赋值
     * @return 返回BlogPostVO列表
     */
    @Override
    public Result<BlogPostVO> blogsOfTag(BlogPostDTO blogPostDTO) {
        Result<BlogPostVO> blogPostVOResult = new Result<>();
        blogPostDTO.setId(0);        //设置Id为0让findPostTag通过tag的id来查找
        List<PostTagVO> postTagVOList = postTagMapper.findPostTag(blogPostDTO);     //根据blogPostDTO中的tagId找到所有符合此id的PostId

        if (postTagVOList.size() != 0) {                                     //有结果
            List<BlogPostVO> blogPostVOList = new ArrayList<>();    //存放找到的结果
            for (PostTagVO postTagVO : postTagVOList) {             //根据id在blogPost表中找到其对应的blog的完整信息
                BlogPostDTO blogPostDTOTemp = new BlogPostDTO();
                blogPostDTOTemp.setId(postTagVO.getPostId());       //把当前遍历的blog的id放到查询的输入id里面

                List<BlogPostVO> singleBlogPostVO = blogPostMapper.findBlogPosts(blogPostDTOTemp);  //根据blog的id找到其详细信息
                blogPostVOList.add(singleBlogPostVO.get(0));        //把上面查找的结果放到blogPostVOList里面
            }
            blogPostVOResult = ResultFactory.buildResult(ResultCode.SUCCESS, "search blogs of tag success", blogPostVOList);
        } else {
            blogPostVOResult = ResultFactory.buildResult(ResultCode.FAILURE, "search blogs of tag failure", null);
        }
        return blogPostVOResult;
    }

    /**
     * 添加blog的tag
     *
     * @param blogPostDTO 里面有blog的id以及对应要添加的tagId
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addPostTag(BlogPostDTO blogPostDTO) {
        Result<Object> result = new Result<>();
        List<PostTagVO> resultList = postTagMapper.findPostTag(blogPostDTO);    //查询文章对应的tag是否已存在
        if (resultList.size() == 0) {
            //tag不存在
            //添加PostTag
            int flag = postTagMapper.addPostTag(blogPostDTO);
            if (flag == 1) {
                //添加成功
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "add tag to the post success", null);
            } else {
                //添加失败
                result = ResultFactory.buildResult(ResultCode.FAILURE, "add tag to the post failure", null);
            }
        } else {
            //tag已存在
            result = ResultFactory.buildResult(ResultCode.FAILURE, "tag already exists", null);
        }

        return result;
    }

    /**
     * 删除文章对应的tag
     *
     * @param blogPostDTO 里面有blog的id以及对应要删除的tagId
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deletePostTag(BlogPostDTO blogPostDTO) {
        Result<Object> result = new Result<>();
        List<PostTagVO> resultList = postTagMapper.findPostTag(blogPostDTO);    //查询文章对应的tag是否已存在
        if (resultList.size() != 0) {
            //tag存在
            //删除PostTag
            int flag = postTagMapper.deletePostTag(blogPostDTO);
            if (flag == 1) {
                //删除成功
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete tag to the post success", null);
            } else {
                //删除失败
                result = ResultFactory.buildResult(ResultCode.FAILURE, "delete tag to the post failure", null);
            }
        } else {
            //tag不存在
            result = ResultFactory.buildResult(ResultCode.FAILURE, "tag not exists", null);
        }

        return result;
    }

    @Override
    public Result<BlogPostVO> findBlogPostByBlurSearch(BlogPostDTO blogPostDTO) {
        blogPostDTO.setContent("%" + blogPostDTO.getContent() + "%");
        List<BlogPostVO> blogPostVOList = blogPostMapper.findBlogPostByBlurSearch(blogPostDTO);
        if (blogPostVOList.size() != 0) {
            return ResultFactory.buildResult(ResultCode.SUCCESS, "search success", blogPostVOList);
        } else {
            return ResultFactory.buildResult(ResultCode.FAILURE, "search failure", null);
        }
    }

    /**
     * 遍历list中的tagname，如果有则不动，没有就插入到表中
     * @param blogPostDTO 传入tag的name的list
     * @return
     */
    @Override
    public Result<Object> insertTagListForOneBlog(BlogPostDTO blogPostDTO) {
        BlogPostDTO init = blogPostDTO;

        for (String tagName : blogPostDTO.getTagList()) {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setName(tagName);
            int tagId;
            List<TagVO> temp = tagMapper.findTag(tagDTO);
            if (temp.size() == 0){
                tagMapper.addTag(tagDTO);
                tagId = tagMapper.findTag(tagDTO).get(0).getId();
            } else {
                tagId = temp.get(0).getId();
            }
            BlogPostDTO blogPostDTO1 = new BlogPostDTO();
            blogPostMapper.addBlogPost(init);
            BlogPostVO blogPostVO = blogPostMapper.findBlogPosts(blogPostDTO).get(0);
            blogPostDTO1.setTagId(tagId);
            blogPostDTO1.setId(blogPostVO.getId());
            postTagMapper.addPostTag(blogPostDTO1);
        }


        return ResultFactory.buildResult(ResultCode.SUCCESS, "insert success", null);
    }


}
