package cn.edu.jnu.devhub.model.dto;

import java.sql.Timestamp;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{15:09}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class CommentDTO {
    private int id;
    private int postId;
    private int authorId;
    private String content;
    private Timestamp createdTime;
    private Timestamp updatedTime;

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", postId=" + postId +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
