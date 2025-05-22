package cn.gxust.springboot.repository;

import cn.gxust.springboot.entity.Order;
import cn.gxust.springboot.vo.OrderVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllOrderByUserId(Integer userId);

    @Query("SELECT new cn.gxust.springboot.vo.OrderVO(" +
            "o.id, s.name, u.name, o.content, o.price, " +
            "o.time, o.addr, o.phone, o.state) " +
            "FROM Order o " +
            "JOIN Shop s ON o.shopId = s.id " +
            "JOIN User u ON o.userId = u.id " +
            "WHERE o.id = :id")
    OrderVO findOrderVOById(@Param("id") long id);
}
