package cn.edu.jnu.devhub.model.vo;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{9:50}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class RoleVO {
    private int id;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", role_name='" + roleName + '\'' +
                '}';
    }
}
