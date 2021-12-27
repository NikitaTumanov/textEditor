package com.example.texteditor.server.controller;

import com.example.texteditor.server.model.File;
import com.example.texteditor.server.service.FileService;
import com.example.texteditor.server.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping(value = "/myfiles")
    public String takenByUser(Authentication authentication, Model model) {
        model.addAttribute("files", fileService.findByUser
                (userService.findByName(authentication.getName()).getId()));

        model.addAttribute("myName", authentication.getName());
        return "/files";
    }

    @GetMapping(value = "/myfiles/takebyname")
    public String takenByUserAndName(Authentication authentication, @RequestParam String name, Model model) {
        model.addAttribute("files", fileService.findByUserAndName
                (userService.findByName(authentication.getName()).getId(), name));

        model.addAttribute("myName", authentication.getName());
        return "myfiles/takebyname";
    }

    @SneakyThrows
    @PostMapping(path = "/myfiles")
    public String addToBasket(Authentication authentication, Long id, String name, String password, String author,
                              @RequestParam String action, Model model) {
        if ("delete".equals(action)) {
            fileService.delete(id);
        }
        else if ("create".equals(action)){
            File file = new File();

            file.setName(name);

            int count = 1;
            String staticName = file.getName();
            while (fileService.findByUserAndName(userService.findByName(authentication.getName())
                    .getId(),file.getName())!=null){
                file.setName(staticName);
                file.setName(file.getName()+"("+count+")");
                count+=1;
            }

            file.setPassword(bCryptPasswordEncoder.encode(password));
            file.setFile("");
            file.setUserId(userService.findByName(authentication.getName()).getId());
            fileService.create(file);
        }
        else if ("open".equals(action)) {
            model.addAttribute("file", fileService.findByNamePassAuthor
                    (name, password, userService.findByName(author).getId()));
            model.addAttribute("myName", authentication.getName());
            return "/editor";
        }
        model.addAttribute("files", fileService.findByUser
                (userService.findByName(authentication.getName()).getId()));
        model.addAttribute("myName", authentication.getName());
        return "/files";
    }

    @GetMapping("/sign")
    public String index() {
        return "signup";
    }

    @GetMapping(path = "/")
    public String index2(Authentication authentication, Model model) {
        model.addAttribute("myName", authentication.getName());
        return "signup";
    }

    @PostMapping(path = "/signUperror")
    public String SignUp(@RequestParam String username, String password, String password2, Model model) {
        if (!password.equals(password2)) {
            model.addAttribute("Status", "pass1!=pass2");
            return "signup";
        } else {
            if (userService.loadUserByUsername(username) != null) {
                model.addAttribute("Status", "user_exists");
                return "signup";
            } else {
                userService.create(username, password);
                return "redirect:/";
            }
        }
    }
}
