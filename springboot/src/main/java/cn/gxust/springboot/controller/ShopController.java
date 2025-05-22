package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.service.ShopService;
import cn.gxust.springboot.vo.ShopVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 获取店铺信息
     *
     * @param id 店铺ID
     * @return 店铺信息包装类ShopVO
     */
    @GetMapping("/shops/{id}")
    public Response<ShopVO> getShopById(@PathVariable
                                        @Min(value = 100000000, message = "店铺ID长度在9-10之间")
                                        @Max(value = 2147483647, message = "店铺ID长度在9-10之间")
                                        Integer id) {
        return Response.success(shopService.getShopById(id));
    }

    /**
     * 获取所有店铺信息
     *
     * @return 所有店铺信息包装类List<ShopVO>
     */
    @GetMapping("/shops")
    public Response<List<ShopVO>> getAllShop() {
        return Response.success(shopService.getAllShop());
    }
}
