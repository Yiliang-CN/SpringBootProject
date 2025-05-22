package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.OrderConverter;
import cn.gxust.springboot.entity.Order;
import cn.gxust.springboot.entity.Shop;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.repository.OrderRepository;
import cn.gxust.springboot.repository.ShopRepository;
import cn.gxust.springboot.repository.UserRepository;
import cn.gxust.springboot.service.OrderService;
import cn.gxust.springboot.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderVO getOrderById(Integer id) {
        // 验证订单ID
        if (id == null || id < 100000) {
            throw new IllegalStateException("订单ID长度不少于6位");
        }

//        // 获取指定订单信息及相关联数据
//        Order orderInDB = orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("订单不存在"));
//
//        // 获取相关店铺信息
//        Shop shopInDB = shopRepository.findById(orderInDB.getShopId()).orElseThrow(() -> new IllegalStateException("店铺不存在"));
//
//        // 获取相关用户信息
//        User userInDB = userRepository.findById(orderInDB.getUserId()).orElseThrow(() -> new IllegalStateException("用户不存在"));

        return orderRepository.findOrderVOById(id);
    }

    @Override
    public List<OrderVO> getAllOrderByUserId(Integer userId) {
        // 验证用户ID
        if (userId == null || userId < 100000000) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 获取指定用户所有的订单信息
        List<Order> orderListInDB = orderRepository.findAllOrderByUserId(userId);
        if (orderListInDB.isEmpty()) {
            return Collections.emptyList();     // 订单列表为空
        }

        // 获取所有店铺ID
        Set<Integer> shopIdSet = orderListInDB.stream().map(Order::getShopId).collect(Collectors.toSet());

        // 批量查询店铺信息
        Map<Integer, Shop> shopMap = shopRepository.findAllById(shopIdSet).stream().collect(Collectors.toMap(Shop::getId, shop -> shop));

        // 获取所有订单ID
        Set<Long> orderIdSet = orderListInDB.stream().map(Order::getId).collect(Collectors.toSet());

        // 组装VO列表

        return List.of();
    }
}
