package cn.gxust.springboot.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class UserValidator {

    private static final Integer MIN_ID_LENGTH = 100000000;
    private static final Integer MAX_ID_LENGTH = 2147483647;
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private static final Pattern Gender_PATTERN = Pattern.compile("男|女|保密");
    private static final Pattern Birthday_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    public static boolean isValidId(Integer id) {
        return id != null &&
                id >= MIN_ID_LENGTH &&
                id <= MAX_ID_LENGTH;
    }

    public static boolean isValidName(String userName) {
        return StringUtils.hasText(userName) &&
                !userName.isEmpty() &&
                userName.length() <= 16;
    }

    public static boolean isValidPhone(String phone) {
        return StringUtils.hasText(phone) &&
                PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidPassword(String password) {
        return StringUtils.hasText(password) &&
                password.length() >= 6 &&
                password.length() <= 20;
    }

    public static boolean isValidGender(String gender) {
        return StringUtils.hasText(gender) &&
                Gender_PATTERN.matcher(gender).matches();
    }

    public static boolean isValidBirthday(String birthday) {
        return StringUtils.hasText(birthday) &&
                Birthday_PATTERN.matcher(birthday).matches();
    }

    public static boolean isValidUrl(String url) {
        return StringUtils.hasText(url);
    }
}
