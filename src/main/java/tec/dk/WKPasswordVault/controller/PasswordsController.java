package tec.dk.WKPasswordVault.controller;

import org.springframework.web.bind.annotation.*;
import tec.dk.WKPasswordVault.model.Passwords;
import tec.dk.WKPasswordVault.repository.PasswordRepository;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordsController {
    PasswordRepository passwordRepository;

    public PasswordsController(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @PostMapping()
    void create(@RequestBody Passwords password) {
        passwordRepository.save(password);
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
