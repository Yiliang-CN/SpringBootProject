package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.OrderConverter;
import cn.gxust.springboot.dto.OrderCreateDTO;
import cn.gxust.springboot.dto.OrderShopUserDTO;
import cn.gxust.springboot.entity.Order;
import cn.gxust.springboot.dao.OrderRepository;
import cn.gxust.springboot.dao.ShopRepository;
import cn.gxust.springboot.dao.UserRepository;
import cn.gxust.springboot.service.OrderService;
import cn.gxust.springboot.utils.OrderValidator;
import cn.gxust.springboot.utils.ShopValidator;
import cn.gxust.springboot.utils.UserValidator;
import cn.gxust.springboot.vo.OrderVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderVO getOrderById(Long id) {
        // 验证订单ID
        if (!OrderValidator.isValidId(id)) {
            throw new IllegalStateException("订单ID长度不少于6位");
        }

        // 获取指定订单信息
        OrderShopUserDTO orderShopUserDTOInDB = orderRepository.findOrderById(id);

        return OrderConverter.convertToOrderVO(orderShopUserDTOInDB);
    }

    @Override
    public List<OrderVO> getOrderByUserId(Integer userId) {
        // 验证用户ID
        if (!UserValidator.isValidId(userId)) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 获取指定用户所有订单信息
        List<OrderShopUserDTO> orderShopUserDTOListInDB = orderRepository.findOrderByUserId(userId);

        return orderShopUserDTOListInDB.stream().map(OrderConverter::convertToOrderVO).toList();
    }

    @Override
    @Transactional
    public Long addOrder(OrderCreateDTO orderCreateDTO) {
        // 验证订单信息
        if (!ShopValidator.isValidId(orderCreateDTO.getShopId()) ||
                !UserValidator.isValidId(orderCreateDTO.getUserId()) ||
                !OrderValidator.isValidContent(orderCreateDTO.getContent()) ||
                !OrderValidator.isValidPrice(orderCreateDTO.getPrice()) ||
                !OrderValidator.isValidAddr(orderCreateDTO.getAddr()) ||
                !UserValidator.isValidPhone(orderCreateDTO.getPhone())) {
            throw new IllegalStateException("订单信息不合法");
        }

        // 验证店铺是否存在
        if (!shopRepository.existsById(orderCreateDTO.getShopId())) {
            throw new IllegalStateException("店铺不存在");
        }

        // 验证用户是否存在
        if (!userRepository.existsById(orderCreateDTO.getUserId())) {
            throw new IllegalStateException("用户不存在");
        }

        // 转换订单信息
        Order order = OrderConverter.convertToOrder(orderCreateDTO);

        // 保存订单信息
        orderRepository.save(order);

        return order.getId();
    }
}
