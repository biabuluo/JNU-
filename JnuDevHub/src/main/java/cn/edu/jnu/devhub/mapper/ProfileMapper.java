package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.ProfileDTO;
import cn.edu.jnu.devhub.model.dto.UserDTO;
import cn.edu.jnu.devhub.model.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{16:31}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface ProfileMapper {
    //查询特定用户资料信息
    ProfileVO findProfileByUserId(ProfileDTO profileDTO);
    //修改特定用户资料信息
    int updateProfileByUserId(ProfileDTO profileDTO);
    //添加特定用户资料信息
    int addProfileByUserId(ProfileDTO profileDTO);
    //删除特定用户资料信息
    int deleteProfileByUserId(ProfileDTO profileDTO);
}
