package cn.gxust.springboot.converter;

import cn.gxust.springboot.dto.UserCreateDTO;
import cn.gxust.springboot.dto.UserUpdateDTO;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.vo.UserVO;

public class UserConverter {

    public static UserVO convertUser(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setGender(user.getGender());
        userVO.setBirthday(user.getBirthday());
        userVO.setPhone(user.getPhone());
        userVO.setImage(user.getImage());
        return userVO;
    }

    public static User convertUserCreateDTO(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setPassword(userCreateDTO.getPassword());
        user.setPhone(userCreateDTO.getPhone());
        return user;
    }

    public static User convertUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setName(userUpdateDTO.getName());
        user.setPassword(userUpdateDTO.getPassword());
        user.setGender(userUpdateDTO.getGender());
        user.setBirthday(userUpdateDTO.getBirthday());
        user.setImage(userUpdateDTO.getImage());
        return user;
    }
}
