package tec.dk.WKPasswordVault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.dk.WKPasswordVault.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
