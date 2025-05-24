package cn.gxust.springboot.utils;

import org.springframework.util.StringUtils;

public class CommentValidator {

    private static final int MIN_ID_LENGTH = 1;
    private static final double MIN_SCORE = 0;
    private static final double MAX_SCORE = 5;

    public static boolean isValidId(Long id) {
        return id != null &&
                id >= MIN_ID_LENGTH;
    }

    public static boolean isValidScore(double score) {
        return score >= MIN_SCORE &&
                score <= MAX_SCORE;
    }

    public static boolean isValidContent(String content) {
        return StringUtils.hasText(content);
    }

}
