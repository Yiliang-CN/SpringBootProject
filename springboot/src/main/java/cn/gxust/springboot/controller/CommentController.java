package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.CommentService;
import cn.gxust.springboot.vo.CommentVO;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 根据评论ID获取评论信息
     *
     * @param id 评论ID
     * @return 指定评论信息
     */
    @GetMapping("/comments/{id}")
    public Response<CommentVO> getCommentById(@PathVariable
                                              @Positive(message = "评论ID必须是正数")
                                              Long id) {
        return Response.success(commentService.getCommentById(id));
    }
}
