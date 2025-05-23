package cn.gxust.springboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentCreateDTO {

    @NotNull(message = "店铺ID不能为空")
    @Min(value = 100000000, message = "店铺ID长度在9-10之间")
    @Max(value = 2147483647, message = "店铺ID长度在9-10之间")
    private int shopId;

    @NotNull(message = "用户ID不能为空")
    @Min(value = 100000000, message = "用户ID长度在9-10之间")
    @Max(value = 2147483647, message = "用户ID长度在9-10之间")
    private int userId;

    @NotNull(message = "订单ID不能为空")
    @Min(value = 100000, message = "订单ID长度不少于6位")
    private long orderId;

    @NotNull(message = "评分不能为空")
    @Min(value = 0, message = "评分不能小于0")
    @Max(value = 5, message = "评分不能大于5")
    private double score;

    @NotBlank(message = "评论内容不能为空")
    private String content;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
