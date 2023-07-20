package cn.edu.jnu.devhub.model.vo;

/**
 * @author 小宇
 * @date {2023}-{07}-{12}:{20:32}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class UserVO {
    private int id;

    private String username;
    private String phone;
    private String email;
    private String password;
    private String token;


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


    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\''+
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
