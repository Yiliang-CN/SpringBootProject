package cn.gxust.springboot.utils;

import org.springframework.util.StringUtils;

public class OrderValidator {

    private static final int MIN_ID_LENGTH = 100000;
    private static final double MIN_PRICE = 0;

    public static boolean isValidId(Long id) {
        return id != null &&
                id >= MIN_ID_LENGTH;
    }

    public static boolean isValidContent(String content) {
        return StringUtils.hasText(content);
    }

    public static boolean isValidPrice(double price) {
        return price >= MIN_PRICE;
    }

    public static boolean isValidAddr(String addr) {
        return StringUtils.hasText(addr);
    }
}
