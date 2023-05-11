package com.manas.repository;

import com.manas.dto.response.UserResponse;
import com.manas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.manas.dto.response.UserResponse(" +
            "u.id,u.firstName,u.lastName,u.phoneNumber,u.account.email) " +
            "from User u")
    List<UserResponse> getAllUsers();

}
