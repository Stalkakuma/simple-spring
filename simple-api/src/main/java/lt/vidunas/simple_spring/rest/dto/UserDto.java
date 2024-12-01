package lt.vidunas.simple_spring.rest.dto;

import lt.vidunas.simple_spring.entities.Post;

import java.util.List;

public record UserDto(Long id, String username, String email, String role, List<Post> posts) {

}
