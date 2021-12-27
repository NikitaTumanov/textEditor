package com.example.texteditor.server.service;

import com.example.texteditor.server.model.File;
import com.example.texteditor.server.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private FileRepository fileRepository;


    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void create(File file) {
        fileRepository.save(file);
    }

    public List<File> readAll() {
        return fileRepository.findAll();
    }

    public File findById(long id) {
        return fileRepository.findById(id);
    }

    public File findByNamePassAuthor(String name, String password, long id) {
        return fileRepository.findByNameAndPasswordAndUserId(name, password, id);
    }

    public File findByName(String name) {
        if(fileRepository.findAllByName(name).isPresent()){
            return fileRepository.findAllByName(name).get();
        }
        else {
            return null;
        }
    }

    public List<File> findByUser(long id){
        return fileRepository.findByUserId(id);
    }

    public File findByUserAndName(long id, String name){
        if(fileRepository.findByUserIdAndName(id, name).isPresent()){
            return fileRepository.findByUserIdAndName(id, name).get();
        }
        else {
            return null;
        }
    }

    public void delete(long id) {
        fileRepository.deleteById(id);
    }
}