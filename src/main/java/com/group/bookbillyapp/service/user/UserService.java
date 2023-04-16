package com.group.bookbillyapp.service.user;

import com.group.bookbillyapp.dto.user.request.UserCreateRequest;
import com.group.bookbillyapp.dto.user.request.UserUpdateRequest;
import com.group.bookbillyapp.dto.user.response.UserResponse;

import java.util.List;

public interface UserService {
    public Long saveUser(UserCreateRequest request);

    public List<UserResponse> getUsers();

    public void updateUser(UserUpdateRequest request);

    public void deleteUser(Long id);
}
