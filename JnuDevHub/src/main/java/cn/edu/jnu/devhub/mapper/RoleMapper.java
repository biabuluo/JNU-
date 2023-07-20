package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{9:50}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface RoleMapper {
    //查询所有权限
    List<RoleVO> findAllRole();
    //通过权限id查询对应权限
    String findRoleById(Integer id);
}
