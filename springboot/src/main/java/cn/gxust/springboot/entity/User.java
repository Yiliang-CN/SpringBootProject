package cn.gxust.springboot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user")
@DynamicInsert // 只插入非 NULL 字段，让数据库处理默认值
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // 编号id

    @Column(name = "name")
    private String name;    // 用户名

    @Column(name = "password")
    private String password;    // 密码

    @Column(name = "gender")
    private String gender;      // 性别

    @Column(name = "birthday")
    private String birthday;    // 生日

    @Column(name = "phone")
    private String phone;       // 手机号

    @Column(name = "image")
    private String image;       // 头像

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
