package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.ShopConverter;
import cn.gxust.springboot.dao.ShopRepository;
import cn.gxust.springboot.entity.Shop;
import cn.gxust.springboot.service.ShopService;
import cn.gxust.springboot.utils.ShopValidator;
import cn.gxust.springboot.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public ShopVO getShopById(Integer id) {
        // 验证店铺ID
        if (!ShopValidator.isValidId(id)) {
            throw new IllegalStateException("店铺ID长度在9-10之间");
        }

        // 获取店铺信息
        Shop shopInDB = shopRepository.findById(id).orElseThrow(RuntimeException::new);

        return ShopConverter.convertToShopVO(shopInDB);
    }

    @Override
    public List<ShopVO> getAllShop() {

        // 获取所有店铺信息
        List<Shop> shopListInDB = shopRepository.findAll();

        return shopListInDB.stream().map(ShopConverter::convertToShopVO).toList();
    }
}
