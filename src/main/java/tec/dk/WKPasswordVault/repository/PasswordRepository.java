package tec.dk.WKPasswordVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.dk.WKPasswordVault.model.Passwords;
import tec.dk.WKPasswordVault.model.User;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Passwords, Integer> {
    List<Passwords> findByUser(User user);
}
