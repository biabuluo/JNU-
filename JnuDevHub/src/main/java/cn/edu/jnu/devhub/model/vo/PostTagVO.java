package cn.edu.jnu.devhub.model.vo;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{14:43}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class PostTagVO {
    private int id;
    private int postId;
    private int tagId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "PostTagVO{" +
                "id=" + id +
                ", postId=" + postId +
                ", tagId=" + tagId +
                '}';
    }
}
