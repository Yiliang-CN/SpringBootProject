package cn.gxust.springboot.service;

import cn.gxust.springboot.dto.UserDTO;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.vo.UserVO;

public interface UserService {

    UserVO getUserById(Integer id);

    int addUser(UserDTO userDTO);

    void deleteUserById(Integer id);

    UserVO updateUserById(Integer id, String name, String gander, String birthday);
}
