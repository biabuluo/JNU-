package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.ProfileMapper;
import cn.edu.jnu.devhub.model.dto.ProfileDTO;
import cn.edu.jnu.devhub.model.vo.ProfileVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileMapper profileMapper;

    /**
     * 根据用户的id查找对应的Profile信息
     * @param profileDTO 需要传入用户的id
     * @return 查找到的用户信息
     */
    @Override
    public Result<ProfileVO> findProfile(ProfileDTO profileDTO) {
        Result<ProfileVO> result = new Result<>();
        ProfileVO profileVO = profileMapper.findProfileByUserId(profileDTO);

        if (profileVO != null) {                       //查询有结果
            List<ProfileVO> resultList = new ArrayList<>();
            resultList.add(profileVO);
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search profile success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search profile failure", null);
        }
        return result;
    }

    /**
     * 根据用户id添加profile信息
     * @param profileDTO 必须要有用户的id
     * @return 添加是否成功
     */
    @Override
    public Result<Object> addProfile(ProfileDTO profileDTO) {
        Result<Object> result = new Result<>();
        ProfileVO profileVO = profileMapper.findProfileByUserId(profileDTO);    //查找用户资料是否已存在
        if (profileVO == null) {
            //资料不存在
            int flag = profileMapper.addProfileByUserId(profileDTO);
            if (flag != 0) {
                //添加成功
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "add profile success", null);
            } else {
                //添加失败
                result = ResultFactory.buildResult(ResultCode.FAILURE, "add profile failure", null);
            }
        } else {
            //已经存在profile了
            result = ResultFactory.buildResult(ResultCode.FAILURE, "profile already exists", null);
        }

        return result;
    }

    /**
     * 根据用户id删除profile
     * @param profileDTO 里面有用户的id
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteProfile(ProfileDTO profileDTO) {
        Result<Object> result = new Result<>();
        int flag = profileMapper.deleteProfileByUserId(profileDTO);
        if (flag == 1) {
            //删除成功
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete profile success", null);
        } else {
            //删除失败
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete profile failure", null);
        }
        return result;
    }

    /**
     * 根据用户id修改用户信息
     * @param profileDTO 新的profile信息
     * @return 修改后的内容
     */
    @Override
    public Result<ProfileVO> updateProfile(ProfileDTO profileDTO) {
        Result<ProfileVO> result = new Result<>();
        ProfileVO profileVO = profileMapper.findProfileByUserId(profileDTO);    //查找用户资料是否存在
        if (profileVO != null) {
            //资料存在
            int flag = profileMapper.updateProfileByUserId(profileDTO);
            if (flag != 0) {
                //修改成功
                ProfileVO profileVOTemp = new ProfileVO();
                List<ProfileVO> resultList = new ArrayList<>();
                profileVOTemp.setId(profileDTO.getId());
                profileVOTemp.setAvatar(profileDTO.getAvatar());
                profileVOTemp.setBio(profileDTO.getBio());
                profileVOTemp.setNickname(profileDTO.getNickname());
                profileVOTemp.setUserId(profileDTO.getUserId());

                resultList.add(profileVOTemp);
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "add profile success", resultList);
            } else {
                //修改失败
                result = ResultFactory.buildResult(ResultCode.FAILURE, "add profile failure", null);
            }
        } else {
            //不存在此资料
            result = ResultFactory.buildResult(ResultCode.FAILURE, "profile not exists", null);
        }

        return result;
    }
}
