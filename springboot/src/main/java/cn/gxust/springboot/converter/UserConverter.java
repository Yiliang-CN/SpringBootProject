package cn.gxust.springboot.converter;

import cn.gxust.springboot.dto.UserDTO;
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

    public static User convertUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        return user;
    }
}
