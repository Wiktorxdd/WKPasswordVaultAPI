package tec.dk.WKPasswordVault.controller;

import org.springframework.web.bind.annotation.*;
import tec.dk.WKPasswordVault.model.Passwords;
import tec.dk.WKPasswordVault.model.User;
import tec.dk.WKPasswordVault.repository.PasswordRepository;
import tec.dk.WKPasswordVault.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    public UserController(UserRepository userRepository, PasswordRepository passwordRepository) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @PostMapping()
    void create(@RequestBody User user) {
        userRepository.save(user);
    }
    @PostMapping(path ="/register", consumes = "application/json;charset=UTF-8")
    void register(@RequestBody User user) {
        userRepository.save(user);
    }
    @PostMapping("/login")
    User login(@RequestBody User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @GetMapping("/{id}")
    Map<String, Object> read(@PathVariable int id) {
        User user = userRepository.findById(id).get();
        List<Passwords> passwords = passwordRepository.findByUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("passwords", passwords);
        return response;
    }

    @GetMapping()
    List<User> readAll() {
        return userRepository.findAll();
    }
}