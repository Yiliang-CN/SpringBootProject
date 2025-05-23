package cn.gxust.springboot.dto;

import jakarta.validation.constraints.*;

public class OrderCreateDTO {

    @NotNull(message = "店铺ID不能为空")
    @Min(value = 100000000, message = "店铺ID长度在9-10之间")
    @Max(value = 2147483647, message = "店铺ID长度在9-10之间")
    private int shopId;

    @NotNull(message = "用户ID不能为空")
    @Min(value = 100000000, message = "用户ID长度在9-10之间")
    @Max(value = 2147483647, message = "用户ID长度在9-10之间")
    private int userId;

    @NotBlank(message = "订单内容不能为空")
    private String content;

    @NotNull(message = "订单价格不能为空")
    @Positive(message = "订单价格必须是正数")
    private double price;

    @NotBlank(message = "订单地址不能为空")
    private String addr;

    @NotBlank(message = "订单电话不能为空")
    @Pattern(regexp = "^1[3-9][0-9]{9}$", message = "手机号格式不正确")
    private String phone;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
