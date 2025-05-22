package cn.gxust.springboot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "foods")
@DynamicInsert  // 只插入非 NULL 字段 让数据库处理默认值
public class Food {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 编号ID

    @Column(name = "name")
    private String name;    // 菜品名

    @Column(name = "shopId")
    private int shopId;     // 店铺ID

    @Column(name = "type")
    private String type;    // 分类

    @Column(name = "sales")
    private int sales;      // 销量

    @Column(name = "price")
    private double price;   // 价格

    @Column(name = "num")
    private int num;        // 数量

    @Column(name = "image")
    private String image;   // 图片

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
