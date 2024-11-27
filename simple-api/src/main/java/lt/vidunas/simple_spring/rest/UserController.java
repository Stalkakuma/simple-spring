package lt.vidunas.simple_spring.rest;


import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.mapper.UserMapper;
import lt.vidunas.simple_spring.rest.dto.UserDto;
import lt.vidunas.simple_spring.security.SimpleUserDetails;
import lt.vidunas.simple_spring.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

//    @Operation //swgger
    @GetMapping("/me")
    public UserDto getCurrentUsername(@AuthenticationPrincipal SimpleUserDetails currentUser) {
        return userMapper.toUserDto(userService.validateAndGetUser(currentUser.getUsername()));
    }

//    @Operation
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userMapper.toUserDto(userService.validateAndGetUser(username));
    }


    @DeleteMapping("/{username}")
    public UserDto deleteUser(@PathVariable String username) {
        User user = userService.validateAndGetUser(username);
        userService.deleteUser(user);
        return userMapper.toUserDto(user);
    }

}
