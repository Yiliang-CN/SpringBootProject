package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.ShopConverter;
import cn.gxust.springboot.dao.ShopRepository;
import cn.gxust.springboot.entity.Shop;
import cn.gxust.springboot.service.ShopService;
import cn.gxust.springboot.utils.ShopValidator;
import cn.gxust.springboot.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ShopServiceImpl implements ShopService {

    private static final String SHOP_CACHE_KEY = "shops";
    private static final long BASE_CACHE_TTL = 30 * 60;
    private static final long RANDOM_CACHE_TTL = 10 * 60;
    private static final Logger logger = Logger.getLogger(ShopServiceImpl.class.getName());

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ShopVO getShopById(Integer id) {
        // 验证店铺ID
        if (!ShopValidator.isValidId(id)) {
            throw new IllegalStateException("店铺ID长度在9-10之间");
        }

        try {
            Shop shopInCache = (Shop) redisTemplate.opsForValue().get(SHOP_CACHE_KEY + id);

            if (shopInCache != null) {
                logger.log(Level.INFO, "从Redis中获取店铺信息成功");
                return ShopConverter.convertToShopVO(shopInCache);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "从Redis中获取店铺信息失败");
        }

        // 获取店铺信息
        Shop shopInDB = shopRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("店铺: " + id + " 不存在"));

        try {
            long ttl = BASE_CACHE_TTL + ThreadLocalRandom.current().nextLong(RANDOM_CACHE_TTL);
            redisTemplate.opsForValue().set(SHOP_CACHE_KEY + id, shopInDB, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.log(Level.WARNING, "将店铺信息保存到Redis失败");
        }

        return ShopConverter.convertToShopVO(shopInDB);
    }

    @Override
    public List<ShopVO> getAllShop() {
        try {
            // 从Redis中获取店铺信息
            List<Shop> shopListInCache = (List<Shop>) redisTemplate.opsForValue().get(SHOP_CACHE_KEY);

            if (shopListInCache != null) {
                logger.log(Level.INFO, "从Redis中获取店铺信息成功");
                return shopListInCache.stream().map(ShopConverter::convertToShopVO).toList();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "从Redis中获取店铺信息失败");
        }


        // 从数据库中获取店铺信息
        List<Shop> shopListInDB = shopRepository.findAll();

        try {
            long ttl = BASE_CACHE_TTL + ThreadLocalRandom.current().nextLong(RANDOM_CACHE_TTL);
            redisTemplate.opsForValue().set(SHOP_CACHE_KEY, shopListInDB, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.log(Level.WARNING, "将店铺信息保存到Redis失败");
        }

        return shopListInDB.stream().map(ShopConverter::convertToShopVO).toList();
    }
}
