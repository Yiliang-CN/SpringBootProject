package cn.gxust.springboot.service;

import cn.gxust.springboot.vo.OrderVO;

import java.util.List;

public interface OrderService {

    OrderVO getOrderById(Integer id);

    List<OrderVO> getAllOrderByUserId(Integer userId);
}
