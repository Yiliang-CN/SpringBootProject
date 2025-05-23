package cn.gxust.springboot.service;

import cn.gxust.springboot.dto.UserCreateDTO;
import cn.gxust.springboot.dto.UserQueryDTO;
import cn.gxust.springboot.dto.UserUpdateDTO;
import cn.gxust.springboot.vo.UserVO;

public interface UserService {

    UserVO getUserById(Integer id);

    UserVO login(UserQueryDTO userQueryDTO);

    Integer register(UserCreateDTO userCreateDTO);

    Integer deleteUserById(Integer id);

    UserVO updateUserById(Integer id, UserUpdateDTO userUpdateDTO);

}
