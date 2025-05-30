package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.FoodConverter;
import cn.gxust.springboot.entity.Food;
import cn.gxust.springboot.dao.FoodRepository;
import cn.gxust.springboot.service.FoodService;
import cn.gxust.springboot.utils.FoodValidator;
import cn.gxust.springboot.utils.ShopValidator;
import cn.gxust.springboot.vo.FoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FoodServiceImpl implements FoodService {

    private static final String SHOP_CACHE_KEY = "foods";
    private static final long BASE_CACHE_TTL = 30 * 60;
    private static final long RANDOM_CACHE_TTL = 10 * 60;
    private static final Logger logger = Logger.getLogger(FoodServiceImpl.class.getName());

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public FoodVO getFoodById(Integer id) {
        // 验证菜品ID
        if (!FoodValidator.isValidId(id)) {
            throw new IllegalStateException("菜品ID必须是正数");
        }

        // 获取指定菜品信息
        Food foodInDB = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("菜品 " + id + " 不存在"));

        return FoodConverter.convertToFoodVO(foodInDB);
    }

    @Override
    public List<FoodVO> getAllFoodByShopId(Integer shopId) {
        // 验证店铺ID
        if (!ShopValidator.isValidId(shopId)) {
            throw new IllegalStateException("店铺ID长度在9-10之间");
        }

        try {
            List<Food> foodListInCache = (List<Food>) redisTemplate.opsForValue().get(SHOP_CACHE_KEY + shopId);

            if (foodListInCache != null) {
                logger.log(Level.INFO, "从Redis中获取菜品信息成功");
                return foodListInCache.stream().map(FoodConverter::convertToFoodVO).toList();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "从Redis中获取菜品信息失败");
        }

        // 获取店铺所有菜品信息
        List<Food> foodListInDB = foodRepository.findByShopId(shopId);

        try {
            long ttl = BASE_CACHE_TTL + ThreadLocalRandom.current().nextLong(RANDOM_CACHE_TTL);
            redisTemplate.opsForValue().set(SHOP_CACHE_KEY + shopId, foodListInDB, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.log(Level.WARNING, "将菜品信息保存到Redis失败");
        }

        return foodListInDB.stream().map(FoodConverter::convertToFoodVO).toList();
    }
}
