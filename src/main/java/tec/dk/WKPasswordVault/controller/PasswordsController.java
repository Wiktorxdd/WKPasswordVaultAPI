package tec.dk.WKPasswordVault.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tec.dk.WKPasswordVault.model.Passwords;
import tec.dk.WKPasswordVault.model.User;
import tec.dk.WKPasswordVault.repository.PasswordRepository;
import tec.dk.WKPasswordVault.repository.UserRepository;
import tec.dk.WKPasswordVault.service.encryptionService;

import java.util.List;

@RestController
@RequestMapping("/password")
public class PasswordsController {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    public PasswordsController(UserRepository userRepository, PasswordRepository passwordRepository) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @PostMapping(path ="/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void register(@RequestParam("password") String password, @RequestParam("userId") int userId) {
        Passwords passwords = new Passwords();
        password = encryptionService.encrypt(password);
        passwords.setPassword(password);

        User user = userRepository.findById(userId).get();
        if (user == null) return;

        passwords.setUser(user);
        passwordRepository.save(passwords);
    }

    @GetMapping("/{id}")
    Passwords read(@PathVariable int id) {
        return passwordRepository.findById(id).get();
    }

    @GetMapping()
    List<Passwords> readAll() {
        return passwordRepository.findAll();
    }
}
