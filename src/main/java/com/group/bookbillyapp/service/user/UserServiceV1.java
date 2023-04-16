package com.group.bookbillyapp.service.user;

import com.group.bookbillyapp.domain.user.UserRepository;
import com.group.bookbillyapp.dto.user.request.UserCreateRequest;
import com.group.bookbillyapp.dto.user.request.UserUpdateRequest;
import com.group.bookbillyapp.dto.user.response.UserResponse;
import com.group.bookbillyapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceV1 implements UserService {
    UserJdbcRepository userJdbcRepository;//주입식으로 변환

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    @Transactional
    public Long saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
        return 0L;//serviceV1쓰게 되면 생성된 유저 아이디 반환하도록 변경할 것.(안 해도 기능에는 문제 없음)
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUserResponses();
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (userJdbcRepository.isUserNotExist(id)) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUserById(id);
    }
}
