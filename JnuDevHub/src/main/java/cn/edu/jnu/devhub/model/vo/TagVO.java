package cn.edu.jnu.devhub.model.vo;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{13:57}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class TagVO {
    private int id;

    @Override
    public String toString() {
        return "TagVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogPostVOList=" + blogPostVOList +
                '}';
    }

    private String name;

    private List<BlogPostVO> blogPostVOList;

    public List<BlogPostVO> getBlogPostVOList() {
        return blogPostVOList;
    }

    public void setBlogPostVOList(List<BlogPostVO> blogPostVOList) {
        this.blogPostVOList = blogPostVOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
