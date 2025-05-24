package cn.gxust.springboot.converter;

import cn.gxust.springboot.entity.Food;
import cn.gxust.springboot.vo.FoodVO;

public class FoodConverter {
    public static FoodVO convertToFoodVO(Food food) {
        FoodVO foodVO = new FoodVO();
        foodVO.setId(food.getId());
        foodVO.setName(food.getName());
        foodVO.setType(food.getType());
        foodVO.setSales(food.getSales());
        foodVO.setPrice(food.getPrice());
        foodVO.setNum(food.getNum());
        foodVO.setImage(food.getImage());
        return foodVO;
    }
}
