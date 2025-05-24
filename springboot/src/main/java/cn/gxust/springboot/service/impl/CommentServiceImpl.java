package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.CommentConverter;
import cn.gxust.springboot.dto.CommentCreateDTO;
import cn.gxust.springboot.dto.CommentUserDTO;
import cn.gxust.springboot.entity.Comment;
import cn.gxust.springboot.dao.CommentRepository;
import cn.gxust.springboot.dao.OrderRepository;
import cn.gxust.springboot.dao.ShopRepository;
import cn.gxust.springboot.dao.UserRepository;
import cn.gxust.springboot.service.CommentService;
import cn.gxust.springboot.utils.CommentValidator;
import cn.gxust.springboot.utils.OrderValidator;
import cn.gxust.springboot.utils.ShopValidator;
import cn.gxust.springboot.utils.UserValidator;
import cn.gxust.springboot.vo.CommentVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CommentVO getCommentById(Long id) {
        // 验证评论ID
        if (!CommentValidator.isValidId(id)) {
            throw new IllegalStateException("评论ID必须是正数");
        }

        // 获取指定评论信息
        CommentUserDTO commentUserDTOInDB = commentRepository.findCommentById(id);

        return CommentConverter.convertToCommentVO(commentUserDTOInDB);
    }

    @Override
    public List<CommentVO> getCommentByShopId(Integer shopId) {
        // 验证店铺ID
        if (!ShopValidator.isValidId(shopId)) {
            throw new IllegalStateException("店铺ID长度在9-10之间");
        }

        // 获取指定店铺所有评论信息
        List<CommentUserDTO> commentUserDTOListInDB = commentRepository.findCommentByShopId(shopId);

        return commentUserDTOListInDB.stream().map(CommentConverter::convertToCommentVO).toList();
    }

    @Override
    @Transactional
    public Long addComment(CommentCreateDTO commentCreateDTO) {
        // 验证评论信息
        if (!ShopValidator.isValidId(commentCreateDTO.getShopId()) ||
                !UserValidator.isValidId(commentCreateDTO.getUserId()) ||
                !OrderValidator.isValidId(commentCreateDTO.getOrderId()) ||
                !CommentValidator.isValidScore(commentCreateDTO.getScore()) ||
                !CommentValidator.isValidContent(commentCreateDTO.getContent())) {
            throw new IllegalStateException("评论信息不合法");
        }

        // 验证店铺是否存在
        if (!shopRepository.existsById(commentCreateDTO.getShopId())) {
            throw new IllegalStateException("店铺不存在");
        }

        // 验证用户是否存在
        if (!userRepository.existsById(commentCreateDTO.getUserId())) {
            throw new IllegalStateException("用户不存在");
        }

        // 验证订单是否存在
        if (!orderRepository.existsById(commentCreateDTO.getOrderId())) {
            throw new IllegalStateException("订单不存在");
        }

        // 转换评论信息
        Comment comment = CommentConverter.convertToComment(commentCreateDTO);

        // 保存评论信息
        commentRepository.save(comment);

        return comment.getId();
    }
}
