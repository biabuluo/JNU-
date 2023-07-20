package cn.edu.jnu.devhub.service;

import cn.edu.jnu.devhub.model.dto.ProfileDTO;
import cn.edu.jnu.devhub.model.vo.ProfileVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface ProfileService {
    @Tag(name = "findProfile" ,description = "return profileVO and code 200 when search success,else return code 400")
    Result<ProfileVO> findProfile(ProfileDTO profileDTO);
    @Tag(name = "addProfile" ,description = "return code 200 when search success,else return code 400")
    Result<Object> addProfile(ProfileDTO profileDTO);
    @Tag(name = "deleteProfile" ,description = "return code 200 when search success,else return code 400")
    Result<Object> deleteProfile(ProfileDTO profileDTO);
    @Tag(name = "updateProfile" ,description = "return list of updated Profile and code 200 when search success,else return code 400")
    Result<ProfileVO> updateProfile(ProfileDTO profileDTO);
}
