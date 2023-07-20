package cn.edu.jnu.devhub.model.vo;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{10:17}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class UserRoleVO {
    private int id;
    private int userId;
    private int roleId;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
