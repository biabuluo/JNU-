package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.controller.translate.Translate;
import cn.edu.jnu.devhub.model.vo.BlogPostVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.model.vo.RoleVO;
import cn.edu.jnu.devhub.model.vo.UserVO;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.RoleService;
import cn.edu.jnu.devhub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController", description = "Manage all requests about the user interface")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Translate translate;
    private final int defaultRoleId  = 2;

    @Operation(
        summary = "Login for user",
        description = "Login with username and password",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "User info",
                schema = @Schema(
                    implementation = UserDTO.class,
                    description = "{ username & password }"
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Login success"),
            @ApiResponse(
                responseCode = "400",
                description = "Login failure")
        }
    )
    @GetMapping("/login")
    public Result<UserVO> login (UserDTO userDTO) {
        // 用户登陆
        Result<UserVO> result = null;

        String account = userDTO.getAccount();
        String password = userDTO.getPassword();
//        System.out.println(account);
//        System.out.println(password);
        userDTO.setUsername(account);
        result = userService.login(userDTO);        //此方法会清空userDTO的password
        if (result.getCode() == ResultCode.SUCCESS) {
            return result;
        } else {
            userDTO.setUsername(null);
            userDTO.setEmail(account);
            userDTO.setPassword(password);
            result = userService.login(userDTO);
            if (result.getCode() == ResultCode.SUCCESS) {
                return result;
            } else {
                userDTO.setEmail(null);
                userDTO.setPhone(account);
                userDTO.setPassword(password);
                result = userService.login(userDTO);
            }
        }
        return result;
    }

    @Operation(
        summary = "Register for user",
        description = "Register with info of new user",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Info of new user ",
                schema = @Schema(
                    implementation = UserDTO.class,
                    description = "{ username & password & phone & email }"
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Register success"),
            @ApiResponse(
                responseCode = "400",
                description = "Register failure, maybe user exists")
        }
    )
    @PostMapping("/register")
    public Result<Object> register (UserDTO userDTO) {
        // 注册新用户
        Result<Object> result = userService.register(userDTO);
        if (result.getCode() == 200) {
            userDTO.setPassword(null);
            UserVO userVO = userService.listOfUser(userDTO).getData().get(0);
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setId(userVO.getId());
            userDTO1.setRoleId(defaultRoleId);
            roleService.addRole(userDTO1);
        }
        return result;
    }

    @Operation(
        summary = "Get list of user",
        description = "Get a list of users by searching for the criteria",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Search criteria { id | username | password | phone | email }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null array of user info [ { id & username & password & phone & email } ]"
            ),
            @ApiResponse(responseCode = "400",
                description = "Failure and get a null array of user info"
            )
        }
    )
    @GetMapping("/list")
    public Result<UserVO> listOfUser (UserDTO userDTO) {
        // 获取所有符合条件的用户数据
        return userService.listOfUser(userDTO);
    }

    @Operation(
        summary = "Update user info",
        description = "Update info of user with new info of user",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "New info of user { id and { username | password | phone | email } }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Update success and return a not null json array of new user info after update { id & username & password & phone & email }"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Update failure"
            )
        }
    )
    @PutMapping("")
    public Result<UserVO> update (UserDTO userDTO) {
        // 修改用户信息, 并返回新的用户数据
        return userService.update(userDTO);
    }

    @Operation(
        summary = "Delete user info",
        description = "Delete a user with a unique identifier",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Unique identifier of user { id }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Delete success"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Delete failure"
            )
        }
    )
    @DeleteMapping("")
    public Result<Object> delete (UserDTO userDTO) {
        // 删除用户
        return userService.deleteUser(userDTO);
    }


    @Operation(
        summary = "Get all role of user",
        description = "Get all role of user by a unique identifier of user",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Unique identifier of user { id }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null array of role of user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure and get a null array of role "
            )
        }
    )
    @GetMapping("/role")
    public Result<RoleVO> roleOfUser (UserDTO userDTO) {
        // 返回用户的角色表
        return userService.roleOfUser(userDTO);
    }

    @Operation(
        summary = "Add role for user",
        description = "Add a role for user by a unique identifier of user",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Unique identifier of user and role info { id & roleId }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add a new role for user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to add a new role for user "
            )
        }
    )
    @PostMapping("/role")
    public Result<Object> addRoleOfUser (UserDTO userDTO) {
        // 给用户的角色表添加角色
        return roleService.addRole(userDTO);
    }

    @Operation(
        summary = "Delete role for user",
        description = "Delete a role for user by a unique identifier of user",
        parameters = {
            @Parameter(
                name = "userDTO",
                description = "Unique identifier of user and role id { id & roleId }",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to delete a role for user"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to delete a role for user "
            )
        }
    )
    @DeleteMapping("/role")
    public Result<Object> deleteRoleOfUser (UserDTO userDTO) {
        // 删除用户角色表中的角色
        return roleService.deleteRole(userDTO);
    }


    @GetMapping("/password")
    public Result<Object> updatePassword (UserDTO userDTO) {
        // 修改用户的密码
        return userService.checkPassword(userDTO);
    }

    @GetMapping("/getFavoriteOfUser")
    public Result<BlogPostVO> getFavoriteOfUser (UserDTO userDTO) {
        return ResultFactory.buildResult(ResultCode.SUCCESS,"getFavoriteOfUser success",translate.BlogPost(userService.getFavoriteOfUser(userDTO).getData()));
    }
}
