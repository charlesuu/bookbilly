package com.group.bookbillyapp.controller;

import com.group.bookbillyapp.domain.user.User;
import com.group.bookbillyapp.domain.user.UserRepository;
import com.group.bookbillyapp.dto.user.response.UserResponse;
import com.group.bookbillyapp.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class IndexController {
    UserService userService;
    UserRepository userRepository;

    public IndexController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/user/save")
    public String userSave() {
        return "user-save";
    }

    @GetMapping("/user/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("user", new UserResponse(id, user));

        return "user-update";
    }

}
