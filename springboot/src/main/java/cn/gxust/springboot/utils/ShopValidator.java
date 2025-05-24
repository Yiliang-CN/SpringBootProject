package cn.gxust.springboot.utils;

public class ShopValidator {

    private static final Integer MIN_ID_LENGTH = 100000000;
    private static final Integer MAX_ID_LENGTH = 2147483647;

    public static boolean isValidId(Integer id) {
        return id != null &&
                id >= MIN_ID_LENGTH &&
                id <= MAX_ID_LENGTH;
    }
}
