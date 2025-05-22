package cn.gxust.springboot.service;

import cn.gxust.springboot.vo.FoodVO;

import java.util.List;

public interface FoodService {

    FoodVO getFoodById(Integer id);

    List<FoodVO> getAllFoodByShopId(Integer shopId);
}
