package cn.edu.jnu.devhub.model.dto;

/**
 * @author 小宇
 * @date {2023}-{07}-{12}:{23:00}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class UserDTO {
    private int id;

    private String username;
    private String phone;
    private String email;
    private String password;

    private int roleId;

    private String account;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", account='" + account + '\'' +
                '}';
    }
}
