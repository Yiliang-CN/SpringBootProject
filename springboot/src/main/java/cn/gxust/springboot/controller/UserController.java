package cn.gxust.springboot.controller;

import cn.gxust.springboot.dto.Response;
import cn.gxust.springboot.dto.UserDTO;
import cn.gxust.springboot.entity.User;
import cn.gxust.springboot.service.UserService;
import cn.gxust.springboot.vo.UserVO;
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
    @GetMapping("/user/{id}")
    public Response<UserVO> getUserById(@PathVariable Integer id) {
        if (id == null || id < 100000000) {
            return Response.fail(400, "用户ID不能小于100000000");
        }

        UserVO user = userService.getUserById(id);
        if (user == null) {
            return Response.notFound("用户不存在");
        }

        return Response.success(user);
    }


    @PostMapping("/user")
    public Response<Integer> addUser(@RequestBody UserDTO userDTO) {
        return Response.success(userService.addUser(userDTO));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public Response<UserVO> updateUserById(@PathVariable Integer id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String gander,
                                           @RequestParam(required = false) String birthday) {
        return Response.success(userService.updateUserById(id, name, gander, birthday));
    }
}
