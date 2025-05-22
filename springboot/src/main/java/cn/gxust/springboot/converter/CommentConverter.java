package cn.gxust.springboot.converter;

import cn.gxust.springboot.entity.Comment;
import cn.gxust.springboot.vo.CommentVO;

public class CommentConverter {

    public static CommentVO convertComment(Comment comment) {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(comment.getId());
        commentVO.setShopId(comment.getShopId());
        commentVO.setUserId(comment.getUserId());
        commentVO.setOrderId(comment.getOrderId());
        commentVO.setScore(comment.getScore());
        commentVO.setTime(comment.getTime());
        commentVO.setContent(comment.getContent());
        return commentVO;
    }
}
