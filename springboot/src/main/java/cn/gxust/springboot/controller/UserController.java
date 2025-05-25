package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.dto.UserCreateDTO;
import cn.gxust.springboot.dto.UserQueryDTO;
import cn.gxust.springboot.dto.UserUpdateDTO;
import cn.gxust.springboot.service.UserService;
import cn.gxust.springboot.vo.UserVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取指定用户信息
     *
     * @param id 用户ID
     * @return 指定用户信息
     */
    @GetMapping("/users/{id}")
    public Response<UserVO> getUserById(@PathVariable
                                        @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                        @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                        Integer id) {
        return Response.success(userService.getUserById(id));
    }

    /**
     * 用户登录
     *
     * @param userQueryDTO 用户信息
     * @return 用户信息
     */
    @PostMapping("/users/login")
    public Response<UserVO> login(@RequestBody @Valid UserQueryDTO userQueryDTO) {
        return Response.success(userService.login(userQueryDTO));
    }

    /**
     * 用户注册
     *
     * @param userCreateDTO 用户信息
     * @return 新用户的ID
     */
    @PostMapping("/users/register")
    public Response<Integer> register(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return Response.success(userService.register(userCreateDTO));
    }

    /**
     * 删除指定用户信息
     *
     * @param id 用户ID
     * @return 被删除的用户ID
     */
    @DeleteMapping("/users/{id}")
    public Response<Integer> deleteUserById(@PathVariable
                                            @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                            @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                            Integer id) {
        return Response.success(userService.deleteUserById(id));
    }

    /**
     * 更新指定用户信息
     *
     * @param id            用户ID
     * @param userUpdateDTO 用户信息
     * @return 更新后的用户信息
     */
    @PostMapping("/users/{id}")
    public Response<UserVO> updateUserById(@PathVariable
                                           @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                           @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                           Integer id,
                                           @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return Response.success(userService.updateUserById(id, userUpdateDTO));
    }
}
