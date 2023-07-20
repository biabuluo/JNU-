package cn.edu.jnu.devhub.model.dto;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{19:07}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class FavoriteDTO {
    private int id;
    private int postId;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "id=" + id +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
