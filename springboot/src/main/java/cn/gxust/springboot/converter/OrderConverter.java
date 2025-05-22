package cn.gxust.springboot.converter;

import cn.gxust.springboot.entity.Comment;
import cn.gxust.springboot.entity.Order;
import cn.gxust.springboot.entity.Shop;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.vo.OrderVO;

public class OrderConverter {

    public static OrderVO convertOrder(Order order, User user, Shop shop) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(order.getId());
        orderVO.setShopName(shop.getName());
        orderVO.setUserName(user.getName());
        orderVO.setContent(order.getContent());
        orderVO.setPrice(order.getPrice());
        orderVO.setTime(order.getTime());
        orderVO.setAddr(order.getAddr());
        orderVO.setPhone(order.getPhone());
        orderVO.setState(order.getState());
        return orderVO;
    }
}
