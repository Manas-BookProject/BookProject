package com.manas.repository;

import com.manas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> getUserById(Long id);

}
