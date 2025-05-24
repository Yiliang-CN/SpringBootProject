package cn.gxust.springboot.utils;

public class FoodValidator {

    private static final int MIN_ID_LENGTH = 1;

    public static boolean isValidId(Integer id) {
        return id != null &&
                id >= MIN_ID_LENGTH;
    }
}
