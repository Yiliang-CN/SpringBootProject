package cn.gxust.springboot.converter;

import cn.gxust.springboot.dto.OrderCreateDTO;
import cn.gxust.springboot.dto.OrderShopUserDTO;
import cn.gxust.springboot.entity.Order;
import cn.gxust.springboot.vo.OrderVO;

public class OrderConverter {

    public static OrderVO convertToOrderVO(OrderShopUserDTO orderShopUserDTO) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(orderShopUserDTO.getId());
        orderVO.setShopName(orderShopUserDTO.getShopName());
        orderVO.setUserName(orderShopUserDTO.getUserName());
        orderVO.setContent(orderShopUserDTO.getContent());
        orderVO.setPrice(orderShopUserDTO.getPrice());
        orderVO.setTime(orderShopUserDTO.getTime());
        orderVO.setAddr(orderShopUserDTO.getAddr());
        orderVO.setPhone(orderShopUserDTO.getPhone());
        orderVO.setState(orderShopUserDTO.getState());
        return orderVO;
    }

    public static Order convertToOrder(OrderCreateDTO orderCreateDTO){
        Order order = new Order();
        order.setShopId(orderCreateDTO.getShopId());
        order.setUserId(orderCreateDTO.getUserId());
        order.setContent(orderCreateDTO.getContent());
        order.setPrice(orderCreateDTO.getPrice());
        order.setAddr(orderCreateDTO.getAddr());
        order.setPhone(orderCreateDTO.getPhone());
        return order;
    }
}
