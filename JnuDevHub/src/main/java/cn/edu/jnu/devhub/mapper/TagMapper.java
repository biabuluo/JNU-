package cn.edu.jnu.devhub.mapper;

import cn.edu.jnu.devhub.model.dto.TagDTO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{14:01}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
@Mapper
public interface TagMapper {
    //    查询标签
    List<TagVO> findTag(TagDTO tagDTO);
    //    添加标签
    int addTag(TagDTO tagDTO);
    //    删除标签
    int deleteTag(TagDTO tagDTO);
}
