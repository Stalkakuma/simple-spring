package lt.vidunas.simple_spring.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.vidunas.simple_spring.config.SimpleSecurityConfig;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userService.getAllUsers().isEmpty()) {
            return;
        }
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });
        log.info("Database initialized");

    }

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin@gmail.com", "admin", SimpleSecurityConfig.ADMIN),
            new User("user", "user@gmail.com", "user", SimpleSecurityConfig.USER)
    );
}
