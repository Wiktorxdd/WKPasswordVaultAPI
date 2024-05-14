package tec.dk.WKPasswordVault.controller;

import org.springframework.http.MediaType;
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

    @PostMapping(path ="/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void register(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @PostMapping(path = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
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