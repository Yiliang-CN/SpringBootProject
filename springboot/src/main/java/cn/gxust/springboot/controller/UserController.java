package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.dto.UserCreateDTO;
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
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息包装类UserVO
     */
    @GetMapping("/users/{id}")
    public Response<UserVO> getUserById(@PathVariable
                                        @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                        @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                        Integer id) {
        return Response.success(userService.getUserById(id));
    }

    /**
     * 添加新用户
     *
     * @param userCreateDTO 用户信息
     * @return 用户ID
     */
    @PostMapping("/users")
    public Response<Integer> addUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return Response.success(userService.addUser(userCreateDTO));
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 用户ID
     */
    @DeleteMapping("/users/{id}")
    public Response<Integer> deleteUserById(@PathVariable
                                            @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                            @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                            Integer id) {
        return Response.success(userService.deleteUserById(id));
    }

    /**
     * 更新用户信息
     *
     * @param id            用户ID
     * @param userUpdateDTO 用户信息
     * @return 用户信息包装类UserVO
     */
    @PutMapping("/users/{id}")
    public Response<UserVO> updateUserById(@PathVariable
                                           @Min(value = 100000000, message = "用户ID长度在9-10之间")
                                           @Max(value = 2147483647, message = "用户ID长度在9-10之间")
                                           Integer id,
                                           @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return Response.success(userService.updateUserById(id, userUpdateDTO));
    }
}
