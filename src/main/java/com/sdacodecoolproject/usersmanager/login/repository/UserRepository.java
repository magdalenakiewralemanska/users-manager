package com.sdacodecoolproject.usersmanager.login.repository;


import com.sdacodecoolproject.usersmanager.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
