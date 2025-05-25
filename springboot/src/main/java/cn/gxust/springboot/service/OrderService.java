package cn.gxust.springboot.service;

import cn.gxust.springboot.dto.OrderCreateDTO;
import cn.gxust.springboot.vo.OrderVO;

import java.util.List;

public interface OrderService {

    OrderVO getOrderById(Long id);

    List<OrderVO> getOrderByUserId(Integer userId);

    Long addOrder(OrderCreateDTO orderCreateDTO);

    Long cancelOrder(Long id);

    Long deleteOrder(Long id);
}
