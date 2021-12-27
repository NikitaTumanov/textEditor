package com.example.texteditor.server.controller;

import com.example.texteditor.server.model.File;
import com.example.texteditor.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    private FileService fileService;

    @MessageMapping("/chat")
    @SendTo("/topic/chatRoom")
    public File changeFile(@Payload File file) {
        fileService.create(file);
        return file;
    }
}