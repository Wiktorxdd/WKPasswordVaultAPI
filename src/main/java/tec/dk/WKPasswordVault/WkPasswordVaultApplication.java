package tec.dk.WKPasswordVault;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tec.dk.WKPasswordVault.model.Passwords;
import tec.dk.WKPasswordVault.model.User;
import tec.dk.WKPasswordVault.repository.PasswordRepository;
import tec.dk.WKPasswordVault.repository.UserRepository;

import java.util.Arrays;

@SpringBootApplication
public class WkPasswordVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(WkPasswordVaultApplication.class, args);
	}


	@Bean
	public CommandLineRunner demoData(UserRepository userRepository, PasswordRepository passwordRepository) {
		return args -> {

			User user1 = new User();
			user1.setUsername("user1");
			user1.setPassword("password1");

			Passwords password1 = new Passwords();
			password1.setPassword("password1");

			Passwords password2 = new Passwords();
			password2.setPassword("password2");


			user1.setPasswords(Arrays.asList(password1, password2));


			password1.setUser(user1);
			password2.setUser(user1);


			userRepository.save(user1);
		};
	}
}
