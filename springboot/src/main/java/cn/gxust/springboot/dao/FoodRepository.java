package cn.gxust.springboot.dao;

import cn.gxust.springboot.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findByShopId(Integer shopId);
}
