package cn.gxust.springboot.converter;

import cn.gxust.springboot.dto.CommentCreateDTO;
import cn.gxust.springboot.dto.CommentUserDTO;
import cn.gxust.springboot.entity.Comment;
import cn.gxust.springboot.vo.CommentVO;

public class CommentConverter {

    public static CommentVO convertToCommentVO(CommentUserDTO commentUserDTO) {
        CommentVO commentVO = new CommentVO();
        commentVO.setUserName(commentUserDTO.getUserName());
        commentVO.setScore(commentUserDTO.getScore());
        commentVO.setTime(commentUserDTO.getTime());
        commentVO.setContent(commentUserDTO.getContent());
        commentVO.setImage(commentUserDTO.getImage());
        return commentVO;
    }

    public static Comment convertToComment(CommentCreateDTO commentCreateDTO) {
        Comment comment = new Comment();
        comment.setShopId(commentCreateDTO.getShopId());
        comment.setUserId(commentCreateDTO.getUserId());
        comment.setOrderId(commentCreateDTO.getOrderId());
        comment.setScore(commentCreateDTO.getScore());
        comment.setContent(commentCreateDTO.getContent());
        return comment;
    }
}
