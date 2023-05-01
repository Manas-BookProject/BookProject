package com.manas.service;
import com.manas.dto.request.RegisterRequest;
import com.manas.entity.User;
import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User updateUser(Long id, User user);
    User getUserById(Long id);

    void deleteUserById(Long id);
}
