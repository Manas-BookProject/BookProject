package com.manas.service;

import com.manas.dto.request.RegisterRequest;
import com.manas.dto.request.UserRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.dto.response.UserTokenResponse;
import com.manas.entity.User;

import java.util.List;

public interface UserService {


    List<User> findAllUsers();
}
