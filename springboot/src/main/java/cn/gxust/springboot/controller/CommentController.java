package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.CommentCreateDTO;
import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.CommentService;
import cn.gxust.springboot.vo.CommentVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取指定评论信息
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

    /**
     * 获取指定店铺所有评论信息
     *
     * @param shopId 商家ID
     * @return 指定店铺所有评论信息
     */
    @GetMapping("/shops/{shopId}/comments")
    public Response<List<CommentVO>> getCommentByShopId(@PathVariable
                                                        @Min(value = 100000000, message = "店铺ID长度在9-10之间")
                                                        @Max(value = 2147483647, message = "店铺ID长度在9-10之间")
                                                        Integer shopId) {
        return Response.success(commentService.getCommentByShopId(shopId));
    }

    /**
     * 添加新的评论信息
     *
     * @param commentCreateDTO 评论信息
     * @return 新评论的ID
     */
    @PostMapping("/comments")
    public Response<Long> addComment(@RequestBody @Valid CommentCreateDTO commentCreateDTO) {
        return Response.success(commentService.addComment(commentCreateDTO));
    }
}
