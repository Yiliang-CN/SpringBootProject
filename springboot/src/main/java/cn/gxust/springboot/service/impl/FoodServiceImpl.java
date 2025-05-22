package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.FoodConverter;
import cn.gxust.springboot.entity.Food;
import cn.gxust.springboot.repository.FoodRepository;
import cn.gxust.springboot.service.FoodService;
import cn.gxust.springboot.vo.FoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodVO getFoodById(Integer id) {
        // 验证菜品ID
        if (id == null || id < 0) {
            throw new IllegalStateException("菜品ID必须是正数");
        }

        // 获取指定菜品信息
        Food foodInDB = foodRepository.findById(id).orElseThrow(RuntimeException::new);

        return FoodConverter.convertFood(foodInDB);
    }

    @Override
    public List<FoodVO> getAllFoodByShopId(Integer shopId) {
        // 验证店铺ID
        if (shopId == null || shopId < 100000000) {
            throw new IllegalStateException("店铺ID长度在9-10之间");
        }

        // 获取店铺所有菜品信息
        List<Food> foodListInDB = foodRepository.findAllFoodByShopId(shopId);

        return foodListInDB.stream().map(FoodConverter::convertFood).toList();
    }
}
