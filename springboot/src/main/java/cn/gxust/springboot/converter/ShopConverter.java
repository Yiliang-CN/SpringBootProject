package cn.gxust.springboot.converter;

import cn.gxust.springboot.entity.Shop;
import cn.gxust.springboot.vo.ShopVO;

public class ShopConverter {

    public static ShopVO convertShop(Shop shop) {
        ShopVO shopVO = new ShopVO();
        shopVO.setId(shop.getId());
        shopVO.setName(shop.getName());
        shopVO.setSales(shop.getSales());
        shopVO.setPrice(shop.getPrice());
        shopVO.setAddr(shop.getAddr());
        shopVO.setPhone(shop.getPhone());
        shopVO.setImage(shop.getImage());
        return shopVO;
    }
}
