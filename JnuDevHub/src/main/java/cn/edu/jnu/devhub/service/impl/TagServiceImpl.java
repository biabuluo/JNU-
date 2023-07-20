package cn.edu.jnu.devhub.service.impl;

import cn.edu.jnu.devhub.mapper.TagMapper;
import cn.edu.jnu.devhub.model.dto.TagDTO;
import cn.edu.jnu.devhub.model.vo.TagVO;
import cn.edu.jnu.devhub.result.Result;
import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    /**
     * 根据tagDTO的条件查询所有tag
     *
     * @param tagDTO
     * @return 查询到的TagVO结果
     */
    @Override
    public Result<TagVO> findTags(TagDTO tagDTO) {
        Result<TagVO> result = new Result<>();

        List<TagVO> resultList = tagMapper.findTag(tagDTO);
        if (resultList.size() != 0) {                       //查询有结果
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "search success", resultList);
        } else {
            result = ResultFactory.buildResult(ResultCode.FAILURE, "search failure", null);
        }

        return result;
    }

    /**
     * 根据tag的名字将tag插入tag表
     * @param tagDTO tagDTO里有tag的名字
     * @return 是否成功
     */
    @Override
    public Result<Object> addTag(TagDTO tagDTO) {
        Result<Object> result = new Result<>();
        List<TagVO> resultList = tagMapper.findTag(tagDTO); //查找tag是否已存在
        if(resultList.size() == 0){
            //tag不存在
            int flag = tagMapper.addTag(tagDTO);
            if (flag == 1){
                //添加成功
                result = ResultFactory.buildResult(ResultCode.SUCCESS, "add tag success", null);
            } else {
                //添加失败
                result = ResultFactory.buildResult(ResultCode.FAILURE, "add tag failure", null);
            }
        } else {
            //tag已存在
            result = ResultFactory.buildResult(ResultCode.FAILURE, "tag already exists", null);
        }
        return result;
    }

    /**
     * 根据tagDTO的信息删除tag
     * @param tagDTO 可以有id或name
     * @return 删除是否成功
     */
    @Override
    public Result<Object> deleteTag(TagDTO tagDTO) {
        Result<Object> result = new Result<>();
        int flag = tagMapper.deleteTag(tagDTO);
        if(flag != 0){
            //删除了数据
            result = ResultFactory.buildResult(ResultCode.SUCCESS, "delete tag success", null);
        } else {
            //没有删除数据
            result = ResultFactory.buildResult(ResultCode.FAILURE, "delete tag failure", null);
        }
        return result;
    }
}
