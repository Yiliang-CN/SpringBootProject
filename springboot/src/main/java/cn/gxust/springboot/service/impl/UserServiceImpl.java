package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.UserConverter;
import cn.gxust.springboot.dao.UserRepository;
import cn.gxust.springboot.dto.UserDTO;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.service.UserService;
import cn.gxust.springboot.vo.UserVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return UserConverter.convertUser(user);
    }

    @Override
    public int addUser(UserDTO userDTO) {
        List<User> userList = userRepository.findByPhone(userDTO.getPhone());
        if (!CollectionUtils.isEmpty(userList)) {
            throw new IllegalStateException("The Phone: " + userDTO.getPhone() + " has been used");
        }
        User user = userRepository.save(UserConverter.convertUserDTO(userDTO));
        return user.getId();
    }

    @Override
    public void deleteUserById(Integer id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new IllegalStateException("The User: " + id + " not found"));
        userRepository.deleteById(id);
    }

    @Override
    @Transactional  // 操作失败时回滚数据
    public UserVO updateUserById(Integer id, String name, String gender, String birthday) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("The User: " + id + " not found"));
        if(StringUtils.hasLength(name) && !userInDB.getName().equals(name)){
            userInDB.setName(name);
        }
        if(StringUtils.hasLength(gender) && !userInDB.getGender().equals(gender)){
            userInDB.setGender(gender);
        }
        if(StringUtils.hasLength(birthday) && !birthday.equals(userInDB.getBirthday())){
            userInDB.setBirthday(birthday);
        }
        User user = userRepository.save(userInDB);
        return UserConverter.convertUser(user);
    }

}
