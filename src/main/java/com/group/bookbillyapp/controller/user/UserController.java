package com.group.bookbillyapp.controller.user;


import com.group.bookbillyapp.dto.user.request.UserCreateRequest;
import com.group.bookbillyapp.dto.user.request.UserUpdateRequest;
import com.group.bookbillyapp.dto.user.response.UserResponse;
import com.group.bookbillyapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {//userServiceV2가 primary
        this.userService = userService;
    }

    @PostMapping("/user")
    public Long saveUser(@RequestBody UserCreateRequest request) {
        return userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

















