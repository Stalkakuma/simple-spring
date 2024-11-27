package lt.vidunas.simple_spring.services;


import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.exceptions.UserNotFoundException;
import lt.vidunas.simple_spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User validateAndGetUser(String username) {
        return getUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
