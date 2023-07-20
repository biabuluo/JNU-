//package cn.edu.jnu.devhub.model.entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//
///**
// * @author 小宇
// * @date {2023}-{07}-{13}:{8:38}
// * @preference: 类：大驼峰 方法：蛇形 变量：全小写
// * @description:
// */
//public class UserEntity implements UserDetails {
//    private int id;
//
//    private String username;
//    private String phone;
//    private String email;
//    private String password;
//
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
//}
