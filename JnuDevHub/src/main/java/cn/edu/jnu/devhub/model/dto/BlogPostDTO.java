package cn.edu.jnu.devhub.model.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{11:14}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class BlogPostDTO {
    private int id;
    private String title;
    private String content;
    private int authorId;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    private int tagId;

    private List<String> tagList;

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    private String sortBy;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }


}
