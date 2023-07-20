package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.model.vo.UserRoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{10:26}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface UserRoleMapper {
//    查询对应用户的权限id
    List<UserRoleVO> findRoleIdById(UserDTO userDTO);
//    添加对应用户权限
    int addRoleByUserIdRoleId(UserDTO userDTO);
//    删除对应用户权限
    int deleteRoleByUserIdRoleId(UserDTO userDTO);
}
