package lt.vidunas.simple_spring.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.config.SimpleSecurityConfig;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.exceptions.DuplicatedUserInfoException;
import lt.vidunas.simple_spring.rest.dto.AuthResponse;
import lt.vidunas.simple_spring.rest.dto.LoginRequest;
import lt.vidunas.simple_spring.rest.dto.SignUpRequest;
import lt.vidunas.simple_spring.security.TokenProvider;
import lt.vidunas.simple_spring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException("Username already exists");
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException("Email already exists");
        }

        userService.saveUser(mapSignUpRequestToUser(signUpRequest));
        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        return new AuthResponse(token);
    }


    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generateToken(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(SimpleSecurityConfig.USER);
        return user;
    }
}
