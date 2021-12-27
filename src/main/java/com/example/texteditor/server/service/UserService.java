package com.example.texteditor.server.service;

import com.example.texteditor.server.model.User;
import com.example.texteditor.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByName(s);
        return u;
    }

    public void create(String name, String pass) {
        User u = new User();
        u.setName(name);
        u.setPassword(bCryptPasswordEncoder.encode(pass));
        userRepository.save(u);
    }

    public void saveChanges(User user) {
        userRepository.save(user);
    }

    public void delete(String Name) {
        userRepository.deleteByName(Name);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }
}