package com.test.repository;

import com.test.entity.User;

public interface UserRepository {
    public int add(User user);
    public int delete(Integer id);
    public int update(User user);
    public User getById(Integer id);

}
