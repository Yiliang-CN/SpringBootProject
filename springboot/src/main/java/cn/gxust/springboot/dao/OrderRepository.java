package cn.gxust.springboot.dao;

import cn.gxust.springboot.dto.OrderShopUserDTO;
import cn.gxust.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT new cn.gxust.springboot.dto.OrderShopUserDTO(" +
            "o.id, s.name, u.name, o.content, o.price, " +
            "o.time, o.addr, o.phone, o.state) " +
            "FROM Order o " +
            "JOIN Shop s ON o.shopId = s.id " +
            "JOIN User u ON o.userId = u.id " +
            "WHERE o.id = :id")
    OrderShopUserDTO findOrderById(@Param("id") long id);


    @Query("SELECT new cn.gxust.springboot.dto.OrderShopUserDTO(" +
            "o.id, s.name, u.name, o.content, o.price, " +
            "o.time, o.addr, o.phone, o.state) " +
            "FROM Order o " +
            "JOIN Shop s ON o.shopId = s.id " +
            "JOIN User u ON o.userId = u.id " +
            "WHERE o.userId = :userId")
    List<OrderShopUserDTO> findOrderByUserId(@Param("userId") int userId);
}
