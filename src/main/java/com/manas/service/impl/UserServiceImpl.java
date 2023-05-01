package com.manas.service.impl;
import com.manas.dto.request.RegisterRequest;
import com.manas.entity.Account;
import com.manas.entity.User;
import com.manas.exceptions.NotFoundException;
import com.manas.repository.AccountRepository;
import com.manas.repository.UserRepository;
import com.manas.service.UserService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll() ;
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User user1 = userRepository.findById(id).get();
        user1.setId(user.getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.getAccount().setEmail(user.getAccount().getEmail());
        user1.getAccount().setPassword(user.getAccount().getPassword());
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
