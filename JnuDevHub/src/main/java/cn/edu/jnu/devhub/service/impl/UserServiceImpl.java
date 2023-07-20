package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.*;
import cn.edu.jnu.devhub.model.dto.BlogPostDTO;
import cn.edu.jnu.devhub.model.dto.FavoriteDTO;
import cn.edu.jnu.devhub.model.vo.*;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.service.UserService;
import cn.edu.jnu.devhub.util.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    BlogPostMapper blogPostMapper;

    /**
     * 根据用户的用户名和密码登录
     *
     * @param userDTO 用户输入的用户名和密码
     * @return 根据用户名和其最大权限名生成的jwt, 若用户密码输入错误，则返回错误
     */
    @Override
    public Result<UserVO> login(UserDTO userDTO) {
        Result<UserVO> result = new Result<>();
        String userPassword = userDTO.getPassword();        //暂存用户输入的密码
        userDTO.setPassword(null);                          //用户输入的密码不作为搜索条件
        List<UserVO> temp = userMapper.findUsers(userDTO); //根据用户输入的用户名获取当前登录用户的信息
        if (temp.size() == 0) {
            //用户不存在
            result = ResultFactory.buildResult(ResultCode.FAILURE, "user not exists", null);
        } else {
            //用户存在
            //比对用户的密码是否正确
            String correctPassword = temp.get(0).getPassword();             //获取数据库中的密码
            boolean flag = BCrypt.checkpw(userPassword, correctPassword);   //检测密码是否正确
            if (flag == false) {
                //如果密码错误，则返回错误信息
                result = ResultFactory.buildResult(ResultCode.FAILURE, "username or password not correct", null);
            } else {
                //密码正确
                //获取当前登录用户的id
                int userId = temp.get(0).getId();
                userDTO.setId(userId);
                //根据userId来查询该用户的角色,权限id以升序排列
                List<UserRoleVO> userRoleVOList = userRoleMapper.findRoleIdById(userDTO);
                if (userRoleVOList.size() == 0) {
                    //用户权限不存在
                    result = ResultFactory.buildResult(ResultCode.FAILURE, "user's role not exists", null);
                } else {
                    //找到权限了
                    //列表中第一个元素为roleId最小的，也就是权限最大的集合
                    UserRoleVO userRoleVO = userRoleVOList.get(0);
                    //获得该roleId的权限名
                    String roleName = roleMapper.findRoleById(userRoleVO.getRoleId());
                    //根据用户名和权限名生成jwt
                    String jwt = JwtUtils.createJwt(userDTO.getUsername(), roleName);
                    UserVO userVO = userMapper.findUsers(userDTO).get(0);
                    userVO.setPassword(null);
                    userVO.setToken(jwt);
                    List<UserVO> userVOList = new ArrayList<>();
                    userVOList.add(userVO);
                    result = ResultFactory.buildResult(ResultCode.SUCCESS, "return jwt success", userVOList);
                }
            }

        }

        return result;
    }

    /**
     * 注册新用户
     *
     * @param userDTO 用户的信息
     * @return 注册是否成功
     */
    @Override
    public Result<Object> register(UserDTO userDTO) {
        Result<Object> result = null;
        try {
            //加密用户的密码
            String password = userDTO.getPassword();                                //获取用户的明文密码
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());        //加密用户密码
            userDTO.setPassword(hashPassword);                                      //把userDTO中的密码替换成加密之后的密码
            userMapper.addUser(userDTO);
            // 尝试添加用户（注册）
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "register success", null);
            // 无报错则返回注册成功
        } catch (DuplicateKeyException e) {
            // 捕获重复插入错误并返回注册失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "register failure, user exists", null);
        }
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param userDTO 用户输入的新的用户信息
     * @return 新的用户信息
     */
    @Override
    public Result<UserVO> update(UserDTO userDTO) {
        Result<UserVO> result = new Result<>();

        //把修改后的数据赋值给UserVO对象
        UserVO userVO = new UserVO();
        userVO.setId(userDTO.getId());
        userVO.setUsername(userDTO.getUsername());
        userVO.setPhone(userDTO.getPhone());
        userVO.setEmail(userDTO.getEmail());
        userVO.setPassword(userDTO.getPassword());
        //把UserVO添加到resultList里面，用于结果的返回
        List<UserVO> resultList = new ArrayList<>();
        resultList.add(userVO);
        if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")) {
            //如果用户更新了密码，则把它加密处理
            //加密用户的密码
            String password = userDTO.getPassword();                                //获取用户的明文密码
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());        //加密密码
            userDTO.setPassword(hashPassword);                                      //替换成加密后的密码
        }

        //把修改后的用户信息存到数据库里面
        int flag = userMapper.updateUser(userDTO);

        if (flag == 1) {//修改了一个用户,成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "update success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "update failure", null);
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param userDTO 要删除的用户的数据
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteUser(UserDTO userDTO) {
        Result<Object> result = new Result<>();

        int flag = userMapper.deleteUser(userDTO);

        if (flag == 1) {
            //删除了一个用户,成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete success", null);
        } else {
            //删除失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete failure", null);
        }
        return result;
    }

    /**
     * 找到所有符合条件的用户列表
     *
     * @param userDTO 查找用户的条件
     * @return 符合条件的用户列表
     */
    @Override
    public Result<UserVO> listOfUser(UserDTO userDTO) {
        Result<UserVO> result = new Result<>();

        List<UserVO> resultList = userMapper.findUsers(userDTO);

        if (resultList.size() != 0) {
            //查找结果有用户
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search failure", null);
        }
        return result;
    }

    /**
     * 根据用户的id查找该用户的权限
     *
     * @param userDTO
     * @return 用户的权限列表
     */
    @Override
    public Result<RoleVO> roleOfUser(UserDTO userDTO) {
        Result<RoleVO> roleVOResult = new Result<>();

        List<UserRoleVO> userRoleVOS = userRoleMapper.findRoleIdById(userDTO);      //根据用户id从用户角色表查找用户的角色

        if (userRoleVOS.size() != 0) {              //查询有结果
            List<RoleVO> roleVOList = new ArrayList<>();            //存放返回的结果

            for (UserRoleVO userRoleVO : userRoleVOS) {       //通过查找结果的roleId从role表里面找到完整的roleVO并返回给roleVOList
                int roleId = userRoleVO.getRoleId();
                RoleVO roleVO = new RoleVO();
                roleVO.setId(roleId);
                roleVO.setRoleName(roleMapper.findRoleById(roleId));
                roleVOList.add(roleVO);
            }

            roleVOResult = ResultFactory.buildResult(ResultCode.SUCCESS, "search roles success", roleVOList);
        } else {
            roleVOResult = ResultFactory.buildResult(ResultCode.FAILURE, "search roles failure,no result exists", null);
        }

        return roleVOResult;
    }

    /**
     * 检查用户是否知道密码
     *
     * @param userDTO 用户输入的密码和用户的id
     * @return 密码是否正确
     */
    @Override
    public Result<Object> checkPassword(UserDTO userDTO) {
        Result<Object> result = null;
        String userPassword = userDTO.getPassword();        //暂存用户输入的密码
        userDTO.setPassword(null);                          //用户输入的密码不作为搜索条件
        List<UserVO> temp = userMapper.findUsers(userDTO);  //根据用户输入的用户名或id获取当前登录用户的信息
        if (temp.size() == 0) {
            //用户不存在
            result = ResultFactory.buildResult(ResultCode.FAILURE, "user not exists", null);
        } else {
            //用户存在
            //比对用户的密码是否正确
            String correctPassword = temp.get(0).getPassword();         //获取数据库中用户的密码
            boolean flag = BCrypt.checkpw(userPassword, correctPassword);   //检测两个密码是否匹配
            if (flag == false) {
                //如果密码错误，则返回错误信息
                result = ResultFactory.buildResult(ResultCode.FAILURE, "password not correct", null);
            } else {
                //密码正确
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "password correct", null);
            }

        }
        return result;
    }

    @Override
    public Result<BlogPostVO> getFavoriteOfUser(UserDTO userDTO) {
        Result<BlogPostVO> result = null;
        int userId = userDTO.getId();
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUserId(userId);
        List<FavoriteVO> favoriteVOS = favoriteMapper.findFavorites(favoriteDTO);
        List<BlogPostVO> resultList = new ArrayList<>();
        for (FavoriteVO favoriteVO : favoriteVOS) {
            int postId = favoriteVO.getPostId();
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(postId);
            BlogPostVO temp = blogPostMapper.findBlogPosts(blogPostDTO).get(0);
            resultList.add(temp);
        }
        result = ResultFactory.buildResult(ResultCode.SUCCESS, "getFavoriteOfUser success", resultList);
        return result;
    }
}