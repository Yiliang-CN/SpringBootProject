package cn.gxust.springboot.service;

import cn.gxust.springboot.vo.CommentVO;

public interface CommentService {

    CommentVO getCommentById(Long id);
}
