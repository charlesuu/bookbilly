package com.group.bookbillyapp.service.user;

import com.group.bookbillyapp.dto.user.request.UserCreateRequest;
import com.group.bookbillyapp.dto.user.request.UserUpdateRequest;
import com.group.bookbillyapp.dto.user.response.UserResponse;
import com.group.bookbillyapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UserService {
    UserRepository userRepository;//OCP원칙을 지키기 위해 여기서도 주입식으로 변환.

    public UserService(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserRepository(jdbcTemplate);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUserResponses();
    }

    public void updateUser(UserUpdateRequest request) {
        if (userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUserByName(name);
    }
}
