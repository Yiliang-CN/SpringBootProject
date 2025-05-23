package cn.gxust.springboot.dao;

import cn.gxust.springboot.dto.CommentUserDTO;
import cn.gxust.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new cn.gxust.springboot.dto.CommentUserDTO(" +
            "u.name, c.score, c.time, c.content, u.image) " +
            "FROM Comment c " +
            "JOIN User u ON c.userId = u.id " +
            "WHERE c.id = :id")
    CommentUserDTO findCommentById(@Param("id") long id);

    @Query("SELECT new cn.gxust.springboot.dto.CommentUserDTO(" +
            "u.name, c.score, c.time, c.content, u.image) " +
            "FROM Comment c " +
            "JOIN User u ON c.userId = u.id " +
            "WHERE c.shopId = :shopId")
    List<CommentUserDTO> findCommentByShopId(Integer shopId);
}
