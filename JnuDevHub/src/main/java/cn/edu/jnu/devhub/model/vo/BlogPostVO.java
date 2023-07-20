package cn.edu.jnu.devhub.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{11:07}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class BlogPostVO {
    private int id;
    private String title;
    private String content;
    private int authorId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;
    private long liked;
    private long favorite;
    private String authorName;

    private List<TagVO> tags;

    @Override
    public String toString() {
        return "BlogPostVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", liked=" + liked +
                ", favorite=" + favorite +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }



    public long getLiked() {
        return liked;
    }

    public void setLiked(long liked) {
        this.liked = liked;
    }

    public long getFavorite() {
        return favorite;
    }

    public void setFavorite(long favorite) {
        this.favorite = favorite;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public List<TagVO> getTags() {
        return tags;
    }
}
