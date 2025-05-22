package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.CommentConverter;
import cn.gxust.springboot.entity.Comment;
import cn.gxust.springboot.repository.CommentRepository;
import cn.gxust.springboot.service.CommentService;
import cn.gxust.springboot.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentVO getCommentById(Long id) {
        // 验证评论ID
        if (id == null || id < 0) {
            throw new IllegalStateException("评论ID必须是正数");
        }

        // 获取指定评论信息
        Comment commentInDB = commentRepository.findById(id).orElseThrow(RuntimeException::new);

        return CommentConverter.convertComment(commentInDB);
    }
}
