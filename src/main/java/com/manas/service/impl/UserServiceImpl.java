package com.manas.service.impl;

import com.manas.config.jwt.JwtUtil;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.request.UserRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.dto.response.UserTokenResponse;
import com.manas.entity.Account;
import com.manas.entity.User;
import com.manas.enums.Role;
import com.manas.exceptions.AlreadyExistException;
import com.manas.exceptions.NotFoundException;
import com.manas.repository.UserRepository;
import com.manas.service.UserService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public SimpleResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByAccount_Email(registerRequest.email())){
            log.error(String.format("User with login: %s is exists", registerRequest.email()));
            throw new AlreadyExistException(String.format(
                    "User with login: %s is exists", registerRequest.email()));
        }
        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .phoneNumber(registerRequest.phoneNumber())
                .account(Account.builder()
                                .email(registerRequest.email())
                                .password(passwordEncoder.encode(registerRequest.password()))
                                .role(Role.CUSTOMER).build()
                        ).build();
        userRepository.save(user);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .description("You have registered successfully!")
                .build();
    }
    @Override
    public UserTokenResponse authenticate(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userRequest.login(),
                        userRequest.password())
        );

        User user = userRepository.findUserByAccount_Email(userRequest.login())
                .orElseThrow(()->{
                    log.error(String.format("User %s is not found!", userRequest.login()));
                    return new AlreadyExistException(String.format("User %s is not found!", userRequest.login()));
                });

        return UserTokenResponse.builder()
                .login(userRequest.login())
                .token(jwtUtil.generateToken(user.getAccount()))
                .build();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(()-> new NotFoundException(
                        String.format("User with id %d not found", userId)
                ));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll() ;
    }

}
