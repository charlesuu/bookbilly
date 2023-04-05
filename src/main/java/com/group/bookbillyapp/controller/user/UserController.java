package com.group.bookbillyapp.controller.user;


import com.group.bookbillyapp.domain.user.User;
import com.group.bookbillyapp.dto.user.request.UserCreateRequest;
import com.group.bookbillyapp.dto.user.request.UserUpdateRequest;
import com.group.bookbillyapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user(name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        String readSql = "select * from user where id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0,request.getId()).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String updateSql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void updateUser(@RequestParam String name) {
        String readSql = "select * from user where name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String deleteSql = "DELETE FROM user WHERE name  = ?";
        jdbcTemplate.update(deleteSql, name);
    }
}

















