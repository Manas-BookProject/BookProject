package com.manas.service;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.dto.response.UserResponse;
import com.manas.entity.User;
import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

//    SimpleResponse getProfile(ProfileRequest request);

//    ProfileResponse getTransactions(String email);

}
