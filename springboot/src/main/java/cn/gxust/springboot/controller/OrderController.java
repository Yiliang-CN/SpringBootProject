package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.OrderCreateDTO;
import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.OrderService;
import cn.gxust.springboot.vo.OrderVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取指定订单信息
     *
     * @param id 订单ID
     * @return 指定订单信息
     */
    @GetMapping("/orders/{id}")
    public Response<OrderVO> getOrderById(@PathVariable
                                          @Min(value = 100000, message = "订单ID长度不少于6位")
                                          Long id) {
        return Response.success(orderService.getOrderById(id));
    }

    /**
     * 获取指定用户所有订单信息
     *
     * @param userId 用户ID
     * @return 指定用户所有订单信息
     */
    @GetMapping("/users/{userId}/orders")
    public Response<List<OrderVO>> getOrderByUserId(@PathVariable
                                                    @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                                    @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                                    Integer userId) {
        return Response.success(orderService.getOrderByUserId(userId));
    }

    /**
     * 添加订单
     *
     * @param orderCreateDTO 订单信息
     * @return 新订单的ID
     */
    @PostMapping("/orders")
    public Response<Long> addOrder(@RequestBody @Valid OrderCreateDTO orderCreateDTO) {
        return Response.success(orderService.addOrder(orderCreateDTO));
    }

    @PostMapping("/orders/cancel/{id}")
    public Response<Long> cancelOrder(@PathVariable
                                      @Min(value = 100000, message = "订单ID长度不少于6位")
                                      Long id) {
        return Response.success(orderService.cancelOrder(id));
    }

    @PostMapping("/orders/delete/{id}")
    public Response<Long> deleteOrder(@PathVariable
                                      @Min(value = 100000, message = "订单ID长度不少于6位")
                                      Long id) {
        return Response.success(orderService.deleteOrder(id));
    }
}
