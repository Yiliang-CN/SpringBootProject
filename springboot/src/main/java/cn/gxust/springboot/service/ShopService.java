package cn.gxust.springboot.service;

import cn.gxust.springboot.vo.ShopVO;

import java.util.List;

public interface ShopService {

    ShopVO getShopById(Integer id);

    List<ShopVO> getAllShop();
}
