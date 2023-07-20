package cn.edu.jnu.devhub.service;

import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.model.vo.RoleVO;
import cn.edu.jnu.devhub.model.vo.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface UserService {
    @Tag(name = "login", description = "return code 200 when login success ,else return code 400")
    Result<UserVO> login(UserDTO userDTO);

    @Tag(name = "register", description = "return code 200 when register success ,else return code 400")
    Result<Object> register(UserDTO userDTO);

    @Tag(name = "update", description = "return code 200 when update success ,else return code 400")
    Result<UserVO> update(UserDTO userDTO);

    @Tag(name = "delete", description = "return code 200 when delete success ,else return code 400")
    Result<Object> deleteUser(UserDTO userDTO);

    @Tag(name = "GetListOfUser", description = "return code 200 when search success ,else return code 400,and return List of Users")
    Result<UserVO> listOfUser(UserDTO userDTO);

    @Tag(name = "roleOfUser", description = "return code 200 when search success ,else return code 400,and return List of Users")
    Result<RoleVO> roleOfUser(UserDTO userDTO);

    @Tag(name = "checkPassword", description = "return code 200 when password correct ,else return code 400")
    Result<Object> checkPassword(UserDTO userDTO);

    Result<BlogPostVO> getFavoriteOfUser(UserDTO userDTO);
    //----------------------------------------------------------------
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

