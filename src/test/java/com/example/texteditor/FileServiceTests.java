package com.example.texteditor;

import com.example.texteditor.server.model.File;
import com.example.texteditor.server.repository.FileRepository;
import com.example.texteditor.server.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FileServiceTests {
    @InjectMocks
    private FileService fileService;
    @Mock
    private FileRepository fileRepository;
    @Captor
    private ArgumentCaptor<File> captor;

    private File file1, file2, file3;
    @BeforeEach
    void init() {
        file1 = new File();
        file1.setId(1L);
        file1.setName("First");
        file1.setPassword("qwerty");
        file1.setFile("");
        file1.setUserId(1L);

        file2 = new File();
        file2.setId(2L);
        file2.setName("Second");
        file2.setPassword("qwerty");
        file2.setFile("");
        file2.setUserId(1L);

        file3 = new File();
        file3.setId(3L);
        file3.setName("Third");
        file3.setPassword("qwerty");
        file3.setFile("");
        file3.setUserId(1L);
    }
    @Test
    void getFilesById() {
        Mockito.when(fileRepository.findByUserId(1L)).thenReturn(List.of(file1, file2, file3));
        Mockito.when(fileRepository.findById(3L)).thenReturn(file3);
        assertEquals(List.of(file1, file2, file3), fileRepository.findByUserId(1L));
        assertEquals(file3, fileRepository.findById(3L));
    }
    @Test
    void getAllFiles() {
        Mockito.when(fileRepository.findAll()).thenReturn(List.of(file1, file2, file3));
        assertEquals(List.of(file1, file2, file3), fileRepository.findAll());
    }
    @Test
    void deleteFile() {
        fileService.delete(1L);
        Mockito.verify(fileRepository).deleteById(1L);
    }
    @Test
    void addFile() {
        fileService.create(file3);
        Mockito.verify(fileRepository).save(captor.capture());
        File captured = captor.getValue();
        assertEquals("Third", captured.getName());
    }
}