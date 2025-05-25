package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.UserConverter;
import cn.gxust.springboot.dto.UserCreateDTO;
import cn.gxust.springboot.dto.UserQueryDTO;
import cn.gxust.springboot.dto.UserUpdateDTO;
import cn.gxust.springboot.dao.UserRepository;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.service.UserService;
import cn.gxust.springboot.utils.UserValidator;
import cn.gxust.springboot.vo.UserVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUserById(Integer id) {
        // 验证用户ID格式
        if (!UserValidator.isValidId(id)) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 获取用户信息
        User userInDB = userRepository.findById(id).orElseThrow(RuntimeException::new);

        return UserConverter.convertToUserVO(userInDB);
    }

    @Override
    public UserVO login(UserQueryDTO userQueryDTO) {
        // 验证手机号格式
        if (!UserValidator.isValidPhone(userQueryDTO.getPhone())) {
            throw new IllegalStateException("手机号格式不正确");
        }

        // 验证密码格式
        if (!UserValidator.isValidPassword(userQueryDTO.getPassword())) {
            throw new IllegalStateException("用户密码格式不正确");
        }

        // 验证用户手机号是否已存在
        User userInDB = userRepository.findByPhone(userQueryDTO.getPhone());
        if (userInDB == null) {
            throw new IllegalStateException("用户手机号不存在: " + userQueryDTO.getPhone());
        }

        // 验证密码
        if (!Objects.equals(userQueryDTO.getPassword(), userInDB.getPassword())) {
            throw new IllegalStateException("用户密码错误");
        }

        return UserConverter.convertToUserVO(userInDB);
    }

    @Override
    @Transactional  // 添加新用户属于写操作 要添加事务 保证数据一致性
    public Integer register(UserCreateDTO userCreateDTO) {
        // 验证手机号格式
        if (!UserValidator.isValidPhone(userCreateDTO.getPhone())) {
            throw new IllegalStateException("手机号格式不正确");
        }

        // 验证密码格式
        if (!UserValidator.isValidPassword(userCreateDTO.getPassword())) {
            throw new IllegalStateException("用户密码格式不正确");
        }

        // 验证用户手机号是否已存在
        if (userRepository.existsByPhone(userCreateDTO.getPhone())) {
            throw new IllegalStateException("用户手机号已存在: " + userCreateDTO.getPhone());
        }

        // 设置用户数据 设置默认用户名
        User user = UserConverter.convertToUser(userCreateDTO);
        user.setName("新用户" + userCreateDTO.getPhone());

        // 保存用户信息
        userRepository.save(user);

        return user.getId();
    }

    @Override
    @Transactional
    public Integer deleteUserById(Integer id) {
        // 验证用户ID
        if (!UserValidator.isValidId(id)) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 验证用户信息是否存在
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("用户: " + id + " 不存在"));

        // 删除用户信息
        userRepository.deleteById(id);

        return userInDB.getId();
    }

    @Override
    @Transactional  // 操作失败时回滚数据
    public UserVO updateUserById(Integer id, UserUpdateDTO userUpdateDTO) {
        // 验证用户ID
        if (!UserValidator.isValidId(id)) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 验证用户名格式
        if (!UserValidator.isValidName(userUpdateDTO.getName())) {
            throw new IllegalStateException("用户名格式不正确");
        }

//        // 验证密码格式
//        if (!UserValidator.isValidPassword(userUpdateDTO.getPassword())) {
//            throw new IllegalStateException("用户密码格式不正确");
//        }

        // 验证性别
        if (!UserValidator.isValidGender(userUpdateDTO.getGender())) {
            throw new IllegalStateException("用户性别格式不正确");
        }

        // 验证生日格式
        if (!UserValidator.isValidBirthday(userUpdateDTO.getBirthday())) {
            throw new IllegalStateException("用户生日格式不正确");
        }

        // 验证图片URL
        if (!UserValidator.isValidUrl(userUpdateDTO.getImage())) {
            throw new IllegalStateException("图片URL格式不正确");
        }

        // 验证用户信息是否存在
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("用户: " + id + " 不存在"));

        // 更新用户信息 信息不同时才更新
        // 检验用户名
        if (!Objects.equals(userUpdateDTO.getName(), userInDB.getName())) {
            userInDB.setName(userUpdateDTO.getName());
        }
//        // 检验密码
//        if (!Objects.equals(userUpdateDTO.getPassword(), userInDB.getPassword())) {
//            userInDB.setPassword(userUpdateDTO.getPassword());
//        }
        // 检验性别
        if (!Objects.equals(userUpdateDTO.getGender(), userInDB.getGender())) {
            userInDB.setGender(userUpdateDTO.getGender());
        }
        // 检验生日
        if (!Objects.equals(userUpdateDTO.getBirthday(), userInDB.getBirthday())) {
            userInDB.setBirthday(userUpdateDTO.getBirthday());
        }
        // 检验头像
        if (!Objects.equals(userUpdateDTO.getImage(), userInDB.getImage())) {
            userInDB.setImage(userUpdateDTO.getImage());
        }

        // 更新用户信息到数据库
        User user = userRepository.save(userInDB);

        return UserConverter.convertToUserVO(user);
    }

}
