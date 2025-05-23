package cn.gxust.springboot.dto;

public class OrderShopUserDTO {
    private long id;
    private String shopName;
    private String userName;
    private String content;
    private double price;
    private String time;
    private String addr;
    private String phone;
    private String state;

    public OrderShopUserDTO(long id, String shopName, String userName, String content, double price, String time, String addr, String phone, String state) {
        this.id = id;
        this.shopName = shopName;
        this.userName = userName;
        this.content = content;
        this.price = price;
        this.time = time;
        this.addr = addr;
        this.phone = phone;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
