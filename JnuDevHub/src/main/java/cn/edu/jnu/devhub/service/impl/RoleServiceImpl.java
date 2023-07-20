package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.UserRoleMapper;
import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 添加用户的权限
     *
     * @param userDTO 在userDTO中设置user的id以及要添加的roleId
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addRole(UserDTO userDTO) {
        Result<Object> result = null;
        try {
            userRoleMapper.addRoleByUserIdRoleId(userDTO);
            // 尝试添加用户（注册）
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "add role success", null);
            // 无报错则返回注册成功
        } catch (DuplicateKeyException e) {
            // 捕获重复插入错误并返回注册失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "add role failure, user's role already exists", null);
        }
        return result;
    }

    /**
     * 删除用户的权限
     *
     * @param userDTO 在userDTO中设置user的id以及要删除的roleId
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteRole(UserDTO userDTO) {
        Result<Object> result = null;
        int flag = userRoleMapper.deleteRoleByUserIdRoleId(userDTO);
        if (flag == 0) {
            //删除不存在的数据
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete role failure, user or user's role not exists", null);
        } else {
            //删除成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete role success", null);
        }
        return result;
    }


}
