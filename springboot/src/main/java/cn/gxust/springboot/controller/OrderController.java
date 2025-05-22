package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.OrderService;
import cn.gxust.springboot.vo.OrderVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据订单ID获取订单信息
     *
     * @param id 订单ID
     * @return 指定订单信息
     */
    @GetMapping("/orders/{id}")
    public Response<OrderVO> getOrderById(@PathVariable
                                          @Min(value = 100000, message = "订单ID长度不少于6位")
                                          Integer id) {
        return Response.success(orderService.getOrderById(id));
    }

    /**
     * 根据用户ID获取指定用户所有的订单信息
     *
     * @param userId 用户ID
     * @return 指定用户所有的订单信息
     */
    @GetMapping("/users/{userId}/orders")
    public Response<List<OrderVO>> getAllOrderByUserId(@PathVariable
                                                       @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                                       @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                                       Integer userId) {
        return Response.success(orderService.getAllOrderByUserId(userId));
    }
}
