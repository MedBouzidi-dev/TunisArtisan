package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.User;
import java.util.List;

public interface IUserService {
    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

