package com.example.texteditor.server.repository;

import com.example.texteditor.server.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findById(long id);
    Optional<File> findAllByName(String name);
    void deleteById(long id);
    List<File> findByUserId(long id);
    Optional<File> findByUserIdAndName(long id, String name);
    File findByNameAndPasswordAndUserId(String name, String password, long id);
}