package cn.gxust.springboot.service;

import cn.gxust.springboot.vo.ShopVO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface ShopService {

    ShopVO getShopById(Integer id);

    List<ShopVO> getAllShop();
}
