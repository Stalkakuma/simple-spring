package lt.vidunas.simple_spring.mapper;


import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.rest.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
