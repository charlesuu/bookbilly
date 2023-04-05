package com.group.bookbillyapp.dto.user.request;

import javax.persistence.criteria.CriteriaBuilder;

public class UserUpdateRequest {
    private String name;
    private long id;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
