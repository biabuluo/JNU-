package cn.edu.jnu.devhub.service;

import cn.edu.jnu.devhub.model.dto.TagDTO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface TagService {
    @Tag(name = "findTags" ,description = "return List of tagDTO and code 200 when search success,else return code 400")
    Result<TagVO> findTags(TagDTO tagDTO);
    @Tag(name = "addTag" ,description = "return code 200 when search success,else return code 400")
    Result<Object> addTag(TagDTO tagDTO);
    @Tag(name = "deleteTag" ,description = "return code 200 when search success,else return code 400")
    Result<Object> deleteTag(TagDTO tagDTO);
}
