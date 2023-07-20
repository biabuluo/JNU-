package cn.edu.jnu.devhub.service;

import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface RoleService {
    @Tag(name = "addRole", description = "add user's role by userId and roleId")
    Result<Object> addRole(UserDTO userDTO);

    @Tag(name = "deleteRole", description = "delete user's role by userId and roleId")
    Result<Object> deleteRole(UserDTO userDTO);
}
