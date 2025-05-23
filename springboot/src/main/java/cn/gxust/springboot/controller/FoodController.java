package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.FoodService;
import cn.gxust.springboot.vo.FoodVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    /**
     * 获取指定菜品信息
     *
     * @param id 菜品ID
     * @return 指定菜品信息
     */
    @GetMapping("/foods/{id}")
    public Response<FoodVO> getFoodById(@PathVariable
                                        @Positive(message = "菜品ID必须是正数")
                                        Integer id) {
        return Response.success(foodService.getFoodById(id));
    }


    /**
     * 获取指定店铺所有菜品信息
     *
     * @param shopId 店铺ID
     * @return 指定店铺所有菜品信息
     */
    @GetMapping("/shops/{shopId}/foods")
    public Response<List<FoodVO>> getAllFoodByShopId(@PathVariable
                                                  @Min(value = 100000000, message = "店铺ID长度在9-10之间")
                                                  @Max(value = 2147483647, message = "店铺ID长度在9-10之间")
                                                  Integer shopId) {
        return Response.success(foodService.getAllFoodByShopId(shopId));
    }
}
