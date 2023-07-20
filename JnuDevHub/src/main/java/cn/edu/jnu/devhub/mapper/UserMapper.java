package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{12}:{20:10}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface UserMapper {
    //根据提供信息查找特定用户
    List<UserVO> findUsers(UserDTO userDTO);
    //注册用户：插入
    int addUser(UserDTO userDTO) throws DuplicateKeyException;
    //修改用户信息：修改
    int updateUser(UserDTO userDTO);
    //删除用户信息：删除
    int deleteUser(UserDTO userDTO);
}
