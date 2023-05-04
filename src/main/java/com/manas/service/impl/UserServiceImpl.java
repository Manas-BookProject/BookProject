package com.manas.service.impl;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.dto.response.UserResponse;
import com.manas.entity.Account;
import com.manas.entity.User;
import com.manas.exceptions.NotFoundException;
import com.manas.repository.AccountRepository;
import com.manas.repository.UserRepository;
import com.manas.service.UserService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers() ;
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
    @Transactional
    @Override
    public SimpleResponse updateUser(Long id, RegisterRequest registerRequest) {
        User user = userRepository.findById(id).get();
        Account account = user.getAccount();
        account.setEmail(registerRequest.email());
        account.setPassword(registerRequest.password());
        user.setAccount(account);
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setPhoneNumber(registerRequest.phoneNumber());
        account.setUser(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .description("The user update successfully!")
                .build();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
