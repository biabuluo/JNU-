package cn.edu.jnu.devhub.controller;

import cn.edu.jnu.devhub.model.dto.ProfileDTO;
import cn.edu.jnu.devhub.model.vo.ProfileVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ProfileController", description = "Manage all requests about the profile interface")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Operation(
        summary = "Get user profile",
        description = "Get a profile of user by userId",
        parameters = {
            @Parameter(
                name = "profileDTO",
                description = "a unique identify of user { userId }",
                schema = @Schema(
                    implementation = ProfileDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and get a not null info of user profile { id & userId & avatar & nickname & bio }"
            ),
            @ApiResponse(responseCode = "400",
                description = "Failure and get a null int of user profile"
            )
        }
    )
    @GetMapping("")
    public Result<ProfileVO> get (ProfileDTO profileDTO) {
        // 获取指定条件的用户信息
        return profileService.findProfile(profileDTO);
    }

    @Operation(
        summary = "Add user profile",
        description = "Add a profile for user by userId",
        parameters = {
            @Parameter(
                name = "profileDTO",
                description = "a unique identify of user and info of profileDTO { userId & avatar & nickname & bio }",
                schema = @Schema(
                    implementation = ProfileDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to add user profile"
            ),
            @ApiResponse(responseCode = "400",
                description = "Failure to add user profile"
            )
        }
    )
    @PostMapping("")
    public Result<Object> add (ProfileDTO profileDTO) {
        // 添加用户信息
        return profileService.addProfile(profileDTO);
    }

    @Operation(
        summary = "Update user profile",
        description = "Update a profile of user by userId and new info of profile",
        parameters = {
            @Parameter(
                name = "profileDTO",
                description = "a unique identify of user and new info of profile { userId & { avatar | nickname | bio } }",
                schema = @Schema(
                    implementation = ProfileDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success and return a not null new info of user profile after update { id & userId & avatar & nickname & bio }"
            ),
            @ApiResponse(responseCode = "400",
                description = "Failure and get a null int of user profile"
            )
        }
    )
    @PutMapping("")
    public Result<ProfileVO> update (ProfileDTO profileDTO) {
        // 更新用户信息
        return profileService.updateProfile(profileDTO);
    }

    @Operation(
        summary = "Delete user profile",
        description = "Delete a profile for user by userId",
        parameters = {
            @Parameter(
                name = "profileDTO",
                description = "a unique identify of user { userId }",
                schema = @Schema(
                    implementation = ProfileDTO.class
                )
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success to delete user profile"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Failure to delete user profile"
            )
        }
    )
    @DeleteMapping("")
    public Result<Object> delete (ProfileDTO profileDTO) {
        // 删除用户信息
        return profileService.deleteProfile(profileDTO);
    }
}
