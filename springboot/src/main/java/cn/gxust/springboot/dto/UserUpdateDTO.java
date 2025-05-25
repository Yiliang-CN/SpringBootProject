package cn.gxust.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
    @NotBlank
    @Size(min = 1, max = 16, message = "用户名长度在1-16之间")
    private String name;

//    @NotBlank
//    @Size(min = 6, max = 20, message = "密码长度在6-20之间")
//    private String password;

    @NotBlank
    @Pattern(regexp = "男|女|保密", message = "性别只能是男、女或保密")
    private String gender;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "生日格式为yyyy-MM-dd")
    private String birthday;

    @NotBlank
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
