package com.stackroute.wipro.backend.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.wipro.backend.User.model.UserData;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData,Long> {

    public UserData findByEmail(String email);
    public UserData findByUserName(String username);

}
