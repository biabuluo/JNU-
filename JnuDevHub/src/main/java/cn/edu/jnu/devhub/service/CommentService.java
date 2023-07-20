package cn.edu.jnu.devhub.service;


import cn.edu.jnu.devhub.model.dto.CommentDTO;
import cn.edu.jnu.devhub.model.vo.CommentVO;
import cn.edu.jnu.devhub.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface CommentService {

    @Tag(name = "listOfComments" ,description = "return List of CommentVO and code 200 when search success,else return code 400")
    Result<CommentVO> listOfComments(CommentDTO commentDTO);
    @Tag(name = "addComment" ,description = "return code 200 when search success,else return code 400")
    Result<Object> addComment(CommentDTO commentDTO);
    @Tag(name = "deleteComment" ,description = "return code 200 when search success,else return code 400")
    Result<Object> deleteComment(CommentDTO commentDTO);
    @Tag(name = "updateComment" ,description = "return list of updated Comments and code 200 when search success,else return code 400")
    Result<CommentVO> updateComment(CommentDTO commentDTO);
}
