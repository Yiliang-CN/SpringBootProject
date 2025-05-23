package cn.gxust.springboot.service;

import cn.gxust.springboot.dto.CommentCreateDTO;
import cn.gxust.springboot.vo.CommentVO;

import java.util.List;

public interface CommentService {

    CommentVO getCommentById(Long id);

    List<CommentVO> getCommentByShopId(Integer shopId);

    Long addComment(CommentCreateDTO commentCreateDTO);
}
