package com.manas.service.impl;
import com.manas.entity.User;
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
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll() ;
    }

}
