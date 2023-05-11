package com.manas.service;

import com.manas.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

//    SimpleResponse getProfile(ProfileRequest request);

//    ProfileResponse getTransactions(String email);

}
