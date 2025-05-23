package cn.gxust.springboot.service.impl;

import cn.gxust.springboot.converter.UserConverter;
import cn.gxust.springboot.dto.UserCreateDTO;
import cn.gxust.springboot.dto.UserQueryDTO;
import cn.gxust.springboot.dto.UserUpdateDTO;
import cn.gxust.springboot.dao.UserRepository;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.service.UserService;
import cn.gxust.springboot.vo.UserVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUserById(Integer id) {
        // 验证用户ID
        if (id == null || id < 100000000) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 获取用户信息
        User userInDB = userRepository.findById(id).orElseThrow(RuntimeException::new);

        return UserConverter.convertToUserVO(userInDB);
    }

    @Override
    public UserVO login(UserQueryDTO userQueryDTO) {
        // 验证用户信息
        if (!StringUtils.hasText(userQueryDTO.getPhone()) ||
                !StringUtils.hasText(userQueryDTO.getPassword())) {
            throw new IllegalStateException("用户登录信息不完整");
        }

        // 验证手机号格式
        if (!userQueryDTO.getPhone().matches("^1[3-9][0-9]{9}$")) {
            throw new IllegalStateException("手机号格式不正确");
        }

        // 验证用户手机号是否已存在
        User userInDB = userRepository.findByPhone(userQueryDTO.getPhone());
        if(userInDB == null){
            throw new IllegalStateException("用户手机号不存在: " + userQueryDTO.getPhone());
        }

        // 验证密码
        if(!Objects.equals(userQueryDTO.getPassword(), userInDB.getPassword())){
            throw new IllegalStateException("用户密码错误");
        }

        return UserConverter.convertToUserVO(userInDB);
    }

    @Override
    @Transactional  // 添加新用户属于写操作 要添加事务 保证数据一致性
    public Integer register(UserCreateDTO userCreateDTO) {
        // 验证用户信息
        if (!StringUtils.hasText(userCreateDTO.getPhone()) || !StringUtils.hasText(userCreateDTO.getPassword())) {
            throw new IllegalStateException("用户信息不完整");
        }
        // 验证手机号格式
        if (!userCreateDTO.getPhone().matches("^1[3-9][0-9]{9}$")) {
            throw new IllegalStateException("手机号格式不正确");
        }
        // 验证密码格式
        if (userCreateDTO.getPassword().length() < 6 || userCreateDTO.getPassword().length() > 20) {
            throw new IllegalStateException("密码长度在6-20之间");
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
        if (id == null || id < 100000000) {
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
        if (id == null || id < 100000000) {
            throw new IllegalStateException("用户ID长度在9-10之间");
        }

        // 验证用户名格式
        if (!StringUtils.hasText(userUpdateDTO.getName()) ||
                userUpdateDTO.getName().isEmpty() ||
                userUpdateDTO.getName().length() > 16) {
            throw new IllegalStateException("用户名长度在1-16之间");
        }

        // 验证密码格式
        if (!StringUtils.hasText(userUpdateDTO.getPassword()) ||
                userUpdateDTO.getPassword().length() < 6 ||
                userUpdateDTO.getPassword().length() > 20) {
            throw new IllegalStateException("密码长度在6-20之间");
        }

        // 验证性别
        if (!StringUtils.hasText(userUpdateDTO.getGender()) ||
                !userUpdateDTO.getGender().matches("男|女|保密")) {
            throw new IllegalStateException("性别只能是男、女或保密");
        }

        // 验证生日格式
        if (!StringUtils.hasText(userUpdateDTO.getBirthday()) ||
                !userUpdateDTO.getBirthday().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalStateException("生日格式为yyyy-MM-dd");
        }

        // 验证图片URL
        if (!StringUtils.hasText(userUpdateDTO.getImage())) {
            throw new IllegalStateException("图片URL格式错误");
        }

        // 验证用户信息是否存在
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("用户: " + id + " 不存在"));

        // 更新用户信息 信息不同时才更新
        // 检验用户名
        if (!Objects.equals(userUpdateDTO.getName(), userInDB.getName())) {
            userInDB.setName(userUpdateDTO.getName());
        }
        // 检验密码
        if (!Objects.equals(userUpdateDTO.getPassword(), userInDB.getPassword())) {
            userInDB.setPassword(userUpdateDTO.getPassword());
        }
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
