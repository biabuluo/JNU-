package cn.edu.jnu.devhub.model.vo;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{16:30}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class ProfileVO {
    private int id;
    private int userId;
    private String avatar;
    private String nickname;
    private String bio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "ProfileVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
