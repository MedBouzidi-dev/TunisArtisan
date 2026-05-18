package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    User findByUserNameIgnoreCase(String userName);
    User findByEmailIgnoreCase(String email);
}
