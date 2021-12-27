package com.example.texteditor.server.repository;

import com.example.texteditor.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Long deleteByName(String name);
    User findByName(String name);
    User findById(long id);
}