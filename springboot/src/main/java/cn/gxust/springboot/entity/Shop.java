package cn.gxust.springboot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@Entity
@Table(name = "shops")
@DynamicInsert  // 只插入非 NULL 字段 让数据库处理默认值
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // 编号id

    @Column(name = "name")
    private String name;        // 店铺名

    @Column(name = "score")
    private double score;       // 评分

    @Column(name = "sales")
    private int sales;          // 销量

    @Column(name = "addr")
    private String addr;        // 地址

    @Column(name = "phone")
    private String phone;       // 手机号

    @Column(name = "image")
    private String image;       // 图片

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
